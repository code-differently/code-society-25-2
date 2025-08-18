export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    return this.divide(this.pow(this.add(a, b), c), this.multiply(d, e));
  }

add(a: number, b: number): number {
    return a + b;
  }

multiply(d: number, e: number): number {
    return d * e;
  }  

pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

divide(a: number, b: number): number {
    if (b === 0) {
      throw new Error("Division by zero is not allowed.");
    }
    return a / b;

  } 

}
