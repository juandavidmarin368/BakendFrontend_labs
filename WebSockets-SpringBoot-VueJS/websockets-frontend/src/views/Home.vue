<template>
  <div class="home">

    <center><div id="nav">
      <router-link to="/">WebSockets</router-link> |
      <router-link to="/about">Form</router-link>
    </div>
    </center>
    <HelloWorld></HelloWorld>

   
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  }
}
</script>
