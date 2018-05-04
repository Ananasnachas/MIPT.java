package com.github.ananasnachas.fileUtils.main;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class FileUtils {

    private final static String NULL_SOURCE_PATH_MESSAGE = "Source path must not be null";
    private final static String NULL_DEST_PATH_MESSAGE = "Destination path must not be null";
    private final static String WRONG_RESOURCE_MESSAGE = "Resource '%s' does not exist or does not a file";
    private final static String UNREADABLE_FILE_MESSAGE = "File '%s' exists but is unreadable";
    private final static String READ_ONLY_FILE_MESSAGE = "File '%s' exists but is read-only";

    private FileUtils(){}

    public static void delete(String path) throws FileNotFoundException, FileSystemException {
        if(path == null) {
            throw new NullPointerException("File path must not be null");
        }
        File file = new File(path);
        if(!file.isFile()){
            throw new FileNotFoundException(String.format(WRONG_RESOURCE_MESSAGE, path));
        }else if(!file.delete()){
            throw new FileSystemException("Can not delete file: " + path);
        }
    }

    public static void writeAll(String path, List<String> lines) throws IOException{
        if(path == null) {
            throw new NullPointerException(NULL_DEST_PATH_MESSAGE);
        } else if(lines == null){
            throw new NullPointerException("List must not be null");
        }
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException(String.format(WRONG_RESOURCE_MESSAGE, path));
        }else if(!file.canWrite()){
            throw new IOException(String.format(READ_ONLY_FILE_MESSAGE, path));
        }else {
            try (FileWriter fileWriter = new FileWriter(path)) {
                for (String line : lines) {
                    fileWriter.write(line + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new IOException("Error while writing to file: " + path);
            }
        }
    }

    public static List<String> readAll(String path) throws IOException{
        if(path == null) {
            throw new NullPointerException(NULL_SOURCE_PATH_MESSAGE);
        }
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException(String.format(WRONG_RESOURCE_MESSAGE, path));
        }else if(!file.canRead()){
            throw new IOException(String.format(UNREADABLE_FILE_MESSAGE, path));
        }
        List<String> lines = new ArrayList<>();
        try(FileReader fileReader = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fileReader);
            String s;
            while ((s=br.readLine())!=null){
                lines.add(s);
            }
        }
        catch (IOException e) {
            throw new IOException("Error while reading from file: " + file);
        }
        return lines;
    }

    public static void copy(String source, String dest) throws IOException{
        if (source == null) {
            throw new NullPointerException(NULL_SOURCE_PATH_MESSAGE);
        } else if (dest == null) {
            throw new NullPointerException(NULL_DEST_PATH_MESSAGE);
        } else if (source.equals(dest)) {
            throw new IOException("Source and destination must not be the same");
        } else {
            List<String> lines = readAll(source);
            writeAll(dest,lines);
            if (new File(source).length() != new File(dest).length()) {
                throw new IOException("Failed to copy full contents from '" + source + "' to '" + dest + "'");
            }
        }
    }

    public static Iterator<String> createIterator(String path) throws IOException {
        return new FileIterator(path);
    }

    private static class FileIterator implements Iterator<String>{
        private List<String> lines;
        private int numberOfNextLine;
        private int countOfLines;
        private String path;

        FileIterator(String p) throws IOException {
            path = p;
            update();
            numberOfNextLine = 0;
        }

        private void update() throws IOException {
            lines = readAll(path);
            countOfLines = lines.size();
        }

        @Override
        public boolean hasNext() {
            try {
                update();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return numberOfNextLine < countOfLines;
        }

        @Override
        public String next() {
            if(!hasNext()) {
                throw new NoSuchElementException("No more lines");
            }
            else {
                String thisLine = lines.get(numberOfNextLine);
                numberOfNextLine++;
                return thisLine;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported");
        }
    }
}