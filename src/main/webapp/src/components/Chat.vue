<template>
  <UserHeader :username="username"/>
  <Logo/>
  <div class="chat-container">
      <div class="message-container">
          <Message v-for="msg in messages" :messageBody="msg" />
      </div>
  </div>

  <InputBox @messageSent="addMessage" :username="username"/>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import Logo from './Logo.vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';
import UserHeader from "./UserHeader.vue";
import notify from "../js-modules/notify";

const username = ref("");
const messages = ref([]);
let isNotified = false;
let isFocused = true;
let messagesAmount = 0;

onMounted(() => {
  const evtSource = new EventSource(
      "http://localhost:8080/sse"
  );

  fetch("/username")
      .then(response => response.text())
      .then(response => username.value = response);

  (async () => {
    if (Notification.permission === 'granted') {
        isNotified = true;
    } else if (Notification.permission !== 'denied') {
        let permission = await Notification.requestPermission();
        isNotified = permission === 'isNotified' ? true : false;
    }
  })();

  window.addEventListener("focus", () => {isFocused = true;});
  window.addEventListener("blur", () => {isFocused = false;});

  evtSource.onmessage = (event) => {
    const message = JSON.parse(event.data);

    console.log(message);

    messages.value.push(message);

    if (!isFocused && messages.value.length != messagesAmount) {
        messagesAmount = messages.value.length;
        if(isNotified && message.user != username.value) 
          notify(message.user, message.message);
    }
  }
})

function addMessage(messageBody) {
    fetch("/send", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(messageBody)
    });
}
</script>

<style lang="scss">
@use "../scss/abstracts/index" as s;

    .chat-container {
        width: 60%;
        height: 60vh;
        margin: auto;
        margin-bottom: 20px;
        border-bottom: s.$base-border-radius solid s.$primary;
        background-color: s.$chat;
        overflow-y: scroll;
        animation: s.$fadeTransition;
        .message-container{
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: flex-end;
        }
    }

</style>
