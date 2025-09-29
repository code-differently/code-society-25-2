const grid = document.querySelectorAll("td");
const playerTurn = document.querySelectorAll(".player-turn");
const restart = document.querySelectorAll(".restartBtn")

let board = ["", "", "", "", "", "", "", "", ""];
let currentSelection = "X";

let gameOver = false;


const winningSelections = [
    [0,1,2],[0,3,6],[0,4,8],
    [1,4,7] ,[2,5,8],[2,4,6]
    [3,4,5] ,[6,7,8]
                        ]


function handlePlayerSelection(clicked) {
    const selection = clicked.target;
    const index  = parseInt(selection.getAttribute("data-index"));

    if (board[index] !== "" || gameOver) {
        return;
    }
}

function updateBoard(square,index) {
    board[index] = currentSelection;
    square.textContext = currentSelection;
}

function changeTurns() {
    currentSelection = currentSelection === "X" ? "O" : "X";
    playerTurn.textContext = `Player ${currentSelection} turn`;

}

function checkResult() {
    
}