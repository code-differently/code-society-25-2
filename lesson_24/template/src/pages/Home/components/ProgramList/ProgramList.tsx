import './ProgramList.scss';
import {Program as ProgramType} from '@code-differently/types';
import React, {useEffect, useState} from 'react';

import {Program} from '../Program';


export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramType[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        const response = await fetch('http://localhost:4000/programs');

        if (!response.ok) {
          throw new Error('Failed to fetch programs: ' + response.statusText);
        }

        const data: ProgramType[] = await response.json();
        setPrograms(data);
      } catch (e) {
        console.error('Fetching error:', e)
        setError(
          'Unable to load programs. Ensure the API server is running on port 4000.'
        );
      } finally {
        setIsLoading(false);
      }
    };

    fetchPrograms();
  }, []);

  if (isLoading) {
    return <div className="loading-message">Loading programs...</div>;
  }

  if (error) {
    return <div className="error-message">Error: {error}</div>;
  }

  if (programs.length === 0) {
    return (
      <div className="no-programs">
        No programs found. Use the "Add Program" link to create one!
      </div>
    );
  }

  return (
    <ul className="programs">
      {programs.map(program => (
        <Program key={program.id} title={program.title}>
          {/* Ensure the program object has a description property */}
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
};
