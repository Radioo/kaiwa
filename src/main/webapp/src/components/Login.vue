<template>
  <Logo />
  <div class="login" v-if="register">
    <Signup/>
    <div class="account-prompt">
      <span>Already have an account?</span>
      <button id="register-button" @click="register = false">Sign in</button>
    </div>
  </div>
  <div class="register" v-else>
    <LoginForm/>
    <div class="account-prompt">
      <span>Need an account?</span>
      <button id="register-button" @click="register = true">Sign up here</button>
    </div>
  </div>
</template>


<script setup>
import { ref } from 'vue';
import Logo from './Logo.vue';
import LoginForm from './LoginForm.vue';
import Signup from './Signup.vue';

const register = ref(false);
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;
@use '../scss/test';

p, .account-prompt {
  text-align: center;
}

.login, .register {
  animation: $fadeTransition;
}

.account-prompt {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;

  span {
    position: relative;
    &:before, &:after {
      content: "";
      position: absolute;
      top: 50%;
      width: 100px;
      height: 1px;
      background-color: white;
    }
    &:before {
      right: 100%;
      margin-right: 10px;
    }
    &:after {
      left: 100%;
      margin-left: 10px;
    }
  }

  a {
    margin-top: 10px; // adjust as needed
  }
}
#register-button{
  @include inputstuff;
  width: 15%;
  height: 10%;
  &:hover{
    background-color: var(--chat-message);
  }
}

@media (max-width: $mobile-width){
  #register-button{
    width: 25%;
    font-size: $font-size-mobile;
  }
  .account-prompt {
    span {
      &:before, &:after {
        width: 50px;
      }

    }
  }
}
</style>

