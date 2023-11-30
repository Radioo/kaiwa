<script setup>
import { ref } from 'vue';
import filerWord from "../js-modules/SwearFilter";
import swearFilter from "../js-modules/SwearFilter";

const emit = defineEmits(["messageSent"])

const props = defineProps({
  username: String
})

const messageContent = ref("");

function sendMessage() {
    messageContent.value = messageContent.value.trim();

    if (!messageContent.value) { // if message input box is empty, do nothing
        return;
    }

    emit("messageSent", swearFilter(messageContent.value));

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
@use '../scss/abstracts/index' as s;

.input-container{
  width: 60%;
  margin: auto;
  animation: s.$fadeTransition;

  .form-container{
    display: flex;
    flex-direction: row;
    height: 5rem;
    gap: 10px;

    .user-input, .send-button{
      width: 15%;
      font-size: s.$font-size-base;
      margin: s.$base-margin 0 s.$base-margin;
      padding: s.$base-padding;
      border-radius: s.$base-border-radius;
      border-width: s.$base-border-thickness;
      background-color: s.$chat;
    }
    .message-input{
      flex: 1.5;
      font-size: s.$font-size-base;
      margin: s.$base-margin 0 s.$base-margin;
      padding: s.$base-padding;
      border-radius: s.$base-border-radius;
      border-width: s.$base-border-thickness;
      background-color: s.$chat;
    }
  }
}

</style>
