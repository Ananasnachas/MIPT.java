package com.github.ananasnachas.fileUtils.tests;

import com.github.ananasnachas.fileUtils.main.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorJUnit4Test {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullPathTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(Constants.NULL_SOURCE_PATH_MESSAGE);
        FileUtils.createIterator(null);

    }

    @Test
    public void wrongPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.NON_EXISTENT_FILE));
        FileUtils.createIterator(Constants.NON_EXISTENT_FILE);
    }

    @Test
    public void directoryPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format(Constants.WRONG_RESOURCE_MESSAGE, Constants.DIRECTORY));
        FileUtils.createIterator(Constants.DIRECTORY);
    }

    @Test
    public void removeTest() throws IOException {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage(Constants.REMOVE_UNSUPPORTED_MESSAGE);
        FileUtils.createIterator(Constants.LINES_FILE).remove();
    }

    @Test
    public void emptyFileTest() throws IOException {
        Iterator<String> itr = FileUtils.createIterator(Constants.EMPTY_FILE);
        List<String> lines = new ArrayList<>();
        while (itr.hasNext()){
            lines.add(itr.next());
        }
        Assert.assertEquals(0, lines.size());
    }

    @Test
    public void normalFileTest() throws IOException {
        Iterator<String> itr = FileUtils.createIterator(Constants.LINES_FILE);
        List<String> lines = new ArrayList<>();
        while (itr.hasNext()){
            lines.add(itr.next());
        }
        Assert.assertEquals(Constants.LINES_LIST, lines);
    }
}
