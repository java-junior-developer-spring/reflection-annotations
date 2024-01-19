package com.itmo.container;

import com.itmo.container.annotations.TaskClass;
import com.itmo.container.annotations.TaskFunction;

@TaskClass
public class HelloPrinter {

    @TaskFunction
    public void print(){
        System.out.println("Hello");
    }
}
