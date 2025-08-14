# Explanation

We were able to build the same tool in two different languages! Even though they look a bit different, Python `is_prime` function and JavaScript `isPrime` function actually have a lot in common.

---

## Similarities

At their core, both functions do the same job, they figure out if a number is prime or not. They both use the same **logic** to do this, which is important for finding prime numbers efficiently:

* **Handling Small Numbers:** Both codes know that numbers less than or equal to 1 are not prime. They also both correctly identify that the number 2 is the only even prime number.

* **Skipping Even Numbers:** After checking for 2, both functions say that any other even number is not prime.

* **Checking Divisors Efficiently:** Both use a loop that goes up to the square root of the number. You only need to check for divisors up to that point. If a number has a divisor larger than its square root, it must also have one smaller than its square root, which would have already been found.

* **Returning True/False:** Both functions return a true or false answer to tell you if the number is prime.

---

## Differences

While the core function is the same, Python and JavaScript have their own rules, which lead to these differences:

* **How You Start a Function:**

  * In Python, you say `def` to start a function, like `def is_prime(number)`.

  * In JavaScript, you say `function` to start one, like `function isPrime(number) {`. There are curly braces `{}` in JavaScript to show where the function's code begins and ends, whereas Python uses indentation.

* **True/False Words:**

  * Python uses `True` and `False` with capital first letters.

  * JavaScript uses `true` and `false` all lowercase.

* **Printing Things Out:**

  * To show results in Python, you use `print()`.

  * In JavaScript, you use `console.log()`.

* **Variable Setup:**

  * In Python, you just name a variable and give it a value.

  * In JavaScript, you often use `let` or `const` when you first introduce a variable.