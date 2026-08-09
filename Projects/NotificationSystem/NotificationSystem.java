import java.util.*;

// ───────────────────────────────────────────────────────────
// Notification & Decorators
// ───────────────────────────────────────────────────────────

interface INotification{
    String getContent();
}

// Concrete Notification: Simple Text Notification
class SimpleNotification implements INotification{
    private String text;

    public SimpleNotification(String msg){
        this.text = msg;
    }

    public String getContent(){
        return text;
    }
}

// Abstract Decorator: wraps a Notification object
abstract class INotificationDecorator implements INotification{
    protected INotification notification;

    public INotificationDecorator(INotification n){
        this.notification = n;
    }
}

// Decorator to add a timestamp to the content
class TimestampDecorator extends INotificationDecorator{
    public TimestampDecorator(INotification n){
        super(n);
    }

    public String getContent(){
        return "[2026-06-07 06:07:67]" + notification.getContent();
    }
}

// Decorator to append a signature to the content
class SignatureDecorator extends INotificationDecorator{
    private String signature;

    public SignatureDecorator(INotification n, String sig){
        super(n);
        this.signature = sig;
    }

    public String getContent(){
        return notification.getContent() + "\n--" + signature + "\n\n";
    }
}

// ───────────────────────────────────────────────────────────
// Observer Pattern Components
// ───────────────────────────────────────────────────────────

interface IObserver{
    void update();
}

interface IObservable{
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

// Concrete Observable
class NotificationObservable implements IObservable{
    private List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification = null;

    public void addObserver(IObserver obs){
        observers.add(obs);
    }

    public void removeObserver(IObserver obs){
        observers.remove(obs);
    }

    public void notifyObservers(){
        for(IObserver obs: observers){
            obs.update();
        }
    }

    public void setNotification(INotification notification){
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getNotification(){
        return currentNotification;
    }

    public String getNotificationContent(){
        return currentNotification.getContent();
    }
}





// ───────────────────────────────────────────────────────────
// NotificationService
// ───────────────────────────────────────────────────────────


// NotificationService manages notification, keeps tract of notification
// Any client code will interact with this service



// Singleton class
class NotificationService{
    private NotificationObservable observable;
    private static NotificationService instance = null;
    private List<INotification> notifications = new ArrayList<>();

    private NotificationService(){
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(instance == null){
            instance = new NotificationService();
        }
        return instance;
    }

    // Expose the observable so observers can attach
    public NotificationObservable getObservable(){
        return observable;
    }

    // Creates a new notification and notifies observers
    public void sendNotification(INotification notification){
        notifications.add(notification);
        observable.setNotification(notification);
    }


}



// ───────────────────────────────────────────────────────────
// ConcreteObservers
// ───────────────────────────────────────────────────────────

class Logger implements IObserver{
    private NotificationObservable notificationObservable;

    public Logger(){
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public Logger(NotificationObservable observable){
        notificationObservable.addObserver(this);
        this.notificationObservable = observable;
    }

    public void update(){
        System.out.println("Logging new Notification: \n " + notificationObservable.getNotificationContent());
    }
} 




// ───────────────────────────────────────────────────────────
// Strategy Pattern Components (Concrete Observer 2)
// ───────────────────────────────────────────────────────────

interface INotificationStrategy{
    void sendNotification(String content);
}

class EmailStrategy implements INotificationStrategy{
    private String emailID;
    public EmailStrategy(String emailID){
        this.emailID = emailID;
    }

    public void sendNotification(String content){
        System.out.println("Sending E-Mail Notification to: " + emailID + "\n" + content);
    }
}

class SMSStrategy implements INotificationStrategy{
    private String mobileNumber;
    public SMSStrategy(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public void sendNotification(String content){
        System.out.println("Sending SMS Notification to: " + mobileNumber + "\n" + content);
    }
}

class PopUpStrategy implements INotificationStrategy{
    public void sendNotification(String content){
        System.out.println("Sending Popup Notification to: " + content);
    }
}


class NotificationEngine implements IObserver{
    private NotificationObservable notificationObservable;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine(){
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public NotificationEngine(INotificationStrategy ns){
        this.notificationStrategies.add(ns);
    }

    public void addNotificationStrategy(INotificationStrategy ns){
        this.notificationStrategies.add(ns);
    }

    public void update(){
        String notificationContent = notificationObservable.getNotificationContent();
        for(INotificationStrategy strategy: notificationStrategies){
            strategy.sendNotification(notificationContent);
        }
    }
}



public class NotificationSystemUpdated {
    public static void main(String[] args){

        // Create Notification Service
        NotificationService notificationService = NotificationService.getInstance();

        // Create Logger Observer
        Logger logger = new Logger();

        // Create NotificationEngine observers
        NotificationEngine notificationEngine = new NotificationEngine();

        notificationEngine.addNotificationStrategy(new EmailStrategy("ayyyy@gmail.com"));
        notificationEngine.addNotificationStrategy(new SMSStrategy("+91 99999 99544"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());

        INotification notification = new SimpleNotification("Your order has been shipped");
        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");

        notificationService.sendNotification(notification);

    }
}
