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
//@use "../scss/test;
@use "../scss/abstracts/index" as *;

.chat-message {
  animation: $fadeTransition;
  display: flex;
  justify-content: space-between;
  margin: 10px 0 0 10px;
  #message-user{
      margin-left: 10px;
      width: 18%;
  }
  #message-timestamp{
    width: 14%;
  }
  #message-message{
      width: 68%;
      overflow-wrap: break-word;
  }
}
</style>