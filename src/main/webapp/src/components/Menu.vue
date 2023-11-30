<template>
  <div id="sidebar" ref="sidebarRef">
    <div class="toggle-btn" @click="show">
      <span></span>
      <span></span>
      <span></span>
    </div>
    <ul>
      <li>Chat</li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
  </div>

</template>

<script setup>

import { ref } from 'vue';

const sidebarRef = ref(null);

const show = () => {
  sidebarRef.value.classList.toggle('active');
};
</script>

<style lang="scss" setup>
@use "../scss/abstracts/index" as *;

#sidebar {
  position: absolute;
  top: 0;
  width: 300px;
  height: 100%;
  background: var(--chat);
  left: -300px;
  transition: .4s;
  animation: $fadeTransition;
  ul li {
    list-style: none;
    color: var(--font-color);
    font-size: 20px;
    padding: 26px 24px;
  }
  .toggle-btn {
    position: absolute;
    top: 30px;
    left: 330px;
  }
}

.toggle-btn span {
  width: 45px;
  height: 4px;
  background: #000;
  display: block;
  margin-top: 4px;
}

#sidebar.active {
  left: 0;
}
</style>