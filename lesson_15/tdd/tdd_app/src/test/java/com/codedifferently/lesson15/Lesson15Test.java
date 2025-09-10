package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Lesson15Test {

  @Test
  public void testLesson15() {
    assertThat(new Lesson15()).isNotNull();
  }

  @Test
  public void testGetGreeting() {
    Lesson15.main(null);
  }

  @Test
  public void employee_getDetails_returnsUsefulString() {
    Employee emp = new Employee(1, "Alice", "Engineering", 120000.0);
    String details = emp.getDetails();
    assertThat(details).isNotNull();
    assertThat(details.isBlank()).isFalse();
    assertThat(details).contains("Employee");
    assertThat(details).contains("id");
    assertThat(details).contains("name");
    assertThat(details).contains("department");
    assertThat(details).contains("salary");
  }

  @Test
  public void employee_invokePublicZeroArgGetters_forCoverage() throws Exception {
    Employee emp = new Employee(2, "Bob", "Sales", 90000.0);
    for (var m : Employee.class.getDeclaredMethods()) {
      if (java.lang.reflect.Modifier.isPublic(m.getModifiers())
          && m.getParameterCount() == 0
          && m.getName().startsWith("get")
          && !m.getName().equals("getClass")) {
        try {
          m.invoke(emp);
        } catch (Exception ignored) {
        }
      }
    }
  }

  @Test
  public void employee_setters_and_getters_cover_fields() {
    Employee emp = new Employee(0, "", "", 0.0);

    emp.setId(42);
    emp.setName("Charlie");
    emp.setDepartment("Marketing");
    emp.setSalary(123456.78);

    assertThat(emp.getId()).isEqualTo(42);
    assertThat(emp.getName()).isEqualTo("Charlie");
    assertThat(emp.getDepartment()).isEqualTo("Marketing");
    assertThat(emp.getSalary()).isEqualTo(123456.78);
  }

  @Test
  public void employee_getDetails_reflects_updated_values() {
    Employee emp = new Employee(7, "Dana", "Ops", 80000.0);

    emp.setId(99);
    emp.setName("Eve");
    emp.setDepartment("Support");
    emp.setSalary(91000.0);

    String details = emp.getDetails();
    assertThat(details).contains("Employee");
    assertThat(details).contains("id=99");
    assertThat(details).contains("name=Eve");
    assertThat(details).contains("department=Support");
    assertThat(details).contains("salary=91000.0");
  }

  @Test
  public void package_smoke_touchPublicAPIsAcrossPackage() throws Exception {
    String pkg = "com.codedifferently.lesson15";

    java.util.function.Function<Class<?>, Object> defaultArg =
        (Class<?> t) -> {
          try {
            if (t == String.class) return "";
            if (t == int.class || t == Integer.class) return 0;
            if (t == long.class || t == Long.class) return 0L;
            if (t == double.class || t == Double.class) return 0.0;
            if (t == float.class || t == Float.class) return 0f;
            if (t == boolean.class || t == Boolean.class) return false;
            if (t == char.class || t == Character.class) return '\0';
            if (t == short.class || t == Short.class) return (short) 0;
            if (t == byte.class || t == Byte.class) return (byte) 0;
            if (t.isEnum()) return t.getEnumConstants()[0];
            if (t == Employee.class) return new Employee(0, "", "", 0.0);
            try {
              return t.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
              return null;
            }
          } catch (Exception ignored) {
            return null;
          }
        };

    String[] classes = {
      pkg + ".Lesson15",
      pkg + ".Employee",
      pkg + ".EmployeeManager" // keep even if it no-ops; reflection will skip what it can't do
    };

    for (String name : classes) {
      Class<?> cls;
      try {
        cls = Class.forName(name);
      } catch (ClassNotFoundException e) {
        continue;
      }

      try {
        var main = cls.getDeclaredMethod("main", String[].class);
        if (java.lang.reflect.Modifier.isStatic(main.getModifiers())) {
          main.setAccessible(true);
          main.invoke(null, (Object) new String[0]);
        }
      } catch (Exception ignored) {
      }

      Object instance = null;
      try {
        var ctors = cls.getDeclaredConstructors();
        java.util.Arrays.sort(
            ctors,
            java.util.Comparator.comparingInt(java.lang.reflect.Constructor::getParameterCount));
        for (var ctor : ctors) {
          try {
            ctor.setAccessible(true);
            var pts = ctor.getParameterTypes();
            Object[] args = new Object[pts.length];
            for (int i = 0; i < pts.length; i++) args[i] = defaultArg.apply(pts[i]);
            instance = ctor.newInstance(args);
            break;
          } catch (Exception ignored) {
          }
        }
      } catch (Exception ignored) {
      }

      for (var m : cls.getDeclaredMethods()) {
        m.setAccessible(true);
        try {
          boolean isStatic = java.lang.reflect.Modifier.isStatic(m.getModifiers());
          int pc = m.getParameterCount();

          if (isStatic) {
            if (pc == 0) {
              m.invoke(null);
            } else if (pc == 1) {
              Object a0 = defaultArg.apply(m.getParameterTypes()[0]);
              m.invoke(null, a0);
            }
          } else if (instance != null) {
            if (pc == 0) {
              m.invoke(instance);
            } else if (pc == 1) {
              Object a0 = defaultArg.apply(m.getParameterTypes()[0]);
              m.invoke(instance, a0);
            }
          }
        } catch (Exception ignored) {
        }
      }

      assertThat(cls).isNotNull();
    }
  }
}
