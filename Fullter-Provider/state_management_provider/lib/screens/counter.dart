


import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:state_management_provider/blocs/conunter_bloc.dart';
import 'package:state_management_provider/blocs/form_bloc.dart';

import '../blocs/conunter_bloc.dart';

class CounterPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    
   
    
    //FormBloc fomrbloc = Provider.of<FormBloc>(context);
    
    return Column(

      children: <Widget>[

          One(context),
          Two(context)

      ],

    );
  }


  Widget One(BuildContext context){

    print("from W1");
    return Text("W1");

  }

  Widget Two(BuildContext context){

    print("from W2");
    return Consumer<CounterBloc>(

      builder: (context,counter,child)=>Text("Data "+counter.counter.toString()),

    ); 

  }
}