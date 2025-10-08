import {useMutation, useQueryClient} from '@tanstack/react-query';

const API_BASE_URL = 'http://localhost:4000';

interface AddProgramData {
  title: string;
  description: string;
}

const addProgram = async (programData: AddProgramData): Promise<void> => {
  const response = await fetch(`${API_BASE_URL}/programs`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(programData),
  });

  if (!response.ok) {
    throw new Error('Failed to add program');
  }
};

export const useAddProgram = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: addProgram,
    onSuccess: () => {
      queryClient.invalidateQueries({queryKey: ['programs']});
    },
  });
};
