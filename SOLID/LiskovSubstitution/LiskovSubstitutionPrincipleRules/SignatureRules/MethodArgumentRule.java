// Method Argument Rule : 
// Subtype method arguments can be identical or wider than the supertype
// Java enforces this by requiring the same method signature for overrides

class Parent{
    public void print(String msg){
        System.out.println("I'm runnin around town with my system bumpin L S D" + msg);
    }
}

// Client that passes a String msg as the client expects.
class Child extends Parent{
    @Override
    public void print(String msg){
        System.out.println("Girl i really want some Love Sex Dreams " + msg);
    }
}

class Client{
    private Parent p;

    public Client(Parent p){
        this.p = p;
    }

    public void printMsg(){
        p.print("Hello");
    }
}

public class MethodArgumentRule {
    public static void main(String[] args){
        Parent parent = new Parent();
        Child child = new Child();

        Client client = new Client(parent);
        Client client2 = new Client(child);

        client.printMsg();
        client2.printMsg();
    }
}
