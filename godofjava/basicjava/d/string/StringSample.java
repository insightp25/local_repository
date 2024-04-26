package d.string;

public class StringSample {
	public static void main(String[] args) {
		StringSample sample = new StringSample();
		sample.convertUTF16();
	}

	public void printByteArray(byte[] array) {
		try {
			for (byte data : array) {
				System.out.print(data + " ");
			}

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void convertUTF16() {
		try {
			String korean = "자바의 신!!!";
			byte[] array1 = korean.getBytes("UTF-16");
			printByteArray(array1);
			System.out.println(array1.length);

			String korean9 = "자바의 신!!!";
			byte[] array2 = korean9.getBytes("EUC-KR");
			printByteArray(array2);
			System.out.println(array2.length);

			// String korean2 = new String(array1, "UTF-16");
			// System.out.println(korean2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
