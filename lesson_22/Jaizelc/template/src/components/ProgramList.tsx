import React from 'react';
import Program from './Program';

interface ProgramData {
  title: string;
  description: string;
}

interface ProgramListProps {
  programs: ProgramData[];
}

const ProgramList: React.FC<ProgramListProps> = ({ programs }) => (
  <ul className="programs">
    {programs.map((program, idx) => (
      <Program key={idx} title={program.title} description={program.description} />
    ))}
  </ul>
);

export default ProgramList;
