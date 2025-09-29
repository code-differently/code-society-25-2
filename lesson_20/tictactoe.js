document.addEventListener('DOMContentLoaded', function() {
    initializeTicTacToe();
});

function initializeTicTacToe() {
    const gameBoard = document.getElementById('gameBoard');
    const cells = document.querySelectorAll('.cell');
    const currentPlayerDisplay = document.getElementById('currentPlayerDisplay');
    const resetButton = document.getElementById('resetButton');
    const resetScoreButton = document.getElementById('resetScoreButton');
    const playAgainButton = document.getElementById('playAgainButton');
    const gameMessage = document.getElementById('gameMessage');
    const messageText = document.getElementById('messageText');
    const winningLine = document.getElementById('winningLine');
    
    const player1ScoreElement = document.getElementById('player1Score');
    const player2ScoreElement = document.getElementById('player2Score');
    const tieScoreElement = document.getElementById('tieScore');

    const PLAYER1 = '🥑';
    const PLAYER2 = '🌱';
    
    let currentPlayer = PLAYER1;
    let gameActive = true;
    let gameState = ['', '', '', '', '', '', '', '', ''];
    let scores = {
        player1: 0,
        player2: 0,
        ties: 0
    };

    const winningConditions = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], // Horizontal
        [0, 3, 6], [1, 4, 7], [2, 5, 8], // Vertical
        [0, 4, 8], [2, 4, 6]             // Diagonal
    ];

    function initializeGame() {
        loadScores();
        updateScoreDisplay();
        updateCurrentPlayerDisplay();
        
        cells.forEach((cell, index) => {
            cell.addEventListener('click', () => handleCellClick(index));
            cell.addEventListener('keydown', (e) => {
                if (e.key === 'Enter' || e.key === ' ') {
                    e.preventDefault();
                    handleCellClick(index);
                }
            });
            cell.setAttribute('tabindex', '0');
        });

        resetButton.addEventListener('click', resetGame);
        resetScoreButton.addEventListener('click', resetScores);
        playAgainButton.addEventListener('click', () => {
            hideGameMessage();
            resetGame();
        });

        document.addEventListener('keydown', handleKeyboardNavigation);
    }

    function handleCellClick(index) {
        if (gameState[index] !== '' || !gameActive) {
            return;
        }

        gameState[index] = currentPlayer;
        cells[index].textContent = currentPlayer;
        cells[index].classList.add('filled');
        
        animateCell(cells[index]);

        if (checkWin()) {
            handleGameEnd('win');
        } else if (checkTie()) {
            handleGameEnd('tie');
        } else {
            switchPlayer();
        }
    }

    function animateCell(cell) {
        cell.style.transform = 'scale(1.2)';
        setTimeout(() => {
            cell.style.transform = 'scale(1)';
        }, 150);
    }

    function checkWin() {
        for (let condition of winningConditions) {
            const [a, b, c] = condition;
            if (gameState[a] && gameState[a] === gameState[b] && gameState[a] === gameState[c]) {
                highlightWinningCells([a, b, c]);
                showWinningLine(condition);
                return true;
            }
        }
        return false;
    }

    function checkTie() {
        return gameState.every(cell => cell !== '');
    }

    function highlightWinningCells(indices) {
        indices.forEach(index => {
            cells[index].classList.add('winning');
        });
    }

    function showWinningLine(condition) {
        const [a, b, c] = condition;
        winningLine.className = 'winning-line show';

        if (a === 0 && b === 1 && c === 2) {
            winningLine.classList.add('horizontal');
            winningLine.style.top = '16.6%';
        } else if (a === 3 && b === 4 && c === 5) {
            winningLine.classList.add('horizontal');
            winningLine.style.top = '50%';
        } else if (a === 6 && b === 7 && c === 8) {
            winningLine.classList.add('horizontal');
            winningLine.style.top = '83.4%';
        } else if (a === 0 && b === 3 && c === 6) {
            winningLine.classList.add('vertical');
            winningLine.style.left = '16.6%';
        } else if (a === 1 && b === 4 && c === 7) {
            winningLine.classList.add('vertical');
            winningLine.style.left = '50%';
        } else if (a === 2 && b === 5 && c === 8) {
            winningLine.classList.add('vertical');
            winningLine.style.left = '83.4%';
        } else if (a === 0 && b === 4 && c === 8) {
            winningLine.classList.add('diagonal', 'diagonal1');
        } else if (a === 2 && b === 4 && c === 6) {
            winningLine.classList.add('diagonal', 'diagonal2');
        }
    }

    function handleGameEnd(result) {
        gameActive = false;
        
        setTimeout(() => {
            if (result === 'win') {
                const winner = currentPlayer === PLAYER1 ? 'Avocados' : 'Seeds';
                messageText.textContent = `🎉 ${winner} Win! 🎉`;
                
                if (currentPlayer === PLAYER1) {
                    scores.player1++;
                } else {
                    scores.player2++;
                }
            } else {
                messageText.textContent = `🤝 It's a Tie! 🤝`;
                scores.ties++;
            }
            
            updateScoreDisplay();
            saveScores();
            showGameMessage();
        }, 500);
    }

    function switchPlayer() {
        currentPlayer = currentPlayer === PLAYER1 ? PLAYER2 : PLAYER1;
        updateCurrentPlayerDisplay();
    }

    function updateCurrentPlayerDisplay() {
        currentPlayerDisplay.textContent = currentPlayer;
        currentPlayerDisplay.style.animation = 'none';
        setTimeout(() => {
            currentPlayerDisplay.style.animation = 'pulse 0.5s ease';
        }, 10);
    }

    function updateScoreDisplay() {
        player1ScoreElement.textContent = scores.player1;
        player2ScoreElement.textContent = scores.player2;
        tieScoreElement.textContent = scores.ties;
    }

    function resetGame() {
        currentPlayer = PLAYER1;
        gameActive = true;
        gameState = ['', '', '', '', '', '', '', '', ''];
        
        cells.forEach(cell => {
            cell.textContent = '';
            cell.classList.remove('filled', 'winning');
            cell.style.transform = '';
        });
        
        winningLine.className = 'winning-line';
        winningLine.style.top = '';
        winningLine.style.left = '';
        
        updateCurrentPlayerDisplay();
        hideGameMessage();
    }

    function resetScores() {
        scores = {
            player1: 0,
            player2: 0,
            ties: 0
        };
        updateScoreDisplay();
        saveScores();
        
        const scoreItems = document.querySelectorAll('.score-item');
        scoreItems.forEach(item => {
            item.style.animation = 'none';
            setTimeout(() => {
                item.style.animation = 'bounce 0.5s ease';
            }, 10);
        });
    }

    function showGameMessage() {
        gameMessage.classList.remove('hidden');
    }

    function hideGameMessage() {
        gameMessage.classList.add('hidden');
    }

    function saveScores() {
        localStorage.setItem('tictactoe_scores', JSON.stringify(scores));
    }

    function loadScores() {
        const savedScores = localStorage.getItem('tictactoe_scores');
        if (savedScores) {
            scores = JSON.parse(savedScores);
        }
    }

    function handleKeyboardNavigation(e) {
        if (!gameActive) return;

        const focusedElement = document.activeElement;
        if (!focusedElement.classList.contains('cell')) return;

        const currentIndex = Array.from(cells).indexOf(focusedElement);
        let newIndex = currentIndex;

        switch(e.key) {
            case 'ArrowUp':
                e.preventDefault();
                newIndex = currentIndex - 3;
                if (newIndex >= 0) cells[newIndex].focus();
                break;
            case 'ArrowDown':
                e.preventDefault();
                newIndex = currentIndex + 3;
                if (newIndex < 9) cells[newIndex].focus();
                break;
            case 'ArrowLeft':
                e.preventDefault();
                newIndex = currentIndex - 1;
                if (newIndex >= 0 && Math.floor(newIndex / 3) === Math.floor(currentIndex / 3)) {
                    cells[newIndex].focus();
                }
                break;
            case 'ArrowRight':
                e.preventDefault();
                newIndex = currentIndex + 1;
                if (newIndex < 9 && Math.floor(newIndex / 3) === Math.floor(currentIndex / 3)) {
                    cells[newIndex].focus();
                }
                break;
            case 'Home':
                e.preventDefault();
                cells[0].focus();
                break;
            case 'End':
                e.preventDefault();
                cells[8].focus();
                break;
        }
    }

    function addGameCompletionEffects() {
        const style = document.createElement('style');
        style.textContent = `
            @keyframes pulse {
                0% { transform: scale(1); }
                50% { transform: scale(1.1); }
                100% { transform: scale(1); }
            }
            
            @keyframes bounce {
                0%, 20%, 50%, 80%, 100% { transform: translateY(0); }
                40% { transform: translateY(-10px); }
                60% { transform: translateY(-5px); }
            }
        `;
        document.head.appendChild(style);
    }

    function addTouchSupport() {
        let touchStarted = false;
        
        cells.forEach(cell => {
            cell.addEventListener('touchstart', (e) => {
                e.preventDefault();
                touchStarted = true;
                cell.style.transform = 'scale(0.95)';
            });
            
            cell.addEventListener('touchend', (e) => {
                e.preventDefault();
                if (touchStarted) {
                    cell.style.transform = '';
                    const index = Array.from(cells).indexOf(cell);
                    handleCellClick(index);
                }
                touchStarted = false;
            });
            
            cell.addEventListener('touchcancel', () => {
                touchStarted = false;
                cell.style.transform = '';
            });
        });
    }

    function addSoundEffects() {
        const AudioContext = window.AudioContext || window.webkitAudioContext;
        if (!AudioContext) return;

        const audioContext = new AudioContext();

        function playTone(frequency, duration) {
            const oscillator = audioContext.createOscillator();
            const gainNode = audioContext.createGain();
            
            oscillator.connect(gainNode);
            gainNode.connect(audioContext.destination);
            
            oscillator.frequency.value = frequency;
            oscillator.type = 'sine';
            
            gainNode.gain.setValueAtTime(0.1, audioContext.currentTime);
            gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + duration);
            
            oscillator.start(audioContext.currentTime);
            oscillator.stop(audioContext.currentTime + duration);
        }

        const originalHandleCellClick = handleCellClick;
        handleCellClick = function(index) {
            if (gameState[index] === '' && gameActive) {
                playTone(400, 0.1);
            }
            originalHandleCellClick(index);
        };

        const originalHandleGameEnd = handleGameEnd;
        handleGameEnd = function(result) {
            if (result === 'win') {
                setTimeout(() => playTone(600, 0.3), 100);
                setTimeout(() => playTone(800, 0.3), 300);
            } else {
                playTone(300, 0.5);
            }
            originalHandleGameEnd(result);
        };
    }

    initializeGame();
    addGameCompletionEffects();
    addTouchSupport();
    
    try {
        addSoundEffects();
    } catch (e) {
        console.log('Sound effects not available');
    }

    console.log('🥑 Verde Tropical Tic Tac Toe initialized!');
    console.log('Features: Responsive design, keyboard navigation, touch support, score tracking');
    console.log('Theme: Avocados vs Seeds - Agricultural fun while waiting for shipments!');
}

