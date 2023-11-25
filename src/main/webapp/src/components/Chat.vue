<template>
  <Logo/>
  <div class="chat-container">
      <div class="message-container">
          <Message v-for="msg in messages" :messageBody="msg" />
      </div>
  </div>

  <InputBox @messageSent="addMessage" />
</template>

<script setup>
import {onMounted, ref} from 'vue';
import Logo from './Logo.vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';

const messages = ref([]);

onMounted(() => {
  const evtSource = new EventSource("http://localhost:8080/sse");

  evtSource.onmessage = (event) => {
    const message = JSON.parse(event.data);

    console.log(message);

    messages.value.push(message);
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