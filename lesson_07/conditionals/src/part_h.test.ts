import * as jest from "@jest/globals";
import { convertTemperature, findGCD, generateMultiplicationTable } from "./part_h.js";

const describe =
  !process.env.HW_VERSION || process.env.HW_VERSION === "H"
    ? jest.describe
    : jest.describe.skip;

describe("generateMultiplicationTable", () => {
  it("should generate multiplication table for a given number", () => {
    const result = generateMultiplicationTable(3);
    expect(result).toEqual([
      "3 x 1 = 3",
      "3 x 2 = 6",
      "3 x 3 = 9",
      "3 x 4 = 12",
      "3 x 5 = 15",
      "3 x 6 = 18",
      "3 x 7 = 21",
      "3 x 8 = 24",
      "3 x 9 = 27",
      "3 x 10 = 30"
    ]);
  });

  it("should handle multiplication table for 1", () => {
    const result = generateMultiplicationTable(1);
    expect(result[0]).toBe("1 x 1 = 1");
    expect(result[9]).toBe("1 x 10 = 10");
    expect(result.length).toBe(10);
  });
});

describe("findGCD", () => {
  it("should find GCD of two positive numbers", () => {
    expect(findGCD(12, 8)).toBe(4);
    expect(findGCD(15, 25)).toBe(5);
    expect(findGCD(17, 13)).toBe(1);
    expect(findGCD(100, 75)).toBe(25);
  });

  it("should handle when one number is zero", () => {
    expect(findGCD(5, 0)).toBe(5);
    expect(findGCD(0, 7)).toBe(7);
  });

  it("should handle identical numbers", () => {
    expect(findGCD(7, 7)).toBe(7);
    expect(findGCD(20, 20)).toBe(20);
  });

  it("should handle when one number divides the other", () => {
    expect(findGCD(20, 5)).toBe(5);
    expect(findGCD(6, 18)).toBe(6);
  });

  it("should handle order independence", () => {
    expect(findGCD(8, 12)).toBe(4);
    expect(findGCD(12, 8)).toBe(4);
  });
});

describe("convertTemperature", () => {
  it("should convert Celsius to Fahrenheit", () => {
    expect(convertTemperature(0, "C", "F")).toBe(32);
    expect(convertTemperature(100, "C", "F")).toBe(212);
    expect(convertTemperature(25, "C", "F")).toBe(77);
  });

  it("should convert Fahrenheit to Celsius", () => {
    expect(convertTemperature(32, "F", "C")).toBe(0);
    expect(convertTemperature(212, "F", "C")).toBe(100);
  });

  it("should convert Celsius to Kelvin", () => {
    expect(convertTemperature(0, "C", "K")).toBe(273.15);
    expect(convertTemperature(25, "C", "K")).toBe(298.15);
  });

  it("should convert Kelvin to Celsius", () => {
    expect(convertTemperature(273.15, "K", "C")).toBe(0);
    expect(convertTemperature(298.15, "K", "C")).toBe(25);
  });

  it("should return same temperature for same scales", () => {
    expect(convertTemperature(25, "C", "C")).toBe(25);
    expect(convertTemperature(77, "F", "F")).toBe(77);
  });

  it("should handle complex conversions", () => {
    // F to K: (77°F - 32) × 5/9 + 273.15 = 298.15K
    expect(convertTemperature(77, "F", "K")).toBeCloseTo(298.15, 2);
    // K to F: (298.15K - 273.15) × 9/5 + 32 = 77°F
    expect(convertTemperature(298.15, "K", "F")).toBeCloseTo(77, 2);
  });
});
