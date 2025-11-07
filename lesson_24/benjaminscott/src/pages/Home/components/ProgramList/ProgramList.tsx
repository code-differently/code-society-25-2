import React from 'react';

import {Program as ProgramType} from '../../../../../../types/src';
import {Program} from '../Program';

export default function ProgramList() {
  const [programs, setPrograms] = React.useState<Program[]>([]);
  const [loading, setLoading] = React.useState(true);
  const [error, setError] = React.useState(null);

  React.useEffect(() => {
    fetch('http://localhost:4000/programs')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setPrograms(data);
        setLoading(false);
      })
      .catch(error => {
        setError(error);
        setLoading(false);
      });
  }, []);

  return (
    <ul className="programs">
      {programs.map((program: ProgramType) => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
}
