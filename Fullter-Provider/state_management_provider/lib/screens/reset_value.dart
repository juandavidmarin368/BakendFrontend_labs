

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:state_management_provider/blocs/conunter_bloc.dart';

class ResetValue extends StatelessWidget{


  @override
  Widget build(BuildContext context) {
    
    print("data reload..");

    return Scaffold(

      appBar: AppBar(
        title: Text("Reseting values"),
      ),

      body:Center(

        child: Padding(

          padding: EdgeInsets.all(25.0),
          child:Consumer<CounterBloc>(  
          
                builder:(context,counterbloc,child)=>Column(

                  children: <Widget>[

                        Text("The current data counter is "+counterbloc.counter.toString()),
                        RaisedButton(
                          child:Text("RESET VALUES"),
                          onPressed:(){
                            counterbloc.counter = 0;
                          },
                        ),
              ],
            ),
            )
        ),
      ),

    );
  }


}