const cells =document.querySelectorAll('.cell');
const statusText = document.getElementById('status');
const restartBtn = document.getElementById('restartBtn'); 

let board = ["", "", "", "", "", "", "", "", ""];
let currentPlayer = "X";
let gameActive = true;

const winningCondtions = [
    [0,1,2], [0,3,6], [0,4,8],
    [1,4,7],
    [2,5,8], [2,4,6],
    [3,4,5],
    [6,7,8]
];

function handleCellClick(clicked){
    const clickedCell = clicked.target;
    const index = parseInt(clickedCell.getAttribute('data-index'));

    if(board[index] !== "" || !gameActive){
        return;
    }

    updateCell(clickedCell,index);
    checkResult();
}

function updateCell(cell, index){
    board[index] = currentPlayer;
    cell.textContent = currentPlayer;
}

function changePlayer(){
    currentPlayer = currentPlayer === "X" ? "O" : "X";
    statusText.textContent = `Player ${currentPlayer}'s turn`;
}

function checkResult(){
    let roundWon = false;
    for(let i=0;i<winningCondtions.length;i++){
        const [a,b,c] = winningCondtions[i];
        if(board[a]==="" || board[b]==="" || board[c]===""){
            continue;
        }
         if (board[a] === board[b] && board[b] === board[c]) {
      roundWon = true;
      break;
    }
  }

  if (roundWon) {
    statusText.textContent = `Player ${currentPlayer} wins!`;
    gameActive = false;
    return;
  }

  if (!board.includes('')) {
    statusText.textContent = "It's a draw!";
    gameActive = false;
    return;
  }

  changePlayer();
}

function restartGame() {
  board = ["", "", "", "", "", "", "", "", ""];
  gameActive = true;
  currentPlayer = 'X';
  statusText.textContent = `Player ${currentPlayer}'s turn`;
  cells.forEach(cell => (cell.textContent = ''));
}

cells.forEach(cell => cell.addEventListener('click', handleCellClick));
restartBtn.addEventListener('click', restartGame);
