document.addEventListener('DOMContentLoaded', function() {
    initializeTicTacToe();
});

function initializeTicTacToe() {
    const cells = document.querySelectorAll('.cell');
    const gameMessage = document.getElementById('game-message');
    const resetButton = document.getElementById('reset-button');
    const playerXIndicator = document.getElementById('player-x');
    const playerOIndicator = document.getElementById('player-o');
    
    let currentPlayer = 'X';
    let gameBoard = ['', '', '', '', '', '', '', '', ''];
    let gameActive = true;
    
    const winningConditions = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];
    
    function updatePlayerIndicator() {
        if (currentPlayer === 'X') {
            playerXIndicator.classList.add('active');
            playerOIndicator.classList.remove('active');
        } else {
            playerOIndicator.classList.add('active');
            playerXIndicator.classList.remove('active');
        }
    }
    
    function handleCellClick(event) {
        const cell = event.target;
        const cellIndex = parseInt(cell.getAttribute('data-index'));
        
        if (gameBoard[cellIndex] !== '' || !gameActive) {
            return;
        }
        
        gameBoard[cellIndex] = currentPlayer;
        cell.textContent = currentPlayer;
        cell.classList.add(currentPlayer.toLowerCase());
        cell.classList.add('disabled');
        
        if (checkWinner()) {
            gameMessage.textContent = `Player ${currentPlayer} Wins!`;
            gameMessage.classList.add('winner');
            gameActive = false;
            disableAllCells();
            return;
        }
        
        if (checkDraw()) {
            gameMessage.textContent = "It's a Draw!";
            gameActive = false;
            return;
        }
        
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
        gameMessage.textContent = `Player ${currentPlayer}'s Turn`;
        updatePlayerIndicator();
    }
    
    function checkWinner() {
        return winningConditions.some(condition => {
            const [a, b, c] = condition;
            return gameBoard[a] && gameBoard[a] === gameBoard[b] && gameBoard[a] === gameBoard[c];
        });
    }
    
    function checkDraw() {
        return gameBoard.every(cell => cell !== '');
    }
    
    function disableAllCells() {
        cells.forEach(cell => {
            cell.classList.add('disabled');
        });
    }
    
    function resetGame() {
        currentPlayer = 'X';
        gameBoard = ['', '', '', '', '', '', '', '', ''];
        gameActive = true;
        gameMessage.textContent = "Player X's Turn";
        gameMessage.classList.remove('winner');
        
        cells.forEach(cell => {
            cell.textContent = '';
            cell.classList.remove('x', 'o', 'disabled');
        });
        
        updatePlayerIndicator();
    }
    
    cells.forEach(cell => {
        cell.addEventListener('click', handleCellClick);
    });
    
    resetButton.addEventListener('click', resetGame);
    
    updatePlayerIndicator();
}
