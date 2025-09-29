// Tic Tac Toe Game - Vanilla JavaScript Implementation
// No external libraries - pure JavaScript

class TicTacToe {
    constructor() {
        // Game state
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.gameMode = 'pvp'; // 'pvp' or 'pvc'
        this.difficulty = 'hard';
        this.isGameActive = true;
        this.moveHistory = [];
        
        // Score tracking
        this.scores = {
            X: 0,
            O: 0,
            draw: 0
        };
        
        // Winning combinations
        this.winningCombinations = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
            [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
            [0, 4, 8], [2, 4, 6] // Diagonals
        ];
        
        // DOM elements
        this.gameBoard = document.getElementById('gameBoard');
        this.cells = document.querySelectorAll('.cell');
        this.currentPlayerDisplay = document.getElementById('currentPlayer');
        this.gameStatus = document.getElementById('gameStatus');
        this.scoreX = document.getElementById('scoreX');
        this.scoreO = document.getElementById('scoreO');
        this.scoreDraw = document.getElementById('scoreDraw');
        this.modal = document.getElementById('modalOverlay');
        this.modalTitle = document.getElementById('modalTitle');
        this.modalMessage = document.getElementById('modalMessage');
        this.winningLine = document.getElementById('winningLine');
        
        this.initializeGame();
    }
    
    initializeGame() {
        this.setupEventListeners();
        this.updateDisplay();
        this.loadScores();
    }
    
    setupEventListeners() {
        // Cell clicks
        this.cells.forEach((cell, index) => {
            cell.addEventListener('click', () => this.handleCellClick(index));
            cell.setAttribute('tabindex', '0');
            cell.addEventListener('keydown', (e) => {
                if (e.key === 'Enter' || e.key === ' ') {
                    e.preventDefault();
                    this.handleCellClick(index);
                }
            });
        });
        
        // Control buttons
        document.getElementById('resetGame').addEventListener('click', () => this.resetGame());
        document.getElementById('resetScore').addEventListener('click', () => this.resetScore());
        document.getElementById('undoMove').addEventListener('click', () => this.undoMove());
        
        // Game mode buttons
        document.querySelectorAll('.mode-btn').forEach(btn => {
            btn.addEventListener('click', (e) => this.setGameMode(e.target.dataset.mode));
        });
        
        // Difficulty selector
        document.getElementById('difficulty').addEventListener('change', (e) => {
            this.difficulty = e.target.value;
        });
        
        // Modal buttons
        document.getElementById('modalNewGame').addEventListener('click', () => {
            this.hideModal();
            this.resetGame();
        });
        document.getElementById('modalClose').addEventListener('click', () => this.hideModal());
        
        // Close modal on overlay click
        this.modal.addEventListener('click', (e) => {
            if (e.target === this.modal) {
                this.hideModal();
            }
        });
        
        // Keyboard navigation
        document.addEventListener('keydown', (e) => this.handleKeyboardNavigation(e));
    }
    
    handleCellClick(index) {
        if (!this.isGameActive || this.board[index] !== '') {
            return;
        }
        
        this.makeMove(index, this.currentPlayer);
        
        // Check for game end
        if (this.checkWin() || this.checkDraw()) {
            return;
        }
        
        // Switch players
        this.switchPlayer();
        
        // AI move if in PvC mode and it's O's turn
        if (this.gameMode === 'pvc' && this.currentPlayer === 'O' && this.isGameActive) {
            setTimeout(() => {
                this.makeAIMove();
            }, 500); // Small delay for better UX
        }
    }
    
    makeMove(index, player) {
        this.board[index] = player;
        this.moveHistory.push({ index, player });
        
        const cell = this.cells[index];
        cell.textContent = player;
        cell.classList.add(player.toLowerCase(), 'occupied');
        
        // Animate cell
        cell.style.animation = `cellFill${player} 0.5s ease-out`;
        
        this.updateUndoButton();
        this.announceMove(index, player);
    }
    
    switchPlayer() {
        this.currentPlayer = this.currentPlayer === 'X' ? 'O' : 'X';
        this.updateDisplay();
    }
    
    checkWin() {
        for (let combination of this.winningCombinations) {
            const [a, b, c] = combination;
            if (this.board[a] && this.board[a] === this.board[b] && this.board[a] === this.board[c]) {
                this.handleWin(combination);
                return true;
            }
        }
        return false;
    }
    
    checkDraw() {
        if (this.board.every(cell => cell !== '')) {
            this.handleDraw();
            return true;
        }
        return false;
    }
    
