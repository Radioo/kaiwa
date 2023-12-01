<script setup>
import { ref } from 'vue';
import filterWord from "../js-modules/SwearFilter";
import swearFilter from "../js-modules/SwearFilter";

const emit = defineEmits(["messageSent"])

const username = ref("");
const messageContent = ref("");

function sendMessage() {
    messageContent.value = messageContent.value.trim();

    if (!messageContent.value) { // if message input box is empty, do nothing
        return;
    }

    const messageBody = {
        user: username.value,
        message: swearFilter(messageContent.value),
        timestamp: new Date().toLocaleString()
    }

    emit("messageSent", messageBody);

    messageContent.value = "";
}
</script>

<template>
    <div class="input-container">
        <form class="form-container" @submit.prevent="sendMessage">
            <input class="message-input" v-model="messageContent" placeholder="Message">
            <button class="send-button">Send</button>
        </form>
    </div>
</template>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@mixin inputstuff{
  font-size: $font-size-base;
  margin: $base-margin 0 $base-margin;
  padding: $base-padding;
  border-radius: $base-border-radius;
  border-width: $base-border-thickness;
  background-color: var(--chat);
  color: var(--font-color);
}

.input-container{
  width: 60%;
  margin: auto;
  animation: $fadeTransition;

  .form-container{
    display: flex;
    flex-direction: row;
    height: 5rem;
    gap: 10px;

    .user-input, .send-button{
      @include inputstuff;
      width: 15%;
    }
    .message-input{
      @include inputstuff;
      flex: 1.5;
    }
  }
}

</style>
