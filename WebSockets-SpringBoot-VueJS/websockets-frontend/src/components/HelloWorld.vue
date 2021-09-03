<template>
  
<div>

  <!--<v-btn block color="secondary" dark @click="connect">CONNECT!</v-btn>-->

 <v-simple-table >
   
    <template v-slot:default>
      <thead>
        <tr>
          <th>ID</th>
          <th class="text-left">CC</th>
          <th class="text-left">NAME</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item,key) in arraySocket2" :key="key">
          <td>  {{ key }}</td>
          <td>{{ item.cc }}</td>
          <td>{{ item.name }}</td>
        
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</div>
  
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

  export default {
    data () {
      return {
        arraySocket: [
        ],
        arraySocket2: [
        ],
      }
    },
    mounted(){

      this.connect();
    },
    methods:{

      roleCount(key) {
      return key + 1;
      },

      connect() {
      //  let that = this;
      this.socket = new SockJS("http://localhost:8445/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/notification", tick => {
            console.log(tick);

            if(this.arraySocket.length>0){

              this.arraySocket=[];

           }

            this.arraySocket.push(JSON.parse(tick.body));
            //console.log("the array..");
           // console.log(this.arraySocket);
           if(this.arraySocket2.length>0){

              this.arraySocket2=[];

           }

            this.arraySocket.map(item=>{

              //console.log(item);
              item.map(item2=>{
                //console.log("name --> "+item2.name);
                //console.log("cc --> "+item2.cc);
                this.arraySocket2.push(item2);

              });
              

            })
            


          });
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );


      
    },



    }
  }
</script>