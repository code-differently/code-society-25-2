import { programArray } from '@/types/types';
import { ProgramType } from '@/types/types';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Program from '@/components/program/Program';




const NewProgram = () => {
    const [titleValue,setTitleValue] = useState('');
    const [descValue,setDescValue] = useState('');
    const [createdProgram,setCreatedProgram] = useState<ProgramType|null>(null);
    
    const navigate = useNavigate();
    const handleTitleChange = (event:React.ChangeEvent<HTMLInputElement>)=> {
        setTitleValue(event.target.value);


    }

    const handleDescChange =  (event:React.ChangeEvent<HTMLInputElement>) => {
        setDescValue(event.target.value)

    }
    const subimtNewProgram = () => {
      const newProgram: ProgramType = {
        title: titleValue,
        description: descValue,
      };
      programArray.push(newProgram);

    };
    
  return (
    <div>
      <h1>Add a new Program</h1>
      <div>
      
                        
      </div>
      <form onSubmit={(e)=>{e.preventDefault(); navigate(`/${programArray.length-1}`)}}>
        <input type="text" name="title" id="" placeholder='Enter a title' value={titleValue} onChange={handleTitleChange}/>
        <input type="text" name="description" id="" placeholder='Enter a description' value={descValue} onChange={handleDescChange}/>
        <button type='submit' onClick={subimtNewProgram}>Submit</button>
      </form>

      
    </div>
  )
}

export default NewProgram