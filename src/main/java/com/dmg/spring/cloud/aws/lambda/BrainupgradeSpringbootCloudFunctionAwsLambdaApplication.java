package com.dmg.spring.cloud.aws.lambda;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.brainupgrade.spring.cloud.aws.lambda.entity.Student;
import com.brainupgrade.spring.cloud.aws.lambda.repository.StudentRepository;

@SpringBootApplication
public class BrainupgradeSpringbootCloudFunctionAwsLambdaApplication {

  @Autowired
  StudentRepository studentRepository;

  @Bean
  public Supplier<List<Student>> students() {
    return () -> studentRepository.studentList();
  }

  @Bean
  public Function<APIGatewayProxyRequestEvent, List<Student>> findByName() {
    return (proxyRequestEvent) -> studentRepository.studentList().stream()
        .filter(student -> student.getName().equals(proxyRequestEvent.getQueryStringParameters().get("name"))).collect(Collectors.toList());
  }
  
  @Bean
  public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getResponse() {
    return value ->{
      APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
      apiGatewayProxyResponseEvent.setBody(value.getBody());
      apiGatewayProxyResponseEvent.setStatusCode(201);
      apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("Content-type", "application/json"));
      return apiGatewayProxyResponseEvent;
    };
  }
  
    

  @Bean
  public MyConsumer myConsumerBean() {
    return new MyConsumer();
  }
  
  public static void main(String[] args) {
    SpringApplication.run(BrainupgradeSpringbootCloudFunctionAwsLambdaApplication.class, args);
  }
}
