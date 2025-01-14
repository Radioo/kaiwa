<template>
  <div id="sidebar" ref="sidebarRef">
    <div class="toggle-btn" @click="show">
      <span></span>
      <span></span>
      <span></span>
    </div>
    <span id="user-header">Hello {{ username }}!</span>
    <div id="connected-users">
      <h3>Chatting now:</h3>
      <ul>
        <li v-for="user in connectedUsers" :key="user">{{ user }}</li>
      </ul>
    </div>
    <div class="description">Censor settings</div>
    <div class="radio">
      <input v-model="censorStatus" class="radio__input" type="radio" value="T" id="censorOn" @change="toggleCensor">
      <label class="radio__label" for="censorOn">On</label>
      <input v-model="censorStatus" class="radio__input" type="radio" value="F" id="censorOff" @change="toggleCensor">
      <label class="radio__label" for="censorOff">Off</label>
    </div>
    <div class="description">Animation settings</div>
    <div class="radio">
      <input v-model="selectedAnim" class="radio__input" type="radio" value="fade-in-anim" name="myRadio" id="myRadio1" @change="emitOptionChange">
      <label class="radio__label" for="myRadio1">Fade in</label>
      <input v-model="selectedAnim" class="radio__input" type="radio" value="slide-in-anim" name="myRadio" id="myRadio2" @change="emitOptionChange">
      <label class="radio__label" for="myRadio2">Slide left</label>
      <input v-model="selectedAnim" class="radio__input" type="radio" value="reveal-in-anim" name="myRadio" id="myRadio3" @change="emitOptionChange">
      <label class="radio__label" for="myRadio3">Slide up</label>
    </div>
    <div class="" id="logout">
      <button onclick="location.href='/logout'" class="pointer">Log out</button>
    </div>
  </div>

</template>

<script setup>

import { ref } from 'vue';

const selectedAnim = ref("fade-in-anim");
const savedSettings = JSON.parse(localStorage.getItem('animationSettings')) || { selectedAnim: 'slide-in-anim' };
console.log('Loaded animation settings:', savedSettings);
selectedAnim.value = savedSettings.selectedAnim;

const sidebarRef = ref(null);

const show = () => {
  sidebarRef.value.classList.toggle('active');
};

defineProps({
  username: String,
  connectedUsers: Array
})

const openPopup = () => {
  const popup = document.getElementById('popup');
  popup.classList.add("open-popup");
};

const closePopup = () => {
  const popup = document.getElementById('popup');
  popup.classList.remove("open-popup");
};
  

let isCensored
if (!localStorage.getItem('censor'))
  isCensored = "T"
else
  isCensored = JSON.parse(localStorage.getItem('censor'))

setTimeout(function() {
  const censorEl = document.getElementById("censor");
  if(censorEl) {
    censorEl.innerHTML = "Censor: " + (isCensored=="T"?"ON":"OFF")
  }
}, 0);

const censorStatus = ref(isCensored);

const toggleCensor = () => {
  isCensored = censorStatus.value;
  localStorage.setItem('censor', JSON.stringify(isCensored));
  location.reload();
};

const emit = defineEmits(["optionChanged"])
const emitOptionChange = () => {
  const settingsToSave = { selectedAnim: selectedAnim.value };
  localStorage.setItem('animationSettings', JSON.stringify(settingsToSave));
  emit('optionChanged', selectedAnim.value);
};

</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;

