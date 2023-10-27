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
        username: username.value,
        content: swearFilter(messageContent.value),
    }

    emit("messageSent", messageBody);

    messageContent.value = "";
}
</script>

<template>
    <div>
        <form @submit.prevent="sendMessage">
            <input v-model="username" placeholder="Użytkownik">
            <input v-model="messageContent" placeholder="Napisz wiadomość...">
            <button>Wyślij</button>
        </form>
    </div>
</template>
