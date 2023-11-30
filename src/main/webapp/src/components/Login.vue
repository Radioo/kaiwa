<template>
    <Logo />
    <form class="login-form" method="post">
        <input type="text" name="username" class="username-input" required placeholder="Username">
        <input type="password" name="password" class="password-input" required placeholder="Password">
        <input type="submit" value="Sign In" class="login-button">
    </form>
</template>

<script setup>

import Logo from "./Logo.vue";
</script>

<style lang="scss">
@use '../scss/abstracts/index' as s;

.login-form {
    width: 33%;
    margin: 0 auto;
    display: grid;
    grid-template-rows: repeat(3, 5em);
    row-gap: 10px;
    animation: s.$fadeTransition;
}

.username-input, .password-input, .login-button {
    font-size: s.$font-size-base;
    margin: s.$base-margin 0 s.$base-margin;
    padding: s.$base-padding;
    border-radius: s.$base-border-radius;
    border-width: s.$base-border-thickness;
    background-color: s.$chat;
  animation: s.$fadeTransition;
}
</style>
