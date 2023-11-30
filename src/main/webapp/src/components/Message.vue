<template>
  <div class="chat-message">
    <div id="message-user">
      <b>{{ props.messageBody.user }}</b>
    </div>
    <div id="message-message">{{ props.messageBody.text }}</div>
    <div id="message-timestamp">{{ props.messageBody.date }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
    messageBody: {
        user: String,
        text: String,
        date: String
    }
});
</script>

<style lang="scss">
@use '../scss/test';
@use "../scss/abstracts/index" as s;

.chat-message {
  animation: s.$fadeTransition;
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