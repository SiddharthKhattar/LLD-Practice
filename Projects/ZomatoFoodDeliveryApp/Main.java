import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Simulating a happy flow
        // Create TomatoApp Object
        TomatoApp tomato = new TomatoApp();

        // Simulating a user coming in
        User user = new User(101, "Siddharth", "Delhi");
        System.out.println("User: " + user.getName() + " is active.");

        // User searches for restaurants by location
        List<Restaurant> restaurantList = tomato.searchRestaurants("Delhi");

        if (restaurantList.isEmpty()) {
            System.out.println("No restaurants found!");
            return;
        }

        System.out.println("Found Restaurants:");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(" - " + restaurant.getName());
        }

        // User selects a restaurant
        tomato.selectRestaurant(user, restaurantList.get(0));
        System.out.println("Selected restaurant: " + restaurantList.get(0).getName());

        // User adds items to the cart
        tomato.addToCart(user, "P1");
        tomato.addToCart(user, "P2");

        tomato.printUserCart(user);

        // User checkout the cart
        Order order = tomato.checkoutNow(user, "Delivery", new UpiPaymentStrategy("1234567890"));

        // User pays for the cart. If payment is successful, notification is sent.
        tomato.payForOrder(user, order);
    }
}

// ======================= APP FACADE =====================

class TomatoApp {

    public TomatoApp() {
        initializeRestaurants();
    }

    public void initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Bikaner", "Delhi");
        restaurant1.addMenuItem(new MenuItem("P1", "Chole Bhature", 120));
        restaurant1.addMenuItem(new MenuItem("P2", "Samosa", 15));

        Restaurant restaurant2 = new Restaurant("Haldiram", "Kolkata");
        restaurant2.addMenuItem(new MenuItem("P1", "Raj Kachori", 80));
        restaurant2.addMenuItem(new MenuItem("P2", "Pav Bhaji", 100));
        restaurant2.addMenuItem(new MenuItem("P3", "Dhokla", 50));

        Restaurant restaurant3 = new Restaurant("Saravana Bhavan", "Chennai");
        restaurant3.addMenuItem(new MenuItem("P1", "Masala Dosa", 90));
        restaurant3.addMenuItem(new MenuItem("P2", "Idli Vada", 60));
        restaurant3.addMenuItem(new MenuItem("P3", "Filter Coffee", 30));

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);
    }

    public List<Restaurant> searchRestaurants(String location) {
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant == null) {
            System.out.println("Please select a restaurant first.");
            return;
        }
        for (MenuItem item : restaurant.getMenu()) {
            if (item.getCode().equals(itemCode)) {
                user.getCart().addItem(item);
                break;
            }
        }
    }

    public Order checkoutNow(User user, String orderType, PaymentStrategy paymentStrategy) {
        return checkout(user, orderType, paymentStrategy, new NowOrderFactory());
    }

    public Order checkoutScheduled(User user, String orderType, PaymentStrategy paymentStrategy, String scheduleTime) {
        return checkout(user, orderType, paymentStrategy, new ScheduledOrderFactory(scheduleTime));
    }

    public Order checkout(User user, String orderType, PaymentStrategy paymentStrategy, OrderFactory orderFactory) {
        if (user.getCart().isEmpty()) return null;

        Cart userCart = user.getCart();
        Restaurant orderedRestaurant = userCart.getRestaurant();
        List<MenuItem> itemsOrdered = userCart.getItems();
        double totalCost = userCart.getTotalCost();

        Order order = orderFactory.createOrder(user, userCart, orderedRestaurant, itemsOrdered, paymentStrategy, totalCost, orderType);
        OrderManager.getInstance().addOrder(order);
        return order;
    }

    public void payForOrder(User user, Order order) {
        boolean isPaymentSuccess = order.processPayment();

        if (isPaymentSuccess) {
            NotificationService.notify(order);
            user.getCart().clear();
        }
    }

    public void printUserCart(User user) {
        System.out.println("Items in cart:");
        System.out.println("------------------------------------");
        for (MenuItem item : user.getCart().getItems()) {
            System.out.println(item.getCode() + " : " + item.getName() + " : ₹" + item.getPrice());
        }
        System.out.println("------------------------------------");
        System.out.println("Grand total : ₹" + user.getCart().getTotalCost());
    }
}

// ======================= MODELS =====================

class Cart {
    private Restaurant restaurant;
    private List<MenuItem> items = new ArrayList<>();

    public Cart() {
        restaurant = null;
    }

    public void addItem(MenuItem item) {
        if (restaurant == null) {
            System.out.println("Cart: Set a restauarant before adding items: ");
            return;
        }
        items.add(item);
    }

    public double getTotalCost() {
        double sum = 0;
        for (MenuItem it : items) {
            sum += it.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return restaurant == null || items.isEmpty();
    }

    public void clear() {
        items.clear();
        restaurant = null;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}

class User {
    private int userID;
    private String name;
    private String address;
    private Cart cart;

    public User(int userID, String name, String address) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.cart = new Cart();
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

class MenuItem {
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

class Restaurant {
    private static int nextRestaurantId = 0;
    private int restaurantId;
    private String name;
    private String location;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
        this.restaurantId = ++nextRestaurantId;
    }

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

    public boolean processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.pay(total);
            return true;
        } else {
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

    public void setItems(List<MenuItem> its) {
        items = its;
        total = 0;
        for (MenuItem i : items) {
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

class DeliveryOrder extends Order {
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

class PickupOrder extends Order {
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

interface OrderFactory {
    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                      PaymentStrategy paymentStrategy, double totalCost, String orderType);
}

class NowOrderFactory implements OrderFactory {
    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                             PaymentStrategy paymentStrategy, double totalCost, String orderType) {

        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
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

class ScheduledOrderFactory implements OrderFactory {
    private String scheduleTime;

    public ScheduledOrderFactory(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                             PaymentStrategy paymentStrategy, double totalCost, String orderType) {

        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
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

class UpiPaymentStrategy implements PaymentStrategy {
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

class NotificationService {
    public static void notify(Order order) {
        System.out.println("\nNotification: New " + order.getType() + " order placed!");
        System.out.println("---------------------------------------------");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getUser().getName());
        System.out.println("Restaurant: " + order.getRestaurant().getName());
        System.out.println("Items Ordered:");

        List<MenuItem> items = order.getItems();
        for (MenuItem item : items) {
            System.out.println("   - " + item.getName() + " (₹" + item.getPrice() + ")");
        }

        System.out.println("Total: ₹" + order.getTotal());
        System.out.println("Scheduled For: " + order.getScheduled());
        System.out.println("Payment: Done");
        System.out.println("---------------------------------------------");
    }
}

class TimeUtils {
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
    }
}