

import 'package:flutter/material.dart';

class FormBloc extends ChangeNotifier{

  String name;
  String email;
  String get getName => name;

  set setName(String name){

    this.name = name;
    notifyListeners();

  }

  String get getEmail => email;

  set setEmail(String value) {

    this.email = value;
    notifyListeners();
  }

}