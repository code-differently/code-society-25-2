import React from 'react'
import Program from '@/components/program/Program'
import { useParams } from 'react-router-dom'
import {programArray, ProgramType}  from '@/types/types'

const ProgramInfo = () => {
    const {id} = useParams();
    
    const currentProgram:ProgramType = programArray[Number(id)]
    
    return (
      
      <div>
        {
          currentProgram?
        <Program program={currentProgram}/>
        : <p>Program not found</p>
        }   
      </div>
    )
}

export default ProgramInfo
