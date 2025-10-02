import { programlist } from '@/types/types';
import { ProgramType } from '@/types/types';
import React, { useState, } from 'react';
import { useNavigate } from 'react-router-dom';





const NewProgram = () => {
    const [titleValue,setTitleValue] = useState('');
    const [descValue,setDescValue] = useState('');
    
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
      

    };
    
  return (
    <div>
      <h1>Add a new Program</h1>
      <form onSubmit={}>
        <input type="text" name="title" id="" placeholder='Enter a title' value={titleValue} onChange={handleTitleChange}/>
        <input type="text" name="description" id="" placeholder='Enter a description' value={descValue} onChange={handleDescChange}/>
        <button type='submit' onClick={subimtNewProgram}>Submit</button>
      </form>
    </div>
  )
}

export default NewProgram