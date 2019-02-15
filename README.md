# demo1
This is a demo project for the concept of Service Throttler, in which, the request access will be limited to N requests per M milliseconds for each token where N and M are configurable. The N and M will be loaded from environment or the config file or be set as default value (sequenced by the priority).
There is still something need to be imporved, such as we can consider that move the filter service to the gateway service, so we can easily decoupling the tenant setting and the buisness logic in the backend service and then we can eaily. But currently it's just a simple demo, so I choose this simple solution.
For the convenience of test, I provided two endpoints to retrieve the user list:
1. EndPoint: /UserList/withParameter?accessToken=token
  Just use the URL with a parameter named "accessToken", so we can test this via web browser directly.
2. EndPoint: UserList/withHeader
  Set the accessToken in the HttpHeaders(choose the OAuth2.0 in authorization), we may need some tools like postman to do the test.
  
In the first way, if we missed the accessToken, the service will return 400 Bad Request.
In the second way, if we missed the accessToken, the service will return 401 Unauthorized.
Both will return 429 Too Many Requst if the request exceed the limitation we set for each token.

Image address: https://hub.docker.com/r/windwolves/demo1
Command to run this image: docker run -e accessThreshold=2 -e intervalThreshold=2000 -p 8080:8080 -t windwolves/demo1
The accessThreshold and intervalThreshold can be configurated in command.
