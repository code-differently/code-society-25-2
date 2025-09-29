let board = []
for (let i = 0; i < 3; i++) {
  board[i] = []
  for (let j = 0; j < 3; j++) {
    board[i][j] = ''
  }
}

function winner() {
  // Check rows
  for (let i = 0; i < 3; i++) {
    for (let j = 1; j < 3; j++) {
      if (board[i][j] !== board[i][0]){
        break
      }
      if (j === 2 && board[i][0] !== ''){
        return board[i][0]
      }
    }
  }

  // Check columns
  for (let j = 0; j < 3; j++) {
    for (let i = 1; i < 3; i++) {
      if (board[i][j] !== board[0][j]){
        break
      }
      if (i === 2 && board[0][j] !== '') return board[0][j]
    }
  }

  // Check diagonals
  if (board[1][1] !== '') {
    if (board[0][0] === board[1][1] && board[1][1] === board[2][2]) return board[1][1]
    if (board[0][2] === board[1][1] && board[1][1] === board[2][0]) return board[1][1]
  }

  return null
}

let currentPlayer = 'X'
const cells = document.querySelectorAll('.cell')
cells.forEach((cell) => {
  cell.addEventListener('click', () => {
    const row = cell.getAttribute('data-row')
    const col = cell.getAttribute('data-col')
    if (board[row][col] === '' && !winner()) {
      board[row][col] = currentPlayer
      cell.textContent = currentPlayer
      if (winner()) {
        alert(`${currentPlayer} wins!`)
      } else {
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X'
      }
    }
  })
})

document.getElementById('reset').addEventListener('click', () => {
  board = []
  for (let i = 0; i < 3; i++) {
    board[i] = []
    for (let j = 0; j < 3; j++) {
      board[i][j] = ''
    }
  }
  cells.forEach((cell) => {
    cell.textContent = ''
  })
  currentPlayer = 'X'
})