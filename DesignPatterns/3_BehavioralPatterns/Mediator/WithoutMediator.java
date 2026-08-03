import java.util.*;

// Each user knows all the other directly
// If you have N users, you wind up wiring N * (N - 1) / 2 connections,
// and every new feature (mute, private send, logging...) lives in User too

class User{
    private String name;
    private List<User> peers;
    private List<String> mutedUsers;

    public User(String n){
        name = n;
        peers = new ArrayList<>();
        mutedUsers = new ArrayList<>();
    }

    // must manually connect every pair -> N^2 wiring
    public void addPeer(User u){
        peers.add(u);
    }

    // duplication: everyone has its own mute list
    public void mute(String userToMute){
        mutedUsers.add(userToMute);
    }

    public void send(String msg){
        System.out.println("[" + name + "broadcasts]:" + msg);
        for (User peer: peers){

            // if they have muted me dont send
            if(!peer.isMuted(name)){
                peer.recieve(name, msg);
            }
        }
    }

    public boolean isMuted(String userName){
        for(String name: mutedUsers){
            if(name.equals(userName)){
                return true;
            }
        }
        return false;
    }

    // private send - duplicated in evert class
    public void sendTo(User target, String msg){
        System.out.println("[" + name + "->" + target.name + "]:" + msg);
        if(!target.isMuted(name)){
            target.recieve(name, msg);
        }
    }

    public void recieve(String from, String msg){
        System.out.println("    " + name + " got from " + from + ": "+ msg);
    }
}




public class WithoutMediator {
    public static void main(String[] args){
        // create users
        User user1 = new User("Rohan");
        User user2 = new User("Neha");
        User user3 = new User("Mohan");


        // wire up peers (each knows each other) -> n * n(n + 1)/2 connections
        user1.addPeer(user2);
        user2.addPeer(user3);

        user1.addPeer(user3);
        user3.addPeer(user1);

        user2.addPeer(user3);
        user3.addPeer(user2);

        // mute example: Mohan mutes Rohan
        user1.mute("Mohan");

        // broadcast
        user1.send("Hello Everyone!");

        // private
        user3.sendTo(user2, "Hey Neha!");
    }
}
