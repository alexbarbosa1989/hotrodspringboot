package com.redhat.hotrod.hotrodspringboot.controller;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Person {
   @ProtoField(number = 1)
   String firstName;

   @ProtoField(number = 2)
   String lastName;

   @ProtoField(number = 3, defaultValue = "-1")
   int bornYear;

   @ProtoField(number = 4)
   String bornIn;

   @ProtoFactory
   public Person(String firstName, String lastName, int bornYear, String bornIn) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.bornYear = bornYear;
      this.bornIn = bornIn;
   }

   @Override
   public String toString() {
      return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", bornYear='" + bornYear + '\'' +
            ", bornIn='" + bornIn + '\'' +
            '}';
   }
}
