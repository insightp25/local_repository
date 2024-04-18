public class PrimitiveTypes {
	public static void main(String[] args) {
		PrimitiveTypes types = new PrimitiveTypes();
		types.checkByte();
	}

	public void checkByte() {
		byte byteMin = -128;
		byte byteMax = 127;
		System.out.println("byteMin = " + byteMin);
		System.out.println("byteMax = " + byteMax);

		byteMin = (byte) (byteMin - 1);
		byteMax = (byte) (byteMax + 1);
		System.out.println("byteMin - 1 = " + byteMin);
		System.out.println("byteMax + 1 = " + byteMax);
	}
}