<template>
  <div class="chat-message" :class="[props.animationClass, { 'even': isEven, 'odd': !isEven }]">
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
    isEven: Boolean
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
      width: 12%;
  }
  #message-timestamp{
    width: 12%;
    margin-left: 5px;
  }
  #message-message{
      width: 70%;
      overflow-wrap: break-word;
  }
}
.chat-message.even {
  background-color: var(--chat-message);
}
.chat-message.odd {
  background-color: var(--chat-message-darker);
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
@media (max-width: $smaller-width){
  .chat-message {
    margin: 0 14px 14px 14px;
    //font-size: $font-size-mobile;
    padding: 10px;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: auto auto;
    #message-user {
      grid-column: 1 / 2;
      grid-row: 1;
      margin-left: 5px;
    }
    #message-timestamp {
      grid-column: 2 / 3;
      grid-row: 1;
      text-align: right;
      width: 92%;
      font-size: 0.8rem;
      margin-right: 5px;
    }
    #message-message {
      grid-column: 1 / 3;
      grid-row: 2;
      margin: 8px 0 4px 5px;
      width: 97%;
    }
  }
}

@media (max-width: $mobile-width){
  .chat-message {
    margin: 0 14px 14px 14px;
    font-size: $font-size-mobile;
    padding: 10px;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: auto auto;
    #message-user {
      grid-column: 1 / 2;
      grid-row: 1;
      margin-left: 5px;
    }
    #message-timestamp {
      grid-column: 2 / 3;
      grid-row: 1;
      text-align: right;
      width: 92%;
      font-size: 0.6rem;
      margin-right: 5px;
    }
    #message-message {
      grid-column: 1 / 3;
      grid-row: 2;
      margin: 8px 0 4px 5px;
      width: 97%;
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
  }
}

</style>