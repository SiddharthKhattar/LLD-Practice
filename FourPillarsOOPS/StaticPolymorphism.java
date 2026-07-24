/*
Static Polymorphism (Compile-time polymorphism) in real life says that
the same action can behave differently depending on the input parameters.
For example, a Manual car can accelerate by a fixed amount or by a
specific amount you request. In programming, we achieve this via method
overloading: multiple methods with the same name but different signatures.
*/

class Car {
    private String brand;
    private String model;
    private boolean isEngineOn;
    private int currentSpeed;
    private int currentGear;    

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
        this.currentGear = 0;
    }

    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " :  Shifted to gear " + currentGear);
    }

    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + " :  Engine has started");
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

    // static polymorphism - overriding accelerate
    public void accelerate(int speed){
    if(!isEngineOn){
        System.out.println(brand + " " + model + " :  Cannot accelerate! Engine is off!");
        return ;
    }
    currentSpeed += speed;
    System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h");
    }

    public void brake(){
        currentSpeed -= 20;
        if(currentSpeed < 0) currentSpeed = 0;
         System.out.println(brand + " " + model + " :  Braking! Speed is now " + currentSpeed + "km/h");
    }
}





public class StaticPolymorphism {
    public static void main(String[] args) {
        Car car = new Car("Suzuki", "WagonR");
        car.startEngine();
        car.shiftGear(1); // Specific to Manual Car
        car.accelerate();
        car.brake();
        car.stopEngine();
    }
}
