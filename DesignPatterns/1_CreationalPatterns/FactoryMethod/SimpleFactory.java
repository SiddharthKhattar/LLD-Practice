// Burger Interface
interface Burger{
    void prepare();
}

// Concrete Burger Implementation
class BasicBurger implements Burger{
    @Override
    public void prepare(){
        System.out.println("Preparing Basic Burger with Bun, Patty and Ketchup!");
    }
}

class StandardBurger implements Burger{
    @Override
    public void prepare(){
        System.out.println("Preparing Standard Burger with Bun, Patty, Cheese Slice and Ketchup!");
    }
}

class PremiumBurger implements Burger{
    @Override
    public void prepare(){
        System.out.println("Preparing Premium Burger with Bun, Double Deluxe Patty, Cheese Slice, Lettuce and BBQ Sauce");
    }
}


// Burger Factory
class BurgerFactory{
    public Burger createBurger(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicBurger();
        }else if(type.equalsIgnoreCase("standard")){
            return new StandardBurger();
        }else if(type.equalsIgnoreCase("premium")){
            return new PremiumBurger();
        }else{
            System.out.println("Invalid Burger Type!");
            return null;
        }
    }
}




// Main Clas
public class SimpleFactory {
    public static void main(String[] args){
        String type = "standard";
        BurgerFactory myBurgerFactory = new BurgerFactory();
        Burger burger = myBurgerFactory.createBurger(type);

        if(burger != null){ 
            burger.prepare();
        }
    }
}
