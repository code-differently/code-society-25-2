import * as jest from "@jest/globals";
import { daysUntilBirthday, findLargestNumber, isPalindrome } from "./part_g.js";

const describe =
  !process.env.HW_VERSION || process.env.HW_VERSION === "G"
    ? jest.describe
    : jest.describe.skip;

describe("findLargestNumber", () => {
  it("should find the largest number in an array", () => {
    expect(findLargestNumber([1, 5, 3, 9, 2])).toBe(9);
    expect(findLargestNumber([10, 20, 5, 30])).toBe(30);
  });

  it("should handle arrays with one element", () => {
    expect(findLargestNumber([42])).toBe(42);
  });

  it("should handle negative numbers", () => {
    expect(findLargestNumber([-1, -5, -3])).toBe(-1);
  });

  it("should handle arrays with duplicate largest values", () => {
    expect(findLargestNumber([5, 5, 5])).toBe(5);
  });
});

describe("isPalindrome", () => {
  it("should return true for palindromes", () => {
    expect(isPalindrome("racecar")).toBe(true);
    expect(isPalindrome("A man a plan a canal Panama")).toBe(true);
    expect(isPalindrome("race a car")).toBe(false);
  });

  it("should ignore case", () => {
    expect(isPalindrome("Racecar")).toBe(true);
    expect(isPalindrome("RaceCar")).toBe(true);
  });

  it("should ignore spaces", () => {
    expect(isPalindrome("race car")).toBe(false);
    expect(isPalindrome("taco cat")).toBe(true);
  });

  it("should handle empty string", () => {
    expect(isPalindrome("")).toBe(true);
  });

  it("should handle single character", () => {
    expect(isPalindrome("a")).toBe(true);
  });
});

describe("daysUntilBirthday", () => {
  it("should calculate days until birthday in same year", () => {
    // Birthday is later in the same year
    expect(daysUntilBirthday(3, 15, 6, 15)).toBe(92); // March 15 to June 15 (roughly)
  });

  it("should calculate days until birthday next year", () => {
    // Birthday already passed this year
    expect(daysUntilBirthday(6, 15, 3, 15)).toBe(273); // June 15 to March 15 next year (roughly)
  });

  it("should return 0 if today is the birthday", () => {
    expect(daysUntilBirthday(5, 20, 5, 20)).toBe(0);
  });

  it("should handle birthdays tomorrow", () => {
    expect(daysUntilBirthday(5, 20, 5, 21)).toBe(1);
  });
});
