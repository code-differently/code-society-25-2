export const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:4000';

export async function getPrograms() {
  const response = await fetch(`${API_URL}/programs`);
  if (!response.ok) {
    throw new Error('Failed to fetch programs');
  }
  return response.json();
}
