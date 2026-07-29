// Implementation Hierarchy: Engine Interface (LLL)
interface Engine{
    void start();
}

// Concrete Implementaors (LLL)
class PetrolEngine implements Engine{
    @Override
    public void start(){
        System.out.println("Petrol engine started!");
    } 
}

class ElectricEngine implements Engine{
    @Override
    public void start(){
        System.out.println("Electric engine started silently!");
    } 
}

class DieselEngine implements Engine{
    @Override
    public void start(){
        System.out.println("Diesel engine started!");
    } 
}




// Abstraction Hierarchy: Car (HLL)
abstract class Car{
    protected Engine engine;
    public Car(Engine e){
        this.engine = e;
    }
    public abstract void drive();
} 

// Refined Abstraction: Sedan
class Sedan extends Car{
    public Sedan(Engine e){
        super(e);
    }

    @Override 
    public void drive(){
        engine.start();
        System.out.println("Sedan is being driven.");
    }
}

class SUV extends Car{
    public SUV(Engine e){
        super(e);
    }

    @Override 
    public void drive(){
        engine.start();
        System.out.println("SUV is being driven.");
    }
}




public class BridgePattern {
    public static void main(String[] args) {
        // Create Engine implementations
        Engine petrolEng = new PetrolEngine();
        Engine dieselEng = new DieselEngine();
        Engine electricEng = new ElectricEngine();

        // Create Car abstractions, injecting Engine implementations
        Car mySedan = new Sedan(petrolEng);
        Car mySUV = new SUV(electricEng);
        Car yourSUV = new SUV(dieselEng);

        // Use the cars
        mySedan.drive();   // Petrol engine + Sedan
        mySUV.drive();     // Electric engine + SUV
        yourSUV.drive();   // Diesel engine + SUV

        // No explicit cleanup needed in Java
    }
}
