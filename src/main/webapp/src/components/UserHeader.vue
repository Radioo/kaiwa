<template>
  <div id="user-header">
    <span>Hello {{ username }}</span>
    <a href="/logout">Log out</a>
  </div>
</template>

<script setup>

defineProps({
  username: String
})
</script>