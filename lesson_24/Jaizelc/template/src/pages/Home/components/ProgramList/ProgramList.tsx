import './ProgramList.scss';
import {Program as ProgramType} from '@code-differently/types';
import {useQuery} from '@tanstack/react-query';

import {Program} from '../Program';

const API_BASE_URL = 'http://localhost:4000';

const fetchPrograms = async (): Promise<ProgramType[]> => {
  const response = await fetch(`${API_BASE_URL}/programs`);
  if (!response.ok) {
    throw new Error('Failed to fetch programs');
  }
  return response.json();
};

export const ProgramList: React.FC = () => {
  const {
    data: programs,
    isLoading,
    error,
  } = useQuery({
    queryKey: ['programs'],
    queryFn: fetchPrograms,
  });

  if (isLoading) {
    return (
      <ul className="programs">
        <li>Loading programs...</li>
      </ul>
    );
  }

  if (error) {
    return (
      <ul className="programs">
        <li>Error loading programs: {error.message}</li>
      </ul>
    );
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
