package d.string.practice;

public class UseStringMethods {
	public static void main(String[] args) {
		UseStringMethods sample = new UseStringMethods();
		String sampleStr = "The String class represents character strings.";
		sample.printWords(sampleStr);
		sample.findString(sampleStr, "string");
		sample.findAnyCaseString(sampleStr, "string");
		sample.countChar(sampleStr, 's');
		sample.printContainWords(sampleStr, "ss");
	}

	public void printWords(String str) {
		String[] strArr = str.split(" ");

		for (String s : strArr) {
			System.out.println(s);
		}
	}

	public void findString(String str, String findStr) {
		int indexResult = str.indexOf(findStr);
		if (indexResult > 0)
			System.out.println(new StringBuilder()
				.append("string is appeared at ")
				.append(indexResult)
				.toString());
		else 
			System.out.println("string is not found");
	}

	public void findAnyCaseString(String str, String findStr) {
		int indexResult = str.toLowerCase().indexOf(findStr);
		if (indexResult > 0)
			System.out.println(new StringBuilder()
				.append("string is appeared at ")
				.append(indexResult)
				.toString());
		else 
			System.out.println("string is not found");

	}

	public void countChar(String str, char c) {
		char[] charArr = str.toCharArray();
		int count = 0;
		for (char character : charArr) {
			if (character == c)
				count++;
		}
		System.out.println(new StringBuilder()
			.append("char '")
			.append(c)
			.append("' count is ")
			.append(count)
			.toString());
	}

	public void printContainWords(String str, String findStr) {
		String[] strArr = str.split(" ");

		for (String s : strArr) {
			if (s.contains(findStr))
				System.out.println(new StringBuilder()
					.append("word ")
					.append("\"")
					.append(s)
					.append("\" contains \"")
					.append(findStr)
					.append("\"")
					.toString());
		}
	}
}
