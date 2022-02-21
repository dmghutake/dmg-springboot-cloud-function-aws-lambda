package com.brainupgrade.spring.cloud.aws.lambda.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class MyApiGatewayHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

}
