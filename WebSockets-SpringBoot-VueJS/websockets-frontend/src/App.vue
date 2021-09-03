<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        
        <span >WEBSOCKETS</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">sockjs-client</span>
        <span class="mr-2">webstomp-client</span>
      </v-btn>
    </v-app-bar>

   <v-content>

    <!-- Provides the application the proper gutter -->
    <v-container fluid>

      <!-- If using vue-router -->
      <router-view></router-view>
    </v-container>
  </v-content>
  
  </v-app>

  
</template>

<script>
//import HelloWorld from './components/HelloWorld';

export default {
  name: 'App',
  components: {
  //  HelloWorld,
  },
  data: () => ({
    //
  }),
};
</script>
