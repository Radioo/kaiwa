<template>
  <div>
    <Message v-for="msg in messages" :messageBody="msg" />
  </div>

  <InputBox @messageSent="addMessage" />
</template>

<script setup>
import {onMounted, ref} from 'vue';
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
@import "../scss/test";

</style>