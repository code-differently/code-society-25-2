import React from 'react';
import { Program as ProgramType } from '../../context/ProgramsContext';

interface ProgramProps {
  program: ProgramType;
}

export const Program: React.FC<ProgramProps> = ({program}) => {
  // Handle edge cases for empty/null/undefined values
  const displayTitle = program.title?.trim() || 'Untitled';
  const displayDescription = program.description?.trim() || 'No description provided.';

  return (
    <li className="program">
      <h3>{displayTitle}</h3>
      <p>{displayDescription}</p>
    </li>
  );
};
