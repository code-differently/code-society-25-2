// Tic Tac Toe Game Logic
class TicTacToe {
    constructor() {
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.gameMode = 'human'; // 'human' or 'ai'
        this.aiDifficulty = 'easy'; // 'easy', 'medium', 'hard'
        this.gameActive = true;
        this.scores = {
            x: 0,
            o: 0,
            ties: 0
        };
        
        this.winningCombinations = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
            [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
            [0, 4, 8], [2, 4, 6] // Diagonals
        ];
        
        this.initializeGame();
    }
    
    initializeGame() {
        this.bindEvents();
        this.updateDisplay();
        this.loadScores();
    }
    
    bindEvents() {
        // Cell clicks
        document.querySelectorAll('.cell').forEach(cell => {
            cell.addEventListener('click', (e) => this.handleCellClick(e));
        });
        
        // Game controls
        document.getElementById('reset-game').addEventListener('click', () => this.resetGame());
        document.getElementById('reset-scores').addEventListener('click', () => this.resetScores());
        
        // Game modes
        document.getElementById('mode-human').addEventListener('click', () => this.setGameMode('human'));
        document.getElementById('mode-ai').addEventListener('click', () => this.setGameMode('ai'));
        
        // AI difficulty
        document.querySelectorAll('.difficulty-btn').forEach(btn => {
            btn.addEventListener('click', (e) => this.setAIDifficulty(e.target.id));
        });
        
        // Modal controls
        document.getElementById('play-again').addEventListener('click', () => this.playAgain());
        document.getElementById('close-modal').addEventListener('click', () => this.closeModal());
        
        // Keyboard support
        document.addEventListener('keydown', (e) => this.handleKeyPress(e));
        
        // Click outside modal to close
        document.getElementById('game-modal').addEventListener('click', (e) => {
            if (e.target.id === 'game-modal') {
                this.closeModal();
            }
        });
    }
    
    handleCellClick(e) {
        const cellIndex = parseInt(e.target.getAttribute('data-cell'));
        
        if (!this.gameActive || this.board[cellIndex] !== '') {
            return;
        }
        
        this.makeMove(cellIndex, this.currentPlayer);
        
        if (this.gameActive && this.gameMode === 'ai' && this.currentPlayer === 'O') {
            setTimeout(() => this.makeAIMove(), 500);
        }
    }
    
    makeMove(cellIndex, player) {
        this.board[cellIndex] = player;
        const cell = document.querySelector(`[data-cell="${cellIndex}"]`);
        cell.textContent = player;
        cell.classList.add(player.toLowerCase());
        cell.classList.add('disabled');
        
        const gameResult = this.checkGameEnd();
        
        if (gameResult) {
            this.endGame(gameResult);
        } else {
            this.switchPlayer();
        }
        
        this.updateDisplay();
    }
    
    makeAIMove() {
        if (!this.gameActive) return;
        
        let move;
        
        switch (this.aiDifficulty) {
            case 'easy':
                move = this.getRandomMove();
                break;
            case 'medium':
                move = this.getMediumMove();
                break;
            case 'hard':
                move = this.getBestMove();
                break;
        }
        
        if (move !== -1) {
            this.makeMove(move, 'O');
        }
    }
    
    getRandomMove() {
        const availableMoves = this.getAvailableMoves();
        return availableMoves.length > 0 ? 
            availableMoves[Math.floor(Math.random() * availableMoves.length)] : -1;
    }
    
    getMediumMove() {
        // 70% chance of making optimal move, 30% random
        if (Math.random() < 0.7) {
            return this.getBestMove();
        } else {
            return this.getRandomMove();
        }
    }
    
