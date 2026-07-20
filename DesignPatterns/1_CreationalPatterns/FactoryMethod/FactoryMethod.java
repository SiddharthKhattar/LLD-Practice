// Product Interface and subclasses
interface Burger{
    void prepare();
}

class BasicBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Basic Burger with Bun, Patty and Ketchup!");
    }
}

class BasicVegBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Basic Veg Burger with Bun, Aalo Patty and Ketchup!");
    }
}

class StandardBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Standard Burger with Bun, Patty, Cheese Slice and Ketchup!");
    }
}

class StandardVegBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Standard Burger with Bun, Aalo Patty, Cheese Slice and Ketchup!");
    }
}

class PremiumBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Premium Burger with Bun, Double Deluxe Patty, Cheese Slice, Lettuce and BBQ Sauce!");
    }
}

class PremiumVegBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Premium Burger with Bun, Double Aalo Patty, Cheese Slice, Lettuce and BBQ Sauce!");
    }
}

class PremiumWheatBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Premium Wheat Burger with Gourmet Bun, Premium Mutton Patty, Cheese Slice, Lettuce and BBQ Sauce!");
    }
}

class PremiumWheatVegBurger implements Burger{
    public void prepare(){
        System.out.println("Preparing Premium Wheat Burger with Gourmet Bun, Premium Paneer Patty, Cheese Slice, Lettuce and BBQ Sauce!");
    }
}

interface BurgerFactory{
    Burger createBurger(String type);
}

class SinghBurger implements BurgerFactory{
    public Burger createBurger(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicBurger();
        }else if(type.equalsIgnoreCase("standard")){
            return new StandardBurger();
        }else if(type.equalsIgnoreCase("premium")){
            return new PremiumBurger();
        }else if(type.equalsIgnoreCase("premiumWheat")){
            return new PremiumWheatBurger();
        }
        else{
            System.out.println("Invalid Burger Type!");
            return null;
        }
    }
}

class VeganKingBurger implements BurgerFactory{
    public Burger createBurger(String type){
        if(type.equalsIgnoreCase("basic")){
            return new BasicVegBurger();
        }else if(type.equalsIgnoreCase("standard")){
            return new StandardVegBurger();
        }else if(type.equalsIgnoreCase("premium")){
            return new PremiumVegBurger();
        }else if(type.equalsIgnoreCase("premiumWheat")){
            return new PremiumWheatVegBurger();
        }else{
            System.out.println("Invalid Burger Type!");
            return null;
        }
    }
}




public class FactoryMethod {
    public static void main(String[] args){
        String type = "basic";

        BurgerFactory myFactory = new SinghBurger();
        Burger burger = myFactory.createBurger(type);

        BurgerFactory myVegFactory = new VeganKingBurger();
        Burger vegBurger = myVegFactory.createBurger(type);

        if(burger != null) burger.prepare();
        if(vegBurger != null) vegBurger.prepare();
    }
}
