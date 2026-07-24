
/*
Encapsulation says 2 things:
1. An Object's Characteristics and its behaviour are encapsulated together
within that Object.
2. All the characteristics or behaviours are not for everyone to access.
Object should provide data security.

We follow above 2 pointers about Object of real world in programming by:
1. Creating a class that act as a blueprint for Object creation. Class contain
all the characteristics (class variable) and behaviour (class methods) in one block,
encapsulating it together.
2. We introduce access modifiers (public, private, protected, default) etc to provide data
security to the class members.
*/

class SportsCar {
    private String brand;
    private String model;
    private boolean isEngineOn = false;
    private int currentSpeed = 0;
    private int currentGear = 0;    

    public SportsCar(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    // Introducing new variable to explain setters
    private String tyreCompany;

    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " :  Shifted to gear " + currentGear);
    }

    public int getSpeed(){
        return currentSpeed;
    }

    public String getTyreCompany(){
        return tyreCompany;
    }

    public void setTyreCompany(String tyreCompany){
        this.tyreCompany = tyreCompany;
    }

    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + " :  Engine has started with a roar!");
    }

    public void stopEngine(){
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " :  Engine turned off");
    }

    public void accelerate(){
        if(!isEngineOn){
            System.out.println(brand + " " + model + " :  Cannot accelerate! Engine is off!");
            return ;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h");
    }

    public void brake(){
        currentSpeed -= 20;
        if(currentSpeed < 0) currentSpeed = 0;
         System.out.println(brand + " " + model + " :  Braking! Speed is now " + currentSpeed + "km/h");
    }
}





public class Encapsulation {
    public static void main(String[] args) {
        SportsCar car = new SportsCar("Lamborghini", "Gallardo");
        car.startEngine();
        car.shiftGear(1); 
        car.accelerate();
        car.shiftGear(2); 
        car.accelerate();
        car.brake();
        car.stopEngine();

        //Setting arbitrary value to speed.
        //car.currentSpeed = 500;
       // System.out.println("Current Speed of My Sports Car is set to " + car.currentSpeed);

       System.out.println("Current Speed of My Sports Car is " + car.getSpeed());
    }
}
