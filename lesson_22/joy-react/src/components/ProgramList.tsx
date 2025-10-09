import React from "react";
import Program from "./Program";

export type ProgramItem = {
  title: string;
  description: string;
};

type ProgramListProps = {
  programs: ProgramItem[];
};

const ProgramList: React.FC<ProgramListProps> = ({ programs }) => {
  return (
    <ul className="programs">
      {programs.map((p, i) => (
        <Program key={i} title={p.title} description={p.description} />
      ))}
    </ul>
  );
};

export default ProgramList;
