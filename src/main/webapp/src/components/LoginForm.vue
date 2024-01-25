<template>
  <form class="login-form" method="post" @submit.prevent="logIn">
    <input type="text" v-model.trim="username" name="username" class="username-input" required placeholder="Username">
    <input type="password" v-model="password" name="password" class="password-input" required placeholder="Password">
    <input type="submit" value="Sign In" class="login-button">
  </form>
  <p id="error-message">{{ errorMessage }}</p>
</template>

<script setup>
import { ref } from 'vue';

const username = ref("");
const password = ref("");
const errorMessage = ref("");

function logIn() {
  const loginInfo = new URLSearchParams({ username: username.value, password: password.value });

  fetch("/", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: loginInfo,
  })
    .then((response) => {
      if (response.url.includes("error")) {
        errorMessage.value = "Incorrect username and/or password. Please try again."
      }
      else if (response.url.includes("chat")) {
        window.location.replace("/chat");
      }
    })
    .catch((error) => {
      errorMessage.value = "Something went wrong. Please try again later.";
      console.error(error);
    });
}
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@use '../scss/test';

.login-button {
  cursor: pointer;
}

.login-form {
    width: 25%;
    margin: 0 auto;
    display: grid;
    grid-template-rows: repeat(3, 5em);
    row-gap: 10px;
    justify-content: space-around;
    grid-template-columns: repeat(1, 100%);
    font-size: 15px;
    input {
      @include inputstuff;
    }
}
@media (max-width: $mobile-width){
  .login-form{
    width: 50%;
    font-size: $font-size-mobile;
  }
}
#error-message {
  color: red;
}
</style>
