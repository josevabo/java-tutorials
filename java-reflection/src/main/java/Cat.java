public class Cat {
    private final String name;
    private int age;
    public Cat(String name, int age){
        this.age=age;
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void meow(){
        System.out.println("Meow");
    }

    private void heyThisIsPrivate(){
        System.out.println("Hey this method is private!");
    }

    public static void thisIsAPublicStaticMethod(){
        System.out.println("This is a public static method! Just passed null as the instance");
    }
    private static void thisIsAPrivateAndStaticMethod(){
        System.out.println("This is a private and static method! Just passed null as the instance");
    }



}
