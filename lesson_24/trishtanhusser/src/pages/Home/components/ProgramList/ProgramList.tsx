import './ProgramList.scss';

import { Program as ProgramType } from '@code-differently/types';
import { useQuery } from '@tanstack/react-query';
import { Program } from '../Program';

const fetchPrograms = async (): Promise<ProgramType[]> => {
  const response = await fetch('http://localhost:4000/programs');
  if (!response.ok) {
    throw new Error('Failed to fetch programs');
  }
  return response.json();
};

export const ProgramList: React.FC = () => {
  const { data: programs, isLoading, error } = useQuery({
    queryKey: ['programs'],
    queryFn: fetchPrograms,
  });

  if (isLoading) {
    return <div>Loading programs...</div>;
  }

  if (error) {
    return <div>Error loading programs. Please try again later.</div>;
  }

  return (
    <>
      {programs?.map((program) => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
    </>
  );
};
