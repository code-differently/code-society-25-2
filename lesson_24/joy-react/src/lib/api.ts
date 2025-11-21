import type { Program } from '@code-differently/types';

const BASE = import.meta.env.VITE_API_URL ?? 'http://localhost:4000';
const PROGRAMS_PATH = '/programs';

export async function getPrograms(): Promise<Program[]> {
  const res = await fetch(`${BASE}${PROGRAMS_PATH}`);
  if (!res.ok) throw new Error('Failed to load programs');
  return res.json();
}

export async function createProgram(input: { title: string; description: string }): Promise<void> {
  const res = await fetch(`${BASE}${PROGRAMS_PATH}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(input),
  });
  if (!res.ok) {
    const text = await res.text().catch(() => '');
    throw new Error(text || 'Failed to create program');
  }
}
