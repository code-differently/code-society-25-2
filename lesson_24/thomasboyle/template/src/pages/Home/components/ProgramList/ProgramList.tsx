import './ProgramList.scss';
import React from 'react';

import {usePrograms} from '../../../../hooks';
import {Program} from '../Program';

export const ProgramList: React.FC = () => {
  const {data: programs, isLoading, error} = usePrograms();

  if (isLoading) {
    return (
      <div className="programs">
        <p>Loading programs...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="programs">
        <p>Error loading programs: {error.message}</p>
      </div>
    );
  }

  if (!programs || programs.length === 0) {
    return (
      <div className="programs">
        <p>No programs available.</p>
      </div>
    );
  }

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
