import React,{useState} from "react";
import { useNavigate } from "react-router-dom";

export default function AddProgram(){
  const nav=useNavigate();
  const [title,setTitle]=useState("");
  const [description,setDescription]=useState("");
  async function onSubmit(e:React.FormEvent){
    e.preventDefault();
    try{
      await fetch("/api/programs",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({title,description})});
    }catch(_){}
    nav("/");
  }
  return(
    <main>
      <h1>Add Program</h1>
      <form onSubmit={onSubmit} style={{display:"grid",gap:"12px",maxWidth:480}}>
        <label>Title<input value={title} onChange={e=>setTitle(e.target.value)} required/></label>
        <label>Description<textarea value={description} onChange={e=>setDescription(e.target.value)} required rows={4}/></label>
        <button type="submit">Save</button>
      </form>
    </main>
  );
}
