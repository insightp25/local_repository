package ch07;

class InstanceofTest {
    public static void main(String[] args) {
        FireEngine fe = new FireEngine();

        if (fe instanceof FireEngine) {
            System.out.println("this FireEngine instance.");
        }

        if (fe instanceof ch07.Car) {
            System.out.println("this Car instance.");
        }

        if (fe instanceof Object) {
            System.out.println("this Object instance.");
        }

        System.out.println(fe.getClass().getName());
    }
}

class Car {}
class FireEngine extends ch07.Car {}
