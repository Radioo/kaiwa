<template>
    <div>
        <p><b>{{ props.messageBody.username }}</b></p>
        <p>{{ props.messageBody.content }}</p>
    </div>
</template>

<script setup>
const props = defineProps({
    messageBody: {
        username: String,
        content: String,
    }
});
</script>
