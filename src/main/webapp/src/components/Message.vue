<template>
  <div class="chat-message" :class="props.animationClass">
    <div id="message-user" :class="props.animationClass">
      <b>{{ props.messageBody.user }}</b>
    </div>
    <div id="message-message" :class="props.animationClass">{{ props.messageBody.text }}</div>
    <div id="message-timestamp" :class="props.animationClass">{{ props.messageBody.date }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
    messageBody: {
        user: String,
        text: String,
        date: String
    },
    animationClass: String,
});
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;

.chat-message {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: var(--chat-message);
  margin: 0 30px 20px 30px;
  padding: 10px;
  border-radius: $base-border-radius;
  #message-user{
      margin-left: 10px;
      width: 14%;
  }
  #message-timestamp{
    width: 14%;
  }
  #message-message{
      width: 66%;
      overflow-wrap: break-word;
  }
}

.fade-in-anim{
  animation: $fadeTransition;
}

.slide-in-anim{
  animation: slide-in-anim 0.75s ease-out forwards;
  opacity: 0;
}
@keyframes slide-in-anim {
  0% {
    opacity: 0;
    transform: translateX(-100%);
  }
  100% {
    opacity: 1;
  }
}

.reveal-in-anim{
  animation: reveal 0.75s;
}
@keyframes reveal {
  0% {
    opacity: 0;
    transform: translate(0,100%);
  }
  100% {
    opacity: 1;
    transform: translate(0,0);
  }
}

</style>