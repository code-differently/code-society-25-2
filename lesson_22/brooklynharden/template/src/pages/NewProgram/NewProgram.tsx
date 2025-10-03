import React, { useState } from 'react'
import Data from '../../assets/Data.json'

export const NewProgram: React.FC = () => {
    const [title, setTitle] = useState<string>('');
    const [description, setDescription] = useState<string>('');
   
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        Data.programs.push({
            "title": title, 
            "description": description});
    }

    console.log('New Program Submitted: ', {title, description});

    // setTitle('');
    // setDescription('');

  return (
    <section className="new-program">
    <h1>Program Form</h1>
    <form onSubmit={handleSubmit} className="new-program">
   <div>
        <label htmlFor="title">Program Title</label>
        <input
            id="title"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            />
   </div>

    <div>
        <label htmlFor="description">Program Description</label>
        <input
            id="description"
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            />
   </div>
   
        <button type="submit">Add Program</button>
   </form>
    </section>


  )
}
