# server-client-sample
Server and client sample code. Server is done in Java, using Tomcat 8.5 as server.
Clients are done in Python 3.6.
## Server
Expected JSON with the following format:
```JSON
  {
    "firstValue": 1,
    "secondValue": 2,
    "operator": "+"
  }
  ```
  Where firstValue and secondValue are integers.<br />
  In case of correct input, it returns status 200 and a JSON like the following one:  
  
  ```JSON
  {
    "firstNumber": 10,
    "operator": "*",
    "result": 40,
    "secondNumber": 4
  } 
 ```
  If not,  it returns a json with the parameter "error" and status 400 (bad request)
  
  ## Client
  Both clients try to prevent bad formatted csv.  
  The first one, just prints in console the result of the calls.  
  The second one, prints it in a log file in JSON format with more detail.  
  By default both clients read sample.csv, but it can be changed by executing client passing by argument the path you want.  
  The rest of default parameters can be changed by modifying the code. They are all at the top of the code.
