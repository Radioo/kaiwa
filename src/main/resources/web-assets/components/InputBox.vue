<script setup>
import { ref } from 'vue';
import filerWord from "../../js-modules/SwearFilter";
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
        user: username.value,
        message: swearFilter(messageContent.value),
        timestamp: new Date().toLocaleString()
    }

    emit("messageSent", messageBody);

    messageContent.value = "";
}
</script>

<template>
    <div>
        <form @submit.prevent="sendMessage">
            <input v-model="username" placeholder="User Name">
            <input v-model="messageContent" placeholder="Message">
            <button>Send</button>
        </form>
    </div>
</template>
