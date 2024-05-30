package d.collection;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapSample {
    public static void main(String args[]) {
		TreeMapSample sample = new TreeMapSample();
		sample.checkTreeMap();
    }

	public void checkTreeMap() {
		TreeMap<String, String> map = new TreeMap<>();
		map.put("A", "a");
		map.put("가", "e");
		map.put("1", "f");
		map.put("a", "g");

		TreeMap<Integer, String> map2 = new TreeMap<>();
		map2.put(9, "9-in-str");
		map2.put(88, "88-in-str");
		map2.put(3, "3-in-str");
		map2.put(200, "200-in-str");
		map2.put(-1, "-1-in-str");

		Set<Map.Entry<String, String>> entries = map.entrySet();

		for (Map.Entry<String, String> tempEntry : entries) {
			System.out.println(tempEntry.getKey() + "=" + tempEntry.getValue());
		}

		System.out.println();

		Set<Map.Entry<Integer, String>> entries2 = map2.entrySet();

		for (Map.Entry<Integer, String> tempEntry : entries2) {
			System.out.println(tempEntry.getKey() + " = " + tempEntry.getValue());
		}
	}
}
