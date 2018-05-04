package com.github.ananasnachas.fileUtils.tests;

import com.github.ananasnachas.fileUtils.main.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadAllJUnit4Test {

    @Before
    public void makeFiles() throws IOException {
        org.apache.commons.io.FileUtils.writeLines(new File(Constants.LINES_FILE), Constants.LINES_LIST);
        org.apache.commons.io.FileUtils.writeLines(new File(Constants.EMPTY_FILE), Constants.EMPTY_LIST);
    }

    @AfterClass
    public static void restoreFiles() throws IOException {
        org.apache.commons.io.FileUtils.writeLines(new File(Constants.LINES_FILE), Constants.LINES_LIST);
        org.apache.commons.io.FileUtils.writeLines(new File(Constants.EMPTY_FILE), Constants.EMPTY_LIST);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullPathTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_SOURCE_PATH_MESSAGE);
        FileUtils.readAll(null);

    }

    @Test
    public void wrongPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.NON_EXISTENT_FILE));
        FileUtils.readAll(Constants.NON_EXISTENT_FILE);
    }

    @Test
    public void directoryPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.DIRECTORY));
        FileUtils.readAll(Constants.DIRECTORY);
    }

    @Test
    @Ignore
    public void unreadableFileTest() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage(String.format(Constants.UNREADABLE_FILE_MESSAGE, Constants.UNREADABLE_FILE));
        FileUtils.readAll(Constants.UNREADABLE_FILE);
    }

    @Test
    public void emptyFileTest() throws IOException {
        Assert.assertEquals(0, FileUtils.readAll(Constants.EMPTY_FILE).size());
    }

    @Test
    public void normalFileTest() throws IOException {
        Assert.assertEquals(Constants.LINES_LIST, FileUtils.readAll(Constants.LINES_FILE));
    }
}
