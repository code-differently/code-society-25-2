import './ProgramList.scss';
import React from 'react';
import {Link} from 'react-router-dom';

import type {Program as ProgramType} from '../../context/ProgramsContext';

import {Program} from '../Program/Program';

interface ProgramListProps {
  programs: ProgramType[];
}

export const ProgramList: React.FC<ProgramListProps> = ({programs}) => {
  return (
    <section className="programs-section">
      <div className="programs-header">
        <h2>
          Our <em className="highlight">Programs</em>
        </h2>
        <Link to="/add-program" className="add-program-link">
          Add New Program
        </Link>
      </div>
      <ul className="programs">
        {programs.map(program => (
          <Program
            key={program.id}
            title={program.title}
            description={program.description}
          />
        ))}
      </ul>
    </section>
  );
};
