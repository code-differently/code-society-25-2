import React from "react";
type Props={title:string;description:string};
export default function Program({title,description}:Props){
  return(
    <article className="program-card">
      <h3 className="program-title">{title}</h3>
      <p className="program-desc">{description}</p>
    </article>
  );
}
