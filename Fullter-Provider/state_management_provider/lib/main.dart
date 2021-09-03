import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:state_management_provider/blocs/conunter_bloc.dart';

import 'blocs/form_bloc.dart';
import 'screens/counter.dart';
import 'screens/counter2.dart';
import 'screens/reset_value.dart';

void main() => runApp(
  
    MultiProvider(
      providers: [
        ChangeNotifierProvider<CounterBloc>.value(
          value: CounterBloc(),
        ),

        ChangeNotifierProvider<FormBloc>.value(
          value: FormBloc(),
        ),
        
      ],
      child: MyApp(),
    ),
  
  );

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
      
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  
  

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
  
    final CounterBloc counterbloc = Provider.of<CounterBloc>(context,listen:false);
    return Scaffold(
      appBar: AppBar(
       
        title: Text("title"),
      ),
      body: Center(
       
        child: Column(
         
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

            CounterPage(),
            CounterPage2(),
            RaisedButton(
              child:Text("PUSH TO THE ANOTHER PAGE"),
              onPressed: (){

                Navigator.push(context, new MaterialPageRoute(
              
                  builder: (context)=>ResetValue())
                
                );


              },
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){

          counterbloc.increment();

        },
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
