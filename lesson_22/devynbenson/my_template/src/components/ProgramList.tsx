import React from 'react';
import { Program } from './Program';
import { Program as ProgramType } from '../contexts/ProgramsContext';

interface ProgramListProps {
  programs: ProgramType[];
}

export const ProgramList: React.FC<ProgramListProps> = ({ programs }) => {
  return (
    <section className="programs-section">
      <h2>
        Our <em className="highlight">Programs</em>
      </h2>
      <ul className="programs">
        {programs.map((program) => (
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
