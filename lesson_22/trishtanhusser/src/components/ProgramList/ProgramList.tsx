import React from 'react';
import { Program } from '../Program';
import './ProgramList.scss';

interface ProgramData {
  id: number;
  title: string;
  description: string;
}

interface ProgramListProps {
  programs: ProgramData[];
}

export const ProgramList: React.FC<ProgramListProps> = ({ programs }) => {
  return (
    <ul className="programs">
      {programs.map((program) => (
        <Program
          key={program.id}
          title={program.title}
          description={program.description}
        />
      ))}
    </ul>
  );
};
