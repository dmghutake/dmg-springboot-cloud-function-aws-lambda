package com.brainupgrade.spring.cloud.aws.lambda.repository;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.brainupgrade.spring.cloud.aws.lambda.entity.Student;

@Repository
public class StudentRepository {

  public List<Student> studentList(){
    return Arrays.asList(
        new Student(1,"TOM",40),
        new Student(2,"DIK",29),
        new Student(3, "HARRY",42));
  }
}
