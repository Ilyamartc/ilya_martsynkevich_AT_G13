package lesson1.bubbles;

public class Bubble {
    private final double volume = 0.3;
    private String gas;
    public String getGas() {
        return gas;
    }
    public Bubble (String gas) {
        this.gas = gas;
    }
    public void pop() {
        System.out.println("Cramp! " + gas);
    }
}


//создать класс Bubble
//-- у пузырька должен быть объем, газовый состав
//-- он должен уметь лопаться с выводом в консоль «Cramp!»
//-- объем пузырька постоянный и равен 0.3 мм2, а газовый состав переменный в зависимости от образующего газа и задается в конструкторе класса