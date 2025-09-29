/* Lesson 20 Components Script (Thomas Boyle) */
(function(){
  const qs = (s,sc=document)=>sc.querySelector(s);
  const qsa = (s,sc=document)=>Array.from(sc.querySelectorAll(s));

  /* Tabs */
  function initTabs(root){
    const tablist = qs('[role="tablist"]', root);
    if(!tablist) return;
    const tabs = qsa('[role="tab"]', tablist);
    const panels = tabs.map(t=>qs('#'+t.getAttribute('aria-controls')));

    function activate(tab){
      tabs.forEach(t=>{ const sel=t===tab; t.setAttribute('aria-selected', sel); t.tabIndex=sel?0:-1; });
      panels.forEach(p=>p.hidden=true);
      const panel = qs('#'+tab.getAttribute('aria-controls'));
      if(panel) panel.hidden=false;
      tab.focus();
    }

    tablist.addEventListener('click', e=>{
      const t = e.target.closest('[role="tab"]');
      if(t) activate(t);
    });

    tablist.addEventListener('keydown', e=>{
      const current = tabs.indexOf(document.activeElement);
      if(current<0) return;
      let next = null;
      switch(e.key){
        case 'ArrowRight': next=(current+1)%tabs.length; break;
        case 'ArrowLeft': next=(current-1+tabs.length)%tabs.length; break;
        case 'Home': next=0; break;
        case 'End': next=tabs.length-1; break;
      }
      if(next!==null){ e.preventDefault(); activate(tabs[next]); }
    });
  }

  /* Accordion */
  function initAccordion(root){
    const triggers = qsa('.accordion-trigger', root);
    if(!triggers.length) return;
    function toggle(btn){
      const exp = btn.getAttribute('aria-expanded')==='true';
      btn.setAttribute('aria-expanded', !exp);
      const panel = qs('#'+btn.getAttribute('aria-controls'));
      if(panel) panel.hidden = exp;
    }
    triggers.forEach(btn=>{
      btn.addEventListener('click', ()=>toggle(btn));
      btn.addEventListener('keydown', e=>{
        const i = triggers.indexOf(btn);
        if(['ArrowDown','ArrowUp'].includes(e.key)){
          e.preventDefault();
          const n = e.key==='ArrowDown' ? (i+1)%triggers.length : (i-1+triggers.length)%triggers.length;
          triggers[n].focus();
        }
        if(['Enter',' '].includes(e.key)) { e.preventDefault(); toggle(btn); }
      });
    });
  }

  /* Gallery */
  function initGallery(root){
    const viewport = qs('.gallery-viewport', root);
    if(!viewport) return;
    const prevBtn = qs('[data-prev]', root);
    const nextBtn = qs('[data-next]', root);
    const pauseBtn = qs('[data-pause]', root);
    const dotsWrap = qs('[data-dots]', root);

    function svg(color,text){
      return `data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='480' height='300'><rect width='100%' height='100%' fill='${color}'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='48' fill='white'>${text}</text></svg>`;
    }
    const images = [
      svg('#0b3b82','One'),
      svg('#f26722','Two'),
      svg('#6b6b6b','Three'),
      svg('#1d7f5f','Four'),
      svg('#8246b3','Five'),
      svg('#b52828','Six')
    ];

    let idx=0; let paused=false; let timer=null;

    function visible(){ if(innerWidth<480) return 1; if(innerWidth<860) return 2; return 3; }

    function render(){
      const count = visible();
      viewport.innerHTML='';
      viewport.className='gallery-viewport count-'+count;
      for(let i=0;i<count;i++){
        const imgI = (idx+i)%images.length;
        const fig=document.createElement('figure');
        const img=document.createElement('img');
        img.src=images[imgI];
        img.alt='Gallery image '+(imgI+1);
        fig.appendChild(img);
        viewport.appendChild(fig);
      }
      updateDots();
    }

    function updateDots(){
      dotsWrap.innerHTML='';
      images.forEach((_,i)=>{
        const b=document.createElement('button');
        b.type='button'; b.className='dot'+(i===idx?' active':'');
        b.setAttribute('role','tab');
        b.setAttribute('aria-label','Go to image '+(i+1));
        b.setAttribute('aria-selected', i===idx);
        b.addEventListener('click', ()=>{ idx=i; render(); });
        dotsWrap.appendChild(b);
      });
    }

    function next(){ idx=(idx+1)%images.length; render(); }
    function prev(){ idx=(idx-1+images.length)%images.length; render(); }
    function play(){ timer=setInterval(()=>{ if(!paused) next(); },4000); }
    function togglePause(){ paused=!paused; pauseBtn.setAttribute('aria-pressed', paused); pauseBtn.textContent=paused?'Play':'Pause'; }

    nextBtn.addEventListener('click', next);
    prevBtn.addEventListener('click', prev);
    pauseBtn.addEventListener('click', togglePause);
    root.addEventListener('mouseenter', ()=>paused=true);
    root.addEventListener('mouseleave', ()=>paused=false);
    root.addEventListener('focusin', ()=>paused=true);
    root.addEventListener('focusout', ()=>paused=false);
    addEventListener('resize', render);

    render(); play();
  }

  /* Init */
  document.addEventListener('DOMContentLoaded', ()=>{
    qsa('[data-tabs]').forEach(initTabs);
    qsa('[data-accordion]').forEach(initAccordion);
    qsa('[data-gallery]').forEach(initGallery);
  });
})();
