# Simple Microservice Observability Example

This example is for quick verification that traces from different microservice running on different programming languages will be propagated based on auto instrumentations.

The example has 2 microservice running on 
- Java
- Golang

The example is built for quick self reference hence no docker images has been created.


## Java Microservice

The java is the client invoking a call to the Golang microservice. Sample output

```text
➜  splunk-O11y-microservice curl localhost:8081/api/greeting
Spring Boot says: Hello from Go!%   
```

## Java configuration

![javaconfig](./images/1.png)
### VM option
-javaagent:./splunk-O11y-java-microservice/opentelemetry-javaagent.jar

### Environment Variables
OTEL_SERVICE_NAME=java-app;OTEL_RESOURCE_ATTRIBUTES=deployment.environment=dev

---

## Golang Microservice

![golangconfig](./images/2.png)

### Environment Variables
OTEL_SERVICE_NAME=java-app;OTEL_RESOURCE_ATTRIBUTES=deployment.environment=dev

## Output

![o11y-traces](./images/3.png)
![o11y-servicemap](./images/4.png)