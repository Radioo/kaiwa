<template>
    <Logo />
    <form class="login-form" @submit.prevent="logIn">
        <input v-model="username" type="text" class="username-input" required placeholder="Username">
        <input v-model="password" type="password" class="password-input" required placeholder="Password">
        <button class="login-button">Login</button>
    </form>
</template>

<script setup>
import { ref } from 'vue';
import Logo from './Logo.vue';

const username = ref("");
const password = ref("");

function logIn() {
    console.log({ Username: username.value, Password: password.value });
}
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@mixin inputstuff{
  font-size: $font-size-base;
  margin: $base-margin 0 $base-margin;
  padding: $base-padding;
  border-radius: $base-border-radius;
  border-width: $base-border-thickness;
  background-color: var(--chat);
}

.login-form {
    width: 33%;
    margin: 0 auto;
    display: grid;
    grid-template-rows: repeat(3, 5em);
    row-gap: 10px;
    animation: $fadeTransition;
}

.username-input, .password-input, .login-button {
    @include inputstuff;
    animation: $fadeTransition;
}
</style>
