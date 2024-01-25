<template>
  <Logo/>
  <ToggleButton/>
  <Menu :username="username" @optionChanged="updateSelectedAnim" :connectedUsers="connectedUsers"/>
  <div class="chat-container" ref="chatContainer">
    <div class="message-container" >
      <Message v-for="(msg, index) in messages" :key="msg.id"  :messageBody="msg" :animationClass="selectedAnim" :isEven="index % 2 === 0"/>
      <div id="scroll-anchor"></div>
    </div>
  </div>

  <InputBox @messageSent="addMessage" :username="username" />
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import Logo from './Logo.vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';
import ToggleButton from './ToggleButton.vue'
import Menu from './Menu.vue'
import swearFilter from "../js-modules/SwearFilter";
import notify from "../js-modules/notify";
import transformWord from "../js-modules/TransformWord";
import transformDate from "../js-modules/TransformDate";

const username = ref("");
const messages = ref([]);
const connectedUsers = ref([]);
const chatContainer = ref(null);
let isNotified = false;
let isFocused = true;
let messagesAmount = 0;
const unreadMessages = ref(0);
const savedSettings = JSON.parse(localStorage.getItem('animationSettings'));
const selectedAnim = ref(savedSettings?.selectedAnim || 'slide-in-anim');

let isCensored;
if (!localStorage.getItem('censor'))
  isCensored = "T"
else
  isCensored = JSON.parse(localStorage.getItem('censor'))
  

onMounted(() => {
  const ws = new WebSocket("wss://" + window.location.host + "/ws");
  const evtSource = new EventSource("/sse");

  ws.onmessage = (message) => {
    console.log('WSMessage', message.data);

    const { type, data} = JSON.parse(message.data);

    if(type === "connectedUsers") {
      connectedUsers.value = data;
    }
  };

  fetch("/username")
      .then(response => response.text())
      .then(response => username.value = response);

  fetch("/history")
    .then((response) => response.json())
    .then((json) => {
      const messagesJson = [...json.reverse()]
      for (let i in messagesJson){
        messagesJson[i].text = transformWord(messagesJson[i].text, i)
        if (isCensored == "T")
          messagesJson[i].text = swearFilter(messagesJson[i].text)
        messagesJson[i].dateParsed = transformDate(messagesJson[i].date)
        messages.value.push(messagesJson[i])
      }
    })
    .then(() => forceScrollToBottom());

  (async () => {
    if (Notification.permission === 'granted') {
        isNotified = true;
    } else if (Notification.permission !== 'denied') {
        let permission = await Notification.requestPermission();
        isNotified = permission === 'isNotified';
    }
  })();

  window.addEventListener("blur", async () => {
    isFocused = false;
  });

  window.addEventListener("focus", async () => {
    isFocused = true;
    unreadMessages.value = 0;
  });

  evtSource.onmessage = (event) => {
    console.log(event);

    if(event.data === "test") {
      return;
    }

    const message = JSON.parse(event.data);
    message.text = transformWord(message.text, messages.value.length)
    if (isCensored == "T")
      message.text = swearFilter(message.text)

    console.log('SSEMessage', message);

    messages.value.push(message);

    if (!isFocused) {
      unreadMessages.value += 1;
    }

    if (!isFocused && messages.value.length != messagesAmount) {
      messagesAmount = messages.value.length;
      if(isNotified && message.user != username.value)
        notify(message.user, message.text);
    }

    if(messages.value.length > 50) {
      messages.value.shift();
    }

    messages.value.map(msg => {
      msg.dateParsed = transformDate(msg.date)
    })
  };

  fetch("/username")
    .then(response => response.text())
    .then(response => username.value = response);

  forceScrollToBottom();
});
const updateSelectedAnim = (option) => {
  console.log('Saving animation option:', option);
  const settingsToSave = { selectedAnim: option };
  localStorage.setItem('animationSettings', JSON.stringify(settingsToSave));
  selectedAnim.value = option;
};

function forceScrollToBottom() {
  chatContainer.value.scrollTop = chatContainer.value.scrollHeight;
}

function addMessage(messageBody) {
  fetch("/send", {
    method: "POST",
    headers: {
      "Content-Type": "text/plain"
    },
    body: messageBody
  });
  
  forceScrollToBottom();
}

watch(unreadMessages, () => {
  document.title = (unreadMessages.value === 0 ? "" : `(${unreadMessages.value}) `) + "Kaiwa";
});

</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@use '../scss/test';

.chat-container {
  width: 60%;
  height: 60vh;
  margin: auto auto 10px;
  //border-bottom: $base-border-radius solid var(--primary);
  background-color: var(--chat);
  overflow-y: scroll;
  scrollbar-color: auto;
  animation: $fadeTransition;

  .message-container {
    height: 100%;
    display: flex;
    flex-direction: column;
    // justify-content: flex-end;
    > :first-child {
      margin-top: auto !important;
    }

    * {
      overflow-anchor: none;
    }

    #scroll-anchor {
      overflow-anchor: auto;
      height: 1px;
      flex-shrink: 0;
    }
  }
}

*::-webkit-scrollbar {
  width: 10px;
}

*::-webkit-scrollbar-track {
  background: var(--chat);
}

*::-webkit-scrollbar-thumb {
  background-color: var(--chat-message);
  border-radius: 100px;
}
* {
  scrollbar-width: auto;
  scrollbar-color: blue orange;
}
@media (max-width: $mobile-width){
  .chat-container {
    width: 90%;
    height: 72vh;
  }
}

</style>
