import React from 'react';

import {Program} from '../Program/Program';
import { Program as ProgramType } from '../../context/ProgramsContext';

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
      {programs.map((program) => (
        <Program
          key={program.id} // Using unique ID as key
          program={program}
        />
      ))}
    </ul>
  );
};
