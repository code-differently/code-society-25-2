import './ProgramList.scss';
import {Program} from '@code-differently/types';
import React, {useEffect, useState} from 'react';

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<Program[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        const response = await fetch('http://localhost:4000/programs');
        if (!response.ok) {
          throw new Error('Failed to fetch programs');
        }
        const data: Program[] = await response.json();
        setPrograms(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchPrograms();
  }, []);

  if (loading) return <div className="loading">Loading programs...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <ul className="programs">
      {programs.map(program => (
        <li key={program.id} className="program">
          <h3>{program.title}</h3>
          <p>{program.description}</p>
        </li>
      ))}
    </ul>
  );
};
