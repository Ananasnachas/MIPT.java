package com.github.ananasnachas.fileUtils.tests;

import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class FileUtilsJUnit4Test extends Assert {

    private static int runTests = 0;
    private static int failedTests = 0;
    private static int ignoredTests = 0;

    public static void main(String[] args) throws Exception {
        runTests(ReadAllJUnit4Test.class);
        runTests(WriteAllJUnit4Test.class);
        runTests(CopyJUnit4Test.class);
        runTests(DeleteJUnit4Test.class);
        runTests(IteratorJUnit4Test.class);
        System.out.println("Run tests: " + runTests);
        System.out.println("Failed tests: " + failedTests);
        System.out.println("Ignored tests: " + ignoredTests);
    }

    private static void runTests(Class c){
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(c);
        runTests += result.getRunCount();
        failedTests += result.getFailureCount();
        ignoredTests += result.getIgnoreCount();
    }
}
