<template>
  <h2>Create an account</h2>
  <form class="signup-form" @submit.prevent="signUp">
    <input type="text" name="name" v-model.trim="username" class="username-input" placeholder="Username">
    <input type="password" name="password" v-model="password" class="password-input" @input="updatePasswordRequirements"
    placeholder="Password">
    <div class="password-requirements">
      <p>Password must include:</p>
      <ul>
        <li :class="{ fulfilled: passwordLongEnough }">At least 8 characters</li>
        <li :class="{ fulfilled: passwordHasCapitalLetter }">One capital letter</li>
        <li :class="{ fulfilled: passwordHasDigit }">One digit</li>
        <li :class="{ fulfilled: passwordHasSpecialChar }">One special character (!@$%& etc.)</li>
      </ul>
    </div>
    <input type="password" v-model="passwordConfirm" class="password-input-confirm"
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

const passwordLongEnough = ref(false);
const passwordHasCapitalLetter = ref(false);
const passwordHasDigit = ref(false);
const passwordHasSpecialChar = ref(false);

function updatePasswordRequirements(e) {
  const password = e.target.value;

  passwordLongEnough.value = (password.length >= 8);
  passwordHasCapitalLetter.value = /[A-Z]/.test(password);
  passwordHasDigit.value = /\d/.test(password);
  passwordHasSpecialChar.value = /[^a-zA-Z\d]/.test(password);
}

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
  if (!(passwordLongEnough.value
    && passwordHasCapitalLetter.value
    && passwordHasDigit.value
    && passwordHasSpecialChar.value)) {
    errorMessage.value = "Password does not meet the requirements; please change the password.";
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
  grid-template-rows: repeat(5, 5em);
  row-gap: 10px;
  justify-content: space-around;
  grid-template-columns: repeat(1, 100%);
  input {
    @include inputstuff;
  }
  
  .password-requirements {
    p {
      text-align: left;
      margin: 0;
    }
    ul {
      margin: 0.2em;
    }
    font-size: 0.7em;

    .fulfilled {
      color: green;
    }
  }
}

#error-message {
  color: red;
}
@media (max-width: $mobile-width){
  .signup-form {
    width: 50%;
    .password-requirements{
      font-size: $font-size-mobile-smaller;
    }
  }
}
</style>
