<template>
    <div class="input-box">
        <form @submit.prevent="sendMessage">
            <input v-model="username" placeholder="Username">
            <input v-model="messageContent" placeholder="Write message..." class="msg-content-input">
            <button>Wyślij</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import swearFilter from "../../js-modules/SwearFilter";

const emit = defineEmits(["messageSent"])

const username = ref("");
const messageContent = ref("");

function sendMessage() {
    messageContent.value = messageContent.value.trim();

    if (!messageContent.value) { // if message input box is empty, do nothing
        return;
    }

    const messageBody = {
        username: username.value,
        content: swearFilter(messageContent.value),
    }

    emit("messageSent", messageBody);

    messageContent.value = "";
}
</script>

<style lang="scss">
.input-box form {
    display: flex;
    width: 100%;

    .msg-content-input {
        flex-grow: 1;
    }
}
</style>
