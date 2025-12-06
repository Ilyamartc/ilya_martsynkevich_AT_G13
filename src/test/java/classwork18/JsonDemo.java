package classwork18;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Arrays;

public class JsonDemo {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Recipe recipe = new Recipe("Salad",
                Arrays.asList(
                        new Ingredient("Tomato", 2),
                        new Ingredient("Cucumber", 1)
                ),
                3
        );

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("src/test/resources/recipe.json"), recipe);

        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(recipe);

        System.out.println(json);
    }
}