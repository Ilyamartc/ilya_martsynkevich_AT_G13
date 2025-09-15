package lesson1.bubbles;

public class Bubble {
    private final double volume = 0.3;
    private String gas;
    public double getVolume() {
        return volume;
    }
    public String getGas() {
        return gas;
    }
    public void pop() {
        System.out.println("Cramp! " + gas);
    }
    public Bubble (String gas) {
        this.gas = gas;
    }
}


//создать класс Bubble
//-- у пузырька должен быть объем, газовый состав -- объем пузырька постоянный и равен 0.3 мм2,
//-- он должен уметь лопаться с выводом в консоль «Cramp!»
// а газовый состав переменный в зависимости от образующего газа и задается в конструкторе класса