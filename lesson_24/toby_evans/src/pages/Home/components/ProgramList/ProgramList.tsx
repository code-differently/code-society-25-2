import { useQuery } from "@tanstack/react-query";
import { getPrograms } from "../../../../lib/api";
import type { Program } from "@code-differently/types";
import "./ProgramList.scss";
export default function ProgramList() {
  const { data, isLoading, isError } = useQuery({ queryKey: ["programs"], queryFn: getPrograms });
  if (isLoading) return <div>Loading…</div>;
  if (isError) return <div>Failed to load programs</div>;
  const programs = (data as Program[]) ?? [];
  return (
    <ul className="program-list">
      {programs.map((p) => (
        <li key={p.id} className="program">
          <h3 className="program__title">{p.title}</h3>
          <p className="program__description">{p.description}</p>
        </li>
      ))}
    </ul>
  );
}
