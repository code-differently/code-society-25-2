package com.codedifferently.lesson9;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.model.DataType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = Lesson9Application.class)
class Lesson9Test {

  @Autowired private ApplicationContext applicationContext;

  @Test
  void testDataProviderInterfaceExistence() {
    Optional<Class<?>> dataProviderInterface =
        findClass("com.codedifferently.lesson9.dataprovider.DataProvider");
    Assertions.assertTrue(
        dataProviderInterface.isPresent(), "DataProvider interface should exist.");
    Assertions.assertTrue(
        dataProviderInterface.get().isInterface(), "DataProvider should be an interface.");
  }

  @Test
  void testDataProviderInterfaceMethods() throws NoSuchMethodException {
    Class<?> dataProvider =
        findClass("com.codedifferently.lesson9.dataprovider.DataProvider").get();
    Method getProviderNameMethod = dataProvider.getMethod("getProviderName");
    Assertions.assertEquals(
        String.class,
        getProviderNameMethod.getReturnType(),
        "getProviderName() should return String.");

    Method getTypesMethod = dataProvider.getMethod("getTypes");
    Assertions.assertEquals(
        Map.class, getTypesMethod.getReturnType(), "getTypes() should return Map.");
    Type returnType = getTypesMethod.getGenericReturnType();
    Assertions.assertTrue(
        returnType instanceof ParameterizedType, "getTypes() should be a parameterized Map.");

    ParameterizedType pType = (ParameterizedType) returnType;
    Type[] typeArguments = pType.getActualTypeArguments();
    Assertions.assertEquals(2, typeArguments.length, "Map should have two type arguments.");
    Assertions.assertEquals(
        String.class, typeArguments[0], "First type argument should be String.");
    Assertions.assertEquals(
        DataType.class, typeArguments[1], "Second type argument should be DataType.");
  }

  @Test
  void testDataTypeEnumExists() {
    Optional<Class<?>> dataTypeEnum = findClass("com.codedifferently.lesson9.model.DataType");
    Assertions.assertTrue(dataTypeEnum.isPresent(), "DataType enum should exist.");
    Assertions.assertTrue(dataTypeEnum.get().isEnum(), "DataType should be an enum.");
  }

  @Test
  void testAllDataProvidersAreRegisteredBeans() {
    Map<String, DataProvider> providers = applicationContext.getBeansOfType(DataProvider.class);
    Assertions.assertFalse(
        providers.isEmpty(), "At least one DataProvider bean should be registered.");
  }

  @Test
  void testDataProviderGetTypesImplementation() {
    Map<String, DataProvider> providers = applicationContext.getBeansOfType(DataProvider.class);
    for (DataProvider provider : providers.values()) {
      Map<String, DataType> types = provider.getTypes();
      Assertions.assertNotNull(types, "getTypes() should not return null.");
      Assertions.assertFalse(types.isEmpty(), "getTypes() should not return an empty map.");
      for (Map.Entry<String, DataType> entry : types.entrySet()) {
        Assertions.assertNotNull(entry.getKey(), "Column name should not be null.");
        Assertions.assertNotNull(entry.getValue(), "DataType should not be null.");
      }
      Assertions.assertEquals(
          Collections.unmodifiableMap(types), types, "The returned map should be unmodifiable.");
    }
  }

  private Optional<Class<?>> findClass(String className) {
    try {
      return Optional.of(Class.forName(className));
    } catch (ClassNotFoundException e) {
      return Optional.empty();
    }
  }
}
