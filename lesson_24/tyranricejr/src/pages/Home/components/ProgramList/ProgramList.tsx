import './ProgramList.scss';
import {useEffect, useState} from 'react';

import {Program as ProgramType} from '../../../../../../types/src';
import {Program} from '../Program';

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        const response = await fetch('http://localhost:4000/programs');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setPrograms(data);
      } catch (err) {
        throw new Error(`Failed to fetch programs: ${err}`);
      } finally {
        setLoading(false);
      }
    };

    fetchPrograms();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <ul className="programs">
      {programs.map((program: ProgramType) => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
};
