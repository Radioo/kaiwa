<template>
  <UserHeader :username="username" />
  <Logo />
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
});

onMounted(() => {
  const evtSource = new EventSource("/sse");

  evtSource.onmessage = (event) => {
    const message = JSON.parse(event.data);

    console.log(message);

    messages.value.push(message);

    if (!isFocused) {
      unreadMessages.value += 1;
    }
  };

  fetch("/username")
    .then(response => response.text())
    .then(response => username.value = response);
});

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

  .message-container {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
  }
}
</style>
