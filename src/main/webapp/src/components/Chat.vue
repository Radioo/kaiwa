<template>
  <Logo/>
  <ToggleButton/>
  <Menu/>
  <div class="chat-container">
    <div class="message-container">
      <Message v-for="msg in messages" :messageBody="msg" />
    </div>
  </div>

  <InputBox @messageSent="addMessage" :username="username" />
</template>

<script setup>
import { onMounted, ref } from 'vue';
import Logo from './Logo.vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';
import ToggleButton from './ToggleButton.vue'
import Menu from './Menu.vue'
import UserHeader from "./UserHeader.vue";

const username = ref("");
const messages = ref([]);

const unreadMessages = ref(0);
document.title = (unreadMessages.value === 0 ? "" : `(${unreadMessages.value}) `) + "Kaiwa";

let isFocused = true;

window.addEventListener("blur", async () => {
  isFocused = false;
});

window.addEventListener("focus", async () => {
  isFocused = true;
  unreadMessages.value = 0;
  updateDocumentTitle();
});

onMounted(() => {
  const evtSource = new EventSource("/sse");

  evtSource.onmessage = (event) => {
    console.log(event);

    const message = JSON.parse(event.data);

    console.log(message);

    messages.value.push(message);

    if (!isFocused) {
      unreadMessages.value += 1;
      updateDocumentTitle();
    }
  };

  fetch("/username")
    .then(response => response.text())
    .then(response => username.value = response);
});

function updateDocumentTitle () {
  document.title = (unreadMessages.value === 0 ? "" : `(${unreadMessages.value}) `) + "Kaiwa";
}

function addMessage(messageBody) {
  fetch("/send", {
    method: "POST",
    headers: {
      "Content-Type": "text/plain"
    },
    body: messageBody
  });
}

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
        .message-container{
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: flex-end;
        }
    }

</style>
