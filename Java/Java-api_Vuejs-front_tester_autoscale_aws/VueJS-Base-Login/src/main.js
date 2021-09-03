import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './registerServiceWorker'
import vuetify from './plugins/vuetify';
import axios from 'axios'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App),
  created(){

    axios.get('/datas.json')
    .then((response)=> {

        let urlStates = {
          "URL_BACKEND":response.data.URL_BACKEND,
          "URL_FRONTEND":response.data.URL_FRONTEND
          
        }
          
        localStorage.setItem('endpoints', JSON.stringify(urlStates));

    
    }) 

  }

}).$mount('#app')
