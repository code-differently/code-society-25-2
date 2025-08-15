"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const prime_1 = require("../src/prime");
describe("isPrime (super-condensed)", () => {
    test.each([2, 3, 5])("returns true for prime number %i", (n) => {
        expect((0, prime_1.isPrime)(n)).toBe(true);
    });
    test.each([0, 1, -7, 4, 6])("returns false for non-prime number %i", (n) => {
        expect((0, prime_1.isPrime)(n)).toBe(false);
    });
});
//# sourceMappingURL=prime_test.js.map