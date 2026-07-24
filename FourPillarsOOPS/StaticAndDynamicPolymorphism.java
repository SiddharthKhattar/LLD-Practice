// Base Car Class
abstract class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }

    // Common Methods for all the cars
    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + " :  Engine has started");
    }

    public void stopEngine(){
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " :  Engine turned off");
    }

    public abstract void accelerate(); // Abstract method for dynamic polymorphism
    public abstract void accelerate(int speed); // Abstract method for static polymorphism
    public abstract void brake();// Abstract method for dynamic polymorphism
}


class ManualCar extends Car{
    private int currentGear; // specific to ManualCar

    public ManualCar(String brand, String model){
        super(brand, model);
        this.currentGear = 0;
    }

    // Specialized method for Manual Car
    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " :  Shifted to gear " + currentGear);
    }

    @Override
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

    @Override 
    public void brake(){
        currentSpeed -= 20;
        if(currentSpeed < 0) currentSpeed = 0;
         System.out.println(brand + " " + model + " :  Braking! Speed is now " + currentSpeed + "km/h");
    }
}


class ElectricCar extends Car{
    private int batteryLevel; // specific to Electric Car

    public ElectricCar(String brand, String model){
        super(brand, model);
        this.batteryLevel = 100;
    }

    public void chargeBattery(){
        batteryLevel = 100;
        System.out.println(brand + " " + model + " :  Battery Fully Charged! at " + batteryLevel + "%");
    }

    // Overriding accelerate - Dynamic Polymorphism
    @Override
    public void accelerate(){
        if(!isEngineOn){
            System.out.println(brand + " " + model + " :  Cannot accelerate! Engine is off!");
            return ;
        }
        if(batteryLevel <= 0){
            System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h. Battery at " + batteryLevel + "%");
            return ;
        }
        currentSpeed += 20;
        batteryLevel -= 10;
        System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h");
        System.out.println(brand + " " + model + " :  Battery Level is " + batteryLevel + "%");
    }

    // static polymorphism - overriding accelerate
    public void accelerate(int speed){
    if(!isEngineOn){
        System.out.println(brand + " " + model + " :  Cannot accelerate! Engine is off!");
        return ;
    }
    if(batteryLevel <= 0){
        System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h. Battery at " + batteryLevel + "%");
        return ;
    }
    batteryLevel -= + speed;
    currentSpeed += speed;
    System.out.println(brand + " " + model + " :  Accelerating to " + currentSpeed + "km/h");
    System.out.println(brand + " " + model + " :  Battery Level is " + batteryLevel + "%");
    }

    @Override 
    public void brake(){
        currentSpeed -= 15;
        if(currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " :  Regenerative Braking! Speed is now " + currentSpeed + "km/h");
        System.out.println(brand + " " + model + " :  Battery Level is " + batteryLevel + "%");
    }

}




public class StaticAndDynamicPolymorphism {
    public static void main(String[] args) {
        ManualCar myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();
        myManualCar.shiftGear(1); // Specific to Manual Car
        myManualCar.accelerate();
        myManualCar.brake();
        myManualCar.stopEngine();

        System.out.println("----------------------");

        ElectricCar myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.chargeBattery(); // Specific to Electric Car
        myElectricCar.startEngine();
        myElectricCar.accelerate();
        myElectricCar.brake();
        myElectricCar.stopEngine();
    }  
}
