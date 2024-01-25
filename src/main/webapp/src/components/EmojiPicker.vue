<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import emojiList from 'unicode-emoji-json/data-ordered-emoji.json';

const popupOpen = ref(false);

const emojiButton = ref(null);
const emojiPickerPopup = ref(null);

const emit = defineEmits(["emojiPicked"]);

function emojiPicked(e) {
    const emoji = e.target.textContent; 
    emit("emojiPicked", emoji);
}

function closePopupOnPageClick(e) {
    // close popup window if clicked elsewhere
    if (
        popupOpen.value === true
     && e.target !== emojiButton.value
     && e.target !== emojiPickerPopup.value
     && !emojiPickerPopup.value?.contains(e.target)
    ) {
        popupOpen.value = false;
    }
}

onMounted(() => {
    document.addEventListener("click", closePopupOnPageClick);
});

onBeforeUnmount(() => {
    document.removeEventListener("click", closePopupOnPageClick);
});

</script>

<template>
    <div class="rel btn">
        <button @click="popupOpen = !popupOpen" ref="emojiButton">Emoji</button>
        <div class="popup" v-if="popupOpen" ref="emojiPickerPopup">
            <div
                class="emoji-btn"
                tabindex="0"
                @click="emojiPicked"
                v-for="(emoji, index) in emojiList">
                {{ emoji }}
            </div>
        </div>
    </div>
</template>

<style lang="scss">
@use "../scss/abstracts/index" as *;

:root {
    --popup-width: 420px;
    --popup-height: 300px;
    --popup-button-gap: 10px;
}

.rel {
    position: relative;
    button {
        width: 100%;
        height: 100%;
        margin: 0;
        cursor: pointer;
        font-size: 1rem;
        border-radius: 8px;
        background-color: var(--chat);
        color: var(--font-color);
    }
}

.popup {
    border-radius: 8px;
    box-sizing: border-box;
    width: var(--popup-width);
    height: var(--popup-height);
    background-color: var(--chat);
    color: white;
    padding: 0.5em;
    display: grid;
    position: absolute;
    top: calc(0px - (var(--popup-height) + var(--popup-button-gap)));
    right: 0px;
    // left: calc(0px - var(--popup-width) / 2);
    overflow-y: scroll;

    grid-template-columns: repeat(7, 1fr);
    column-gap: 0;
    cursor: default;
}

.emoji-btn {
    height: 48px;
    line-height: 48px;
    text-align: center;
    font-size: 1.5em;
    width: 48px;
    display: block;

    margin: 4px;
    border-radius: 8px;
    cursor: pointer;

    user-select: none;
}

.emoji-btn:hover {
    background-color: var(--chat-message);
}
</style>