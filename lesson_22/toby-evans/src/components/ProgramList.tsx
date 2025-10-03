import React from "react";
import Program from "./Program";
export type ProgramItem={id?:string;title:string;description:string};
export default function ProgramList({programs}:{programs:ProgramItem[]}){
  return(
    <section className="program-list">
      {programs.map((p,i)=>(<Program key={p.id??i} title={p.title} description={p.description}/>))}
    </section>
  );
}