#sidebar {
  position: absolute;
  z-index: 15;
  top: 0;
  width: 14%;
  height: 100%;
  background: var(--chat);
  left: -14%;
  animation: $fadeTransition;
  transition: .5s;
  display: flex;
  flex-direction: column;
  align-items: center;

  #user-header{
    display: block;
    padding: 8px;
    width: 80%;
    margin-top: 8px;
    font-weight: 600;
    font-size: $font-size-large;
  }
  .sidebar-button {
    appearance: none;
    height: 5vh;
    width: 80%;
    background-color: var(--primary);
    border-radius: $base-border-radius;
    border-width: $base-border-thickness;
    font-weight: 600;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10%;
    margin-bottom: 10%;
    outline: none;
    &:hover {
      background-color: var(--button-hover);
    }
  }
  .description{
    margin: 8px 0 8px 0;
  }
  .toggle-btn {
    position: absolute;
    top: 1em;
    left: 110%;
    cursor: pointer;
  }
  #logout {
    position: absolute;
    bottom: 2%;
    width: 80%;
    button {
      height: 5vh;
      width: 100%;
      background-color: var(--logout);
      border-radius: $base-border-radius;
      border-width: $base-border-thickness;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 500;
      font-size: 16px;
      &:hover{
        background-color: #bb3434;
      }
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
.radio{
  display: inline-flex;
  overflow: hidden;
  border-radius: 15px;
  box-shadow: 0 0 5px rgba(0,0,0,0.25);
  flex-direction: column;
  width: 60%;
  text-align: center;
  font-size: $font-size-small;
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
  .radio__input:checked + .radio__label{
    background: #006B56;
  }
}

#connected-users{
  height: 45%;
  width: 80%;
  margin-bottom: 10px;
  ul{
    li{
      margin-bottom: 5px;
      font-size: $font-size-base;
    }
  }
}

@media (max-width: $smaller-width){
  #sidebar {
    width: 20%;
    left: -20%;
    #user-header{
      font-size: $font-size-mobile-large;
    }
    .sidebar-button {
      font-size: $font-size-mobile;
    }
    #logout {
      button {
        font-size: $font-size-mobile;
      }
    }
  }
  #connected-users{
    font-size: $font-size-mobile;
    ul{
      li{
        font-size: $font-size-mobile;
      }
    }
  }
  .description{
    font-size: $font-size-mobile;
  }
  .radio{
    .radio__label{
      font-size: $font-size-mobile-smaller;
    }
  }
}

@media (max-width: $mobile-width){
  #sidebar {
    width: 36%;
    left: -36%;
    #user-header{
      display: block;
      font-size: $font-size-mobile-large;
    }
    .sidebar-button {
      font-size: $font-size-mobile;
    }
    #logout {
      button {
        font-size: $font-size-mobile;
      }
    }
  }
  #connected-users{
    font-size: $font-size-mobile;
    ul{
      li{
        font-size: $font-size-mobile;
      }
    }
  }
  .description{
    font-size: $font-size-mobile;
  }
  .radio{
    .radio__label{
      font-size: $font-size-mobile-smaller;
    }
  }
}
@media (max-width: $mobile-width-smaller){
  #sidebar {
    #user-header{
      font-size: $font-size-mobile-smaller-large;
    }
    .sidebar-button {
      font-size: $font-size-mobile-smaller;
    }
    #logout {
      button {
        font-size: $font-size-mobile-smaller;
      }
    }
  }
  #popup {
    h3, h2{
      font-size: $font-size-mobile-smaller;
    }
  }
  .radio__label{
    font-size: $font-size-mobile-smaller;
  }
  .toggle-btn span {
    width: 30px;
    height: 3px;
  }
  #connected-users{
    font-size: $font-size-mobile-smaller;
    ul{
      li{
        font-size: $font-size-mobile-smaller;
      }
    }
  }
  .description{
    font-size: $font-size-mobile-smaller;
  }
  .radio{
    .radio__label{
      font-size: $font-size-mobile-smallest;
    }
  }
}

@media (max-width: $mobile-width-smallest), (max-height: $max-height){
  #sidebar {
    #user-header{
      font-size: $font-size-mobile-smallest-large;
    }
    .sidebar-button {
      font-size: $font-size-mobile-smallest;
    }
    #logout {
      button {
        font-size: $font-size-mobile-smallest;
      }
    }
  }
  #popup {
    h3, h2{
      font-size: $font-size-mobile-smallest;
    }
  }
  .radio__label{
    font-size: $font-size-mobile-smallest;
  }
  .toggle-btn span {
    width: 30px;
    height: 3px;
  }
  #connected-users{
    font-size: $font-size-mobile-smallest;
    height: 40%;
    ul{
      li{
        font-size: $font-size-mobile-smallest;
      }
    }
  }
  .description{
    font-size: $font-size-mobile-smallest;
  }
  .radio{
    .radio__label{
      font-size: $font-size-mobile-smallest;
    }
  }
}

.pointer {
  cursor: pointer;
}

</style>
