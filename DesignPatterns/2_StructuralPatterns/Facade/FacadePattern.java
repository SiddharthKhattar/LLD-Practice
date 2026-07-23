// Subsystems
class PowerSupply{
    public void providePower(){
        System.out.println("Power Supply: Providing Power..");
    }
}

class CoolingSystems{
    public void startFans(){
        System.out.println("Cooling System: Fans Started..");
    }
}

class CPU{
    public void initialize(){
        System.out.println("CPU: Initialization Started..");
    }
}

class Memory{
    public void selfTest(){
        System.out.println("Memory: Self-Test Passed..");
    }
}

class HardDrive{
    public void spinUp(){
        System.out.println("Hard Drive: Spinning Up..");
    }
}

class BIOS{
    public void boot(CPU cpu, Memory memory){
        System.out.println("BIOS: Booting CPU and Memory Checks..");
        cpu.initialize();
        memory.selfTest();
    }
}

class OperatingSystem{
    public void load(){
        System.out.println("OS: Loading into Memory");
    }
}

// Facade
class ComputerFacade{
    private PowerSupply powerSupply = new PowerSupply();
    private CoolingSystems coolingSystem = new CoolingSystems();
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private HardDrive hardDrive = new HardDrive();
    private BIOS bios = new BIOS();
    private OperatingSystem os = new OperatingSystem();

    public void startComputer(){
        System.out.println("------------Starting Computer---------");
        
        powerSupply.providePower();
        coolingSystem.startFans();
        
        bios.boot(cpu, memory);

        hardDrive.spinUp();
        os.load();

        System.out.println("Computer Booted Successfully!");
    }
}



public class FacadePattern {
    public static void main(String[] args){
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}


