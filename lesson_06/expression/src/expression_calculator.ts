export class ExpressionCalculator {
  add(a: number, b: number): number {
    return a + b;
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  divide(a: number, b: number): number {
    if (b === 0) throw new Error("Cannot divide by zero");
    return a / b;
  }

  pow(a: number, b: number): number {
    return Math.pow(a, b);
  }

  calculate(a: number, b: number, c: number, d: number, e: number): number {
    const step1 = this.add(a, b);
    const step2 = this.multiply(step1, c);
    const step3 = this.pow(d, e);
    const result = this.divide(step2, step3);
    return result;
  }
}
