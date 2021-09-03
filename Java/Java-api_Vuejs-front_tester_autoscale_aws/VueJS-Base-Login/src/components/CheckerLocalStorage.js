


export const actionStore={

        
    methods:{

        logout(){

            let todos = JSON.parse(localStorage.getItem('todos'));
            todos.authLogin = false;
            localStorage.removeItem('todos');
            localStorage.removeItem('endpoints');
            window.location.href = getEndpoints().URL_FRONTEND;
        },

        isAlive(){

            let todos = JSON.parse(localStorage.getItem("todos"));
            if(todos!=null){
                return true;
            }else{
                return false;
            }

        },//juandamalo

        getComponentName(){

            let todos = JSON.parse(localStorage.getItem("todos"));
            if(todos!=null){
                
                todos.roles.map((item, index)=>{

                    if(item.role_id == 1){
        
                      return item.role_id;
        
                    }
                    if(item.role_id == 2){
        
                       return item.role_id;
                    }
                    if(item.role_id == 3){
        
                        return item.role_id;
                        
                    }
                           // 
              })


            }else{
                return false;
            }
            
        },

        getEndpoints(){

            let todos = JSON.parse(localStorage.getItem("endpoints"));
            return todos;

        }

    }
}