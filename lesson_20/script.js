document.addEventListener('DOMContentLoaded', function() {
    initializeTabs();
    initializeAccordion();
    initializeGallery();
    initializeTicTacToe();
});

function initializeTabs() {
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabPanels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanels.forEach(panel => panel.classList.remove('active'));
            
            this.classList.add('active');
            
            const targetPanel = document.getElementById(targetTab);
            if (targetPanel) {
                targetPanel.classList.add('active');
            }
        });
    });

    tabButtons.forEach((button, index) => {
        button.addEventListener('keydown', function(e) {
            let nextIndex;
            
            switch(e.key) {
                case 'ArrowRight':
                    e.preventDefault();
                    nextIndex = (index + 1) % tabButtons.length;
                    tabButtons[nextIndex].focus();
                    tabButtons[nextIndex].click();
                    break;
                case 'ArrowLeft':
                    e.preventDefault();
                    nextIndex = (index - 1 + tabButtons.length) % tabButtons.length;
                    tabButtons[nextIndex].focus();
                    tabButtons[nextIndex].click();
                    break;
                case 'Home':
                    e.preventDefault();
                    tabButtons[0].focus();
                    tabButtons[0].click();
                    break;
                case 'End':
                    e.preventDefault();
                    tabButtons[tabButtons.length - 1].focus();
                    tabButtons[tabButtons.length - 1].click();
                    break;
            }
        });
    });
}

function initializeAccordion() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const accordionItem = this.parentElement;
            const accordionContent = accordionItem.querySelector('.accordion-content');
            const icon = this.querySelector('.accordion-icon');
            
            const isActive = this.classList.contains('active');
            
            accordionHeaders.forEach(otherHeader => {
                const otherItem = otherHeader.parentElement;
                const otherContent = otherItem.querySelector('.accordion-content');
                const otherIcon = otherHeader.querySelector('.accordion-icon');
                
                otherHeader.classList.remove('active');
                otherContent.classList.remove('active');
                otherIcon.textContent = '+';
            });
            
            if (!isActive) {
                this.classList.add('active');
                accordionContent.classList.add('active');
                icon.textContent = '×';
            }
        });

        header.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });

        header.setAttribute('tabindex', '0');
    });
}

function initializeGallery() {
    const galleryItems = document.querySelectorAll('.gallery-item');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentStartIndex = 0;
    const itemsPerView = getItemsPerView();
    const totalItems = galleryItems.length;

    function getItemsPerView() {
        const width = window.innerWidth;
        if (width <= 768) return 1;
        if (width <= 1024) return 2;
        return 3;
    }

    function updateGallery() {
        const currentItemsPerView = getItemsPerView();
        
        galleryItems.forEach((item, index) => {
            item.classList.remove('active', 'hidden');
            item.classList.add('hidden');
        });

        for (let i = 0; i < currentItemsPerView; i++) {
            const itemIndex = (currentStartIndex + i) % totalItems;
            const item = galleryItems[itemIndex];
            item.classList.remove('hidden');
            item.classList.add('active');
        }

        indicators.forEach((indicator, index) => {
            indicator.classList.remove('active');
            if (index === currentStartIndex) {
                indicator.classList.add('active');
            }
        });

        updateButtonStates();
    }

    function updateButtonStates() {
        prevBtn.disabled = false;
        nextBtn.disabled = false;
    }

    function showNext() {
        currentStartIndex = (currentStartIndex + 1) % totalItems;
        updateGallery();
    }

    function showPrevious() {
        currentStartIndex = (currentStartIndex - 1 + totalItems) % totalItems;
        updateGallery();
    }

    function goToSlide(index) {
        currentStartIndex = index;
        updateGallery();
    }

    nextBtn.addEventListener('click', showNext);
    prevBtn.addEventListener('click', showPrevious);

    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', () => goToSlide(index));
        
        indicator.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                goToSlide(index);
            }
        });
        
        indicator.setAttribute('tabindex', '0');
        indicator.setAttribute('aria-label', `Go to slide ${index + 1}`);
    });

    document.addEventListener('keydown', function(e) {
        const galleryContainer = document.querySelector('.gallery-container');
        if (galleryContainer && galleryContainer.contains(document.activeElement)) {
            switch(e.key) {
                case 'ArrowLeft':
                    e.preventDefault();
                    showPrevious();
                    break;
                case 'ArrowRight':
                    e.preventDefault();
                    showNext();
                    break;
            }
        }
    });

    let autoRotateInterval;
    
    function startAutoRotate() {
        autoRotateInterval = setInterval(showNext, 4000);
    }
    
    function stopAutoRotate() {
        if (autoRotateInterval) {
            clearInterval(autoRotateInterval);
        }
    }

    const galleryWrapper = document.querySelector('.gallery-wrapper');
    galleryWrapper.addEventListener('mouseenter', stopAutoRotate);
    galleryWrapper.addEventListener('mouseleave', startAutoRotate);

    document.addEventListener('visibilitychange', function() {
        if (document.hidden) {
            stopAutoRotate();
        } else {
            startAutoRotate();
        }
    });

    let touchStartX = 0;
    let touchEndX = 0;

    galleryWrapper.addEventListener('touchstart', function(e) {
        touchStartX = e.changedTouches[0].screenX;
    });

    galleryWrapper.addEventListener('touchend', function(e) {
        touchEndX = e.changedTouches[0].screenX;
        handleSwipe();
    });

    function handleSwipe() {
        const swipeThreshold = 50;
        const diff = touchStartX - touchEndX;

        if (Math.abs(diff) > swipeThreshold) {
            if (diff > 0) {
                showNext();
            } else {
                showPrevious();
            }
        }
    }

    let resizeTimeout;
    window.addEventListener('resize', function() {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(function() {
            updateGallery();
        }, 250);
    });

    updateGallery();
    startAutoRotate();

    prevBtn.setAttribute('aria-label', 'Previous images');
    nextBtn.setAttribute('aria-label', 'Next images');
    
    const galleryDisplay = document.querySelector('.gallery-display');
    galleryDisplay.setAttribute('role', 'region');
    galleryDisplay.setAttribute('aria-label', 'Photo gallery');
}

