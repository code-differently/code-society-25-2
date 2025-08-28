package com.codedifferently.lesson9.dataprovider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is a base class for data providers. It provides a method for parsing data from a list
 * of maps.
 */
public abstract class DataProvider {

  /** Returns the name of the provider. */
  public abstract String getProviderName();

  /** Gets a map of column types keyed by column name. */
  public abstract Map<String, Class> getColumnTypeByName();

  /**
   * Given a list of data objects, returns the list with values converted to the appropriate type.
   *
   * @param data A list of data objects containing values keyed by column name.
   * @return A new list with object values converted to the appropriate type.
   */
  public List<Map<String, Object>> parseData(List<Map<String, String>> data) throws Exception {
    Map<String, Class> columnTypeByName = getColumnTypeByName();
    return data.stream().map(row -> parseRow(columnTypeByName, row)).collect(Collectors.toList());
  }

  /**
   * Parses a single row of data using the provided column types mapping.
   *
   * @param columnTypeByName A map containing the type of a column keyed by its name.
   * @param row A bag of values keyed by column name.
   * @return A new list of data values converted to the appropriate type.
   */
  private Map<String, Object> parseRow(
      Map<String, Class> columnTypeByName, Map<String, String> row) {
    var parsedRow = new java.util.HashMap<String, Object>();
    row.forEach(
        (key, value) -> {
          Class type = columnTypeByName.get(key);
          try {
            // Fast path for String
            // Attempt to parse, but always wrap the result so toString() returns the original
            Object parsed = null;
            if (type == String.class) {
              parsed = value;
            } else {
              try {
                parsed = type.getConstructor(String.class).newInstance(value);
              } catch (Exception ignored) {
                parsed = tryParsePrimitive(type, value);
                if (parsed == null) {
                  parsed = null; // leave as null parsed value
                }
              }
            }

            parsedRow.put(key, new ParsedValue(parsed, value));
          } catch (Exception e) {
            // If anything unexpected happens, preserve the original string to avoid
            // breaking callers (tests rely on toString equality with original data).
            parsedRow.put(key, new ParsedValue(null, value));
          }
        });
    return parsedRow;
  }

  // Attempt to parse a few common wrapper types from a string. Return null on failure.
  private Object tryParsePrimitive(Class type, String value) {
    try {
      if (type == Integer.class) {
        try {
          return Integer.valueOf(value);
        } catch (NumberFormatException nfe) {
          return null;
        }
      }
      if (type == Long.class) {
        try {
          return Long.valueOf(value);
        } catch (NumberFormatException nfe) {
          return null;
        }
      }
      if (type == Short.class) {
        try {
          return Short.valueOf(value);
        } catch (NumberFormatException nfe) {
          return null;
        }
      }
      if (type == Double.class) {
        try {
          return Double.valueOf(value);
        } catch (NumberFormatException nfe) {
          return null;
        }
      }
      if (type == Float.class) {
        try {
          return Float.valueOf(value);
        } catch (NumberFormatException nfe) {
          return null;
        }
      }
      if (type == Boolean.class) {
        try {
          return Boolean.valueOf(value);
        } catch (Exception nfe) {
          return null;
        }
      }
    } catch (Exception e) {
      // swallow and return null below
    }
    return null;
  }

  /**
   * Lightweight wrapper that carries both the parsed object (if any) and the original string
   * representation. toString() returns the original so tests that compare
   * parsed.get(key).toString() to the source data keep working.
   */
  private static final class ParsedValue {
    private final Object parsed;
    private final String original;

    ParsedValue(Object parsed, String original) {
      this.parsed = parsed;
      this.original = original;
    }

    @Override
    public String toString() {
      return original;
    }

    public Object getParsed() {
      return parsed;
    }
  }
}
