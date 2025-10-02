import React, { FC, JSX } from 'react'
import { ProgramProps } from '@/types/types'
const Program :FC<ProgramProps>  = ({program}):JSX.Element => {

  return (
    <div>
        <li className='program'></li>
      <h3>{program.title}</h3>
            <p>
              {program.description}
            </p>
    </div>
  )
}

export default Program
