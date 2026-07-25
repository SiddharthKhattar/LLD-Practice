import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


// ======================= APP FACADE =====================






















// ======================= MODELS =====================

class Cart{
    private Restaurant restaurant;
    private List<MenuItem> items = new ArrayList<>();

    public Cart(){
        restaurant = null;
    }

    public void addItem(MenuItem item){
        if(restaurant == null){
            System.out.println("Cart: Set a restauarant before adding items: ");
            return ;
        }
        items.add(item);
    }

    public double getTotalCost(){
        double sum = 0;
        for(MenuItem it: items){
            sum += it.getPrice();
        }
        return sum;
    }

    public boolean isEmpty(){
        return restaurant == null || items.isEmpty();
    }

    public void clear(){
        items.clear();
        restaurant = null;
    }

    public void setRestaurant(Restaurant r){
        restaurant = r;
    }

    public Restaurant getRestauarant(){
        return restaurant;
    }

    public List<MenuItem> getItems(){
        return items;
    }

}

class User{
    private int userID;
    private String name;
    private String address;
    private Cart cart;

    public User(int userID, String name, String address, Cart cart){
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public Cart getCart() {
        return cart;
    }

}

class MenuItem{
    private String code;
    private String name;
    private int price;

public MenuItem(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String c) {
        code = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int p) {
        price = p;
    }

}

class Restaurant{
    private static int nextRestaurantId = 0;
    private int restaurantId;
    private String name;
    private String location;
    private List<MenuItem> menu = new ArrayList<>();

public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

}

abstract class Order {
    private static int nextOrderId = 0;

    protected int orderId;
    protected User user;
    protected double total;
    protected String scheduled;
    protected Restaurant restaurant;
    protected List<MenuItem> items;
    protected PaymentStrategy paymentStrategy;

    public Order() {
        this.user = null;
        this.restaurant = null;
        this.paymentStrategy = null;
        this.total = 0.0;
        this.scheduled = "";
        this.orderId = ++nextOrderId;
    }

    public boolean processPayment(){
        if(paymentStrategy != null){
            paymentStrategy.pay(total);
            return true;
        }else{
            System.out.println("Please choose a payment method first ");
            return false;
        }
    }

    public abstract String getType();

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setUser(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setItems(List<MenuItem> its){
        items = its;
        total = 0;
        for(MenuItem i: items){
            total += i.getPrice();
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setPaymentStrategy(PaymentStrategy p) {
        paymentStrategy = p;
    }

    public void setScheduled(String s) {
        scheduled = s;
    }

    public String getScheduled() {
        return scheduled;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



}



class DeliveryOrder extends Order { //[cite: 3]
    private String userAddress;

    public DeliveryOrder() {
        userAddress = "";
    }

    @Override
    public String getType() {
        return "Delivery";
    }

    public void setUserAddress(String addr) {
        userAddress = addr;
    }

    public String getUserAddress() {
        return userAddress;
    }
}

class PickupOrder extends Order { //[cite: 13]
    private String restaurantAddress;

    public PickupOrder() {
        restaurantAddress = "";
    }

    @Override
    public String getType() {
        return "Pickup";
    }

    public void setRestaurantAddress(String addr) {
        restaurantAddress = addr;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }
}


// ======================= MANAGERS =====================


    class OrderManager { 
        private List<Order> orders = new ArrayList<>();
        private static OrderManager instance = null;

        private OrderManager() {
            // Private Constructor
        }

        public static OrderManager getInstance() {
            if (instance == null) {
                instance = new OrderManager();
            }
            return instance;
        }

        public void addOrder(Order order) {
            orders.add(order);
        }

        public void listOrders() {
            System.out.println("\n--- All Orders ---");
            for (Order order : orders) {
                System.out.println(order.getType() + " order for " + order.getUser().getName()
                        + " | Total: ₹" + order.getTotal()
                        + " | At: " + order.getScheduled());
            }
        }
    }


class RestaurantManager { 
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;

    private RestaurantManager() {
        // private constructor
    }

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public List<Restaurant> searchByLocation(String loc) {
        List<Restaurant> result = new ArrayList<>();
        loc = loc.toLowerCase();
        for (Restaurant r : restaurants) {
            String rl = r.getLocation().toLowerCase();
            if (rl.equals(loc)) {
                result.add(r);
            }
        }
        return result;
    }
}




// ======================= FACTORIES =====================


interface OrderFactory{
    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems, 
        PaymentStrategy paymentStrategy, double totalCost, String orderType);
}


class NowOrderFactory implements OrderFactory{
    @Override 
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems, 
        PaymentStrategy paymentStrategy, double totalCost, String orderType){

            Order order = null;

            if(orderType.equals("Delivery")){
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setUserAddress(user.getAddress());
                order = deliveryOrder;
            }else{
                PickupOrder pickupOrder = new PickupOrder();
                pickupOrder.setRestaurantAddress(restaurant.getLocation());
                order = pickupOrder;
            }

            order.setUser(user);
            order.setRestaurant(restaurant);
            order.setItems(menuItems);
            order.setScheduled(TimeUtils.getCurrentTime());
            order.setPaymentStrategy(paymentStrategy);
            order.setTotal(totalCost);

            return order;
        }
    }


class ScheduledOrderFactory implements OrderFactory{
    private String scheduleTime;

    public ScheduledOrderFactory(String scheduleTime){
        this.scheduleTime = scheduleTime;
    }

    @Override 
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems, 
        PaymentStrategy paymentStrategy, double totalCost, String orderType){

            Order order = null;

            if(orderType.equals("Delivery")){
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setUserAddress(user.getAddress());
                order = deliveryOrder;
            }else{
                PickupOrder pickupOrder = new PickupOrder();
                pickupOrder.setRestaurantAddress(restaurant.getLocation());
                order = pickupOrder;
            }

            order.setUser(user);
            order.setRestaurant(restaurant);
            order.setItems(menuItems);
            order.setScheduled(scheduleTime);
            order.setPaymentStrategy(paymentStrategy);
            order.setTotal(totalCost);

            return order;
        }
    }




// ======================= STRATEGIES =====================

    interface PaymentStrategy {
        void pay(double amount);
    }

    class CreditCardPaymentStrategy implements PaymentStrategy { 
        private String cardNumber;

        public CreditCardPaymentStrategy(String card) {
            this.cardNumber = card;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid ₹" + amount + " using Credit Card (" + cardNumber + ")");
        }
    }

    class UpiPaymentStrategy implements PaymentStrategy { //[cite: 19]
        private String mobile;

        public UpiPaymentStrategy(String mob) {
            this.mobile = mob;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid ₹" + amount + " using UPI (" + mobile + ")");
            }
    }


// ======================= SERVICES & UTILS =====================


public class Main {
    public static void main(String[] args){
        // Simulating a happy flow
        // Create TomatoApp Object
        TomatoApp tomato = new TomatoApp();

        // Simulating a user coming in
        User user = new User(101, "Siddharth", "Delhi");
        System.out.println("User: " + user.getName() + " is active.");

        // User searches for restaurants by location

    }
}


class TimeUtils { //[cite: 17]
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
    }
}