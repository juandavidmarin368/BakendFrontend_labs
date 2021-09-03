import Vue from 'vue'
import Router from 'vue-router'

import HomeAdmin from './views/HomeAdmin/HomeAdmin.vue';

import SolicitudPermiso from './views/HomeEndUser/SolicitudPermiso.vue';
import Historico from './views/HomeEndUser/Historico.vue';

import HomeSuperadmin from './views/HomeSuperAdmin/HomeSuperadmin.vue';


Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
      
      //******************THE WHOLE MENU FOR SUPERADMINUSER************ */

      {
        path: "/homesuperadmin",
        name: "HomeSuperadmin",
        component: HomeSuperadmin,
        meta: { requiresAuthsuperadmin: true }
      },


      //******************THE WHOLE MENU FOR SUPERADMINUSER************ */
  


      //******************THE WHOLE MENU FOR ADMINUSER************ */
      {
        path: "/homeadmin",
        name: "homeadmin",
        component: HomeAdmin,
        meta: { requiresAuthadmin: true }
      },

      //******************THE WHOLE MENU FOR ADMINUSER************ */



      //******************THE WHOLE MENU FOR ENDUSER************ */

      /*{
        path: "/homenduser",
        name: "HomeEndUser",
        component: HomeEndUser,
        meta: { requiresAuthenduser: true }
      },*/

      {
        path: "/enduser/solicitud",
        name: "solicitud",
        component: SolicitudPermiso,
        meta: { requiresAuthenduser: true }
      },

      {
        path: "/enduser/historico",
        name: "historico",
        component: Historico,
        meta: { requiresAuthenduser: true }
      },
      
       //******************THE WHOLE MENU FOR ENDUSER************ */

      {
        path: '*',
        meta: { requiresAuthsuperadmin: true }
      }

  ]
})


router.beforeEach((to, from, next) => {

  let endUser = true;
  let admin = true;
  let superadmin = true;
  let redirectPath = "";
  let anyuser=true;

  let todos = JSON.parse(localStorage.getItem("todos"));
  if (todos != null) {

    todos.roles.map((item) => {
      if (item.role_id == 1) {
        endUser = false;
        redirectPath = "/enduser/solicitud";
      }
      if (item.role_id == 2) {
        admin = false;
        redirectPath = "/homeadmin";
      }
      if (item.role_id == 3) {
        superadmin = false;
        redirectPath = "/homesuperadmin";
      }
    })

  }else{
    anyuser = false;
    redirectPath = "/";
  }




  if (to.matched.some(record => record.meta.requiresAuthenduser)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.

    if (endUser) {
      console.log('WE HAVE GOT HERE...enduser');
      next({
        path: redirectPath,
        //query: { redirect: to.fullPath }
      })
    } else {
      next()
    }

  } else {
    next() // make sure to always call next()!
  }

  if (to.matched.some(record => record.meta.requiresAuthadmin)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.

    if (admin) {
      console.log('WE HAVE GOT HERE...admin');
      next({
        path: redirectPath,
        //query: { redirect: to.fullPath }
      })
    } else {
      next()
    }

  } else {
    next() // make sure to always call next()!
  }

  if (to.matched.some(record => record.meta.requiresAuthsuperadmin)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.

    if (superadmin) {
      console.log('WE HAVE GOT HERE...superadmin');
      next({
        path: redirectPath,
        //query: { redirect: to.fullPath }
      })
    } else {
      next()
    }

  } else {
    next() // make sure to always call next()!
  }

  if (to.matched.some(record => record.meta.requiresAuthsuperadmin)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.

    if (anyuser) {
      console.log('WE HAVE GOT HERE...anyuser...'+redirectPath);
      next({
        path: redirectPath,
        //query: { redirect: to.fullPath }
      })
    } else {
      next()
    }

  } else {
    next() // make sure to always call next()!
  }


})


export default router;