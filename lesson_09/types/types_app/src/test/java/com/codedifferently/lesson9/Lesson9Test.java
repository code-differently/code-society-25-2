package com.codedifferently.lesson9;

import com.codedifferently.lesson9.Model.DataType;
import com.codedifferently.lesson9.dataprovider.DataProvider;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class Lesson9Test {

  @Autowired private ListableBeanFactory beanFactory;

  @InjectMocks private Lesson9Application lesson9Application;

  @Test
  @DisplayName("test DataProvider interface existence")
  void testDataProviderInterfaceExistence() throws ClassNotFoundException {
    findClass("com.codedifferently.lesson9.dataprovider.DataProvider");
  }

  @Test
  @DisplayName("test all DataProviders are registered beans")
  void testAllDataProvidersAreRegisteredBeans() throws ClassNotFoundException {
    Set<String> providers =
        Stream.of(
                "com.codedifferently.lesson9.dataprovider.AnthonyMaysProvider",
                "com.codedifferently.lesson9.dataprovider.JoneeDataFileProvider")
            .collect(Collectors.toSet());

    Assertions.assertEquals(
        providers.size(),
        beanFactory.getBeansOfType(DataProvider.class).size(),
        "All DataProvider implementations should be Spring beans.");
  }

  @Test
  @DisplayName("test DataProvider interface methods")
  void testDataProviderInterfaceMethods() throws NoSuchMethodException {
    Class<?> providerInterface = DataProvider.class;
    Method getProviderName = providerInterface.getMethod("getProviderName");
    Method getTypes = providerInterface.getMethod("getTypes");

    Assertions.assertEquals(
        String.class, getProviderName.getReturnType(), "getProviderName should return a String.");

    Assertions.assertEquals(Map.class, getTypes.getReturnType(), "getTypes should return a Map.");

    Type returnType = getTypes.getGenericReturnType();
    Assertions.assertTrue(
        returnType instanceof ParameterizedType,
        "getTypes return type should be a parameterized Map.");

    ParameterizedType parameterizedType = (ParameterizedType) returnType;
    Type[] typeArguments = parameterizedType.getActualTypeArguments();

    Assertions.assertEquals(
        String.class, typeArguments[0], "First type argument should be String.");
    Assertions.assertEquals(
        DataType.class, typeArguments[1], "Second type argument should be DataType.");
  }

  @Test
  @DisplayName("test DataProvider getTypes implementation")
  void testDataProviderGetTypesImplementation() throws ClassNotFoundException {
    Map<String, DataProvider> providers = beanFactory.getBeansOfType(DataProvider.class);
    for (DataProvider provider : providers.values()) {
      Assertions.assertNotNull(provider.getProviderName(), "Provider name should not be null.");
      Assertions.assertFalse(
          provider.getProviderName().isBlank(), "Provider name should not be blank.");

      Map<String, DataType> types = provider.getTypes();
      Assertions.assertNotNull(types, "getTypes() should not return null.");
      Assertions.assertFalse(types.isEmpty(), "getTypes() should return a non-empty map.");

      for (Map.Entry<String, DataType> entry : types.entrySet()) {
        Assertions.assertNotNull(entry.getKey(), "Column name (key) should not be null.");
        Assertions.assertFalse(entry.getKey().isBlank(), "Column name (key) should not be blank.");
        Assertions.assertNotNull(entry.getValue(), "Data type (value) should not be null.");
      }
    }
  }

  @Test
  @DisplayName("test DataType enum exists")
  void testDataTypeEnumExists() throws ClassNotFoundException {
    findClass("com.codedifferently.lesson9.Model.DataType");
  }

  private void findClass(String className) throws ClassNotFoundException {
    try {
      Class.forName(className);
    } catch (ClassNotFoundException e) {
      Assertions.fail("Class not found: " + className);
    }
  }
}