    getBestMove() {
        // Check if AI can win
        for (let i = 0; i < 9; i++) {
            if (this.board[i] === '') {
                this.board[i] = 'O';
                if (this.checkWinner() === 'O') {
                    this.board[i] = '';
                    return i;
                }
                this.board[i] = '';
            }
        }
        
        // Check if AI needs to block player
        for (let i = 0; i < 9; i++) {
            if (this.board[i] === '') {
                this.board[i] = 'X';
                if (this.checkWinner() === 'X') {
                    this.board[i] = '';
                    return i;
                }
                this.board[i] = '';
            }
        }
        
        // Try to take center
        if (this.board[4] === '') {
            return 4;
        }
        
        // Try to take corners
        const corners = [0, 2, 6, 8];
        const availableCorners = corners.filter(corner => this.board[corner] === '');
        if (availableCorners.length > 0) {
            return availableCorners[Math.floor(Math.random() * availableCorners.length)];
        }
        
        // Take any available move
        return this.getRandomMove();
    }
    
    getAvailableMoves() {
        return this.board.map((cell, index) => cell === '' ? index : null).filter(val => val !== null);
    }
    
    checkGameEnd() {
        const winner = this.checkWinner();
        if (winner) {
            return { type: 'win', winner };
        } else if (this.board.every(cell => cell !== '')) {
            return { type: 'tie' };
        }
        return null;
    }
    
    checkWinner() {
        for (let combination of this.winningCombinations) {
            const [a, b, c] = combination;
            if (this.board[a] && this.board[a] === this.board[b] && this.board[a] === this.board[c]) {
                this.highlightWinningCells(combination);
                return this.board[a];
            }
        }
        return null;
    }
    
    highlightWinningCells(combination) {
        combination.forEach(index => {
            document.querySelector(`[data-cell="${index}"]`).classList.add('winning');
        });
    }
    
    endGame(result) {
        this.gameActive = false;
        
        if (result.type === 'win') {
            this.scores[result.winner.toLowerCase()]++;
            this.updateScores();
            this.showModal(`Player ${result.winner} Wins!`, `Congratulations! Player ${result.winner} is the winner!`);
            this.updateGameStatus(`🎉 Player ${result.winner} wins!`);
        } else {
            this.scores.ties++;
            this.updateScores();
            this.showModal("It's a Tie!", "Great game! It's a tie!");
            this.updateGameStatus("🤝 It's a tie!");
        }
        
        this.saveScores();
    }
    
    switchPlayer() {
        this.currentPlayer = this.currentPlayer === 'X' ? 'O' : 'X';
        this.updatePlayerTurn();
    }
    
    resetGame() {
        this.board = Array(9).fill('');
        this.currentPlayer = 'X';
        this.gameActive = true;
        
        document.querySelectorAll('.cell').forEach(cell => {
            cell.textContent = '';
            cell.className = 'cell';
        });
        
        this.updateDisplay();
        this.updateGameStatus('Make your move!');
    }
    
    resetScores() {
        this.scores = { x: 0, o: 0, ties: 0 };
        this.updateScores();
        this.saveScores();
    }
    
    setGameMode(mode) {
        this.gameMode = mode;
        
        document.querySelectorAll('.mode-btn').forEach(btn => btn.classList.remove('active'));
        document.getElementById(`mode-${mode}`).classList.add('active');
        
        const difficultySelection = document.getElementById('difficulty-selection');
        difficultySelection.style.display = mode === 'ai' ? 'block' : 'none';
        
        this.resetGame();
    }
    
    setAIDifficulty(difficulty) {
        this.aiDifficulty = difficulty;
        
        document.querySelectorAll('.difficulty-btn').forEach(btn => btn.classList.remove('active'));
        document.getElementById(difficulty).classList.add('active');
        
        this.resetGame();
    }
    
    updateDisplay() {
        this.updatePlayerTurn();
        this.updateScores();
    }
    
    updatePlayerTurn() {
        const playerTurnElement = document.getElementById('current-player');
        if (this.gameMode === 'ai') {
            playerTurnElement.textContent = this.currentPlayer === 'X' ? 'Your' : 'AI';
        } else {
            playerTurnElement.textContent = `Player ${this.currentPlayer}`;
        }
    }
    
