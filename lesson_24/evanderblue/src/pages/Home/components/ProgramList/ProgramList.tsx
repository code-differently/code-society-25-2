import './ProgramList.scss';
import {useEffect, useState} from 'react';

import {Program} from '../Program/Program';

interface ProgramType {
  id: string;
  title: string;
  description: string;
}

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramType[]>([]);

  useEffect(() => {
    fetch('/api/programs')
      .then(response => response.json())
      .then(data => setPrograms(data.programs));
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
