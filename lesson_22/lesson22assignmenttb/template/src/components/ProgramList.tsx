import {Program} from './Program';
import React from 'react';

interface ProgramData {
  title: string;
  description: string;
}

interface ProgramListProps {
  programs: ProgramData[];
}

export const ProgramList: React.FC<ProgramListProps> = ({programs}) => {
  return (
    <section className="programs-section">
      <h2>
        Our <em className="highlight">Programs</em>
      </h2>
      <ul className="programs">
        {programs.map((program, index) => (
          <Program
            key={index}
            title={program.title}
            description={program.description}
          />
        ))}
      </ul>
    </section>
  );
};
