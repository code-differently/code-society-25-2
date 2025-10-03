import './Programs.scss';
import React from 'react';
import {Link} from 'react-router-dom';

import {useProgramsContext} from '../../context/ProgramsContext';

import {ProgramList} from '../../components/ProgramList/ProgramList';

export const Programs: React.FC = () => {
  const {programs} = useProgramsContext();

  return (
    <article>
      <section className="programs-hero-section">
        <div className="hero-overlay"></div>
        <div className="hero-content">
          <h2 className="hero-title">
            Our <em className="highlight">Programs</em>
          </h2>
          <div className="hero-text">
            Explore our diverse range of programs designed to empower and
            inspire learners of all ages.
          </div>
          <div className="programs-hero-actions">
            <Link to="/" className="back-to-home-button">
              ← Back to Home
            </Link>
          </div>
        </div>
      </section>
      <ProgramList programs={programs} />
    </article>
  );
};
