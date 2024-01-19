package com.itmo.container.reflection;

public class ValidationError {
    private final String fieldName;
    private final String errorReason;
    private final Object fieldValue;

    public ValidationError(String fieldName, Object fieldValue, String errorReason) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.errorReason = errorReason;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "fieldName='" + fieldName + '\'' +
                ", errorReason='" + errorReason + '\'' +
                ", fieldValue=" + fieldValue +
                '}';
    }
}

