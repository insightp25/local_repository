package d.collection;

public class HashFunctionSample {
    
    public static void main(String[] args) {
        HashFunctionSample hfs = new HashFunctionSample();
        System.out.println(hfs.calculateHash65599("apple"));
        System.out.println(hfs.calculateHash65599("banana"));
    }

    public int calculateHash65599(String str) {
        int hash = 0;
        char[] charArr = str.toCharArray();
        int charArrLen = charArr.length;

        for (int i = 0; i < charArrLen; i++) {
            hash = 65599 * hash + charArr[i];
        }

        return hash ^ (hash >> 16);
    }

}
