import AddProgram from './pages/AddProgram/AddProgram';
import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Link, Route, Routes} from 'react-router-dom';

function Home() {
  return <h1 style={{color: 'limegreen'}}>✅ React is Working</h1>;
}

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <nav style={{padding: '1rem', backgroundColor: '#f5f5f5'}}>
        <Link to="/" style={{marginRight: '1rem'}}>
          🏠 Home
        </Link>
        <Link to="/add-program">➕ Add Program</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-program" element={<AddProgram />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
