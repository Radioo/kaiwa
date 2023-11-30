<template>
  <div class="logo"><img src="../../static/images/Kaiwa.svg" alt="Logo"></div>
</template>

<script setup>

</script>

<style lang="scss">
@use "../scss/abstracts/index" as s;

    .logo{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 60%;
        margin: auto;
        animation: s.$fadeTransition;
        img{
            max-width: 20%;
            height: auto;
        }
    }

</style>
