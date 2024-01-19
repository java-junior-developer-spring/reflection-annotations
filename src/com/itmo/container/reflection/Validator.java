package com.itmo.container.reflection;

import com.itmo.container.annotations.Between;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator<T> {
    private final List<T> validatedList;
    private final List<ValidationError> errorsInfo = new ArrayList<>();

    public Validator(List<T> validatedList) {
        if (validatedList == null || validatedList.isEmpty())
            throw new IllegalArgumentException("validatedList");
        this.validatedList = validatedList;
    }

    private boolean isBetweenValid(T object, Field field) {
        Between between = field.getDeclaredAnnotation(Between.class);
        int minValue = between.min();
        int maxValue = between.max();
        String errorMessage = between.errorMessage();
        if (!field.getType().equals(Integer.class) && !field.getType().equals(int.class)) {
            throw new RuntimeException("Аннотация Between может быть использована только с типами int или Integer");
        }
        field.setAccessible(true);
        int fieldValue = 0;
        try {
            fieldValue = field.getInt(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Невозможно считать прочитать целое число", e);
        }
        if (fieldValue < minValue || fieldValue > maxValue) {
            errorsInfo.add(new ValidationError(field.getName(), fieldValue, errorMessage));
            return false;
        }
        return true;
    }

    public void validate() {
        Class<?> elementClass = validatedList.get(0).getClass();
        Field[] fields = elementClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Between.class)) {
                validatedList
                        .removeIf(element ->
                                !isBetweenValid(element, field));
            }
        }
    }

    public List<T> getValidatedList() {
        return validatedList;
    }

    public List<ValidationError> getErrorInfo() {
        return errorsInfo;
    }

}
