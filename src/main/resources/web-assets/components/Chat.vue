<template>
  <div>
    <Message v-for="msg in messages" :messageBody="msg" />
  </div>

  <InputBox @messageSent="addMessage" />
</template>

<script setup>
import { ref } from 'vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';

const messages = ref([]);

function addMessage(messageBody) {
    messages.value.push({
        username: messageBody.username,
        content: messageBody.content,
    });
}
</script>

<style lang="scss">
@import "../scss/test";

</style>