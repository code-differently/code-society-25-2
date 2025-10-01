const WIN_LINES = [
  [0,1,2],[3,4,5],[6,7,8], // rows
  [0,3,6],[1,4,7],[2,5,8], // cols
  [0,4,8],[2,4,6]          // diags
];

const state = {
  board: Array(9).fill(null),
  currentPlayer: 'X',
  mode: 'PVP', 
  humanPlays: 'X', 
  isGameOver: false,
  winner: null,
  winningLine: null,
  score: { X: 0, O: 0, draws: 0 }
};

const cells = Array.from(document.querySelectorAll('.cell'));
const statusBar = document.getElementById('status');
const restartBtn = document.getElementById('restart-btn');
const modePvpBtn = document.getElementById('mode-pvp');
const modePvcBtn = document.getElementById('mode-pvc');
const sideChooser = document.getElementById('side-chooser');
const chooseXBtn = document.getElementById('choose-x');
const chooseOBtn = document.getElementById('choose-o');
const scoreX = document.getElementById('score-x');
const scoreO = document.getElementById('score-o');
const scoreDraws = document.getElementById('score-draws');

function render() {
  // Board
  state.board.forEach((mark, i) => {
    cells[i].textContent = mark || '';
    cells[i].className = 'cell';
    if (mark === 'X') cells[i].classList.add('is-x');
    if (mark === 'O') cells[i].classList.add('is-o');
    cells[i].disabled = !!mark || state.isGameOver;
    cells[i].setAttribute('aria-disabled', !!mark || state.isGameOver);
    if (state.winningLine && state.winningLine.includes(i)) {
      cells[i].classList.add('win');
    }
    if (state.isGameOver && !state.winner) {
      cells[i].classList.add('draw');
    }
  });
  
  if (state.isGameOver) {
    if (state.winner) {
      statusBar.textContent = `${state.winner} wins!`;
    } else {
      statusBar.textContent = `It's a draw.`;
    }
  } else {
    statusBar.textContent = `${state.currentPlayer}’s turn`;
  }
  
  scoreX.textContent = state.score.X;
  scoreO.textContent = state.score.O;
  scoreDraws.textContent = state.score.draws;
}

function checkWinner() {
  for (const line of WIN_LINES) {
    const [a, b, c] = line;
    if (
      state.board[a] &&
      state.board[a] === state.board[b] &&
      state.board[a] === state.board[c]
    ) {
      state.isGameOver = true;
      state.winner = state.board[a];
      state.winningLine = line;
      state.score[state.winner]++;
      return;
    }
  }
  if (!state.board.includes(null)) {
    state.isGameOver = true;
    state.winner = null;
    state.winningLine = null;
    state.score.draws++;
  }
}

function handleCellClick(e) {
  const idx = Number(e.target.dataset.index);
  if (state.board[idx] || state.isGameOver) return;
  state.board[idx] = state.currentPlayer;
  checkWinner();
  if (!state.isGameOver) {
    state.currentPlayer = state.currentPlayer === 'X' ? 'O' : 'X';
    if (state.mode === 'PVC' && state.currentPlayer !== state.humanPlays) {
      setTimeout(aiMove, 350);
    }
  }
  render();
}

function aiMove() {
  
  const empties = state.board.map((v, i) => v ? null : i).filter(i => i !== null);
  if (empties.length === 0) return;
  const move = empties[Math.floor(Math.random() * empties.length)];
  state.board[move] = state.currentPlayer;
  checkWinner();
  if (!state.isGameOver) {
    state.currentPlayer = state.currentPlayer === 'X' ? 'O' : 'X';
  }
  render();
}

function restartGame() {
  state.board = Array(9).fill(null);
  state.currentPlayer = 'X';
  state.isGameOver = false;
  state.winner = null;
  state.winningLine = null;
  render();
}

function setMode(mode) {
  state.mode = mode;
  modePvpBtn.setAttribute('aria-pressed', mode === 'PVP');
  modePvcBtn.setAttribute('aria-pressed', mode === 'PVC');
  sideChooser.hidden = mode !== 'PVC';
  if (mode === 'PVC') {
    state.humanPlays = chooseXBtn.getAttribute('aria-pressed') === 'true' ? 'X' : 'O';
    state.currentPlayer = 'X';
  }
  restartGame();
}

function setSide(side) {
  chooseXBtn.setAttribute('aria-pressed', side === 'X');
  chooseOBtn.setAttribute('aria-pressed', side === 'O');
  state.humanPlays = side;
  state.currentPlayer = 'X';
  restartGame();
}

cells.forEach(cell => {
  cell.addEventListener('click', handleCellClick);
  cell.addEventListener('keydown', e => {
    if ((e.key === 'Enter' || e.key === ' ') && !cell.disabled) {
      handleCellClick({ target: cell });
    }
    
    const idx = Number(cell.dataset.index);
    let next;
    switch (e.key) {
      case 'ArrowUp': next = (idx + 6) % 9; break;
      case 'ArrowDown': next = (idx + 3) % 9; break;
      case 'ArrowLeft': next = (idx + 8) % 9; break;
      case 'ArrowRight': next = (idx + 1) % 9; break;
      default: return;
    }
    cells[next].focus();
    e.preventDefault();
  });
});

restartBtn.addEventListener('click', restartGame);
modePvpBtn.addEventListener('click', () => setMode('PVP'));
modePvcBtn.addEventListener('click', () => setMode('PVC'));
chooseXBtn.addEventListener('click', () => setSide('X'));
chooseOBtn.addEventListener('click', () => setSide('O'));

render();