import './ProgramList.scss';
import { Program as ProgramType } from '@code-differently/types';
import { use, useEffect, useState } from 'react';
import { Program } from '../Program';

export const ProgramList: React.FC = () => {
  const programs: ProgramType[] = [];
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch('http://localhost:4000/programs');
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const result = await response.json();
      const programs = result;
      console.log(programs);
    };
    fetchData();
  },[]);
  
  
  return (
    <ul className="programs">
      {
      programs.map((program)=>(
          <Program key={program.id} title={program.id}>
            <p>{program.description}</p>
          </Program>
      ))}
    </ul>
  );
};