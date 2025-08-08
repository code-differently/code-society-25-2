#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Usage: $0 <markdown_file>"
  exit 1
fi

rm -f test.html

python3 conversion.py "$1"
