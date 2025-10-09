import './AddProgram.scss';
import {Program as ProgramType} from '@code-differently/types';
import React, { useState} from 'react';
import { useNavigate } from 'react-router-dom';



export const AddProgram: React.FC = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent)=> {
        e.preventDefault();

     
       const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({title: title, description:description}),
       };
        try {
      const response = await fetch('http://localhost:4000/programs', requestOptions);
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      navigate('/'); // redirect to home after success
    } catch (error) {
      console.error('Error adding program:', error);
      alert('Failed to add program. Please try again.');
    }
  };
    
  return (
     <div className="new-program">
      <h2>Add New Program</h2>
      <form 
            method='POST'
            className="form-new-program"
            onSubmit={handleSubmit}>
        <div className="form-title">
          <input
            id="title"
            type="text"
            placeholder= "Enter a title"
            value={title}
            onChange= {(e)=> setTitle(e.target.value)}
          />
        </div>

        <div className="form-description">
          <input
            id="description"
            type="text"
            placeholder="Enter a description"
            value={description}
            onChange={(e)=> setDescription(e.target.value)}
          />
        </div>

        <button type="submit">Add Program</button>
      </form>
    </div>
  );
};