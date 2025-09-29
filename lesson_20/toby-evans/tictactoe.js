(function(){
  var root=document.querySelector('[data-ttt]');
  var statusEl=root.querySelector('.ttt-status');
  var resetBtn=root.querySelector('.ttt-reset');
  var cells=[].slice.call(root.querySelectorAll('.ttt-cell'));
  var current='X';
  var board=new Array(9).fill(null);
  var over=false;
  var lines=[[0,1,2],[3,4,5],[6,7,8],[0,3,6],[1,4,7],[2,5,8],[0,4,8],[2,4,6]];
  function setStatus(t){statusEl.textContent=t;}
  function winner(b){
    for(var i=0;i<lines.length;i++){
      var a=lines[i][0],c=lines[i][1],d=lines[i][2];
      if(b[a]&&b[a]===b[c]&&b[a]===b[d]) return b[a];
    }
    return null;
  }
  function isDraw(b){return b.every(function(v){return v!==null;});}
  function handleClick(e){
    if(over) return;
    var idx=parseInt(e.currentTarget.getAttribute('data-cell'),10);
    if(board[idx]!==null) return;
    board[idx]=current;
    e.currentTarget.textContent=current;
    var w=winner(board);
    if(w){
      over=true;
      cells.forEach(function(btn){btn.disabled=true;});
      setStatus('Player '+w+' wins');
      return;
    }
    if(isDraw(board)){
      over=true;
      cells.forEach(function(btn){btn.disabled=true;});
      setStatus('Draw');
      return;
    }
    current=current==='X'?'Y':'X';
    setStatus('Player '+current+' to move');
  }
  function reset(){
    board=new Array(9).fill(null);
    current='X';
    over=false;
    cells.forEach(function(btn){btn.textContent='';btn.disabled=false;});
    setStatus('Player X to move');
  }
  cells.forEach(function(btn){btn.addEventListener('click',handleClick);});
  resetBtn.addEventListener('click',reset);
  setStatus('Player X to move');
})();
