public class ReferenceTypes {
  public void callPassByValue() {
    int a = 10;
    String b = "b";
    MemberDTO member = new MemberDTO("Big Boss");
    passByValue(a, b, member);
    System.out.println("-- callPassByValue method result --");
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("member.name = " + member.name);
    System.out.println("");
  }

  public void passByValue(int a, String b, MemberDTO member) {
    a = 99;
    b = "z";
    // member = new MemberDTO("Snakeeeeee");
    member.name = "Snakeeeeeee";
    System.out.println("-- callPassByValue method result --");
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("member.name = " + member.name);
    System.out.println("");
  }
}

