package com.github.ananasnachas.fileUtils.tests;

import com.github.ananasnachas.fileUtils.main.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class CopyJUnit4Test {

    @Before
    public void makeFiles() throws IOException {
        FileUtils.writeAll(Constants.LINES_FILE, Constants.LINES_LIST);
        FileUtils.writeAll(Constants.EMPTY_FILE, Constants.EMPTY_LIST);
    }

    @AfterClass
    public static void restoreFiles() throws IOException {
        FileUtils.writeAll(Constants.LINES_FILE, Constants.LINES_LIST);
        FileUtils.writeAll(Constants.EMPTY_FILE, Constants.EMPTY_LIST);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullSourcePathTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_SOURCE_PATH_MESSAGE);
        FileUtils.copy(null, Constants.LINES_FILE);
    }

    @Test
    public void nullDestPathTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_DEST_PATH_MESSAGE);
        FileUtils.copy(Constants.LINES_FILE, null);
    }

    @Test
    public void sameFilesTest() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage(Constants.SAME_SOURCE_AND_DEST_MESSAGE);
        FileUtils.copy(Constants.LINES_FILE, Constants.LINES_FILE);
    }

    @Test
    public void emptySourceFileTest() throws IOException {
        FileUtils.copy(Constants.EMPTY_FILE, Constants.LINES_FILE);
        Assert.assertEquals(0, FileUtils.readAll(Constants.LINES_FILE).size());
    }

    @Test
    public void normalFileTest() throws IOException {
        FileUtils.copy(Constants.LINES_FILE, Constants.EMPTY_FILE);
        Assert.assertEquals(Constants.LINES_LIST, FileUtils.readAll(Constants.EMPTY_FILE));
    }
}
