package d.collection;

import java.util.Properties;
import java.util.Set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PropertiesSample {
    public static void main (String[] args) {
        // String a = "wow";
        // String b = "wow";
        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(a == b);
        // System.out.println(a.equals(b));
        // String c = new String("wow");
        // String d = new String("wow");
        // System.out.println(c);
        // System.out.println(d);
        // System.out.println(c == d);
        // System.out.println(c.equals(d));
        // int e = 1;
        // int f = 1;
        // System.out.println(e == f);
        PropertiesSample sample = new PropertiesSample();
        sample.checkProperties();
        // sample.saveAndLoadPropertiesXML();
    }

    public void checkProperties() {
        Properties prop = System.getProperties();
        Set<Object> keySet = prop.keySet();
        for (Object tempObject : keySet) {
            System.out.println(tempObject + " = " + prop.get(tempObject));
        }
    }

    public void saveAndLoadProperties() {
        try {
            String fileName = "test.properties";
            File propertiesFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(propertiesFile);
            
            Properties prop = new Properties();
            prop.setProperty("Writer", "Yongbae, Dong");
            prop.setProperty("WriterHome", "http://www.GodOfJava.com");
            prop.store(fos, "Basic Yorobun Properties file.");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.load(fis);
            fis.close();
            System.out.println(propLoaded);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveAndLoadPropertiesXML() {
        try {
            String fileName = "text.xml";
            File propertiesFile = new File(fileName);
            
            FileOutputStream fos = new FileOutputStream(propertiesFile);
            Properties prop = new Properties();
            prop.setProperty("Writer", "Yongbae, Dong");
            prop.setProperty("WriterHome", "http://www.yorobun.com");
            prop.storeToXML(fos, "Basic Yorobun XML Property file.");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.loadFromXML(fis);
            System.out.println(propLoaded);
            fis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