    updateScores() {
        document.getElementById('score-x').textContent = this.scores.x;
        document.getElementById('score-o').textContent = this.scores.o;
        document.getElementById('score-ties').textContent = this.scores.ties;
    }
    
    updateGameStatus(message) {
        document.getElementById('game-status').textContent = message;
    }
    
    showModal(title, message) {
        document.getElementById('modal-title').textContent = title;
        document.getElementById('modal-message').textContent = message;
        document.getElementById('game-modal').style.display = 'block';
    }
    
    closeModal() {
        document.getElementById('game-modal').style.display = 'none';
    }
    
    playAgain() {
        this.closeModal();
        this.resetGame();
    }
    
    saveScores() {
        localStorage.setItem('tictactoe-scores', JSON.stringify(this.scores));
    }
    
    loadScores() {
        const savedScores = localStorage.getItem('tictactoe-scores');
        if (savedScores) {
            this.scores = JSON.parse(savedScores);
            this.updateScores();
        }
    }
    
    handleKeyPress(e) {
        if (!this.gameActive) return;
        
        // Number keys 1-9 for cell selection
        const key = parseInt(e.key);
        if (key >= 1 && key <= 9) {
            const cellIndex = key - 1;
            if (this.board[cellIndex] === '') {
                document.querySelector(`[data-cell="${cellIndex}"]`).click();
            }
        }
        
        // Escape to close modal
        if (e.key === 'Escape') {
            this.closeModal();
        }
        
        // Enter to play again when modal is open
        if (e.key === 'Enter' && document.getElementById('game-modal').style.display === 'block') {
            this.playAgain();
        }
        
        // R for reset game
        if (e.key.toLowerCase() === 'r') {
            this.resetGame();
        }
    }
}

// Initialize game when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    const game = new TicTacToe();
    
    // Add some fun animations
    setTimeout(() => {
        document.querySelector('.container').style.animation = 'slideIn 0.5s ease';
    }, 100);
});

// Add some interactive sound effects (using Web Audio API)
class SoundEffects {
    constructor() {
        this.audioContext = null;
        this.initAudio();
    }
    
    initAudio() {
        try {
            this.audioContext = new (window.AudioContext || window.webkitAudioContext)();
        } catch (e) {
            console.log('Web Audio API not supported');
        }
    }
    
    playSound(frequency, duration, type = 'sine') {
        if (!this.audioContext) return;
        
        const oscillator = this.audioContext.createOscillator();
        const gainNode = this.audioContext.createGain();
        
        oscillator.connect(gainNode);
        gainNode.connect(this.audioContext.destination);
        
        oscillator.frequency.value = frequency;
        oscillator.type = type;
        
        gainNode.gain.setValueAtTime(0.3, this.audioContext.currentTime);
        gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + duration);
        
        oscillator.start(this.audioContext.currentTime);
        oscillator.stop(this.audioContext.currentTime + duration);
    }
    
    playMoveSound() {
        this.playSound(800, 0.1);
    }
    
    playWinSound() {
        this.playSound(1000, 0.3);
        setTimeout(() => this.playSound(1200, 0.3), 100);
        setTimeout(() => this.playSound(1400, 0.3), 200);
    }
    
    playTieSound() {
        this.playSound(400, 0.5);
    }
}

// Add sound effects to the game
const soundEffects = new SoundEffects();

// Override the original makeMove method to add sound
const originalMakeMove = TicTacToe.prototype.makeMove;
TicTacToe.prototype.makeMove = function(cellIndex, player) {
    soundEffects.playMoveSound();
    originalMakeMove.call(this, cellIndex, player);
};

// Override the endGame method to add win/tie sounds
const originalEndGame = TicTacToe.prototype.endGame;
TicTacToe.prototype.endGame = function(result) {
    if (result.type === 'win') {
        soundEffects.playWinSound();
    } else {
        soundEffects.playTieSound();
    }
    originalEndGame.call(this, result);
};
