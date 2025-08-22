import * as jest from "@jest/globals";
import { countCharacter, getLetterGrade, sumEvenNumbers } from "./part_f.js";

const describe =
  !process.env.HW_VERSION || process.env.HW_VERSION === "F"
    ? jest.describe
    : jest.describe.skip;

describe("getLetterGrade", () => {
  it("should return 'A' for scores 90-100", () => {
    expect(getLetterGrade(95)).toBe("A");
    expect(getLetterGrade(90)).toBe("A");
    expect(getLetterGrade(100)).toBe("A");
  });

  it("should return 'B' for scores 80-89", () => {
    expect(getLetterGrade(85)).toBe("B");
    expect(getLetterGrade(80)).toBe("B");
    expect(getLetterGrade(89)).toBe("B");
  });

  it("should return 'C' for scores 70-79", () => {
    expect(getLetterGrade(75)).toBe("C");
    expect(getLetterGrade(70)).toBe("C");
    expect(getLetterGrade(79)).toBe("C");
  });

  it("should return 'D' for scores 60-69", () => {
    expect(getLetterGrade(65)).toBe("D");
    expect(getLetterGrade(60)).toBe("D");
    expect(getLetterGrade(69)).toBe("D");
  });

  it("should return 'F' for scores below 60", () => {
    expect(getLetterGrade(59)).toBe("F");
    expect(getLetterGrade(0)).toBe("F");
    expect(getLetterGrade(45)).toBe("F");
  });
});

describe("sumEvenNumbers", () => {
  it("should return 0 for an empty array", () => {
    expect(sumEvenNumbers([])).toBe(0);
  });

  it("should return the sum of even numbers", () => {
    expect(sumEvenNumbers([1, 2, 3, 4, 5, 6])).toBe(12); // 2 + 4 + 6
    expect(sumEvenNumbers([2, 4, 6, 8])).toBe(20);
  });

  it("should return 0 if no even numbers exist", () => {
    expect(sumEvenNumbers([1, 3, 5, 7])).toBe(0);
  });

  it("should handle negative even numbers", () => {
    expect(sumEvenNumbers([-2, -1, 0, 1, 2])).toBe(0); // -2 + 0 + 2
  });
});

describe("countCharacter", () => {
  it("should count occurrences of a character", () => {
    expect(countCharacter("hello", "l")).toBe(2);
    expect(countCharacter("hello", "o")).toBe(1);
    expect(countCharacter("hello", "h")).toBe(1);
  });

  it("should return 0 if character is not found", () => {
    expect(countCharacter("hello", "x")).toBe(0);
  });

  it("should handle empty string", () => {
    expect(countCharacter("", "a")).toBe(0);
  });

  it("should be case sensitive", () => {
    expect(countCharacter("Hello", "h")).toBe(0);
    expect(countCharacter("Hello", "H")).toBe(1);
  });
});