    handleWin(winningCombination) {
        this.isGameActive = false;
        const winner = this.board[winningCombination[0]];
        
        // Highlight winning cells
        winningCombination.forEach(index => {
            this.cells[index].classList.add('winning');
        });
        
        // Draw winning line
        this.drawWinningLine(winningCombination);
        
        // Update score
        this.scores[winner]++;
        this.updateScoreDisplay();
        this.saveScores();
        
        // Update status
        this.gameStatus.textContent = `Player ${winner} wins!`;
        
        // Show modal after animation
        setTimeout(() => {
            this.showWinModal(winner);
        }, 1000);
        
        this.announceWin(winner);
    }
    
    handleDraw() {
        this.isGameActive = false;
        this.scores.draw++;
        this.updateScoreDisplay();
        this.saveScores();
        
        this.gameStatus.textContent = "It's a draw!";
        
        setTimeout(() => {
            this.showDrawModal();
        }, 500);
        
        this.announceChange("Game ended in a draw");
    }
    
    drawWinningLine(combination) {
        const line = this.winningLine;
        const [a, b, c] = combination;
        
        // Calculate line position and rotation
        const cellSize = 120;
        const gap = 4;
        const boardPadding = 4;
        
        let lineStyle = {
            width: '4px',
            height: `${cellSize * 3 + gap * 2}px`,
            left: '50%',
            top: '50%',
            transform: 'translate(-50%, -50%)'
        };
        
        // Determine line type and adjust styles
        if (a === 0 && b === 1 && c === 2) { // Top row
            lineStyle = { width: `${cellSize * 3 + gap * 2}px`, height: '4px', left: '50%', top: `${cellSize/2 + boardPadding}px`, transform: 'translateX(-50%)' };
        } else if (a === 3 && b === 4 && c === 5) { // Middle row
            lineStyle = { width: `${cellSize * 3 + gap * 2}px`, height: '4px', left: '50%', top: '50%', transform: 'translate(-50%, -50%)' };
        } else if (a === 6 && b === 7 && c === 8) { // Bottom row
            lineStyle = { width: `${cellSize * 3 + gap * 2}px`, height: '4px', left: '50%', bottom: `${cellSize/2 + boardPadding}px`, transform: 'translateX(-50%)' };
        } else if (a === 0 && b === 3 && c === 6) { // Left column
            lineStyle = { width: '4px', height: `${cellSize * 3 + gap * 2}px`, left: `${cellSize/2 + boardPadding}px`, top: '50%', transform: 'translateY(-50%)' };
        } else if (a === 1 && b === 4 && c === 7) { // Middle column
            lineStyle = { width: '4px', height: `${cellSize * 3 + gap * 2}px`, left: '50%', top: '50%', transform: 'translate(-50%, -50%)' };
        } else if (a === 2 && b === 5 && c === 8) { // Right column
            lineStyle = { width: '4px', height: `${cellSize * 3 + gap * 2}px`, right: `${cellSize/2 + boardPadding}px`, top: '50%', transform: 'translateY(-50%)' };
        } else if (a === 0 && b === 4 && c === 8) { // Main diagonal
            const diagonalLength = Math.sqrt(2) * (cellSize * 3 + gap * 2);
            lineStyle = { width: '4px', height: `${diagonalLength}px`, left: '50%', top: '50%', transform: 'translate(-50%, -50%) rotate(45deg)' };
        } else if (a === 2 && b === 4 && c === 6) { // Anti-diagonal
            const diagonalLength = Math.sqrt(2) * (cellSize * 3 + gap * 2);
            lineStyle = { width: '4px', height: `${diagonalLength}px`, left: '50%', top: '50%', transform: 'translate(-50%, -50%) rotate(-45deg)' };
        }
        
        // Apply styles
        Object.assign(line.style, lineStyle);
        line.classList.add('active');
    }
    
    makeAIMove() {
        if (!this.isGameActive) return;
        
        let move;
        
        switch (this.difficulty) {
            case 'easy':
                move = this.getRandomMove();
                break;
            case 'medium':
                move = Math.random() < 0.7 ? this.getBestMove() : this.getRandomMove();
                break;
            case 'hard':
                move = this.getBestMove();
                break;
        }
        
        if (move !== -1) {
            this.makeMove(move, 'O');
            
            if (this.checkWin() || this.checkDraw()) {
                return;
            }
            
            this.switchPlayer();
        }
    }
    
    getRandomMove() {
        const availableMoves = [];
        this.board.forEach((cell, index) => {
            if (cell === '') availableMoves.push(index);
        });
        
        return availableMoves.length > 0 ? 
            availableMoves[Math.floor(Math.random() * availableMoves.length)] : -1;
    }
    
