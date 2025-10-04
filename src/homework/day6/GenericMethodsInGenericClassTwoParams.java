package homework.day6;

public class GenericMethodsInGenericClassTwoParams<X, Y> {
    private X xValue;
    private Y yValue;

    public GenericMethodsInGenericClassTwoParams(X xValue, Y yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public String genericMethodGenArgs(Object arg) {
        return "I received 1 argument of type: " + arg.getClass().getSimpleName() + " class";
    }

    public String genericMethodGenArgs(Object arg1, Object arg2) {
        return "I received 2 arguments of type: " + arg1.getClass().getSimpleName()
                + " class, " + arg2.getClass().getSimpleName() + " class";
    }

    public void genericMethodHalfGenArgsForX(X arg, String str) {
        System.out.println("I got an object of " + arg.getClass().getSimpleName()
                + " class and string with " + str.length() + " characters");
    }

    public void genericMethodHalfGenArgsForY(Y arg, String str) {
        System.out.println("I got an object of " + arg.getClass().getSimpleName()
                + " class and string with " + str.length() + " characters");
    }
}
