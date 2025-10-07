import './ProgramList.scss';
import { Program as ProgramType } from '@code-differently/types';
import React, { useEffect, useState } from 'react';



import { Program } from '../Program';





export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramType[]>([]);

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        const response = await fetch('http://localhost:4000/programs');
        if (!response.ok) {
          console.error('Failed to fetch programs');
          return;
        }
        console.log(response);
        const data = await response.json();

        setPrograms(data);
      } catch (err) {
        console.error('Error fetching programs:', err);
        console.error(
          'Error details:',
          err instanceof Error ? err.message : err
        );
      }
    };
    fetchPrograms();
  }, []);

  return (
    <ul className="programs">
      {programs.map(program => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
};