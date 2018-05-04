package com.github.ananasnachas.fileUtils.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public final static String RESOURCES_PATH = "src/test/resources";
    public final static String LINES_FILE = RESOURCES_PATH + "/lines";
    public final static String EMPTY_FILE = RESOURCES_PATH + "/empty.txt";
    public final static String DIRECTORY = RESOURCES_PATH + "/directory";
    public final static String READ_ONLY_FILE = RESOURCES_PATH + "/read-only";
    public final static String NON_EXISTENT_FILE = "/djvh kJSNjkn airsbj arzkjnrj/d.kJBiIWNJ JKNNDND LWOIJBKNL/VIHWR JGIUH 43GHRJVJK BFKHF/fbljfnj/lsn/sbk";
    public final static String UNREADABLE_FILE = RESOURCES_PATH + "/insufficientRights";

    public final static String REMOVE_UNSUPPORTED_MESSAGE = "Remove is unsupported";
    public final static String NULL_SOURCE_PATH_MESSAGE = "Source path must not be null";
    public final static String NULL_DEST_PATH_MESSAGE = "Destination path must not be null";
    public final static String SAME_SOURCE_AND_DEST_MESSAGE = "Source and destination must not be the same";
    public final static String NULL_INPUT_LIST_MESSAGE = "List must not be null";
    public final static String WRONG_RESOURCE_MESSAGE = "Resource '%s' does not exist or does not a file";
    public final static String UNREADABLE_FILE_MESSAGE = "File '%s' exists but is unreadable";
    public final static String READ_ONLY_FILE_MESSAGE = "File '%s' exists but is read-only";
    public final static String NULL_FILE_PATH_MESSAGE = "File path must not be null";

    public final static List<String> LINES_LIST = new ArrayList<>(
            Arrays.asList("first line", "second line", "Third line", "", "\t", "sixth line", "seventh line", "")
    );
    public final static List<String> EMPTY_LIST = new ArrayList<>();
}
