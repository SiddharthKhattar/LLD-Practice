// Strategy Interface for Walk
interface WalkableRobot{
    void walk();
}

// Concrete Strategies for walk
class NormalWalk implements WalkableRobot{
    public void walk(){
        System.out.println("Walking normally...");
    }
}

class NoWalk implements WalkableRobot{
    public void walk(){
        System.out.println("Cannot Walk");
    }
}

// Strategy Interface for Talk
interface TalkableRobot{
    void talk();
}

// Concrete Strategies for Talk
class NormalTalk implements TalkableRobot{
    public void talk(){
        System.out.println("Talking Normally..");
    }
}

class NoTalk implements TalkableRobot{
    public void talk(){
        System.out.println("Cannot Talk!");
    }
}

// Strategy Interface for Fly
interface FlyableRobot{
    void fly();
}


class NormalFly implements FlyableRobot{
    public void fly(){
        System.out.println("Flying like Superman!");
    }
}

class NoFly implements FlyableRobot{
    public void fly(){
        System.out.println("Cannot Fly!");
    }
}


// Robot Base Class

abstract class Robot{
    protected WalkableRobot walkBehaviour;
    protected TalkableRobot talkBehaviour;
    protected FlyableRobot flyBehaviour;

    public Robot(WalkableRobot w, TalkableRobot t, FlyableRobot f){
        this.walkBehaviour = w;
        this.talkBehaviour = t;
        this.flyBehaviour = f; 
    }

    public void walk(){
        walkBehaviour.walk();
    }

    public void talk(){
        talkBehaviour.talk();
    }

    public void fly(){
        flyBehaviour.fly();
    }

    // Abstract method for subclasses
    public abstract void projection(); 
}

// Concrete Robot Types
class CompanionRobot extends Robot{
    public CompanionRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f){
        super(w, t, f);
    }

    public void projection(){
        System.out.println("Displaying friendly companion features...");
    }
}

class WorkerRobot extends Robot{
    public WorkerRobot(WalkableRobot w, TalkableRobot t, FlyableRobot f){
        super(w, t, f);
    }

    public void projection(){
        System.out.println("Displaying worker efficiency stats");
    }
}


// Main Function

public class StrategyDesignPattern{
    public static void main(String[] args){
        Robot robot1 = new CompanionRobot(new NormalWalk(), new NormalTalk(), new NoFly());
        robot1.walk();
        robot1.talk();
        robot1.fly();
        robot1.projection();

        System.out.println("------------");

        Robot robot2 = new WorkerRobot(new NoWalk(), new NoTalk(), new NormalFly());
        robot2.walk();
        robot2.talk();
        robot2.fly();
        robot2.projection();        
    }
}