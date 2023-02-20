import java.util.ArrayList;

public class ListSample {
  public static void main(String[] args) {
    ListSample sample = new ListSample();
    sample.checkArrayList5();
    
  }

  public void checkArrayList1() {
    ArrayList list1 = new ArrayList<>();
    list1.add(new Object());
    list1.add("ArrayListSample");
    list1.add(new Double(1));
  }

  public void checkArrayList3() {
    ArrayList<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    ArrayList<String> list2 = new ArrayList<>(list);
    for (String tmp : list2) {
      System.out.println(tmp);
    }
  }

  public void checkArrayList5() {
    ArrayList<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");
    int listSize = list.size();
    for (String tmp : list) {
      System.out.println(tmp);
    }
  }
}

