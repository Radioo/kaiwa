<script setup>
import { ref } from 'vue';
import EmojiPicker from './EmojiPicker.vue';

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

    emit("messageSent", messageContent.value);

    messageContent.value = "";
}

const popupOpen = ref(false);

function msgInputEnterHandler(e) {
  if (e.code !== "Enter") {
    return;
  }
  sendMessage();
}

function appendEmoji(emoji) {
  messageContent.value += emoji;
}
</script>

<template>
  <div class="input-container">
    <div class="form-container">
        <input class="message-input" v-model="messageContent" placeholder="Message" @keydown="msgInputEnterHandler" />
        <EmojiPicker @emojiPicked="appendEmoji"/>
        <button class="btn" @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<style lang="scss">
@use "../scss/abstracts/index" as *;

.btn {
  cursor: pointer;
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
    .user-input, .btn{
      @include inputstuff;
      width: 15%;
      padding: 0;
      &:hover{
        background-color: var(--chat-message);
      }
    }
    .message-input{
      @include inputstuff;
      flex: 1.5;
      padding: 0;
      padding-left: 10px;
      border: 2px;
      &:hover{
        background-color: var(--chat-message);
      }
    }
  }
}

@media (max-width: $mobile-width){
  .input-container{
    width: 90%;
    position: absolute;
    bottom: 2%;
    left: 5%;
    height: 10vh;
    .form-container{
      height: 100%;
      .user-input, .btn{
        font-size: $font-size-mobile;
        height: 63%;
      }
      .message-input{
        font-size: $font-size-mobile;
        height: 60%;
      }
    }
  }
}
@media (max-width: $mobile-width-smaller){
  .input-container{
    .form-container{
      .user-input, .btn{
        font-size: $font-size-mobile-smaller;
      }
      .message-input{
        font-size: $font-size-mobile-smaller;
      }
    }
  }
}

</style>
