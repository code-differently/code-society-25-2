import './ProgramList.scss';
import {useQuery} from '@tanstack/react-query';
import {Program as ProgramComponent} from '../Program';

export interface Program {
  id: string;
  title: string;
  description: string;
}

const fetchPrograms = async (): Promise<Program[]> => {
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
    return <div>Error loading programs: {error.message}</div>;
  }

  return (
    <>
      {programs?.map((program) => (
        <ProgramComponent key={program.id} title={program.title}>
          <p>{program.description}</p>
        </ProgramComponent>
      ))}
    </>
  );
};