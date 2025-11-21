package com.example.tests.junit.people;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AutomatedEngineerTest.class,
        ManualEngineerTest.class
})
public class JUnitTestSuite {

}
