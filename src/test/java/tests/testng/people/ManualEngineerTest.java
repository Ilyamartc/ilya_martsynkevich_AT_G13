package tests.testng.people;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import people.ManualEngineer;

import static org.testng.Assert.assertEquals;

public class ManualEngineerTest {

    @DataProvider(name = "manualData")
    public Object[][] data() {
        return new Object[][]{
                {"Kate", 4, "Manual"},
                {"Leo", 7, "Manual"}
        };
    }

    @Test(dataProvider = "manualData")
    public void testManualEngineerType(String name, int exp, String expectedType) {
        ManualEngineer eng = new ManualEngineer(name, exp);
        assertEquals(eng.getType(), expectedType);
    }
}
