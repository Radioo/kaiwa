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
let granted = false;

onMounted(() => {
  const evtSource = new EventSource(
      "http://localhost:8080/sse"
  );

  evtSource.onmessage = (event) => {
    const message = JSON.parse(event.data);

    console.log(message);

    messages.value.push(message);
  }

  fetch("/username")
      .then(response => response.text())
      .then(response => username.value = response);

  (async () => {
    if (Notification.permission === 'granted') {
        granted = true;
    } else if (Notification.permission !== 'denied') {
        let permission = await Notification.requestPermission();
        granted = permission === 'granted' ? true : false;
    }
  })();
})

function addMessage(messageBody) {
    fetch("/send", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(messageBody)
    });

    if(granted) notify(messageBody.user, messageBody.message);
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
