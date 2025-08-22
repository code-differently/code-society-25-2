export function findLargestNumber(numbers: number[]): number {
  if (numbers.length === 0) return 0;
  
  let largest = numbers[0];
  for (let i = 1; i < numbers.length; i++) {
    if (numbers[i] > largest) {
      largest = numbers[i];
    }
  }
  return largest;
}


  export function isPalindrome(text: string): boolean {
    const cleanText = text.replace(/\s/g, "").toLowerCase();
  
    for (let i = 0; i < cleanText.length / 2; i++) {
      if (cleanText[i] !== cleanText[cleanText.length - 1 - i]) {
        return false;
      }
    }
    return true;
  }
  
  export function daysUntilBirthday(
  currentMonth: number,
  currentDay: number,
  birthMonth: number,
  birthDay: number
): number {
  const daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

  const dayOfYear = (m: number, d: number): number => {
    let sum = 0;
    for (let i = 0; i < m - 1; i++) sum += daysInMonth[i];
    return sum + d;
  };

  const cur = dayOfYear(currentMonth, currentDay);
  const bday = dayOfYear(birthMonth, birthDay);

  if (cur === bday) return 0; // Today is the birthday

  let diff = bday - cur;
  if (diff < 0) diff += 365; // Birthday is next year

  return diff;
}