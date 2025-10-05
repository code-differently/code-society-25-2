(function () {
  const cells = Array.from(document.querySelectorAll(".cell"));
  const statusEl = document.getElementById("status");
  const resetBtn = document.getElementById("resetBtn");
  const cpuToggle = document.getElementById("cpuToggle");
  const scoreXEl = document.getElementById("scoreX");
  const scoreOEl = document.getElementById("scoreO");
  const scoreTEl = document.getElementById("scoreT");

  let board, current, gameOver;
  const scores = { X: 0, O: 0, T: 0 };

  const LINES = [
    [0,1,2],[3,4,5],[6,7,8], 
    [0,3,6],[1,4,7],[2,5,8],
    [0,4,8],[2,4,6]         
  ];

  init();

  cells.forEach((btn) => {
    btn.addEventListener("click", () => onMove(parseInt(btn.dataset.idx)));
  
    btn.addEventListener("keydown", (e) => {
      if (e.key === "Enter" || e.key === " ") {
        e.preventDefault();
        btn.click();
      }
    });
  });

  cells.forEach((cell, idx) => {
  cell.addEventListener("mouseenter", () => {
    if (!board[idx] && !gameOver) {
      cell.classList.add(current === "X" ? "hover-x" : "hover-o");
    }
  });

  cell.addEventListener("mouseleave", () => {
    cell.classList.remove("hover-x", "hover-o");
  });
});

  resetBtn.addEventListener("click", init);

  function init() {
    board = Array(9).fill("");
    current = "X";
    gameOver = false;
    cells.forEach((c) => {
      c.textContent = "";
      c.disabled = false;
      c.classList.remove("win");
    });
    setStatus(`Player ${current}’s turn`);
  }

  function onMove(idx) {
    if (gameOver || board[idx]) return;
    place(idx, current);

    const result = evaluate();
    if (handleResult(result)) return;

    if (cpuToggle.checked) {
      const cpuIndex = chooseCpuMove();
      if (cpuIndex != null) {
     
        setTimeout(() => {
          if (!gameOver) {
            place(cpuIndex, current);
            const res2 = evaluate();
            handleResult(res2);
          }
        }, 120);
      }
    }
  }

  function place(idx, mark) {
    board[idx] = mark;
    const btn = cells[idx];
    btn.innerHTML = `<img src="images/${mark.toLowerCase()}.png" alt="${mark}" class="mark-img">`;
    btn.setAttribute("aria-label", `${mark} placed`);
    btn.disabled = true;
    current = mark === "X" ? "O" : "X";
  }

  function evaluate() {
    for (const [a,b,c] of LINES) {
      if (board[a] && board[a] === board[b] && board[a] === board[c]) {
        return { type: "win", line: [a,b,c], player: board[a] };
      }
    }
    if (board.every(Boolean)) return { type: "tie" };
    return { type: "continue" };
  }

  function handleResult(res) {
    if (res.type === "continue") {
      setStatus(`Player ${current}’s turn`);
      return false;
    }

    gameOver = true;
    cells.forEach((c) => (c.disabled = true));

    if (res.type === "win") {
      res.line.forEach((i) => cells[i].classList.add("win"));
      setStatus(`Player ${res.player} wins!`);
      scores[res.player] += 1;
      updateScore();
    } else {
      setStatus(`It's a tie!`);
      scores.T += 1;
      updateScore();
    }
    return true;
  }

  function setStatus(text) {
    statusEl.textContent = text;
  }

  function updateScore() {
    scoreXEl.textContent = scores.X;
    scoreOEl.textContent = scores.O;
    scoreTEl.textContent = scores.T;
  }

  function chooseCpuMove() {
    if (gameOver) return null;
    const me = current;               
    const opp = me === "X" ? "O" : "X";


    const lineToComplete = (p) => {
      for (const [a,b,c] of LINES) {
        const line = [a,b,c];
        const marks = line.map(i => board[i]);
        if (marks.filter(m => m === p).length === 2 && marks.includes("")) {
          return line[marks.indexOf("")];
        }
      }
      return null;
    };

    let idx = lineToComplete(me);
    if (idx != null) return idx;

    idx = lineToComplete(opp);
    if (idx != null) return idx;

    if (!board[4]) return 4;

    const corners = [0,2,6,8].filter(i => !board[i]);
    if (corners.length) return corners[Math.floor(Math.random() * corners.length)];

    const sides = [1,3,5,7].filter(i => !board[i]);
    if (sides.length) return sides[Math.floor(Math.random() * sides.length)];

    return null;
  }
})();
