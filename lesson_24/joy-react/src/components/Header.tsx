import './Header.scss';
import logoImg from '../assets/logo.png';
import * as React from 'react';
import { Link, NavLink } from 'react-router-dom';

export const Header: React.FC = () => {
  return (
    <header className="header">
      <div className="header-logo">
        <Link to="/">
          <img src={logoImg} alt="Code Differently Logo" style={{ height: 64, width: 'auto', display: 'block' }} />
        </Link>
      </div>
      <ul className="header-top-menu">
        <li><NavLink to="/">Home</NavLink></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
        <li><NavLink to="/add">Add Program</NavLink></li>
      </ul>
      <div className="header-cta">
        <a className="sign-up-button" href="#">Sign Up</a>
      </div>
    </header>
  );
};
