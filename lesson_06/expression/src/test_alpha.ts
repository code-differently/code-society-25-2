import { isValidAlphaAbbreviation } from "./alpha_abbreviation.js";

function test(word: string, abbr: string, expected: boolean) {
  const result = isValidAlphaAbbreviation(word, abbr);
  const status = result === expected ? "✅ PASS" : "❌ FAIL";
  console.log(
    `${status} | Word: "${word}" | Abbr: "${abbr}" → Expected: ${expected}, Got: ${result}`,
  );
}

// === Basic valid cases ===
test("internationalization", "i18n", true);
test("substitution", "s10n", true);
test("hello", "4o", true); // skip "e", "l", "l", "o"
test("abcde", "4e", true); // skip "b", "c", "d", arrive at "e"

// === Literal-only match ===
test("dog", "dog", true);
test("cat", "cut", false); // mismatch at "a" vs "u"

// === Skip everything ===
test("abcdef", "6", true); // skip entire word
test("abcdef", "7", false); // skip past word length
test("abcdef", "5g", true); // skip to last letter and match

// === Mixed skip + literal ===
test("compression", "c9n", true);
test("transformation", "t12n", true);
test("transformation", "t13n", false); // too much skip

// === Zero padding (invalid) ===
test("test", "01st", false);
test("skiptest", "s00t", false);

// === Leading and trailing skips ===
test("abcdef", "1bcdef", false); // can't skip and expect literal 'b' immediately
test("abcdef", "a5", true); // literal 'a', skip 5 to end
test("abcdef", "a6", false); // skip past end

// === Multiple number segments ===
test("abcdefg", "1b1d1f1g", true); // skips then matches
test("abcdefg", "1b2e1g", true);
test("abcdefg", "1b3f1g", false); // overshoots

// === Full numeric — skips entire word
test("skipped", "7", true);
test("skipped", "8", false); // overshoot

// === Empty abbreviation or word ===
test("word", "", false); // must match full
test("", "", true); // both empty is valid
test("", "1", false); // can't skip into empty
test("nonempty", "", false); // abbreviation is empty

// === Misc edge junk ===
test("abc", "a01c", false); // invalid leading 0
test("abc", "a0b", false); // zero is illegal
test("abc", "a1b", true); // skip one
