package com.lexwong.database;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;
import static org.apache.commons.io.FileUtils.sizeOf;
import static org.apache.commons.io.FilenameUtils.*;

public class Main {
    public static DatabaseHandler handler;

    public static void main(String[] args) {
        handler = DatabaseHandler.getHandler();

        System.out.println("Please type in your directory path");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Your input was " + input);
        File dir = new File(input);

        System.out.println("Please type in a table name");
        Scanner scanner1 = new Scanner(System.in);
        String tableName = scanner.nextLine();
        System.out.println("Your table name is " + tableName);
        addDir(tableName ,dir);

        File[] test = listFiles(dir);
        for (File file: test){
            System.out.println(file);
            String fileName = String.valueOf(file);
            String fileString = getBaseName(fileName);

            String path = getFullPath(fileName);
            String extension = getExtension(fileName);
            long size =  sizeOf(file);
            String sizeBytes = byteCountToDisplaySize(size);

            System.out.println("file name is " + fileString + "\n"
                    + "path is " + path + "\n"
                    + "extension is " + extension + "\n"
                    + "size is " + sizeBytes + "\n");
        }

    }

    //create table here???
    public static void addDir(String tableName ,File userDir){
        handler.createTable(tableName);

        File[] files = listFiles(userDir);
        for (File file: files) {
            String fileName = String.valueOf(file);
            String fileString = getBaseName(fileName);
            String path = getFullPath(fileName);
            String extension = getExtension(fileName);
            long size =  sizeOf(file);
            String sizeBytes = byteCountToDisplaySize(size);
            addFile(fileString, path, extension, sizeBytes);
        }
    }

    //add a param for table name
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
