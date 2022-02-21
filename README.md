# Spring Boot Application with Spring Cloud Functions, AWS Lambda and API Gateway.
  * We can use [Spring Cloud function](https://spring.io/projects/spring-cloud-function#overview to create serverless application with spring boot.
  * Create spring boot project with Spring Initializer with web, lombok dependencies along aws serverless dependency in pom.xml file.
```
	<properties>
		<aws-lambda-events>3.9.0</aws-lambda-events>
		<spring-cloud-function>3.1.5</spring-cloud-function>
		<wrapper.version>1.0.28.RELEASE</wrapper.version>
	</properties>


  <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-web</artifactId>
			<version>${spring-cloud-function}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-adapter-aws</artifactId>
			<version>${spring-cloud-function}</version>
		</dependency>		
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-events</artifactId>
			<version>${aws-lambda-events}</version>
		</dependency>
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-core</artifactId>
			<version>1.2.1</version>
			<scope>provided</scope>
		</dependency>		
  
  
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
				<dependencies>
					<dependency>
						<groupId>org.springframework.boot.experimental</groupId>
						<artifactId>spring-boot-thin-layout</artifactId>
						<version>${wrapper.version}</version>
					</dependency>
				</dependencies>				
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>3.2.4</version>
				<configuration>
					<createDependencyReducedPom>false</createDependencyReducedPom>
					<shadedArtifactAttached>true</shadedArtifactAttached>
					<shadedClassifierName>aws</shadedClassifierName>
				</configuration>
			</plugin>
      
```

  * Next create business functions and first test locally whethere you are getting expected output with following commands:
 ```
	curl -H "Content-Type:text/palin" localhost:8080/students
	curl -H "Content-Type:text/palin" localhost:8080/findByName -d "TOM"
	
 ```
  * Also create Seperate Class which implements Consumer interface and to access this class function, we need to provide this component scan pacake in properties file.
 ```spring.cloud.function.scan.packages=com.brainupgrade.spring.cloud.aws.lambda```
  * Test locally whethere you are getting expected output with following commands.
 ```
	curl -H "Content-Type:text/palin" localhost:8080/myConsumer -d "brainupgrade"
 ```
### Deployment with AWS LAMBDA
 * Now let's integrate with aws lambda , go to AWS Lambda Console.
 * Create  Lambda function by selecting "Auther from scratch" option, provide function name.
 * select environment Java 11 and you may want to leave other options as default and save.
 * Next edit "Running Settings" and provide the fully qualified handler name.
 * Go to "Course source" section, upload yoor application jar file. Jar file name ends with *-aws.jar.
 * Go to "configuration" > Environment Variable section and provide function name.
 * To integrate functions with API Gateway we need to provide Handler. Since SpringBootRequestHandler has been depricated , we can user below Generic Handler class. There is no need to create Custom Handler class now.
```
org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest
```
 * Once you incorporate you API Gateway code changes , we can create API Gateway Trigger on Lambda Console page for the Lambda function we created.
 * Once API Gateway created, upload the application jar file, Provide Handler configuration, environment variables with Function name.
 * Get API Endpoint and test either with browser or postman for post method.
