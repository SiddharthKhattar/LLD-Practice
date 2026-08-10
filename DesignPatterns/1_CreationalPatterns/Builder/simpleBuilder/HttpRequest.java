package simpleBuilder;

import java.util.*;

public class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private Map<String,String> queryParams;
    private String body;
    private int timeout; // in seconds

    // Private constructor - can only be accessed by the Builder, so that new HTTPRequest doesnt create the class which cause probs like constructor telescoping, class mutability, etc..
    HttpRequest() {
        headers = new HashMap<>();
        queryParams = new HashMap<>();
        body = "";
    }

    // Method to execute the HTTP request
    public void execute() {
        System.out.println("Executing " + method + " request to " + url);

        if (!queryParams.isEmpty()) {
            System.out.println("Query Parameters:");
            for (Map.Entry<String, String> param : queryParams.entrySet()) {
                System.out.println("  " + param.getKey() + "=" + param.getValue());
            }
        }

        System.out.println("Headers:");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            System.out.println("  " + header.getKey() + ": " + header.getValue());
        }

        if (body != null && !body.isEmpty()) {
            System.out.println("Body: " + body);
        }

        System.out.println("Timeout: " + timeout + " seconds");
        System.out.println("Request executed successfully!");
    }

    // Builder class as a nested class to access private members / Alternative to C++'s friend class access specifier
    // Only responsibility of a builder is to build the target class
    public static class HttpRequestBuilder {
        private HttpRequest req; // this is what we are building slowly slowly in the HttpRequestBuilder class

        public HttpRequestBuilder() {
            req = new HttpRequest();
        }

        // Method chaining
        public HttpRequestBuilder withUrl(String u) {
            req.url = u;
            return this; // "this" is the HTTPRequestBuilder class which is returned as a reference 
        }

        public HttpRequestBuilder withMethod(String method) {
            req.method = method;
            return this;
        }

        public HttpRequestBuilder withHeader(String key, String value) {
            req.headers.put(key, value);
            return this;
        }

        public HttpRequestBuilder withQueryParams(String key, String value) {
            req.queryParams.put(key, value);
            return this;
        }

        public HttpRequestBuilder withBody(String body) {
            req.body = body;
            return this;
        }

        public HttpRequestBuilder withTimeout(int timeout) {
            req.timeout = timeout;
            return this;
        }

        // Build method to create the immutable HttpRequest object
        // when we call this we have done building the object with all validations at each step, it is full and final done
        public HttpRequest build() {
            // Validation logic can be added here
            if (req.url == null || req.url.isEmpty()) {
                throw new RuntimeException("URL cannot be empty");
            }
            // returns the object that we have built just now
            return req;
        }
    }
}