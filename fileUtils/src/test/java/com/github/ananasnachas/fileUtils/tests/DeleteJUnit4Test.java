package com.github.ananasnachas.fileUtils.tests;

import com.github.ananasnachas.fileUtils.main.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class DeleteJUnit4Test {
    private final File NEW_FILE = new File(Constants.RESOURCES_PATH + "/newFile");

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void makeNewFile() throws IOException {
        NEW_FILE.createNewFile();
    }

    @After
    public void deleteFile(){
      NEW_FILE.delete();
    }

    @Test
    public void nullPathTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_FILE_PATH_MESSAGE);
        FileUtils.delete(null);
    }

    @Test
    public void wrongPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.NON_EXISTENT_FILE));
        FileUtils.delete(Constants.NON_EXISTENT_FILE);
    }

    @Test
    public void normalFileTest() throws FileNotFoundException, FileSystemException {
        FileUtils.delete(NEW_FILE.getPath());
        Assert.assertFalse(NEW_FILE.exists());
    }
}
