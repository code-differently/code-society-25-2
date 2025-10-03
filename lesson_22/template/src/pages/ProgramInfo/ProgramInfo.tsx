import React from 'react'
import Program from '@/components/program/Program'
import { useParams } from 'react-router-dom'
import { programArray } from '@/types/types'

const ProgramInfo = () => {
    const {id} = useParams();
    console.log(`in home page component ${programArray}` )
  return (
    
    <div>
      <h1>{programArray[id]}</h1>
    </div>
  )
}

export default ProgramInfo
