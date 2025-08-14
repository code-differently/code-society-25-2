import bcrypt from 'bcrypt';

const answers = [
  {
    letter: 'B',
    hash: '$2b$10$ta73QpJyckVs8ycMG3SXmuy44q.ZxmCMoqzvCd6jIojaykqzxbQOm',
  },
  {
    letter: 'B',
    hash: '$2b$10$NB928Rk3CGBERrOFfzFuWeJca9xs2DKfljmOm2nvkcZZfEu7C00Iu',
  },
  {
    letter: 'C',
    hash: '$2b$10$vU9Fk0AiaA/B0r2ur0XyT.sYefBQDBoTdDmp6dkRKSuRjQtCWyuIu',
  },
];

answers.forEach((a, i) => {
  const match = bcrypt.compareSync(a.letter, a.hash);
  console.log(`Question ${i}: ${match ? '✅ matches' : '❌ does not match'}`);
});
