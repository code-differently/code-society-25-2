// Tabbed Component Logic
document.addEventListener('DOMContentLoaded', function() {
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabContents = document.querySelectorAll('.tab-content');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            const tabId = button.getAttribute('data-tab');
            
            // Remove active class from all buttons and contents
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabContents.forEach(content => content.classList.remove('active'));
            
            // Add active class to clicked button and corresponding content
            button.classList.add('active');
            document.getElementById(tabId).classList.add('active');
        });
    });
});

// Accordion Component Logic
document.addEventListener('DOMContentLoaded', function() {
    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', () => {
            const item = header.parentElement;
            const content = item.querySelector('.accordion-content');
            const isActive = header.classList.contains('active');

            // Close all accordion items
            accordionHeaders.forEach(h => {
                h.classList.remove('active');
                h.parentElement.querySelector('.accordion-content').classList.remove('active');
            });

            // If this item wasn't active, open it
            if (!isActive) {
                header.classList.add('active');
                content.classList.add('active');
            }
        });
    });
});

// Photo Gallery Logic
document.addEventListener('DOMContentLoaded', function() {
    const track = document.getElementById('galleryTrack');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicatorsContainer = document.getElementById('galleryIndicators');
    const items = track.querySelectorAll('.gallery-item');
    
    let currentIndex = 0;
    const itemsToShow = 3;
    const totalItems = items.length;
    const maxIndex = Math.max(0, totalItems - itemsToShow);

    // Create indicators
    for (let i = 0; i <= maxIndex; i++) {
        const dot = document.createElement('div');
        dot.classList.add('gallery-dot');
        if (i === 0) dot.classList.add('active');
        dot.addEventListener('click', () => goToSlide(i));
        indicatorsContainer.appendChild(dot);
    }

    const indicators = document.querySelectorAll('.gallery-dot');

    function updateGallery() {
        const isMobile = window.innerWidth <= 768;
        const itemWidth = isMobile ? 100 : 33.333;
        const offset = -currentIndex * itemWidth;
        track.style.transform = `translateX(${offset}%)`;
        
        // Update buttons
        prevBtn.disabled = currentIndex === 0;
        nextBtn.disabled = currentIndex >= maxIndex;
        
        // Update indicators
        indicators.forEach((dot, index) => {
            dot.classList.toggle('active', index === currentIndex);
        });
    }

    function goToSlide(index) {
        currentIndex = Math.max(0, Math.min(index, maxIndex));
        updateGallery();
    }

    prevBtn.addEventListener('click', () => {
        if (currentIndex > 0) goToSlide(currentIndex - 1);
    });

    nextBtn.addEventListener('click', () => {
        if (currentIndex < maxIndex) goToSlide(currentIndex + 1);
    });

    // Handle window resize
    window.addEventListener('resize', () => {
        updateGallery();
    });

    updateGallery();
});

// Tic Tac Toe Logic
document.addEventListener('DOMContentLoaded', function() {
    const board = document.getElementById('board');
    const cells = document.querySelectorAll('.ttt-cell');
    const status = document.getElementById('gameStatus');
    const resetBtn = document.getElementById('resetBtn');
    
    let currentPlayer = 'X';
    let gameBoard = Array(9).fill('');
    let gameActive = true;

    const winPatterns = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
        [0, 4, 8], [2, 4, 6] // Diagonals
    ];

    function checkWinner() {
        for (let pattern of winPatterns) {
            const [a, b, c] = pattern;
            if (gameBoard[a] && gameBoard[a] === gameBoard[b] && gameBoard[a] === gameBoard[c]) {
                return gameBoard[a];
            }
        }
        return null;
    }

    function checkDraw() {
        return gameBoard.every(cell => cell !== '');
    }

    function updateStatus() {
        const winner = checkWinner();
        if (winner) {
            status.innerHTML = `<span class="winner">Player ${winner} wins!</span>`;
            gameActive = false;
        } else if (checkDraw()) {
            status.innerHTML = `<span class="draw">It's a draw!</span>`;
            gameActive = false;
        } else {
            status.textContent = `Player ${currentPlayer}'s turn`;
        }
    }

    function handleCellClick(e) {
        const index = parseInt(e.target.getAttribute('data-index'));
        
        if (gameBoard[index] !== '' || !gameActive) return;

        gameBoard[index] = currentPlayer;
        e.target.textContent = currentPlayer;
        e.target.classList.add(currentPlayer.toLowerCase());
        e.target.disabled = true;

        updateStatus();
        
        if (gameActive) {
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            updateStatus();
        }
    }

    function resetGame() {
        gameBoard.fill('');
        currentPlayer = 'X';
        gameActive = true;
        
        cells.forEach(cell => {
            cell.textContent = '';
            cell.disabled = false;
            cell.classList.remove('x', 'o');
        });
        
        updateStatus();
    }

    cells.forEach(cell => {
        cell.addEventListener('click', handleCellClick);
    });

    resetBtn.addEventListener('click', resetGame);

    updateStatus();
});