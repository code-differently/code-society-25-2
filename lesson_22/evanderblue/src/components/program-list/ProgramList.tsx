import React from 'react';

import {Program} from '../program';

// 1. Define what data structure each program should have
interface ProgramData {
  id: number;
  title: string;
  description: string;
}

// 2. Define what props this component expects
interface ProgramListProps {
  programs: ProgramData[];
}

// 3. Create the component that renders a list of programs
export const ProgramList: React.FC<ProgramListProps> = ({programs}) => {
  return (
    <ul className="programs">
      {programs.map(program => (
        <Program
          key={program.id}
          title={program.title}
          description={program.description}
        />
      ))}
    </ul>
  );
};
