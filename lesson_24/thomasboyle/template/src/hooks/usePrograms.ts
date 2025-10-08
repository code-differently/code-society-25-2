import {Program} from '@code-differently/types';
import {useQuery} from '@tanstack/react-query';

const API_BASE_URL = 'http://localhost:4000';

const fetchPrograms = async (): Promise<Program[]> => {
  const response = await fetch(`${API_BASE_URL}/programs`);

  if (!response.ok) {
    throw new Error('Failed to fetch programs');
  }

  return response.json();
};

export const usePrograms = () => {
  return useQuery({
    queryKey: ['programs'],
    queryFn: fetchPrograms,
    staleTime: 5 * 60 * 1000,
    gcTime: 10 * 60 * 1000,
  });
};
