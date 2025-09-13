package lesson1.page44;

class TestFoo {
    public void foo() {
        int i = 23;
        while (i < 28) {
            if (i % 13 == 0) {
                System.out.println("continue!");
                break;
            }
            System.out.println("i is : " + i++);
        }
        System.out.println("number is : " + i);
    }

    public static void main(String[] args) {
        TestFoo test = new TestFoo(); // создаём объект
        test.foo(); // вызываем метод foo()
    }
}
