package tests.testng;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestNGSuiteRunner {
    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList("src/test/resources/testng.xml");
        testng.setTestSuites(suites);
        testng.addListener(tla);
        testng.run();
    }
}
