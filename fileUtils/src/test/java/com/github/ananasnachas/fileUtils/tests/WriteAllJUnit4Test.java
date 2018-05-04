package com.github.ananasnachas.fileUtils.tests;

import com.github.ananasnachas.fileUtils.main.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriteAllJUnit4Test {

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
        thrown.expectMessage(Constants.NULL_DEST_PATH_MESSAGE);
        FileUtils.writeAll(null, Constants.EMPTY_LIST);
    }

    @Test
    public void wrongPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.NON_EXISTENT_FILE));
        FileUtils.writeAll(Constants.NON_EXISTENT_FILE, Constants.EMPTY_LIST);
    }

    @Test
    public void directoryPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.DIRECTORY));
        FileUtils.writeAll(Constants.DIRECTORY, Constants.EMPTY_LIST);
    }

    @Test
    public void canNotWriteTest() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage(String.format(Constants.READ_ONLY_FILE_MESSAGE, Constants.READ_ONLY_FILE));
        FileUtils.writeAll(Constants.READ_ONLY_FILE, Constants.EMPTY_LIST);
    }

    @Test
    public void nullListTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_INPUT_LIST_MESSAGE);
        FileUtils.writeAll(Constants.LINES_FILE, null);
    }

    @Test
    public void normalFileTest() throws IOException {
        FileUtils.writeAll(Constants.EMPTY_FILE, Constants.LINES_LIST);
        Assert.assertEquals(Constants.LINES_LIST, FileUtils.readAll(Constants.EMPTY_FILE));
    }

    @Test
    public void emptyListTest() throws IOException {
        FileUtils.writeAll(Constants.LINES_FILE, Constants.EMPTY_LIST);
        Assert.assertEquals(0, FileUtils.readAll(Constants.LINES_FILE).size());
    }
}
