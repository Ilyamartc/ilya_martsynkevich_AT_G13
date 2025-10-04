package homework.day7;

import playground.essence.craft.hand.Bottle;
import java.io.*;

public class SerializeObjectTask {
    public void saveObjectToFile(SerializableBottle obj, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
            System.out.println("Объект сериализован и сохранен в файл: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Bottle bottle = new Bottle();
        SerializableBottle serializableBottle = new SerializableBottle(bottle); // обертка

        SerializeObjectTask task = new SerializeObjectTask();
        task.saveObjectToFile(serializableBottle, "bottle_object.ser");
    }
}
