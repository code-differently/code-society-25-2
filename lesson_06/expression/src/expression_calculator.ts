export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    const intial_value:number = this.multiply(a, b);
    const second_value:number = this.pow(this.add(c,d),e);
    
    return this.divide(intial_value, second_value);
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  divide(a: number, b: number): number {
    if (b === 0) {
      throw new Error("Cannot divide by zero");
    }
    return a / b;
  }
  multiply(a: number, b: number): number {
    return a * b;
  }
  add(a: number, b: number): number {
    return a + b;
  }
  subtract(a: number, b: number): number {
    return a - b;
  }
}
