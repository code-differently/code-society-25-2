import './ProgramList.scss';
import React, { useState, useEffect } from 'react';
import { Program } from '../Program';
import { Program as ProgramType } from '@code-differently/types';

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramType[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  async function getDataFromAPI(url: string) {
    try {
      const result = await fetch(url);
      console.log(result);

      if (result.ok) {
        const data = await result.json();
        return data;
      } else {
        throw new Error(`Failed to load data: ${result.status}`);
      }
    } catch (error) {
      console.log("Couldn't load data", error);
    }
  }

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        setLoading(true);
        const data = await getDataFromAPI('http://localhost:4000/programs');
        setPrograms(data);
      } catch (err) {
        if (err instanceof Error) {
          setError(err.message);
        } else {
          setError("An error occurred");
        }
      } finally {
        setLoading(false);
      }
    };
    fetchPrograms();
  }, []);

  if (loading) {
    return <div className='programs'>Loading Programs...</div>
  }

  if (error) {
    return <div className='programs'>Error: {error}</div>
  }

  return (
    <ul className="programs">
      {programs.map((program) => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
}