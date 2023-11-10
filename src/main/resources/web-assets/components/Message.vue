<template>
  <div class="chat-message">
    <div id="message-user">
      <b>{{ props.messageBody.user }}</b>
    </div>
    <div id="message-message">{{ props.messageBody.message }}</div>
    <div id="message-timestamp">{{ props.messageBody.timestamp }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
    messageBody: {
        user: String,
        message: String,
        timestamp: String
    }
});
</script>

<style lang="scss">
@use '../scss/test';

.chat-message {
  display: flex;
  justify-content: space-between;
  #message-user{
      width: 19%;
  }
  #message-timestamp{
    width: 14%;
  }
  #message-message{
      flex: 1.5;
  }
}
</style>