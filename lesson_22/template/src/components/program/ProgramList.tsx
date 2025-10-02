import React, { FC, JSX } from 'react'
import { ProgramType } from '@/types/types';
import Program from './Program';
import { programlist } from '@/types/types';
const ProgramList:FC = ():JSX.Element => {
    
  return (
    <div>
      <ul className="programs">
        {
            programlist.map((program,index)=> (
                <li className='program' key={index} >
                    <Program program={program}/>
                </li>

            ))
        }
        </ul>
    </div>
  )
}

export default ProgramList
