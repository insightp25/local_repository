package d.collection;

// import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class MapSample {
    public static void main(String[] args) {
        MapSample sample = new MapSample();
        // sample.checkHashMap();
        // sample.checkKeySet();
        // sample.checkValues();
        // sample.checkHashMapEntry();
        // sample.checkContains();
        sample.checkRemove();
    }

    public void checkHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");
        map.put("E", "e");

        System.out.println(map.get("A"));
        System.out.println(map.get("F"));

        // Set<Map.Entry<String, String>> entries = map.entrySet();
        // for (Map.Entry<String, String> tempEntry : entries) {
        //     System.out.println(tempEntry.getKey() + "=" + tempEntry.getValue());
        // }
    }

    public void checkHashMapEntry() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");
        map.put("E", "e");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> e : entries) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }

    public void checkKeySet() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");
        map.put("E", "e");

        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            System.out.println(key + " = " + map.get(key));
        }
    }

    public void checkValues() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");
        map.put("E", "e");

        Collection<String> values = map.values();

        for (String v : values) {
            System.out.println(v);
        }
    }

    public void checkContains() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");
        map.put("E", "e");

        System.out.println(map.containsKey("A"));
        System.out.println(map.containsKey("Z"));
        System.out.println(map.containsValue("a"));
        System.out.println(map.containsValue("z"));
    }

    public void checkRemove() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        System.out.println(map.size());
        String removed = map.remove("A");
        System.out.println(map.size());
        System.out.println(removed);
    }
}
