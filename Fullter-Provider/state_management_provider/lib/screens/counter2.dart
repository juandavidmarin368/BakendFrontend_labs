

import 'package:flutter/material.dart';

class CounterPage2 extends StatefulWidget{

  createState(){

      return _CounterPage2();
  }
}


class _CounterPage2 extends State<CounterPage2>{


  int counter = 0;

  void increment(){

      counter++;
  }

  @override
  Widget build(BuildContext context) {
    
    print("data from Counterpage2 .."+counter.toString());

    return Center(

      child:Text("CounterPage2"),

    );
  }


}
