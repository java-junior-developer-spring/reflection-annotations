package com.itmo.container.reflection;

import com.itmo.container.annotations.TaskClass;
import com.itmo.container.annotations.TaskFunction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskRunner {
    private ScheduledExecutorService service;

    public TaskRunner() {
        service = Executors.newSingleThreadScheduledExecutor();
    }

    public void run(List<Class<?>> tasks) {
        for (Class<?> task : tasks) {
            if (!task.isAnnotationPresent(TaskClass.class)) continue;
            Method taskMethod = getTaskMethod(task);
            Object object = getObject(task);
            runTask(object, taskMethod);
        }
    }

    private void runTask(Object object, Method taskMethod){
        service.scheduleWithFixedDelay(
                () -> {
                    try {
                        taskMethod.invoke(object);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Задача не может быть запущена");
                    }
                },
                0,
                1,
                TimeUnit.DAYS
        );
    }

    private Object getObject(Class<?> cls) {
        Constructor<?> defaultConstructor;
        try {
            defaultConstructor = cls.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Классу необходим конструктор без параметров");
        }
        Object object;
        try {
            object = defaultConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Экземпляр не можем быть создан");
        }
        return object;
    }

    private Method getTaskMethod(Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(TaskFunction.class)) return method;
        }
        throw new RuntimeException("В TaskClass должен быть метод с TaskFunction аннотацией");
    }
}
