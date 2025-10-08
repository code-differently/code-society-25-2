import './ProgramList.scss';
import {Program} from '@code-differently/types';
import React, { FC, useEffect, useState } from 'react';
import { Program as ProgramType } from '../Program';


export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<Program[]>([]);

  useEffect(()=> {
    const getData = async() => {
    try{
       const response = await fetch("http://localhost:4000/programs");
       if(response.ok){
        const data = await response.json();
        return data;
    }else{
      throw new Error(`Failed to load data, ${response.status}`);
    }
    } catch (error) {
      console.error("Couldn't load data", error);
  }
};
  getData();
},[]);
  return (
    <ul className="programs">
     {programs.map(program => (
      <ProgramType key={program.id} title={program.title}>
        <p>{program.description}</p>
      </ProgramType>
     ))}
    </ul>
  );
};