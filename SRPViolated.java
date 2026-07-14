import java.util.ArrayList;
import java.util.List;

class Product{
    public String name;
    public double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
}

// Violating SRP: ShoppingCart is handling multiple responsibilities
class ShoppingCart{
    public List<Product> products = new ArrayList<>();

    public void addProduct(Product p){
        products.add(p);
    }

    public List<Product> getProducts(){
        return products;
    }

    // 1) Calculates the total price in cart
    public double calculateTotal(){
        double total = 0;
        for(Product p: products){
            total += p.price;
        }
        return total;
    }

    // 2) Violating SRP - Prints Invoice (Should ideally be in a seperate class)
    public void printInvoice(){
        System.out.println("Shopping Cart Invoice: ");
        for(Product p: products){
            System.out.println(p.name + " - Rs " + p.price);
        }
        System.out.println("Total: Rs " + calculateTotal());
    }


    // 3. Violating SRP - Saves to DB (Should be in a separate class)
    public void saveToDatabase() {
        System.out.println("Saving shopping cart to database...");
    }
}


public class SRPViolated {
    public static void main(String[] args){
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("MacBook Pro", 300000));
        
        cart.printInvoice();
        cart.saveToDatabase();
    }
}
