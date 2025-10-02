import React,{useState} from 'react'
import ProgramList from '@/components/program/Program'

const NewProgram = () => {
    const [titleValue,setTitleValue] = useState('');
    const [descValue,setDescValue] = useState('');
    const handleChange = (event:React.ChangeEvent<HTMLInputElement>)=> {
        setTitleValue(event.target.value);

    }
    const subimtNewProgram = ()=> {
        newProgra
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
