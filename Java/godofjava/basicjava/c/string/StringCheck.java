package c.string;

public class StringCheck {

	public static void main(String[] args) {
		StringCheck sample = new StringCheck();

		String addresses[] = new String[] {
			"서울시 구로구 신도림동",
			"경기도 성남시 분당구 정자동 개발 공장",
			"서울시 구로구 개봉동", // , 유무 syntax 확인
		};

		// sample.checkAddress(addresses);

		// sample.containsAddress(addresses);

		// sample.checkMatch();

		// sample.checkIndexOf();

		// sample.checkLastIndexOf();

		// sample.checkSubstring();

		// sample.checkSplit();

		// sample.checkTrim();

		// sample.checkReplace();

		// sample.checkFormat();

		// sample.checkToUpperOrLowerCase();

		sample.checkValueOf();

		// sample.initTest();
	}

	public void checkAddress(String[] addresses) {
		int startCount = 0, endCount = 0; // !

		String startText = "서울시";
		String endText = "동";

		for (String address : addresses) {
			if (address.startsWith(startText)) {
				startCount++;
			}

			if (address.endsWith(endText)) {
				endCount++;
			}
		}

		System.out.println("Starts with " + startText + " count is " + startCount);
		System.out.println("Ends with " + endText + " count is " + endCount);
		System.out.println();
	}

	public void containsAddress(String[] addresses) {
		int containCount = 0;
		String containText = "구로";
		for (String address : addresses) {
			if (address.contains(containText)) {
				containCount++;
			}
		}
		System.out.println("Contains " + containText + " count is " + containCount);
	}

	public void checkMatch() {
		String text = "This is a text";

		String compare1 = "is";
		String compare2 = "this";

		System.out.println(text.regionMatches(2, compare1, 0, 1)); // true
		System.out.println(text.regionMatches(5, compare1, 0, 2)); // true
		System.out.println(text.regionMatches(true, 0, compare2, 0, 4)); // true

	}

	public void checkIndexOf() {
		String text = "Java technology is both a programming language and a platform.";

		System.out.println(text.indexOf('a')); // 1
		System.out.println(text.indexOf("a ")); // 3
		System.out.println(text.indexOf('a', 20)); // 24
		System.out.println(text.indexOf("a ", 20)); // 24
		System.out.println(text.indexOf('z')); // -1
	}

	public void checkLastIndexOf() {
		String text = "Java technology is both a programming language and a platform.";

		System.out.println(text.length()); // 62

		System.out.println(text.lastIndexOf('a')); // 55
		System.out.println(text.lastIndexOf("a ")); // 51
		System.out.println(text.lastIndexOf('a', 20)); // 3
		System.out.println(text.lastIndexOf("a ", 20)); // 3
		System.out.println(text.lastIndexOf('z')); // -1
	}

	public void checkSubstring() {
		String text = "Java technology";
		
		String technology = text.substring(5);
		System.out.println(technology);

		String tech = text.substring(5, 9);
		System.out.println(tech);
	}

	public void checkSplit() {
		String text = "Java technology is both a programming language and a platform.";

		String[] splitArray = text.split(" ");

		for (String temp: splitArray) {
			System.out.println(temp);
		}
	}

	public void checkTrim() {
		String strings[] = new String[] {
			"a", " b ", "    c", "e  f", "    "
		};

		for (String string : strings) {
			System.out.println("[" + string + "]");
			System.out.println("[" + string.trim() + "]");
		}
	}

	public void checkReplace() {
		String text = "The String class represents character strings.";

		System.out.println(text.replace('s', 'z'));

		System.out.println(text);

		System.out.println(text.replace("tring", "trike"));;
		System.out.println(text.replaceAll(" ", "|"));
		System.out.println(text.replaceFirst(" ", "|"));
	}

	public void checkFormat() {
		String text = "제 이름은 %s입니다. 지금까지 %d 권을 썼고, " + "하루에 %f %%의 시간을 책을 쓰는 데 할애하고 있습니다.";

		String realText = String.format(text, "이상민", 7, 10.5);
		// String realText = String.format(text, "이상민", 7);
		System.out.println(realText);
	}

	public void checkToUpperOrLowerCase() {
		String text = "Sing us the song. You're the piano man.";
		String korean = "테스팅";

		System.out.println(text.toLowerCase());
		System.out.println(text.toUpperCase());

		System.out.println(korean.toUpperCase());
	}

	public void checkValueOf() {
		byte b = 1;
		String byte1 = String.valueOf(b);
		String byte2 = b + "";
		
		System.out.println(byte1);
		System.out.println(byte1.getClass().getSimpleName());
		System.out.println(byte2);
		System.out.println(byte2.getClass().getSimpleName());
		System.out.println();

		// test wiht object
		StringCheck check = new StringCheck();		
		System.out.println(String.valueOf(check)); // StringCheck@7ad041f3

		// test with integer
		System.out.println(String.valueOf(1).getClass().getSimpleName()); // String

		// System.out.println(String.TYPE);
		System.out.println(Integer.TYPE);
	}


	// 초기화 syntax 테스트
	Integer i1, i2 = 2;
	int i4, i5 = 5;

	public void initTest() {
		int t1, t2, t3; // 컴파일 test

		t1 = 1;
		System.out.println(t1);
		// System.out.println(t2); // 컴파일도 되지 않는다. 초기화 안됨.

		System.out.println("i1: " + i1);
		System.out.println("i2: " + i2);
		System.out.println();

		System.out.println("i4: " + i4);
		System.out.println("i5: " + i5);
	}
}
