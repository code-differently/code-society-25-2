document.querySelectorAll('[data-tabs]').forEach(function(root){
  var tabs=[].slice.call(root.querySelectorAll('.tab'));
  var panels=[].slice.call(root.querySelectorAll('.tab-panel'));
  function activate(i){
    tabs.forEach(function(t,j){
      var active=j===i;
      t.classList.toggle('is-active',active);
      t.setAttribute('aria-selected',String(active));
      panels[j].classList.toggle('is-active',active);
    });
  }
  tabs.forEach(function(tab,i){
    tab.addEventListener('click',function(){activate(i);});
    tab.addEventListener('keydown',function(e){
      if(e.key==='ArrowRight') activate((i+1)%tabs.length);
      if(e.key==='ArrowLeft') activate((i-1+tabs.length)%tabs.length);
    });
  });
  activate(0);
});

document.querySelectorAll('[data-accordion]').forEach(function(root){
  root.querySelectorAll('.accordion-item').forEach(function(item){
    var header=item.querySelector('.accordion-header');
    header.addEventListener('click',function(){
      item.classList.toggle('open');
    });
  });
});

document.querySelectorAll('[data-gallery]').forEach(function(root){
  var images=[
    './assets/img1.svg',
    './assets/img2.svg',
    './assets/img3.svg',
    './assets/img4.svg',
    './assets/img5.svg'
  ];
  var slots=[].slice.call(root.querySelectorAll('.slot'));
  var index=0;
  function visibleSlots(){
    var width=window.innerWidth;
    if(width<=560) return 1;
    if(width<=900) return 2;
    return 3;
  }
  function render(){
    var count=visibleSlots();
    for(var i=0;i<slots.length;i++){
      if(i<count){
        var src=images[(index+i)%images.length];
        slots[i].src=src;
        slots[i].style.display='';
      }else{
        slots[i].style.display='none';
      }
    }
  }
  function next(){index=(index+1)%images.length;render();}
  function prev(){index=(index-1+images.length)%images.length;render();}
  root.querySelector('.next').addEventListener('click',next);
  root.querySelector('.prev').addEventListener('click',prev);
  window.addEventListener('resize',render);
  render();
});
