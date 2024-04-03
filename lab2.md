## Lab Report 2 - Servers and SSH Keys (Week 3)

### Introduction

In this lab, we delved into the basics of web servers, focusing particularly on the creation of a simplified web server in Java. This server's main function is to append messages sent through HTTP requests to a running string, and display the entire string so far.

### Part 1

#### Task Description

We were required to write a web server named `StringServer`. This server needed to support a particular path and behavior. Specifically, when a GET request is made to `/add-message?s=<string>`, the server should concatenate a new line, the next number in sequence starting from 1, and the string parameter to a running string. It should then display the running string.

#### Implementation

Here is the code snippet for the `StringServer` class:

```java
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class StringServer {
    private StringBuilder runningString = new StringBuilder();
    private int sequenceNumber = 0;

    public static void main(String[] args) throws IOException {
        int port = 8080;  // You can dynamically assign this based on user input or configuration
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/add-message", new StringServer().new AddMessageHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server Started on port " + port);  // Server Started print line
    }

    class AddMessageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            URI uri = httpExchange.getRequestURI();
            String response = processRequest(uri);
            httpExchange.sendResponseHeaders(response.equals("Invalid path") ? 404 : 200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public String processRequest(URI uri) {
        if (uri.getPath().equals("/add-message")) {
            String message = uri.getQuery().split("=")[1];
            sequenceNumber++;
            runningString.append(sequenceNumber).append(". ").append(message).append("\n");
            return runningString.toString();
        }
        return "Invalid path";  // This will trigger a 404 response
    }
}

```

<img width="578" alt="Screen Shot 2023-10-18 at 5 37 12 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/56d60c3f-0cbb-43b3-8df4-a1c428079739">

##### Screenshot 1 Description

- **Methods Called**: `handle` in `AddMessageHandler` class, `processRequest` in `StringServer` class
- **Relevant Arguments and Field Values**: URI: `/add-message?s=Hello`, `runningString`: "1. YourMessageHere\n2. Hi_My_Name_Is_Affaan_AND_THIS_IS_MYYYYYY_SERVER\n3. this lab kinda crazy bro\n4. probably should add a delete feature as well not gonna lie\n", `sequenceNumber`: 4
- **Field Value Changes**: `runningString` becomes "1. YourMessageHere\n2. Hi_My_Name_Is_Affaan_AND_THIS_IS_MYYYYYY_SERVER\n3. this lab kinda crazy bro\n4. probably should add a delete feature as well not gonna lie\n5. Hello\n", `sequenceNumber` becomes 5

In this instance, the server was already populated with four messages. 
The sequence number was at 4 before the request. 
Upon receiving the GET request with the URI `/add-message?s=Hello`, the server appends "5. Hello\n" to the existing `runningString`. 
The sequence number is incremented to 5 to prepare for the next message. This demonstrates the server's capability to maintain state and handle message sequences effectively.

<img width="699" alt="Screen Shot 2023-10-18 at 5 40 09 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/7956144c-0e9e-4fe6-bbf7-48c698568279">

##### Screenshot 2 Description

- **Methods Called**: `handle` in `AddMessageHandler` class, `processRequest` in `StringServer` class
- **Relevant Arguments and Field Values**: URI: `/add-message?s=My Final Message`, `runningString`: "1. YourMessageHere\n2. Hi_My_Name_Is_Affaan_AND_THIS_IS_MYYYYYY_SERVER\n3. this lab kinda crazy bro\n4. probably should add a delete feature as well not gonna lie\n5. Hello\n", `sequenceNumber`: 5
- **Field Value Changes**: `runningString` becomes "1. YourMessageHere\n2. Hi_My_Name_Is_Affaan_AND_THIS_IS_MYYYYYY_SERVER\n3. this lab kinda crazy bro\n4. probably should add a delete feature as well not gonna lie\n5. Hello\n6. My Final Message\n", `sequenceNumber` becomes 6

For the second screenshot, the sequence number was at 5, and the server already had five messages. Upon the GET request with the URI `/add-message?s=My Final Message`, the server appended "6. 
My Final Message\n" to the existing `runningString`. The sequence number is incremented to 6, effectively showcasing the server's ability to dynamically handle and append new messages in a sequence, while also keeping track of the order.

#### Part 2: SSH Keys and Passwordless Login

##### Locating SSH Keys

###### Private Key on Local Machine
**Terminal Command**:  
```bash
ls ~/.ssh/
```
**Screenshot**:  

<img width="349" alt="Screen Shot 2023-10-18 at 6 15 40 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/26308d3f-6c51-4df1-85c5-c414d9d94f88">

###### Public Key on ieng6 Server
**Terminal Command**:  
```bash
ls ~/.ssh/
```
**Screenshot**:  

<img width="349" alt="Screen Shot 2023-10-18 at 6 17 44 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/da868f96-f922-476f-af7a-ed4fd9ec56e0">


##### Demonstrating Passwordless Login
**Terminal Command**:  
```bash
ssh cs15lfa23se@ieng6.ucsd.edu
```
**Screenshot**:  

<img width="839" alt="Screen Shot 2023-10-18 at 6 18 51 PM" src="https://github.com/affaan-m/cse15l-lab-reports/assets/124439313/022e1e3b-9569-4b68-ae5d-c39788fcf233">


---

#### Part 3: What I Learned

In the past couple of weeks, I've gained valuable insights into SSH and SCP (Secure Copy Protocol). Before these labs, I had a theoretical understanding of SSH, but it was through hands-on implementation that I truly understood its importance. SSH is not just a protocol but a secure channel for various network services over an unsecured network. It's like an encrypted tunnel where data can safely travel. I also found SCP to be extremely useful for transferring files securely between local and remote systems, which can be crucial in both academic and enterprise settings.

---

#### Conclusion

This lab served as a comprehensive exercise in understanding server-side operations and secure network communications. It not only provided hands-on experience but also emphasized the importance of security in modern-day networking.

