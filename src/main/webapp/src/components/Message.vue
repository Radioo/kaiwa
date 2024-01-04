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
  font-size: $font-size-base;
  #message-user{
      margin-left: 10px;
      width: 16%;
  }
  #message-timestamp{
    width: 16%;
    margin-left: 5px;
  }
  #message-message{
      width: 64%;
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

@media (max-width: $mobile-width){
  .chat-message {
    margin: 0 8px 8px 8px;
    font-size: $font-size-mobile;
    padding: 6px;
    #message-timestamp{
      font-size: 0.6rem;
    }
    #message-user{
      margin-left: 2px;
    }
  }
}

@media (max-width: $mobile-width-smaller){
  .chat-message {
    font-size: $font-size-mobile-smaller;
    #message-timestamp{
      font-size: 0.5rem;
    }
  }
}

@media (max-width: $mobile-width-smallest){
  .chat-message {
    font-size: $font-size-mobile-smallest;
    #message-timestamp{
      font-size: 0.42rem;
    }
    #message-message{
      width: 58%;
    }
  }
}
</style>