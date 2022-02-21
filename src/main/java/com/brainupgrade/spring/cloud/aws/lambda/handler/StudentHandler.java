package com.brainupgrade.spring.cloud.aws.lambda.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

@SuppressWarnings("deprecation")
public class StudentHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, Object>{

}