// Tic Tac Toe Game Logic
let gameState = {
    board: Array(9).fill(''),
    currentPlayer: 'X',
    isGameActive: true,
    humanPlayer: 'X',
    aiPlayer: 'O',
    scores: {
        X: 0,
        O: 0,
        tie: 0
    }
};

const winningConditions = [
    [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
    [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
    [0, 4, 8], [2, 4, 6] // Diagonals
];

function initializeTicTacToe() {
    const cells = document.querySelectorAll('.game-cell');
    const resetGameBtn = document.getElementById('resetGame');
    const resetScoreBtn = document.getElementById('resetScore');
    const playerXBtn = document.getElementById('playerX');
    const playerOBtn = document.getElementById('playerO');

    console.log('Initializing Tic Tac Toe, found cells:', cells.length);

    if (!cells.length) {
        console.error('No game cells found!');
        return; // Exit if game elements don't exist
    }

    // Initialize game state
    gameState.humanPlayer = 'X';
    gameState.aiPlayer = 'O';
    gameState.currentPlayer = 'X';
    gameState.isGameActive = true;

    // Load saved scores
    loadScores();
    updateScoreDisplay();
    updateGameStatus();

    // Cell click handlers
    cells.forEach((cell, index) => {
        console.log('Setting up cell', index, cell);
        
        cell.addEventListener('click', () => {
            console.log('Cell clicked via addEventListener:', index);
            handleCellClick(index);
        });
        
        // Keyboard support
        cell.addEventListener('keydown', (e) => {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                handleCellClick(index);
            }
        });
        
        // Make cells focusable for accessibility
        cell.setAttribute('tabindex', '0');
        cell.setAttribute('aria-label', `Cell ${index + 1}, empty`);
    });

    // Control button handlers
    if (resetGameBtn) {
        resetGameBtn.addEventListener('click', resetGame);
    }
    
    if (resetScoreBtn) {
        resetScoreBtn.addEventListener('click', resetScores);
    }

    // Player selection handlers
    if (playerXBtn && playerOBtn) {
        playerXBtn.addEventListener('click', () => selectPlayer('X'));
        playerOBtn.addEventListener('click', () => selectPlayer('O'));
    }

    // Touch support for mobile
    cells.forEach((cell, index) => {
        cell.addEventListener('touchstart', (e) => {
            e.preventDefault();
            handleCellClick(index);
        });
    });
}

function selectPlayer(player) {
    gameState.humanPlayer = player;
    gameState.aiPlayer = player === 'X' ? 'O' : 'X';
    
    const playerXBtn = document.getElementById('playerX');
    const playerOBtn = document.getElementById('playerO');
    
    if (playerXBtn && playerOBtn) {
        playerXBtn.classList.toggle('active', player === 'X');
        playerOBtn.classList.toggle('active', player === 'O');
    }
    
    resetGame();
}

function handleCellClick(index) {
    console.log('Cell clicked:', index, 'Current player:', gameState.currentPlayer, 'Human player:', gameState.humanPlayer, 'Game active:', gameState.isGameActive, 'Cell empty:', gameState.board[index] === '');
    
    // Check if game is active and cell is empty
    if (!gameState.isGameActive || gameState.board[index] !== '') {
        console.log('Blocked: Game not active or cell taken');
        return;
    }

    // Check if it's the human player's turn
    if (gameState.currentPlayer !== gameState.humanPlayer) {
        console.log('Blocked: Not human player turn');
        return;
    }

    console.log('Making human move...');
    // Make human move
    makeMove(index, gameState.humanPlayer);
    
    // If game is still active and it's now AI's turn, make AI move
    if (gameState.isGameActive && gameState.currentPlayer === gameState.aiPlayer) {
        setTimeout(() => {
            if (gameState.isGameActive && gameState.currentPlayer === gameState.aiPlayer) {
                const aiMove = getBestMove();
                if (aiMove !== -1) {
                    console.log('Making AI move...');
                    makeMove(aiMove, gameState.aiPlayer);
                }
            }
        }, 300);
    }
}

function makeMove(index, player) {
    gameState.board[index] = player;
    const cell = document.querySelector(`.game-cell[data-index="${index}"]`);
    
    console.log('Making move:', { index, player, cell });
    
    if (cell) {
        const emoji = player === 'X' ? '🥑' : '🌱';
        cell.innerHTML = emoji; // Using innerHTML instead of textContent
        cell.classList.add('taken');
        cell.setAttribute('aria-label', `Cell ${index + 1}, ${player === 'X' ? 'Avocado' : 'Seed'}`);
        
        console.log('Cell updated:', cell.innerHTML, 'Should be:', emoji);
        
        // Force a reflow to ensure the content appears
        cell.offsetHeight;
    } else {
        console.error('Cell not found for index:', index);
        console.log('Available cells:', document.querySelectorAll('.game-cell'));
    }

    const result = checkGameResult();
    
    if (result.winner) {
        gameState.isGameActive = false;
        handleGameEnd(result.winner, result.line);
    } else if (result.tie) {
        gameState.isGameActive = false;
        handleGameEnd('tie');
    } else {
        // Switch turns
        gameState.currentPlayer = gameState.currentPlayer === 'X' ? 'O' : 'X';
        updateGameStatus();
    }
}

function checkGameResult() {
    // Check for winner
    for (let condition of winningConditions) {
        const [a, b, c] = condition;
        if (gameState.board[a] && 
            gameState.board[a] === gameState.board[b] && 
            gameState.board[a] === gameState.board[c]) {
            return { winner: gameState.board[a], line: condition };
        }
    }

    // Check for tie
    if (gameState.board.every(cell => cell !== '')) {
        return { tie: true };
    }

    return { winner: null, tie: false };
}

function handleGameEnd(result, winningLine = null) {
    if (result === 'tie') {
        gameState.scores.tie++;
        updateGameStatus('🤝 It\'s a tie! Great game!');
    } else {
        gameState.scores[result]++;
        const isHumanWin = result === gameState.humanPlayer;
        const message = isHumanWin ? 
            `🎉 ${result === 'X' ? 'Avocados' : 'Seeds'} win! You did it!` : 
            `🤖 ${result === 'X' ? 'Avocados' : 'Seeds'} win! AI got you this time!`;
        updateGameStatus(message);
        
        if (winningLine) {
            highlightWinningLine(winningLine);
        }
    }
    
    updateScoreDisplay();
    saveScores();
    
    // Auto-reset game after delay
    setTimeout(resetGame, 2000);
}

function highlightWinningLine(line) {
    line.forEach(index => {
        const cell = document.querySelector(`[data-index="${index}"]`);
        if (cell) {
            cell.classList.add('winner');
        }
    });
}

function getBestMove() {
    // Simple AI: Try to win, then block, then take center/corners
    
    // Try to win
    for (let i = 0; i < 9; i++) {
        if (gameState.board[i] === '') {
            gameState.board[i] = gameState.aiPlayer;
            if (checkGameResult().winner === gameState.aiPlayer) {
                gameState.board[i] = ''; // Reset for actual move
                return i;
            }
            gameState.board[i] = '';
        }
    }
    
    // Try to block human win
    for (let i = 0; i < 9; i++) {
        if (gameState.board[i] === '') {
            gameState.board[i] = gameState.humanPlayer;
            if (checkGameResult().winner === gameState.humanPlayer) {
                gameState.board[i] = ''; // Reset for actual move
                return i;
            }
            gameState.board[i] = '';
        }
    }
    
    // Take center if available
    if (gameState.board[4] === '') {
        return 4;
    }
    
    // Take corners
    const corners = [0, 2, 6, 8];
    const availableCorners = corners.filter(i => gameState.board[i] === '');
    if (availableCorners.length > 0) {
        return availableCorners[Math.floor(Math.random() * availableCorners.length)];
    }
    
    // Take any available space
    const availableMoves = [];
    for (let i = 0; i < 9; i++) {
        if (gameState.board[i] === '') {
            availableMoves.push(i);
        }
    }
    
    return availableMoves.length > 0 ? 
        availableMoves[Math.floor(Math.random() * availableMoves.length)] : -1;
}

function resetGame() {
    gameState.board = Array(9).fill('');
    gameState.currentPlayer = 'X'; // Always start with X
    gameState.isGameActive = true;
    
    const cells = document.querySelectorAll('.game-cell');
    const winningLine = document.getElementById('winningLine');
    
    console.log('Resetting game, found cells:', cells.length);
    
    cells.forEach((cell, index) => {
        cell.innerHTML = ''; // Using innerHTML to be consistent
        cell.classList.remove('taken', 'winner');
        cell.setAttribute('aria-label', `Cell ${index + 1}, empty`);
    });
    
    if (winningLine) {
        winningLine.classList.remove('show');
    }
    
    updateGameStatus();
    
    // If human chose O (Seeds), AI goes first
    if (gameState.humanPlayer === 'O') {
        setTimeout(() => {
            if (gameState.isGameActive && gameState.currentPlayer === 'X') {
                const aiMove = getBestMove();
                if (aiMove !== -1) {
                    makeMove(aiMove, gameState.aiPlayer);
                }
            }
        }, 300);
    }
}

function resetScores() {
    gameState.scores = { X: 0, O: 0, tie: 0 };
    updateScoreDisplay();
    saveScores();
    resetGame();
}

function updateGameStatus(message = null) {
    const statusElement = document.getElementById('gameStatus');
    if (!statusElement) return;
    
    if (message) {
        statusElement.textContent = message;
    } else if (gameState.isGameActive) {
        const currentSymbol = gameState.currentPlayer === 'X' ? '🥑 Avocados' : '🌱 Seeds';
        statusElement.textContent = `${currentSymbol}' turn`;
    }
}

function updateScoreDisplay() {
    const scoreX = document.getElementById('scoreX');
    const scoreO = document.getElementById('scoreO');
    const scoreTie = document.getElementById('scoreTie');
    
    if (scoreX) scoreX.textContent = gameState.scores.X;
    if (scoreO) scoreO.textContent = gameState.scores.O;
    if (scoreTie) scoreTie.textContent = gameState.scores.tie;
}

function saveScores() {
    try {
        localStorage.setItem('tictactoe_scores', JSON.stringify(gameState.scores));
    } catch (e) {
        console.log('Could not save scores to localStorage');
    }
}

function loadScores() {
    try {
        const saved = localStorage.getItem('tictactoe_scores');
        if (saved) {
            gameState.scores = JSON.parse(saved);
        }
    } catch (e) {
        console.log('Could not load scores from localStorage');
    }
}

function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

function smoothScrollTo(element) {
    element.scrollIntoView({
        behavior: 'smooth',
        block: 'nearest'
    });
}

function manageFocus() {
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Tab') {
            document.body.classList.add('keyboard-navigation');
        }
    });

    document.addEventListener('mousedown', function() {
        document.body.classList.remove('keyboard-navigation');
    });
}

manageFocus();

const style = document.createElement('style');
style.textContent = `
    .keyboard-navigation *:focus {
        outline: 2px solid #4CAF50 !important;
        outline-offset: 2px !important;
    }
    
    body:not(.keyboard-navigation) *:focus {
        outline: none !important;
    }
`;
document.head.appendChild(style);

if ('IntersectionObserver' in window) {
    const observerOptions = {
        root: null,
        rootMargin: '50px',
        threshold: 0.1
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('in-view');
            }
        });
    }, observerOptions);

    document.querySelectorAll('.component-section').forEach(section => {
        observer.observe(section);
    });
}

console.log('Verde Tropical Exports website initialized successfully!');
console.log('Features: Dominican avocado business showcase with interactive components + Tic Tac Toe game');
console.log('Components: Business info tabs, FAQ accordion, farm gallery, Avocados vs Seeds game');
console.log('Theme: Premium avocado export from DR to USA with fun gaming zone');
