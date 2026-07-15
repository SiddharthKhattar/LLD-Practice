import java.util.ArrayList;
import java.util.List;

// Product class representing any item in eCommerce.
class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// 1. ShoppingCart: Only responsible for Cart related business logic.
class ShoppingCart{
    private List<Product> products = new ArrayList<>();

    void addProduct(Product p){
        products.add(p);
    }

    List<Product> getProducts(){
        return products;
    }

    double calculateTotal(){
        double total = 0;
        for(Product p: products){
            total += p.price;
        }
        return total;
    }
}


// 2. ShoppingCartPrinter: Only responsible for printing invoices
class ShoppingCartPrinter{
    private ShoppingCart cart;

    public ShoppingCartPrinter(ShoppingCart cart){
        this.cart = cart;
    }

    public void printInvoice(){
        System.out.println("Shopping Cart Invoice: ");
        for(Product p: cart.getProducts()){
            System.out.println(p.name + " - Rs " + p.price);
        }
        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}

// 3. ShoppingCartStorage: Only responsible for saving cart to DB
class ShoppingCartStorage{
    private ShoppingCart cart;

    public ShoppingCartStorage(ShoppingCart cart){
        this.cart = cart;
    }

    public void saveToSQLDatabase() {
        System.out.println("Saving shopping cart to SQL database...");
    }

    public void saveToMongoDatabase() {
        System.out.println("Saving shopping cart to MongoDB...");
    }

    public void saveToFile() {
        System.out.println("Saving shopping cart to File...");
    }
}





public class OCPViolated {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 90000));
        cart.addProduct(new Product("PS5 Pro", 100000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        ShoppingCartStorage db = new ShoppingCartStorage(cart);
        db.saveToSQLDatabase();
    }    
}
