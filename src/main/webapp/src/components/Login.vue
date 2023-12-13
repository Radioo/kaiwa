<template>
  <Logo />
  <div v-if="register">
    <Signup/>
    <p>
      Already have an account? <a href="register" @click.prevent="register = false">Sign in</a>
    </p>
  </div>
  <div v-else>
    <LoginForm/>
    <p>
      Need an account? <a href="register" @click.prevent="register = true">Sign up here</a>
    </p>
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

p {
  text-align: center;
}
</style>
