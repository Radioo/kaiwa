<template>
  <div id="sidebar" ref="sidebarRef">
    <div class="toggle-btn" @click="show">
      <span></span>
      <span></span>
      <span></span>
    </div>
    <span id="user-header">Hello {{ username }}</span>
    <ul class="main-menu">
      <li><button>Temp1</button></li>
      <li><button>Temp1</button></li>
      <li><button>Temp1</button></li>
      <li><button>Temp1</button></li>
      <li><button @click="openPopup()">Settings</button></li>
    </ul>
    <div class="" id="logout">
      <button onclick="location.href='/logout'">Log out</button>
    </div>
  </div>
  <div id="popup">
    <img src="../../static/images/Settings.svg" alt="hello" id="main">
    <img src="../../static/images/Close.svg" alt="hello" id="close" @click="closePopup">
    <h2>Settings</h2>
  </div>

</template>

<script setup>

import { ref } from 'vue';

const sidebarRef = ref(null);

const show = () => {
  sidebarRef.value.classList.toggle('active');
};

defineProps({
  username: String
})

const openPopup = () => {
  const popup = document.getElementById('popup');
  popup.classList.add("open-popup");
};

const closePopup = () => {
  const popup = document.getElementById('popup');
  popup.classList.remove("open-popup");
};
</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;

#sidebar {
  position: absolute;
  top: 0;
  width: 14%;
  height: 100%;
  background: var(--chat);
  left: -14%;
  animation: $fadeTransition;
  transition: .5s;
  #user-header{
    display: block;
    padding: 20px 0 20px 20px;
    font-size: $font-size-large;
  }
  ul {
    width: 100%;
    margin-top: 25px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 2rem;
    list-style: none;
    color: var(--font-color);
    font-size: $font-size-large;
    padding: 0;
    li ,button {
      height: 5vh;
      width: 100%;
      background-color: var(--primary);
      border-radius: $base-border-radius;
      border-width: $base-border-thickness;
      display: flex;
      align-items: center;
      justify-content: center;
      &:hover{
        background-color: var(--button-hover);
      }
    }
  }
  .toggle-btn {
    position: absolute;
    top: 1em;
    left: 18em;
  }
  #logout {
    position: absolute;
    bottom: 2%;
    width: 100%;
    button {
      height: 5vh;
      width: 100%;
      background-color: #a62f2f;
      border-radius: $base-border-radius;
      border-width: $base-border-thickness;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}

.toggle-btn span {
  width: 40px;
  height: 5px;
  background: #000;
  display: block;
  margin-top: 4px;
}

#sidebar.active {
  left: 0;
}

#popup{
  width: 25%;
  height: 35%;
  background: var(--primary);
  border-radius: 6px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) scale(0.1);
  text-align: center;
  padding: 0 30px 30px;
  color: var(--font-color);
  box-shadow: 0 0 14px var(--secondary-grad);
  visibility: hidden;
  transition: transform 0.4s, top 0.4s;
  #main{
    width: 24%;
    margin-top: -12%;
    border-radius: 50%;
    box-shadow: 0 2px 5px rgba(0,0,0,0);
  }
  #close{
    position: absolute;
    left: 96%;
    width: 8%;
    margin-top: -4%;
    border-radius: 50%;
    box-shadow: 0 2px 5px rgba(0,0,0,0);
  }
}
.open-popup{
  visibility: visible !important;
  top: 50% !important;
  transform: translate(-50%, -50%) scale(1) !important;
}

</style>