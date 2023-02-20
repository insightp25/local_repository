public class Test {
  public int score;

  public Test(int score) {
    this.score = score;
  }

  public static void main(String[] args) {
    Test t1 = new Test(1);
    Test t2 = new Test(1);

    System.out.println(t1.equals(t2));
  }
}
