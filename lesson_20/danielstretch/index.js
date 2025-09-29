const grid = document.querySelectorAll("td");
const playerTurn = document.querySelectorAll(".player-turn");
const restartBtn = document.querySelector(".restartBtn")

let board = ["", "", "", "", "", "", "", "", ""];
let currentSelection = "X";

let gameOver = false;


const winningSelections = [
    [0,1,2],[0,3,6],[0,4,8],
    [1,4,7] ,[2,5,8],[2,4,6],
    [3,4,5] ,[6,7,8]
];

function handlePlayerSelection(clicked) {
    const selection = clicked.target;
    const index  = parseInt(selection.getAttribute("data-index"));

    if (board[index] !== "" || gameOver) {
        return;
    }
    updateBoard(selection,index);
    checkResult();

}

function updateBoard(square,index) {
    board[index] = currentSelection;
    square.textContent = currentSelection;
}

function changeTurns() {
    currentSelection = currentSelection === "X" ? "O" : "X";
    playerTurn.textContent = `Player ${currentSelection} turn`;

}

function checkResult() {
    let roundWon=false;
    for (let i =0;i<winningSelections.length;i++) {
        const [a,b,c] = winningSelections[i];
        if(board[a]==="" || board[b]==="" || board[c]===""){
            continue;
        }
        if (board[a] === board[b] && board[b] === board[c]) {
            roundWon = true;
            break;
        }
    }

    if (roundWon) {
        playerTurn.textContent = `Player ${currentSelection} Wins!`;
        gameOver =true;
        return;
    }

    changeTurns();
}

function restart() {
    board = ["", "", "", "", "", "", "", "", ""];
    gameActive = true;
    currentPlayer = 'X';
    playerTurn.textContent = `Player ${currentPlayer}'s turn`;
    grid.forEach(cell => (cell.textContent = ''));
}

grid.forEach(cell => cell.addEventListener('click', handlePlayerSelection));
restartBtn.addEventListener('click', restart);