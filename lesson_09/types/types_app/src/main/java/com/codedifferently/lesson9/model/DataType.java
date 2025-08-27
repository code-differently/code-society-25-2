package com.codedifferently.lesson9.model;

public enum DataType {
    STRING(String.class),
    INTEGER(Integer.class),
    BOOLEAN(Boolean.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    LONG(Long.class),
    SHORT(Short.class);

    private final Class<?> javaType;

    DataType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public Class<?> getJavaType() {
        return javaType;
    }
}


  public Class<?> getJavaType() {
    return javaType;
  }
}
