import './ProgramList.scss';
import {useQuery} from '@tanstack/react-query';

import {Program} from '../Program';

type ProgramType = {
  id: string;
  title: string;
  description: string;
};

export const ProgramList: React.FC = () => {
  const {
    data: programs = [],
    isLoading,
    error,
  } = useQuery({
    queryKey: ['programs'],
    queryFn: async () => {
      const res = await fetch('http://localhost:4000/programs');
      if (!res.ok) throw new Error('Failed to fetch programs');
      return res.json();
    },
  });

  if (isLoading) {
    return <div>Loading the programs</div>;
  }

  if (error) {
    return <div>Error loading programs</div>;
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
