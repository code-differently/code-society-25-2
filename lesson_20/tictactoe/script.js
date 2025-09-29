const cells = document.querySelectorAll('.cell');
const statusDiv = document.getElementById('status');
const resetBtn = document.getElementById('reset');
let board = Array(9).fill('');
let currentPlayer = 'X';
let gameActive = true;

function checkWinner() {
    const winPatterns = [
        [0,1,2],[3,4,5],[6,7,8], // rows
        [0,3,6],[1,4,7],[2,5,8], // cols
        [0,4,8],[2,4,6]          // diags
    ];
    for (let pattern of winPatterns) {
        const [a, b, c] = pattern;
        if (board[a] && board[a] === board[b] && board[a] === board[c]) {
            return board[a];
        }
    }
    return board.includes('') ? null : 'draw';
}

function updateStatus() {
    const winner = checkWinner();
    if (winner === 'X' || winner === 'O') {
        statusDiv.textContent = `${winner} wins!`;
        gameActive = false;
    } else if (winner === 'draw') {
        statusDiv.textContent = "It's a draw!";
        gameActive = false;
    } else {
        statusDiv.textContent = `${currentPlayer}'s turn`;
    }
}

function handleCellClick(e) {
    const idx = +e.target.dataset.index;
    if (!gameActive || board[idx]) return;
    board[idx] = currentPlayer;
    e.target.textContent = currentPlayer;
    updateStatus();
    if (gameActive) currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
}

function resetGame() {
    board = Array(9).fill('');
    currentPlayer = 'X';
    gameActive = true;
    cells.forEach(cell => cell.textContent = '');
    updateStatus();
}

cells.forEach(cell => cell.addEventListener('click', handleCellClick));
resetBtn.addEventListener('click', resetGame);
updateStatus();
