import java.util.ArrayList;
import java.util.List;

interface ISubscriber{
    void update();
}

interface IChannel{
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers();
}

class Channel implements IChannel{
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name){
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override 
    public void subscribe(ISubscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }

    @Override
    public void unsubscribe(ISubscriber subscriber){
            subscribers.remove(subscriber);
        }

    @Override 
    public void notifySubscribers(){
        for(ISubscriber sub: subscribers){
            sub.update();
        }
    }

    public void uploadVideo(String title){
        latestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    public String getVideoData(){
        return "\nCheckout our new Video: " + latestVideo + "\n";
    }
}


// Concrete Observer: Represents a subscriber to the channel
class Subscriber implements ISubscriber{
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel){
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update(){
        System.out.println("Hey " + name + "," + channel.getVideoData());
    }
}


public class ObserverDesignPattern {
    public static void main(String[] args){
        // Create a channel and subscribers
        Channel channel = new Channel("PlayStation");

        Subscriber sub1 = new Subscriber("Sam", channel);
        Subscriber sub2 = new Subscriber("Bruce", channel);

        // Sam and Bruce subscribe to PlayStation
        channel.subscribe(sub1);
        channel.subscribe(sub2);

        // Uploading a video: Both Bruce and Sam will be notified
        channel.uploadVideo("Spider-Man 3 PS5 Trailer");

        // Sam unsubscribes but Bruce remains subscribed
        channel.unsubscribe(sub1);

        // Uploading a video: Only Sam will be notified
        channel.uploadVideo("Venom PS5 Trailer");
    }
}
