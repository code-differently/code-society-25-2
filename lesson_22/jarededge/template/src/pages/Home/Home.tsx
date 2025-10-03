import './Home.scss';
import React from 'react';
import {Link} from 'react-router-dom';

import {usePrograms} from '../../context/ProgramsContext';

import {ProgramList} from '../../components/ProgramList/ProgramList';

export const Home: React.FC = () => {
  // Get programs from context instead of hardcoded data
  const {programs} = usePrograms();
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
      <section className="programs-section">
        <div className="programs-header">
          <h2>
            Our <em className="highlight">Programs</em>
          </h2>
          <Link to="/add-program" className="btn btn-primary">
            Add New Program
          </Link>
        </div>
        <ProgramList programs={programs} />
      </section>
    </article>
  );
};
