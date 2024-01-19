package com.itmo.container;

import com.itmo.container.annotations.TaskClass;
import com.itmo.container.annotations.TaskFunction;

import java.io.File;
import java.util.Arrays;

@TaskClass
public class DirectoryReader {
    private final File[] files;

    public DirectoryReader() {
        this.files = new File("")
                .getAbsoluteFile()
                .listFiles();
    }

    @TaskFunction
    public void read() {
        Arrays.stream(files)
                .forEach(file -> {
                    System.out.println((file.isDirectory() ? "Директория" : "Файл") + ": " + file.getName());
                });
    }
}
