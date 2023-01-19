package ch07;


import java.util.*;

interface Parseable {
    public abstract void parse(String fileName);
}


class XMLParser implements Parseable {
    public void parse(String fileName) {
        System.out.println((fileName + "- XML parsing completed."));
    }
}
class HTMLParser implements Parseable {
    public void parse(String fileName) {
        System.out.println((fileName + "- HTML parsing completed."));
    }
}


class ParseManager {
    public static Parseable getParser(String type) {
        if (type.equals("XML")) {
            return new XMLParser();
        } else {
            return new HTMLParser();
        }
    }
}



public class ParserTest {
    public static void main(String[] args) {
        Parseable parser = ParseManager.getParser("XML");
        parser.parse("document.xml");
        parser = ParseManager.getParser("HTML");
        parser.parse("document2.html");

        Map<?, ?> wow;
        List<?> wow2;
        Set<?> wow3;
        Vector<?> wow4;
        AbstractList<?> wow5;
        SortedMap<?, ?> wow7;
    }
}
