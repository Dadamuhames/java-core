package lessons.lesson03.person;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        String introductionString = String.format("Hello, my name is %s. I am %d.", name, age);

        System.out.println(introductionString);
    }
}
