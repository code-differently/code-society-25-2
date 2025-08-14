/* global console */

import bcrypt from 'bcrypt';

const answers = ['B', 'B', 'C']; // Correct answers
const saltRounds = 10;

answers.forEach((answer) => {
  const hash = bcrypt.hashSync(answer, saltRounds);
  console.log(`Answer: ${answer} → ${hash}`);
});
