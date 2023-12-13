<template>
  <form class="login-form" method="post">
    <input type="text" name="username" class="username-input" required placeholder="Username">
    <input type="password" name="password" class="password-input" required placeholder="Password">
    <input type="submit" value="Sign In" class="login-button">
  </form>
</template>

<script setup>
import { ref } from 'vue';

const username = ref("");
const password = ref("");

function logIn() {
    console.log({ Username: username.value, Password: password.value });
}
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@use '../scss/test';

@mixin inputstuff{
  font-size: $font-size-base;
  margin: $base-margin 0 $base-margin;
  padding: $base-padding;
  border-radius: $base-border-radius;
  border-width: $base-border-thickness;
  background-color: var(--chat);
  color: var(--font-color);
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
