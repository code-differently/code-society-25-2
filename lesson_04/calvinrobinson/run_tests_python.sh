#!/usr/bin/env bash
# Simple runnable tests without pytest
python3 - <<'PY'
from is_prime import is_prime
cases = [(0, False),(1, False),(2, True),(3, True),(4, False),(17, True),(18, False),(7919, True)]
all_ok = True
for n, exp in cases:
    got = is_prime(n)
    print(f"{n}: expected={exp} got={got}")
    if got != exp:
        all_ok = False
print('OK' if all_ok else 'FAIL')
PY
