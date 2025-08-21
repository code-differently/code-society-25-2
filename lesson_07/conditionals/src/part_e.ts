/**
 * Write a function that takes a single character as an argument and
 * returns boolean value true if the character is an uppercase letter.
 *
 * @param char
 * @returns
 */
export function isUppercase(char: string): boolean {
  if (char.length !== 1) {
    return false; 
  }
  return char === char.toUpperCase() && char !== char.toLowerCase();
}

/**
 * Determine if a person is eligible for a driving license (age and test passed).
 *
 * @param age
 * @param passedTest
 * @returns
 */
export function canGetDriverLicense(age: number, passedTest: boolean): boolean {
  if (age >= 18 && passedTest) {
    return true;
  }
  else{
    return false;
  }
}

/**
 * Check if a store is open based on the day and time. The store is open
 * Monday to Saturday from 9 AM to 9 PM.
 *
 * @param day
 * @param hour
 * @returns
 */
export function isStoreOpen(day: string, hour: number): boolean {
  if (day === "Sunday") {
    return false;
  }
  return hour > 9 && hour < 21;
}

