import React,{useEffect,useState} from "react";
import ProgramList,{ProgramItem} from "../../components/ProgramList";
import { Link } from "react-router-dom";

export default function Home(){
  const [programs,setPrograms]=useState<ProgramItem[]>([]);
  useEffect(()=>{
    let cancelled=false;
    fetch("/api/programs").then(r=>{
      if(!r.ok) throw new Error(String(r.status));
      return r.json();
    }).then((data:ProgramItem[])=>{
      if(!cancelled) setPrograms(data);
    }).catch(()=>{
      if(!cancelled) setPrograms([
        {title:"Intro to JS",description:"Variables, functions, loops"},
        {title:"React Basics",description:"Components, props, state"},
        {title:"APIs 101",description:"REST calls from the browser"}
      ]);
    });
    return ()=>{cancelled=true};
  },[]);
  return(
    <main>
      <h1>Programs</h1>
      <div style={{margin:"12px 0"}}><Link to="/add-program">Add a Program</Link></div>
      <ProgramList programs={programs}/>
    </main>
  );
}
