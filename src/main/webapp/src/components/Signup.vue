<template>
    <Logo />
    <form class="signup-form" @submit.prevent="signUp">
      <h2>Create an account</h2>
      <input type="text" v-model="username" class="username-input" required placeholder="Username">
      <input type="password" v-model="password" class="password-input" required placeholder="Password">
      <input type="submit" value="Sign Up" class="signup-button">
    </form>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import Logo from './Logo.vue';
  
  const username = ref("");
  const password = ref("");
  
  function signUp() {
      if (!username.value.trim()) {
        alert("No username provided");
        return;
      }
      if (!password.value) {
        alert("No password provided");
        return;
      }
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
  
  .signup-form {
      width: 33%;
      margin: 0 auto;
      display: grid;
      grid-template-rows: repeat(3, 5em);
      row-gap: 10px;
      animation: $fadeTransition;
  }
  
  .username-input, .password-input, .signup-button {
      @include inputstuff;
      animation: $fadeTransition;
  }
  </style>
  