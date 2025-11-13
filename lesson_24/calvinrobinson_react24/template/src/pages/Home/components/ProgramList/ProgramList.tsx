import './ProgramList.scss';
import {Program as ProgramType} from '@code-differently/types';
import {useQuery} from '@tanstack/react-query';

import {Program} from '../Program';

export const ProgramList: React.FC = () => {
  const {
    data: programs,
    isLoading,
    error,
  } = useQuery({
    queryKey: ['programs'],
    queryFn: async (): Promise<ProgramType[]> => {
      const response = await fetch('http://localhost:4001/programs');
      if (!response.ok) {
        throw new Error('Failed to fetch programs');
      }
      return response.json();
    },
  });

  if (isLoading) {
    return <div>Loading programs...</div>;
  }

  if (error) {
    return <div>Error loading programs</div>;
  }

  return (
    <ul className="programs">
      {programs?.map(program => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </ul>
  );
};
