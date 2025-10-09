import './ProgramList.scss';
import {Program} from '@code-differently/types';
import {Program as ProgramComponent} from '../Program';
import React, {useEffect, useState} from 'react';

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<Program[]>([]);

  useEffect(() => {
  const fetchData = async () => {
    try {
      const result = await fetch('http://localhost:4000/programs');
      if (!result.ok) {
        throw new Error(`Failed to fetch programs: ${result.status}`);
      }
      const data = await result.json();
      setPrograms(data);
    } catch (error) {
      console.error('Error fetching programs:', error);
    }
  };

  fetchData();
}, []);

  return (
    <ul className="programs">
      {programs.map(program => (
        <ProgramComponent key={program.id} title={program.title}>
          <p>{program.description}</p>
        </ProgramComponent>
      ))}
  </ul>
  );
};
