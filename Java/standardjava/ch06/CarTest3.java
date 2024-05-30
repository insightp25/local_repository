package ch06;

class Car {
    String color;
    String gearType;
    int door;

    Car() {
        this("white", "auto", 4);
    }

    Car(String color, String gearType, int door) {
        this.color = color;
        this.gearType = gearType;
        this.door = door;
    }

    Car(Car c) {
        color       = c.color;
        gearType    = c.gearType;
        door        = c.door;
    }
}


public class CarTest3 {
    public static void main(String[] args) {
        Car c1 = new Car();
        Car c2 = new Car(c1);
        System.out.println(c1.color + c1.gearType + c1.door);
        System.out.println(c2.color + c2.gearType + c2.door);

        c1.door = 2;
        System.out.println(c1.color + c1.gearType + c1.door);
        System.out.println(c2.color + c2.gearType + c2.door);

        System.out.println(Math.random());
        System.out.println((int)(Math.random() * 10));
        System.out.println((int)(Math.random() * 10) + 1);
    }
}
