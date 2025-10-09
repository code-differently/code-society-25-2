import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import AddProgram from "./pages/AddProgram";

const App: React.FC = () => {
  return (
    <>
      <header className="site-header" style={{display:"flex", gap:"1rem", alignItems:"center", padding:"0.75rem 1rem"}}>
        <Link to="/" className="logo">Code Differently</Link>
        <nav style={{display:"flex", gap:"1rem"}}>
          <Link to="/">Home</Link>
          <Link to="/add">Add Program</Link>
        </nav>
      </header>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add" element={<AddProgram />} />
      </Routes>
    </>
  );
};

export default App;
