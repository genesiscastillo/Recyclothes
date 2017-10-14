package com.recyclothes.nio;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World Enterprise Application Client!" );

        FileSystem fileSystem = FileSystems.getDefault();
        Iterator<Path> directories = fileSystem.getRootDirectories().iterator();
        while(directories.hasNext()){
            Path path = directories.next();
            System.out.println(path);
        }
    }
}
