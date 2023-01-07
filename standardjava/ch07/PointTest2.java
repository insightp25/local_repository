package ch07;

class PointTest2 {
    public static void main(String[] args) {
        Point3D2 p3 = new Point3D2();
        String location = p3.getLocation();
        System.out.println(location);
    }
}

class Point2 {
    int x, y;

    Point2() {
    }

    Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    String getLocation() {
        return "x: " + x + ", y: " + y;
    }
}

class Point3D2 extends Point{
    int z;

//    Point3D2() {
//        this(100, 200, 300);
//    }
    Point3D2() {
    }

    Point3D2(int x, int y, int z) {
//        super(x, y);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    String getLocation() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}