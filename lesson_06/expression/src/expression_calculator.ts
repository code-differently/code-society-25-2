export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    // Function to implement: a * b / MAtjh.pow(c + d, e)

    const answer = this.divide(
      this.multiply(a, b),
      this.pow(this.add(c, d), e),
    );
    return answer;
  }

  add(a: number, b: number): number {
    return a + b;
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  divide(a: number, b: number): number {
    if (b === 0) {
      throw new Error("Division by zero is not allowed.");
    }
    return a / b;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
