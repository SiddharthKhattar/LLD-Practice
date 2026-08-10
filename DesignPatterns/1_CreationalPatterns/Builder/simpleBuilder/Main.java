package simpleBuilder;

public class Main {
    public static void main(String[] args) {
        // Using Builder Pattern (nested class)
        HttpRequest request = new HttpRequest.HttpRequestBuilder()
            .withUrl("https://api.example2.com")
            .withMethod("POST")
            .withHeader("Content-Type", "application/json")
            .withHeader("Accept", "application/json")
            .withQueryParams("key", "12345")
            .withBody("{\"name\": \"Aditya\"}")
            .withTimeout(69)
            .build();

        request.execute(); // Guaranteed to be in a consistent state



        // this is wrong ❌ -> Because req is declared as HttpRequest, the compiler looks for withMethod() and build() on the HttpRequest class itself rather than on HttpRequestBuilder.
        // HttpRequest req = new HttpRequest.HttpRequestBuilder();
        // req.withMethod("POST");
        // req.build();
        // req.execute();

        // ✅ Option A: Correct explicit type
        HttpRequest.HttpRequestBuilder builder = new HttpRequest.HttpRequestBuilder();
        builder.withUrl("https://api.example69.com");
        builder.withMethod("POST");

        HttpRequest req = builder.build(); // returns HttpRequest
        req.execute();                     // called on HttpRequest

        // ✅ Option B: Standard method chaining style
        HttpRequest req2 = new HttpRequest.HttpRequestBuilder()
                .withUrl("https://api.example.com")
                .withMethod("POST")
                .build();

        req2.execute();
    }
}