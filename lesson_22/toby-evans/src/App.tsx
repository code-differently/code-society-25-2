import React from "react";
import { Link, Outlet } from "react-router-dom";
export default function App(){
  return(
    <div style={{padding:"16px"}}>
      <nav style={{marginBottom:16,display:"flex",gap:12}}>
        <Link to="/">Home</Link>
        <Link to="/add-program">Add Program</Link>
      </nav>
      <Outlet/>
    </div>
  );
}
