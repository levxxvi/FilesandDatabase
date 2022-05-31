package com.lexwong.database;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;
import static org.apache.commons.io.FileUtils.sizeOf;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getFullPath;

public class Main {
    public static DatabaseHandler handler;

    public static void main(String[] args) {
        handler = DatabaseHandler.getHandler();
        File userDir = FileUtils.getUserDirectory();
        addDir(userDir);
    }

    public static void addDir(File userDir){
        File[] files = listFiles(userDir);
        for (File file: files) {
            String fileName = String.valueOf(file);
            String path = getFullPath(fileName);
            String extension = getExtension(fileName);
            long size =  sizeOf(file);
            String sizeBytes = byteCountToDisplaySize(size);
            addFile(fileName, path, extension, sizeBytes);
        }
    }

    public static void addFile(String fileName, String path, String extension, String sizeBytes){
        String qu = "INSERT INTO MEMBER VALUES (" +
                "'" + fileName + "'," +
                "'" + path + "'," +
                "'" + extension + "'," +
                "'" + sizeBytes + "'," + ")";
        handler.execAction(qu);
    }


    /**
     * See: https://www.techiedelight.com/list-files-directory-java-using-guava-apache-commons-io/
     * @param rootDir
     */
    // Recursive method to list all files in a directory with Apache Commons IO
    public static File[] listFiles(File rootDir)
    {
        // FileFilterUtils.fileFileFilter() returns a file filter that accepts
        // only files and not directories
        File[] files = rootDir.listFiles((FileFilter)
                FileFilterUtils.fileFileFilter());

        return files;
        /*
        for (File file: files) {
            System.out.println(file);
        }

        // FileFilterUtils.directoryFileFilter() returns a file filter that accepts
        // only directories and not files
        File[] dirs = rootDir.listFiles((FileFilter)
                FileFilterUtils.directoryFileFilter());

        for (File dir: dirs) {
            listFiles(dir);
        }
         */
    }
}
