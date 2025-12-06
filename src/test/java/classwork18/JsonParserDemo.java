package classwork18;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JsonParserDemo {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void fromJson() throws IOException {
        Recipe recipe = objectMapper.readValue(
                new File("src/test/resources/test_data/recipe.json"),
                Recipe.class
        );
        System.out.println("Прочитали из файла:");
        System.out.println(recipe);
    }

    public static void toJson() throws IOException {
        Recipe recipe = new Recipe("Borsch",
                Arrays.asList(
                        new Ingredient("beef", 150),
                        new Ingredient("beetroots", 4),
                        new Ingredient("potatoes", 3)
                ),
                120
        );

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("src/test/resources/temp/recipe2.json"), recipe);

        System.out.println("Записали борщ в src/test/resources/temp/recipe2.json");
    }

    public static void main(String[] args) throws IOException {
        toJson();
        fromJson();
    }
}