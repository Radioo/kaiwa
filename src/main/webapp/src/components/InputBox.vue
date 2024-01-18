<script setup>
import { ref } from 'vue';

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
      padding: 0;
    }
    .message-input{
      @include inputstuff;
      flex: 1.5;
      padding: 0;
      padding-left: 10px;
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
      .user-input, .send-button{
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
      .user-input, .send-button{
        font-size: $font-size-mobile-smaller;
      }
      .message-input{
        font-size: $font-size-mobile-smaller;
      }
    }
  }
}

</style>
