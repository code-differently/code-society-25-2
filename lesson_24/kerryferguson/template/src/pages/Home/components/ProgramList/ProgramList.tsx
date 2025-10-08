import './ProgramList.scss';
import {Program as ProgramType} from '@code-differently/types';
import React, {useEffect, useState} from 'react';

import {Program} from '../Program';

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramType[]>([]);

  useEffect(() => {

    fetch('http://localhost:4000/programs')
      .then(response => response.json())
      .then(data => {
        console.log('Fetched programs:', data); 
        setPrograms(data);
      })
      .catch(error => {
        console.error('Error fetching programs:', error);
      });
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
