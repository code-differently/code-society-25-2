import React, { FC } from 'react'

type ProgramType = {
    title:string;
    description:string;
}

const Program:FC<ProgramType> = ({title, description}) => {
  return (
    <div>
        <li>
            <h3>{title}</h3>
            <p>
                {description}
            </p>
        </li> 
    </div>

  )
}

export default Program