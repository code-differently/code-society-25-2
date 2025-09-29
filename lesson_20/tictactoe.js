class TicTacToe {
    constructor() {
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.gameActive = true;
        this.gameBoard = document.getElementById('game-board');
        this.currentPlayerDisplay = document.getElementById('current-player');
        this.gameStatus = document.getElementById('game-status');
        this.resetButton = document.getElementById('reset-button');
        
        this.winningConditions = [
            [0, 1, 2], // top row
            [3, 4, 5], // middle row
            [6, 7, 8], // bottom row
            [0, 3, 6], // left column
            [1, 4, 7], // middle column
            [2, 5, 8], // right column
            [0, 4, 8], // diagonal top-left to bottom-right
            [2, 4, 6]  // diagonal top-right to bottom-left
        ];
        
        this.initializeGame();
    }
    
    initializeGame() {
        this.createBoard();
        this.addEventListeners();
        this.updateDisplay();
    }
    
    createBoard() {
        this.gameBoard.innerHTML = '';
        for (let i = 0; i < 9; i++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.setAttribute('data-index', i);
            this.gameBoard.appendChild(cell);
        }
    }
    
    addEventListeners() {
        this.gameBoard.addEventListener('click', this.handleCellClick.bind(this));
        this.resetButton.addEventListener('click', this.resetGame.bind(this));
    }
    
    handleCellClick(event) {
        const cell = event.target;
        const index = parseInt(cell.getAttribute('data-index'));
        
        // Check if click is valid
        if (!cell.classList.contains('cell') || !this.gameActive || this.board[index] !== '') {
            return;
        }
        
        this.makeMove(index, cell);
    }
    
    makeMove(index, cell) {
        // Update board state
        this.board[index] = this.currentPlayer;
        
        // Update cell display
        cell.textContent = this.currentPlayer;
        cell.classList.add(this.currentPlayer.toLowerCase());
        cell.classList.add('disabled');
        
        // Check for win or draw
        if (this.checkWin()) {
            this.handleWin();
        } else if (this.checkDraw()) {
            this.handleDraw();
        } else {
            this.switchPlayer();
        }
        
        this.updateDisplay();
    }
    
    checkWin() {
        for (let condition of this.winningConditions) {
            const [a, b, c] = condition;
            if (this.board[a] && 
                this.board[a] === this.board[b] && 
                this.board[a] === this.board[c]) {
                this.winningCells = condition;
                return true;
            }
        }
        return false;
    }
    
    checkDraw() {
        return this.board.every(cell => cell !== '');
    }
    
    handleWin() {
        this.gameActive = false;
        this.gameStatus.textContent = `Player ${this.currentPlayer} Wins! 🎉`;
        this.highlightWinningCells();
        this.gameBoard.classList.add('game-over');
    }
    
    handleDraw() {
        this.gameActive = false;
        this.gameStatus.textContent = "It's a Draw! 🤝";
        this.gameBoard.classList.add('game-over');
    }
    
    highlightWinningCells() {
        if (this.winningCells) {
            this.winningCells.forEach(index => {
                const cell = this.gameBoard.children[index];
                cell.classList.add('winning-cell');
            });
        }
    }
    
    switchPlayer() {
        this.currentPlayer = this.currentPlayer === 'X' ? 'O' : 'X';
    }
    
    updateDisplay() {
        if (this.gameActive) {
            this.currentPlayerDisplay.textContent = `Current Player: ${this.currentPlayer}`;
        } else {
            this.currentPlayerDisplay.textContent = 'Game Over';
        }
    }
    
    resetGame() {
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.gameActive = true;
        this.winningCells = null;
        this.gameStatus.textContent = '';
        this.gameBoard.classList.remove('game-over');
        
        // Reset all cells
        const cells = this.gameBoard.querySelectorAll('.cell');
        cells.forEach(cell => {
            cell.textContent = '';
            cell.classList.remove('x', 'o', 'disabled', 'winning-cell');
        });
        
        this.updateDisplay();
    }
}

// Initialize the game when the DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    new TicTacToe();
});

// Additional utility functions for enhanced gameplay
function showGameRules() {
    alert(`Tic Tac Toe Rules:
    
1. The game is played on a 3x3 grid
2. Players take turns placing X's and O's
3. First player to get 3 of their marks in a row (horizontally, vertically, or diagonally) wins
4. If all 9 squares are filled and no one has won, it's a draw
5. Click on any empty cell to make your move
6. Click 'Reset Game' to start over`);
}

// Add keyboard support
document.addEventListener('keydown', (event) => {
    const key = event.key;
    if (key >= '1' && key <= '9') {
        const index = parseInt(key) - 1;
        const cell = document.querySelector(`[data-index="${index}"]`);
        if (cell) {
            cell.click();
        }
    } else if (key === 'r' || key === 'R') {
        document.getElementById('reset-button').click();
    }
});

// Add a help button functionality (optional)
function addHelpButton() {
    const helpButton = document.createElement('button');
    helpButton.textContent = 'How to Play';
    helpButton.style.marginLeft = '10px';
    helpButton.onclick = showGameRules;
    document.querySelector('.controls').appendChild(helpButton);
}

// Uncomment the next line if you want to add a help button
// document.addEventListener('DOMContentLoaded', addHelpButton);
