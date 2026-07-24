interface TwoDShape{
    double area();
}

interface ThreeDShape{
    double area();
    double volume();
}

class Square implements TwoDShape{
    private double side;

    public Square(double s){
        this.side = s;
    }

    @Override
    public double area(){
        return side * side;
    }
}

class Rectangle implements TwoDShape{
    private double length, width;

    public Rectangle(double l, double w){
        this.length = l;
        this.width = w;
    }

    @Override
    public double area(){
        return length * width;
    }
}

class Cube implements ThreeDShape{
    private double side;

    public Cube(double s){
        this.side = s;
    }

    @Override
    public double area(){
        return 6 * side;
    }

    @Override
    public double volume(){
        return side * side * side;
    }
}


public class ISPFollowed {
    public static void main(String[] args) {
        TwoDShape square    = new Square(5);
        TwoDShape rectangle = new Rectangle(4, 6);
        ThreeDShape cube     = new Cube(3);

        System.out.println("Square Area: "    + square.area());
        System.out.println("Rectangle Area: " + rectangle.area());
        System.out.println("Cube Area: "      + cube.area());
        System.out.println("Cube Volume: "    + cube.volume());
    }
}
