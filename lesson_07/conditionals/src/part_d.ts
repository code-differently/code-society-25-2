/**
 * Determine if a number is within the given range.
 *
 * @param num
 * @param min
 * @param max
 * @returns
 */
export function isWithinRange(num: number, min: number, max: number): boolean {
  return num >= min && num <= max;
}

/**
 * Determine if a shape is a triangle based on the given side lengths.
 *
 * @param a
 * @param b
 * @param c
 * @returns
 */
export function isValidTriangle(a: number, b: number, c: number): boolean {
  return Math.pow(a, 2) + Math.pow(b, 2) === Math.pow(c, 2);
}

/**
 * Determines what season it is based on the given month, or returns "Invalid month"
 * if the month is invalid. Month numbers range from 1 to 12.
 *
 * @param month
 * @returns
 */
export function getSeason(month: number): string {
  if (month >= 3 && month <= 5) {
    return "Spring";
  } else if (month >= 6 && month <= 8) {
    return "Summer";
  } else if (month >= 9 && month <= 11) {
    return "Fall";
  } else if (month === 12 || month === 1 || month === 2) {
    return "Winter";
  }

  return "Invalid month";
}
