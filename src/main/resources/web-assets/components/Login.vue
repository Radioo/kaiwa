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
@use '../scss/abstracts' as s;

.login-form {
    width: 33%;
    margin: 0 auto;
    display: grid;
    grid-template-rows: repeat(3, 5em);
    row-gap: 10px;
}

.username-input, .password-input, .login-button {
    font-size: s.$font-size-base;
    margin: s.$base-margin 0 s.$base-margin;
    padding: s.$base-padding;
    border-radius: s.$base-border-radius;
    border-width: s.$base-border-thickness;
    background-color: s.$chat;
}
</style>
