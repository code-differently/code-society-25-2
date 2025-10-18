import React from 'react';
import { Program } from '../Program';

interface ProgramData {
  title: string;
  description: string;
}

interface ProgramListProps {
  programs: ProgramData[];
}

export const ProgramList: React.FC<ProgramListProps> = ({ programs }) => {
  return (
    <ul className="programs">
      {programs.map((program, index) => (
        <Program 
          key={index}
          title={program.title} 
          description={program.description} 
        />
      ))}
    </ul>
  );
};
