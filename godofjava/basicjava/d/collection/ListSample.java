package d.collection;

import java.util.ArrayList;

public class ListSample {
  public static void main(String[] args) {
    ListSample sample = new ListSample();
    sample.checkArrayList8();

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

  public void checkArrayList6() {
    ArrayList<String> list = new ArrayList<>();
    list.add("A");
    String[] strList = list.toArray(new String[0]);
    System.out.println(strList[0]);
  }

  public void checkArrayList7() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    String[] tempArray = new String[] {"wow", "woah", null};
    System.out.println(tempArray.length);
    // String[] tempArray = {"wow", "yo", "hey", "sheesh"};
    String[] strList = list.toArray(tempArray);
    for (String tmp : strList) {
      System.out.println(tmp);
    }
    System.out.println(strList.length);
  }

  public void checkArrayList8() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("A");
//    System.out.println("removed " + list.remove(0));
//    System.out.println(list.remove("A"));
    ArrayList<String> temp = new ArrayList<>();
    temp.add("A");
//    temp.add("B");
    list.removeAll(temp);
    for (int loop = 0; loop < list.size(); loop++) {
      System.out.println("list.get(" + loop + ")= " + list.get(loop));
    }
    String a = list.set(1, "wow");
    System.out.println("a = " + a);
    for (int loop = 0; loop < list.size(); loop++) {
      System.out.println("list.get(" + loop + ")= " + list.get(loop));
    }
  }


}


