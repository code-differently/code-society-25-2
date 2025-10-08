import './App.scss';
import {Outlet} from 'react-router-dom';

import Footer from './components/footer/Footer';
import Header from './components/header/Header';

export default function App() {
  return (
    <div className="app">
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}
