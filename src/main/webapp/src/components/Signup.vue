<template>
  <h2>Create an account</h2>
  <form class="signup-form" @submit.prevent="signUp">
    <input type="text" name="name" v-model.trim="username" class="username-input" required placeholder="Username">
    <input type="password" name="password" v-model="password" class="password-input" required placeholder="Password">
    <input type="password" v-model="passwordConfirm" class="password-input-confirm" required
      placeholder="Confirm password">
    <input type="submit" value="Sign Up" class="signup-button">
  </form>
  <p id="error-message">{{ errorMessage }}</p>
</template>

<script setup>
import { ref } from 'vue';

const username = ref("");
const password = ref("");
const passwordConfirm = ref("");
const errorMessage = ref("");

function signUp(e) {
  if (!username.value) {
    errorMessage.value = "Username is required.";
    return;
  }
  if (!password.value) {
    errorMessage.value = "Password is required.";
    return;
  }
  if (password.value !== passwordConfirm.value) {
    errorMessage.value = "Password input fields do not match.";
    return;
  }

  const registerInfo = {
    name: username.value,
    password: password.value,
  }

  fetch("/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(registerInfo),
  }).then((response) => {
    if (response.status === 400) {
      errorMessage.value = `Username ${username.value} is already taken. Please choose a different username.`;
      return;
    }

    // automatically log in to the new account
    const loginInfo = new URLSearchParams({ username: username.value, password: password.value });
    fetch("/", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: loginInfo,
    }).then((response) => {
      window.location.replace("/chat");
    })
  }).catch((error) => {
      errorMessage.value = "Something went wrong. Please try again later.";
      console.error(error);
    });
}
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@use '../scss/test';

@mixin inputstuff {
  font-size: $font-size-base;
  margin: $base-margin 0 $base-margin;
  padding: $base-padding;
  border-radius: $base-border-radius;
  border-width: $base-border-thickness;
  background-color: var(--chat);
  color: var(--font-color);
}

h2 {
  text-align: center;
}

.signup-form {
  width: 33%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: repeat(4, 5em);
  row-gap: 10px;
  animation: $fadeTransition;

  input {
    @include inputstuff;
    animation: $fadeTransition;
  }
}

#error-message {
  color: red;
}
</style>
