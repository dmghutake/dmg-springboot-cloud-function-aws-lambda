package com.brainupgrade.spring.cloud.aws.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  private int id;
  private String name;
  private int age;
  
}
