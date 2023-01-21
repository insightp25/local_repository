package ch12;

/*

import java.util.ArrayList;

class Fruit2 implements Eatable { public String toString() { return "Fruit"; } }
class Apple2 extends Fruit2     { public String toString() { return "Apple"; } }
class Grape2 extends Fruit2     { public String toString() { return "Grape"; } }
class Toy2                      { public String toString() { return "Toy"  ; } }

interface Eatable {}

public class FruitBoxEx2 {
    public static void main(String[] args) {
        FruitBox2<Fruit2> fruitBox2 = new FruitBox2<Fruit2>();
        FruitBox2<Apple2> appleBox2 = new FruitBox2<Apple2>();
        FruitBox2<Grape2> grapeBox2 = new FruitBox2<Grape2>();
//        FruitBox2<Toy2> toyBox2     = new FruitBox2<Toy2>();

        fruitBox2.add(new Fruit2());
        fruitBox2.add(new Apple2());
        fruitBox2.add(new Grape2());

        appleBox2.add(new Apple2());
//        appleBox2.add(new Grape2());

        grapeBox2.add(new Grape2());
//        grapeBox2.add(new Apple2());

        System.out.println("fruitBox2: " + fruitBox2);
        System.out.println("appleBox2: " + appleBox2);
        System.out.println("grapeBox2: " + grapeBox2);
    }
}

class FruitBox2<T extends Fruit2 & Eatable> extends Box2<T>{}

class Box2<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add(T item)    { list.add(item);       }
    T get(int i)        { return list.get(i);   }
    int size()          { return list.size();   }
    public String toString() { return list.toString(); }
}

*/
