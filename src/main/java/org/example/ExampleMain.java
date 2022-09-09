package org.example;


import org.apache.log4j.BasicConfigurator;
import org.example.file.DirectoryWatcher;

public class ExampleMain {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        DirectoryWatcher watcher = new DirectoryWatcher.Builder()
                .addDirectories("D:\\test")
                .setPreExistingAsCreated(true)
                .build((event, path) -> {
                    switch (event) {
                        case ENTRY_CREATE -> System.out.println(path + " created.");
                        case ENTRY_MODIFY -> System.out.println(path + " modified.");
                        case ENTRY_DELETE -> System.out.println(path + " deleted.");
                    }
                });

        try {
            watcher.start(); // Actual watching starts here
            //TimeUnit.SECONDS.sleep(30);
            //watcher.stop(); // Stop watching
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
