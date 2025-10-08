export const API_URL = import.meta.env.VITE_API_URL || "http://localhost:3000"\;
export async function getPrograms() {
  const r = await fetch(`${API_URL}/programs`, { headers: { "Content-Type": "application/json" } });
  if (!r.ok) throw new Error("Failed to fetch programs");
  return r.json();
}
export async function createProgram(input: { title: string; description: string }) {
  const r = await fetch(`${API_URL}/programs`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(input),
  });
  if (!r.ok) throw new Error("Failed to create program");
  return r.json();
}
