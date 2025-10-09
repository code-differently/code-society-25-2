import React, { FC } from 'react'
import './Program.scss';

type ProgramType = {
    title:string;
    description:string;
}

const Program:FC<ProgramType> = ({title, description}) => {
  return (
    // <div>
        <li className="program">
            <h3>{title}</h3>
            <p>
                {description}
            </p>
        </li> 
    // </div>

  )
}

export default Program