    getBestMove() {
        // Minimax algorithm implementation
        const minimax = (board, depth, isMaximizing, alpha = -Infinity, beta = Infinity) => {
            // Check terminal states
            const winner = this.evaluateBoard(board);
            if (winner === 'O') return 10 - depth;
            if (winner === 'X') return depth - 10;
            if (board.every(cell => cell !== '')) return 0;
            
            if (isMaximizing) {
                let maxEval = -Infinity;
                for (let i = 0; i < 9; i++) {
                    if (board[i] === '') {
                        board[i] = 'O';
                        const evaluation = minimax(board, depth + 1, false, alpha, beta);
                        board[i] = '';
                        maxEval = Math.max(maxEval, evaluation);
                        alpha = Math.max(alpha, evaluation);
                        if (beta <= alpha) break; // Alpha-beta pruning
                    }
                }
                return maxEval;
            } else {
                let minEval = Infinity;
                for (let i = 0; i < 9; i++) {
                    if (board[i] === '') {
                        board[i] = 'X';
                        const evaluation = minimax(board, depth + 1, true, alpha, beta);
                        board[i] = '';
                        minEval = Math.min(minEval, evaluation);
                        beta = Math.min(beta, evaluation);
                        if (beta <= alpha) break; // Alpha-beta pruning
                    }
                }
                return minEval;
            }
        };
        
        let bestMove = -1;
        let bestValue = -Infinity;
        const boardCopy = [...this.board];
        
        for (let i = 0; i < 9; i++) {
            if (boardCopy[i] === '') {
                boardCopy[i] = 'O';
                const moveValue = minimax(boardCopy, 0, false);
                boardCopy[i] = '';
                
                if (moveValue > bestValue) {
                    bestMove = i;
                    bestValue = moveValue;
                }
            }
        }
        
        return bestMove;
    }
    
    evaluateBoard(board) {
        for (let combination of this.winningCombinations) {
            const [a, b, c] = combination;
            if (board[a] && board[a] === board[b] && board[a] === board[c]) {
                return board[a];
            }
        }
        return null;
    }
    
    undoMove() {
        if (this.moveHistory.length === 0 || !this.isGameActive) return;
        
        // Undo last move(s) - in PvC mode, undo both AI and player moves
        const movesToUndo = this.gameMode === 'pvc' ? Math.min(2, this.moveHistory.length) : 1;
        
        for (let i = 0; i < movesToUndo; i++) {
            if (this.moveHistory.length === 0) break;
            
            const lastMove = this.moveHistory.pop();
            const cell = this.cells[lastMove.index];
            
            this.board[lastMove.index] = '';
            cell.textContent = '';
            cell.classList.remove('x', 'o', 'occupied', 'winning');
            
            this.currentPlayer = lastMove.player;
        }
        
        this.isGameActive = true;
        this.hideWinningLine();
        this.updateDisplay();
        this.updateUndoButton();
        this.announceChange("Move undone");
    }
    
    resetGame() {
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.isGameActive = true;
        this.moveHistory = [];
        
        this.cells.forEach(cell => {
            cell.textContent = '';
            cell.classList.remove('x', 'o', 'occupied', 'winning');
            cell.style.animation = '';
        });
        
        this.hideWinningLine();
        this.hideModal();
        this.updateDisplay();
        this.updateUndoButton();
        this.announceChange("New game started");
    }
    
    resetScore() {
        this.scores = { X: 0, O: 0, draw: 0 };
        this.updateScoreDisplay();
        this.saveScores();
        this.announceChange("Score reset");
    }
    
    setGameMode(mode) {
        this.gameMode = mode;
        
        // Update mode buttons
        document.querySelectorAll('.mode-btn').forEach(btn => {
            btn.classList.toggle('active', btn.dataset.mode === mode);
        });
        
        // Show/hide difficulty selector
        const difficultySelector = document.getElementById('difficultySelector');
        difficultySelector.style.display = mode === 'pvc' ? 'block' : 'none';
        
        this.resetGame();
        this.announceChange(`Game mode changed to ${mode === 'pvp' ? 'Player vs Player' : 'Player vs Computer'}`);
    }
    
    updateDisplay() {
        this.currentPlayerDisplay.textContent = this.currentPlayer;
        this.currentPlayerDisplay.classList.toggle('player-o', this.currentPlayer === 'O');
        
        if (this.isGameActive) {
            if (this.gameMode === 'pvc' && this.currentPlayer === 'O') {
                this.gameStatus.textContent = "Computer is thinking...";
            } else {
                this.gameStatus.textContent = `Player ${this.currentPlayer}'s turn`;
            }
        }
    }
    
    updateScoreDisplay() {
        this.scoreX.textContent = this.scores.X;
        this.scoreO.textContent = this.scores.O;
        this.scoreDraw.textContent = this.scores.draw;
    }
    
