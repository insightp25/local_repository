package c.string;

public class StringCompare {
	public static void main(String[] args) {
		StringCompare sample = new StringCompare();
		sample.nullCheck2(null);
	}

	// public boolean nullCheck(String text) {
	// 	int textLength = text.length(); // NPE 발생
	// 	System.out.println(textLength);

	// 	if (text == null) return true;
	// 	else return false;
	// }

	public boolean nullCheck2(String text) {
		if (text == null) {
			System.out.println("input argument is null");
			return true;
		}
		else {
			int textLength = text.length();
			System.out.println(textLength);
			return false;
		}
	}
}
