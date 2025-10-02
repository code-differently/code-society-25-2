import React,{useState} from 'react'
import { programlist } from '@/types/types';
import { ProgramType } from '@/types/types';
const NewProgram = () => {
    const [titleValue,setTitleValue] = useState('');
    const [descValue,setDescValue] = useState('');
    const handleChange = (event:React.ChangeEvent<HTMLInputElement>)=> {
        setTitleValue(event.target.value);

    }
    const subimtNewProgram = ()=> {
        const newProgram:ProgramType = {
            title:titleValue,
            description:descValue
        }
        
    }
  return (
    <div>
      <h1>Add a new Program</h1>
      <form action="subimt">
        <input type="text" name="title" id="" placeholder='Enter a title' value={titleValue} onChange={handleChange}/>
        <input type="text" name="description" id="" placeholder='Enter a description' />
        <button type='submit' onClick={}>Submit</button>
      </form>
    </div>
  )
}

export default NewProgram
