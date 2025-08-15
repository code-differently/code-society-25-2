"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isPrime = isPrime;
function isPrime(n) {
    return n > 1 && Array.from({ length: Math.floor(Math.sqrt(n)) - 1 }, (_, i) => i + 2)
        .every(i => n % i !== 0);
}
//# sourceMappingURL=prime.js.map