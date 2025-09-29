document.addEventListener('DOMContentLoaded', () => {

    function setupTabs() {
        const tabButtons = document.querySelectorAll('.tab-btn');
        const tabPanes = document.querySelectorAll('.tab-pane');
        tabButtons.forEach(button => {
          button.addEventListener('click', (event) => {
            const targetTabId = event.target.dataset.tab;
            
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanes.forEach(pane => pane.classList.remove('active'));

            event.target.classList.add('active');

            const targetPane = document.getElementById(targetTabId);
            if (targetPane) {
                targetPane.classList.add('active');
            }
          });
        });

        if (tabButtons.length > 0){
            tabButtons[0].click();
        }
    }

    setupTabs();

    function setupAccordion(){
        const accordionHeaders = document.querySelectorAll('.accordion-header');
        const allAccordionContent = document.querySelectorAll('.accordion-content');

        accordionHeaders.forEach(header => {
            header.addEventListener('click' , (event) => {

                const targetPanelId = event.target.dataset.panel;
                const targetContent = document.getElementById(targetPanelId);

                if (targetContent){
                    
                    allAccordionContent.forEach(content => {
                    
                        if (content.id !== targetPanelId && content.classList.contains('active')) {
                            content.classList.remove('active');
                        }
                    });

                    targetContent.classList.toggle('active');
                }
            });
        });
    }
    setupAccordion();
    function setupGallery(){
        const track = document.querySelector('.gallery-track');
        const prevBtn = document.querySelector('.prev-btn');
        const nextBtn = document.querySelector('.next-btn');
        const items = document.querySelectorAll('.gallery-item');

        let currentIndex = 0;
        const totalItems = items.length;

        const updateGallery = () => {
            if (totalItems === 0) return;

            const itemWidth = items[0].clientWidth || 220;

          
            const translateAmount = -currentIndex * itemWidth;

            track.style.transform = `translateX(${translateAmount}px)`;
        };
        
        nextBtn.addEventListener('click', () => {
            currentIndex++;

            if (currentIndex >= totalItems) {
                currentIndex = 0;
            }
            updateGallery();
        });

        prevBtn.addEventListener('click', () => {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = totalItems - 1;
            }
            updateGallery();
        });
        
 
        window.addEventListener('resize', updateGallery);
        
       
        updateGallery();
    }
    setupGallery();
});
function setupTicTacToe() {
        const statusDisplay = document.getElementById('game-status');
        const restartButton = document.getElementById('restart-btn');
        const cells = document.querySelectorAll('.cell');

        let gameActive = true;
        let currentPlayer = 'X';
      
        let gameState = ['', '', '', '', '', '', '', '', '']; 

        
        const winningConditions = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8],
            [0, 3, 6], [1, 4, 7], [2, 5, 8], 
            [0, 4, 8], [2, 4, 6]            
        ];

   

        const handleCellPlayed = (clickedCell, clickedCellIndex) => {
            
            gameState[clickedCellIndex] = currentPlayer;
            clickedCell.innerHTML = currentPlayer;
            clickedCell.classList.add(currentPlayer); 
        };

        const handleResultValidation = () => {
            let roundWon = false;
            
         
            for (let i = 0; i < winningConditions.length; i++) {
                const winCondition = winningConditions[i];
                let a = gameState[winCondition[0]];
                let b = gameState[winCondition[1]];
                let c = gameState[winCondition[2]];

               
                if (a === '' || b === '' || c === '') {
                    continue;
                }

               
                if (a === b && b === c) {
                    roundWon = true;
                    break;
                }
            }

            if (roundWon) {
                statusDisplay.innerHTML = `Player ${currentPlayer} has won! 🎉`;
                gameActive = false;
                return;
            }

         
            let roundDraw = !gameState.includes('');
            if (roundDraw) {
                statusDisplay.innerHTML = 'Game ended in a draw! 🤝';
                gameActive = false;
                return;
            }

           
            handlePlayerChange();
        };

        const handlePlayerChange = () => {
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            statusDisplay.innerHTML = `Player ${currentPlayer}'s turn`;
        };

        const handleCellClick = (event) => {
            const clickedCell = event.target;
     
            const clickedCellIndex = parseInt(clickedCell.getAttribute('data-cell-index'));

           
            if (gameState[clickedCellIndex] !== '' || !gameActive) {
                return;
            }

        
            handleCellPlayed(clickedCell, clickedCellIndex);
            handleResultValidation();
        };

        const handleRestartGame = () => {
            gameActive = true;
            currentPlayer = 'X';
            gameState = ['', '', '', '', '', '', '', '', ''];
            statusDisplay.innerHTML = `Player ${currentPlayer}'s turn`;
            
            
            cells.forEach(cell => {
                cell.innerHTML = '';
                cell.classList.remove('X', 'O');
            });
        };


        

        cells.forEach(cell => {
            cell.addEventListener('click', handleCellClick);
        });

        restartButton.addEventListener('click', handleRestartGame);
        
      
        statusDisplay.innerHTML = `Player ${currentPlayer}'s turn`;
    }

setupTicTacToe();