function createAIPlayer() {
    const PLAYER1 = '🥑';
    const PLAYER2 = '🌱';

    function getBestMove(gameState, aiPlayer) {
        const humanPlayer = aiPlayer === PLAYER1 ? PLAYER2 : PLAYER1;
        
        function minimax(state, depth, isMaximizing) {
            const winner = checkWinner(state);
            
            if (winner === aiPlayer) return 10 - depth;
            if (winner === humanPlayer) return depth - 10;
            if (state.every(cell => cell !== '')) return 0;
            
            if (isMaximizing) {
                let bestScore = -Infinity;
                for (let i = 0; i < 9; i++) {
                    if (state[i] === '') {
                        state[i] = aiPlayer;
                        const score = minimax(state, depth + 1, false);
                        state[i] = '';
                        bestScore = Math.max(score, bestScore);
                    }
                }
                return bestScore;
            } else {
                let bestScore = Infinity;
                for (let i = 0; i < 9; i++) {
                    if (state[i] === '') {
                        state[i] = humanPlayer;
                        const score = minimax(state, depth + 1, true);
                        state[i] = '';
                        bestScore = Math.min(score, bestScore);
                    }
                }
                return bestScore;
            }
        }
        
        let bestScore = -Infinity;
        let bestMove = -1;
        
        for (let i = 0; i < 9; i++) {
            if (gameState[i] === '') {
                gameState[i] = aiPlayer;
                const score = minimax(gameState, 0, false);
                gameState[i] = '';
                
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        
        return bestMove;
    }
    
    function checkWinner(state) {
        const winningConditions = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8],
            [0, 3, 6], [1, 4, 7], [2, 5, 8],
            [0, 4, 8], [2, 4, 6]
        ];
        
        for (let condition of winningConditions) {
            const [a, b, c] = condition;
            if (state[a] && state[a] === state[b] && state[a] === state[c]) {
                return state[a];
            }
        }
        return null;
    }
    
    return { getBestMove };
}
