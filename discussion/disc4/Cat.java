public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void greet() {
        if (age < 5) {

            System.out.println("Cat says: MEOW!");
            return;
        }
        System.out.println("Cat says: Meow!");
    }
}
