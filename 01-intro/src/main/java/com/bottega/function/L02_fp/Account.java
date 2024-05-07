package com.bottega.function.L02_fp;

record Account(
  String name,
  String surname,
  PhoneNumber phone,
  Address address,
  int age
) {

}

record Address(){}

record PhoneNumber(){}
