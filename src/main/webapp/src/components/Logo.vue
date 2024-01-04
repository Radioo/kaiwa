<template>
  <div class="logo"><img src="../../static/images/Kaiwa.svg" alt="Logo"></div>
</template>

<script setup>

</script>

<style lang="scss">
@use "../scss/abstracts/index" as *;

    .logo{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 60%;
        margin: auto;
        animation: $fadeTransition;
        img{
            width: 100%;
            height: auto;
            max-height: 200px;
        }
    }

@media (max-width: $mobile-width){
  .logo{
    height: 14%;
    img{
      width: 66%;
      height: auto;
      margin-top: 5%;
    }
  }
}
@media (max-height: 750px) {
  .logo{
    img{
      max-height: 125px;
    }
  }
}
@media (max-height: 500px) {
  .logo{
    img{
      max-height: 75px;
    }
  }
}

</style>
