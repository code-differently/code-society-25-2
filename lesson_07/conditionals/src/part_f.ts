/**
 * Determines the grade letter based on a numeric score.
 * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: below 60
 *
 * @param score
 * @returns
 */
export function getLetterGrade(score: number): string {
   if (score >= 90 && score <= 100) {
    return "A";
  } else if (score >= 80 && score <= 89) {
    return "B";
  } else if (score >= 70 && score <= 79) {
    return "C";
  } else if (score >= 60 && score <= 69) {
    return "D";
  } else if (score < 60) {
    return "F";
  } else {


/**
 * Calculates the sum of all even numbers in the given array.
 *
 * @param numbers
 * @returns
 */
export function sumEvenNumbers(numbers: number[]): number {
  return 0;
}

/**
 * Counts how many times a specific character appears in a string.
 *
 * @param text
 * @param character
 * @returns
 */
export function countCharacter(text: string, character: string): number {
  return 0;
}
