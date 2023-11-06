<template>
  <div class="chat-message">
    <div>
      <b>{{ props.messageBody.user }}</b>
    </div>
    <div>{{ props.messageBody.message }}</div>
    <div>{{ props.messageBody.timestamp }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
    messageBody: {
        user: String,
        message: String,
        timestamp: String
    }
});
</script>

<style lang="scss">
.chat-message {
  display: flex;
  justify-content: space-between;
}
</style>