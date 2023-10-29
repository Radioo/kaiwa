<template>
    <div class="scrollable">
        <div class="chat-history">
            <Message v-for="msg in messages" :messageBody="msg" />
        </div>
    </div>

    <InputBox @messageSent="addMessage" />
</template>

<script setup>
import { ref, onUpdated } from 'vue';
import Message from './Message.vue';
import InputBox from './InputBox.vue';

const messages = ref([]);

const addMessage = (messageBody) => {
    messages.value.push({
        username: messageBody.username,
        content: messageBody.content,
    });
};

onUpdated(() => {
    // scroll to bottom of chat history when new message appears
    const scrollableEl = document.querySelector(".scrollable");
    scrollableEl.scrollTop = scrollableEl.scrollHeight;
});
</script>

<style lang="scss">
.scrollable {
    overflow-y: auto;

    .chat-history {
        min-height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
    }
}
</style>
