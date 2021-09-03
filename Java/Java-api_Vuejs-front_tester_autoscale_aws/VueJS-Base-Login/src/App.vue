<!-- URL ICONS VUETIFY https://iconify.design/icon-sets/mdi/ 
Background IMAGES FOR LOGIN https://www.pexels.com/search/backgrounds/


DELETING PRESETS ON VUEJS 
vue config

-->

<template>
  <div id="app">
    

    <Login v-if="!status" ></Login>

    <div v-if="superadmin">

      <!-- App.vue -->
            <v-app id="inspire">
              <!-- we are building the application based on the Default application Markup model as in here https://vuetifyjs.com/en/components/application -->
              <DashboardSuperAdmin></DashboardSuperAdmin>
              <!-- Sizes your content based upon application components -->
              <v-content>
                <!-- Provides the application the proper gutter -->
                  <!-- If using vue-router -->
                  <router-view></router-view>
              </v-content>

              <v-footer app color="primary" dark>
                  <span>&copy; 2019</span>
                </v-footer>
            </v-app>

    </div>

    <div v-if="admin">
      
      <!-- App.vue -->
            <v-app id="inspire">
              <!-- we are building the application based on the Default application Markup model as in here https://vuetifyjs.com/en/components/application -->
              <DashboardAdmin></DashboardAdmin>
              <!-- Sizes your content based upon application components -->
              <v-content>
                <!-- Provides the application the proper gutter -->
                  <!-- If using vue-router -->
                  <router-view></router-view>
              </v-content>

              <v-footer app color="primary" dark>
                  <span>&copy; 2019</span>
                </v-footer>
            </v-app>

    </div>

    <div v-if="enduser">
  
            <!-- App.vue -->
            <v-app id="inspire">
              <!-- we are building the application based on the Default application Markup model as in here https://vuetifyjs.com/en/components/application -->
              <DashboardEnduser></DashboardEnduser>
              <!-- Sizes your content based upon application components -->
              <v-content>
                <!-- Provides the application the proper gutter -->
                  <!-- If using vue-router -->
                  <router-view></router-view>
              </v-content>

              <v-footer app color="primary" dark>
                  <span>&copy; 2019</span>
                </v-footer>
            </v-app>

    </div>


  </div>
</template>



<script>


import Login from './views/Login.vue';
import DashboardSuperAdmin from './views/HomeSuperAdmin/Dashboard.vue';
import DashboardAdmin from './views/HomeAdmin/Dashboard.vue';
import DashboardEnduser from './views/HomeEndUser/Dashboard.vue';



export default{

   components:{

      Login,
      DashboardEnduser,
      DashboardSuperAdmin,
      DashboardAdmin

  },
  data:function(){

    return{

      status:false,
      admin:false,
      superadmin:false,
      enduser:false

    }

  },

  mounted(){

    if (localStorage.getItem('todos')){

      let todos = JSON.parse(localStorage.getItem('todos'));

      todos.roles.map((item)=>{

            if(item.role_id == 1){

              this.enduser = true;

            }
            if(item.role_id == 2){

                this.admin = true;
                console.log("the roles is -->"+item.role_id);
            }
            if(item.role_id == 3){

                this.superadmin = true;
                
            }
                   // 
      })


      if(todos.authLogin){

        this.status = true;

      }

    }

  }


}


</script>

