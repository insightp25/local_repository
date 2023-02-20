import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    List<Object> daList = new ArrayList<>();
    daList.add(1);
    daList.add(2);
    daList.add(3);
    daList.add("wow");
    List<Object> wowList = new ArrayList<>();
    wowList.addAll(daList);
    for (Object e : wowList) {
      System.out.println(e);
    }
    System.out.println("contains Integer(1): " + wowList.contains((Integer)1));
    System.out.println("contains String(\"wow\"): " + wowList.contains("wow"));
    System.out.println();

    System.out.println("daList.containsAll(wowList): " + daList.containsAll(wowList));
    System.out.println("daList hashCode(): " + daList.hashCode());
    System.out.println();

    System.out.println("wowList hashCode(): " + wowList.hashCode());
    System.out.println("daList.equals(wowList): " + daList.equals(wowList));
    System.out.println();

    List<Object> poorList = new ArrayList<>();
    poorList.addAll(wowList);
    poorList.clear();
    System.out.println("poorList.isEmpty(): " + poorList.isEmpty());
    System.out.println();    

    Iterator iter = daList.iterator();
    System.out.println("iter instanceof: ");
    System.out.println(iter instanceof Iterator);

    wowList.remove(3);
    wowList.add(9);
    wowList.add("meh");
    for (Object e : daList) {
      System.out.print(e + ", ");
    }
    System.out.println("daList.removeAll(wowList): " + daList.removeAll(wowList));
    for (Object e : daList) {
      System.out.print(e + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("daList.retrain(wowList): " + daList.retainAll(wowList));
    for (Object e : daList) {
      System.out.print(e + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("daList().size: " + daList.size());
    System.out.println();

    Object[] daArr = wowList.toArray();
    System.out.println();
    
    List<String> strList = new ArrayList<>();
    strList.add("kiki");
    String[] strArr = strList.toArray(new String[10]);
    System.out.println();
    System.out.println();

  }
}

