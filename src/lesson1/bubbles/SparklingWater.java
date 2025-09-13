package lesson1.bubbles;

public class SparklingWater extends Water {
    public SparklingWater(String color, String transparency, String smell, double temperature) {
        super(color, transparency, smell, temperature);
    }
    private Bubble[] bubbles;
    public void pump(Bubble[] bubbles) {
        this.bubbles = bubbles;
    }
    public void degas() {
        int i = 0;
        while (i < bubbles.length) {
            bubbles[i].pop();
            i++;
        }
        bubbles = new Bubble[0];
    }
}
//-- создать класс SparklingWater, являющийся дочерним Water +
//-- у газировки есть пузырьки +
//-- вода заполняется пузырьками при упаковке на заводе, для этого у нее есть метод pump(Bubble[] bubbles) (pump) +
//-- 1 литр воды содержит 10 тыс пузырьков ->
//-- у газировки есть метод degas(), который удаляет пузырьки по одному и вызывает их лопанье +