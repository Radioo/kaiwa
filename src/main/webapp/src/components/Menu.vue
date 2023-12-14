<template>
  <div id="sidebar" ref="sidebarRef">
    <div class="toggle-btn" @click="show">
      <span></span>
      <span></span>
      <span></span>
    </div>
    <span id="user-header">Hello {{ username }}</span>
    <button class="sidebar-button">Temp1</button>
    <button class="sidebar-button">Temp1</button>
    <button class="sidebar-button">Temp1</button>
    <button class="sidebar-button">Temp1</button>
    <button class="sidebar-button" @click="openPopup()">Settings</button>
    <div class="" id="logout">
      <button onclick="location.href='/logout'">Log out</button>
    </div>
  </div>
  <div id="popup">
    <img src="../../static/images/Settings.svg" alt="hello" id="main">
    <img src="../../static/images/Close.svg" alt="hello" id="close" @click="closePopup">
    <h2>Settings</h2>
    <h3>Message appearance animation</h3>
    <div class="radio">
      <input v-model="selectedOption" class="radio__input" type="radio" value="fade-in-anim" name="myRadio" id="myRadio1" @change="emitOptionChange">
      <label class="radio__label" for="myRadio1">Fade in</label>
      <input v-model="selectedOption" class="radio__input" type="radio" value="slide-in-anim" name="myRadio" id="myRadio2" @change="emitOptionChange">
      <label class="radio__label" for="myRadio2">Slide left</label>
      <input v-model="selectedOption" class="radio__input" type="radio" value="reveal-in-anim" name="myRadio" id="myRadio3" @change="emitOptionChange">
      <label class="radio__label" for="myRadio3">Slide up</label>
    </div>
  </div>

</template>

<script setup>

import { ref } from 'vue';

const selectedOption = ref("fade-in-anim");

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

const emit = defineEmits(["optionChanged"])
const emitOptionChange = () => {
  emit('optionChanged', selectedOption.value);
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
  .sidebar-button {
    appearance: none;
    height: 5vh;
    width: 100%;
    background-color: var(--primary);
    border-radius: $base-border-radius;
    border-width: $base-border-thickness;
    font-weight: 600;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10%;
    margin-bottom: 10%;
    padding: 24px 32px;
    outline: none;
    &:hover {
      background-color: var(--button-hover);
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
  z-index: 10;
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
  h3{
    font-size: $font-size-base;
    font-weight: normal;
  }
}
.open-popup{
  visibility: visible !important;
  top: 50% !important;
  transform: translate(-50%, -50%) scale(1) !important;
}

.radio{
  display: inline-flex;
  overflow: hidden;
  border-radius: 15px;
  box-shadow: 0 0 5px rgba(0,0,0,0.25);
}
.radio__input{
  display: none;
}
.radio__label{
  padding: 8px 14px;
  font-size: 14px;
  color: var(--font-color);
  background: var(--secondary-grad);
  cursor: pointer;
  transition: background 0.1s;
}
.radio__label:not(:last-of-type){
  border-right: 1px solid #006B56;
}
.radio__input:checked + .radio__label{
  background: #006B56;
}


</style>