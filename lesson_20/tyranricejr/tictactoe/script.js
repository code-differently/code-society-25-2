const boardElement = document.getElementById('game-board');
const statusElement = document.getElementById('status');
const resetBtn = document.getElementById('reset-btn');
const scoreXElement = document.getElementById('score-x');
const scoreOElement = document.getElementById('score-o');

const boardWrapper = document.querySelector('.board-wrapper');

let board = Array(9).fill(null);
let firstPlayer = 'X'; // Track who starts
let currentPlayer = firstPlayer;
let gameActive = true;
let score = { X: 0, O: 0 };
let winLineDiv = null;
let confettiTimeout = null;

function renderBoard(winPattern = null) {
    boardElement.innerHTML = '';
    board.forEach((cell, idx) => {
        const cellDiv = document.createElement('div');
        cellDiv.className = 'cell';
        if (cell === 'X') cellDiv.classList.add('x');
        if (cell === 'O') cellDiv.classList.add('o');
        if (winPattern && winPattern.includes(idx)) cellDiv.style.background = '#fffde7';
        cellDiv.textContent = cell || '';
        cellDiv.addEventListener('click', () => handleCellClick(idx));
        boardElement.appendChild(cellDiv);
    });
}

function handleCellClick(idx) {
    if (!gameActive || board[idx]) return;
    board[idx] = currentPlayer;
    const winResult = checkWinner();
    renderBoard(winResult ? winResult.pattern : null);
    if (winResult) {
        statusElement.textContent = `${currentPlayer} wins!`;
        score[currentPlayer]++;
        updateScore();
        gameActive = false;
        showWinLine(winResult.pattern, winResult.player);
        launchConfetti(winResult.player);
    } else if (board.every(cell => cell)) {
        statusElement.textContent = "It's a draw!";
        gameActive = false;
    } else {
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
        statusElement.textContent = `Turn: ${currentPlayer}`;
    }
}

function checkWinner() {
    const winPatterns = [
        [0,1,2],[3,4,5],[6,7,8], // rows
        [0,3,6],[1,4,7],[2,5,8], // cols
        [0,4,8],[2,4,6]          // diags
    ];
    for (let pattern of winPatterns) {
        const [a, b, c] = pattern;
        if (board[a] && board[a] === board[b] && board[a] === board[c]) {
            return { player: board[a], pattern };
        }
    }
    return null;
}

function updateScore() {
    scoreXElement.textContent = `X: ${score.X}`;
    scoreOElement.textContent = `O: ${score.O}`;
}

function resetGame() {
    board = Array(9).fill(null);
    // Alternate first player
    firstPlayer = firstPlayer === 'X' ? 'O' : 'X';
    currentPlayer = firstPlayer;
    gameActive = true;
    statusElement.textContent = `Turn: ${currentPlayer}`;
    renderBoard();
    removeWinLine();
    clearConfetti();
}

resetBtn.addEventListener('click', resetGame);

// --- Confetti effect ---
function launchConfetti(winner) {
    clearConfetti();
    const colors = winner === 'X' ? ['#e53935', '#ffeb3b', '#fbc02d', '#43a047'] : ['#1e88e5', '#ffeb3b', '#fbc02d', '#43a047'];
    const rect = boardWrapper.getBoundingClientRect();
    for (let i = 0; i < 120; i++) { // More confetti
        const conf = document.createElement('div');
        conf.className = 'confetti';
        conf.style.background = colors[Math.floor(Math.random()*colors.length)];
        // Spread confetti more horizontally and vertically
        conf.style.left = (Math.random() * (rect.width + 80) - 40) + 'px';
        conf.style.top = (Math.random() * rect.height * 0.4 - 40) + 'px';
        const angle = Math.random()*360;
        conf.style.setProperty('--angle', angle + 'deg');
        conf.style.animation = `fall 1.7s linear forwards`;
        boardWrapper.appendChild(conf);
    }
    confettiTimeout = setTimeout(clearConfetti, 1900);
}
function clearConfetti() {
    document.querySelectorAll('.confetti').forEach(e => e.remove());
    if (confettiTimeout) clearTimeout(confettiTimeout);
}
// --- Win line effect ---
function showWinLine(pattern, winner) {
    removeWinLine();
    // Map pattern to line position
    const positions = {
        '0,1,2': {x1:0,y1:0.17,x2:1,y2:0.17}, // row 1
        '3,4,5': {x1:0,y1:0.5,x2:1,y2:0.5},   // row 2
        '6,7,8': {x1:0,y1:0.83,x2:1,y2:0.83}, // row 3
        '0,3,6': {x1:0.17,y1:0,x2:0.17,y2:1}, // col 1
        '1,4,7': {x1:0.5,y1:0,x2:0.5,y2:1},   // col 2
        '2,5,8': {x1:0.83,y1:0,x2:0.83,y2:1}, // col 3
        '0,4,8': {x1:0.08,y1:0.08,x2:0.92,y2:0.92}, // diag TL-BR
        '2,4,6': {x1:0.92,y1:0.08,x2:0.08,y2:0.92}  // diag TR-BL
    };
    const key = pattern.sort((a,b)=>a-b).join(',');
    let pos = positions[key];
    if (!pos) return;
    const width = boardElement.offsetWidth;
    const height = boardElement.offsetHeight;
    winLineDiv = document.createElement('div');
    winLineDiv.className = 'win-line ' + (winner === 'X' ? 'x' : 'o');
    winLineDiv.style.left = (pos.x1 * width) + boardElement.offsetLeft + 'px';
    winLineDiv.style.top = (pos.y1 * height) + boardElement.offsetTop + 'px';
    winLineDiv.style.width = Math.sqrt(Math.pow((pos.x2-pos.x1)*width,2)+Math.pow((pos.y2-pos.y1)*height,2)) + 'px';
    winLineDiv.style.transformOrigin = '0 0';
    const angle = Math.atan2((pos.y2-pos.y1)*height, (pos.x2-pos.x1)*width) * 180 / Math.PI;
    winLineDiv.style.transform = `rotate(${angle}deg)`;
    boardWrapper.appendChild(winLineDiv);
}
function removeWinLine() {
    if (winLineDiv && winLineDiv.parentNode) winLineDiv.parentNode.removeChild(winLineDiv);
    winLineDiv = null;
}

// Initial render
renderBoard();
statusElement.textContent = `Turn: ${currentPlayer}`;
updateScore();
