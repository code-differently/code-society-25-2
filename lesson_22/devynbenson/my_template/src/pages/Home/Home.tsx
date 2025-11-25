import './Home.scss';
import React from 'react';
import { Link } from 'react-router-dom';
import { ProgramList } from '../../components';
import { usePrograms } from '../../contexts/ProgramsContext';

export const Home: React.FC = () => {
  // Get programs from context
  const { programs } = usePrograms();

  return (
    <article>
      <section className="hero-section">
        <div className="hero-overlay"></div>
        <div className="hero-content">
          <h2 className="hero-title">
            Together we can move the needle of{' '}
            <em className="highlight">diversity in tech.</em>
          </h2>
          <div className="hero-text">
            <span>Code Differently</span> provides hands on training and
            education through coding classes that gives participants the
            technical and cognitive skills they need to excel in
            technology-driven workplaces.
          </div>
        </div>
      </section>
      <ProgramList programs={programs} />
      
      {/* Call-to-action section for adding programs */}
      <section className="cta-section">
        <div className="cta-content">
          <h3>Want to contribute a new program?</h3>
          <p>Help us expand our offerings by proposing a new program for Code Differently.</p>
          <Link to="/add-program" className="cta-button">
            Add New Program
          </Link>
        </div>
      </section>
    </article>
  );
};