    updateUndoButton() {
        const undoBtn = document.getElementById('undoMove');
        undoBtn.disabled = this.moveHistory.length === 0 || !this.isGameActive;
    }
    
    showWinModal(winner) {
        this.modalTitle.textContent = "🎉 Game Over!";
        this.modalMessage.textContent = this.gameMode === 'pvc' && winner === 'O' ? 
            "Computer Wins!" : `Player ${winner} Wins!`;
        this.modal.classList.add('active');
    }
    
    showDrawModal() {
        this.modalTitle.textContent = "🤝 Game Over!";
        this.modalMessage.textContent = "It's a Draw!";
        document.querySelector('.celebration-icon').textContent = "🤝";
        this.modal.classList.add('active');
    }
    
    hideModal() {
        this.modal.classList.remove('active');
        // Reset celebration icon
        document.querySelector('.celebration-icon').textContent = "🎉";
    }
    
    hideWinningLine() {
        this.winningLine.classList.remove('active');
        this.winningLine.style.cssText = '';
    }
    
    handleKeyboardNavigation(e) {
        const focusedElement = document.activeElement;
        const focusedIndex = Array.from(this.cells).indexOf(focusedElement);
        
        if (focusedIndex === -1) return;
        
        let targetIndex = focusedIndex;
        
        switch (e.key) {
            case 'ArrowUp':
                targetIndex = focusedIndex - 3;
                break;
            case 'ArrowDown':
                targetIndex = focusedIndex + 3;
                break;
            case 'ArrowLeft':
                targetIndex = focusedIndex % 3 === 0 ? focusedIndex : focusedIndex - 1;
                break;
            case 'ArrowRight':
                targetIndex = focusedIndex % 3 === 2 ? focusedIndex : focusedIndex + 1;
                break;
            case 'Escape':
                if (this.modal.classList.contains('active')) {
                    this.hideModal();
                }
                return;
        }
        
        if (targetIndex >= 0 && targetIndex < 9 && targetIndex !== focusedIndex) {
            e.preventDefault();
            this.cells[targetIndex].focus();
        }
    }
    
    // Accessibility announcements
    announceMove(index, player) {
        const row = Math.floor(index / 3) + 1;
        const col = (index % 3) + 1;
        this.announceChange(`Player ${player} placed in row ${row}, column ${col}`);
    }
    
    announceWin(winner) {
        const message = this.gameMode === 'pvc' && winner === 'O' ? 
            "Computer wins the game!" : `Player ${winner} wins the game!`;
        this.announceChange(message);
    }
    
    announceChange(message) {
        if (window.announceChange) {
            window.announceChange(message);
        }
    }
    
    // Local storage for scores
    saveScores() {
        localStorage.setItem('ticTacToeScores', JSON.stringify(this.scores));
    }
    
    loadScores() {
        const savedScores = localStorage.getItem('ticTacToeScores');
        if (savedScores) {
            this.scores = { ...this.scores, ...JSON.parse(savedScores) };
            this.updateScoreDisplay();
        }
    }
}

// Utility function for accessibility announcements
function setupAriaLiveRegion() {
    const liveRegion = document.createElement('div');
    liveRegion.setAttribute('aria-live', 'polite');
    liveRegion.setAttribute('aria-atomic', 'true');
    liveRegion.className = 'sr-only';
    liveRegion.style.cssText = `
        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip: rect(0, 0, 0, 0);
        white-space: nowrap;
        border: 0;
    `;
    
    document.body.appendChild(liveRegion);
    
    window.announceChange = function(message) {
        liveRegion.textContent = message;
        setTimeout(() => {
            liveRegion.textContent = '';
        }, 1000);
    };
}

// Initialize the game when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    setupAriaLiveRegion();
    
    // Add skip link
    const skipLink = document.createElement('a');
    skipLink.href = '#gameBoard';
    skipLink.textContent = 'Skip to game board';
    skipLink.className = 'skip-link';
    skipLink.style.cssText = `
        position: absolute;
        top: -40px;
        left: 6px;
        background: #000;
        color: #fff;
        padding: 8px;
        text-decoration: none;
        border-radius: 0 0 4px 4px;
        z-index: 10000;
        transition: top 0.3s;
    `;
    
    skipLink.addEventListener('focus', () => {
        skipLink.style.top = '0';
    });
    
    skipLink.addEventListener('blur', () => {
        skipLink.style.top = '-40px';
    });
    
    document.body.insertBefore(skipLink, document.body.firstChild);
    
    // Initialize the game
    new TicTacToe();
    
    console.log('Tic Tac Toe game initialized successfully!');
    console.log('Features: PvP, PvC with AI, Undo moves, Score tracking, Accessibility support');
});