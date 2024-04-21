public class ProfilePrint {
  byte age;
  String name;
  boolean isMarried;

  public byte getAge() {
    return age;
  }

  public void setAge(byte age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getMarried() {
    return isMarried;
  }

  public void setMarried(boolean flag) {
    this.isMarried = flag;
  }

  public static void main(String[] args) {
    ProfilePrint person1 = new ProfilePrint();
    byte age = 20;
    person1.setAge(age);
    person1.setName("Aiden");
    person1.setMarried(true);
    System.out.println(person1.getAge());
    System.out.println(person1.getName());
    System.out.println(person1.getMarried());
  }
}
