import React from 'react';

import {Program as ProgramType} from '../../context/ProgramsContext';

import {Program} from '../Program/Program';

interface ProgramListProps {
  programs: ProgramType[];
}

export const ProgramList: React.FC<ProgramListProps> = ({programs}) => {
  // Handle edge case for empty programs array
  if (!programs || programs.length === 0) {
    return (
      <div className="no-programs">
        <p>No programs available.</p>
      </div>
    );
  }

  return (
    <ul className="programs">
      {programs.map(program => (
        <Program
          key={program.id} // Uses unique ID as key
          program={program}
        />
      ))}
    </ul>
  );
};
