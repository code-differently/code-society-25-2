import './AddProgram.scss';
import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!title || !description) {
      alert('Please fill in all fields');
      return;
    }

    const newProgram = {
        id: crypto.randomUUID(),
        title,
        description,
    };

    try {
        const response = await fetch('http://localhost:4000/programs', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newProgram),
        });

    if (!response.ok) {
        throw new Error(`Failed to add program: ${response.statusText}`);
    }

        console.log('Program added successfully');
        alert('Program added successfully');

        navigate('/');
    } catch (error) {
        console.error('Error adding program:', error);
        alert('There was an issue adding the program. Please try again.');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="add-program-form">
      <input
        type="text"
        placeholder="Program Title"
        value={title}
        onChange={e => setTitle(e.target.value)}
      />
      <textarea
        placeholder="Program Description"
        value={description}
        onChange={e => setDescription(e.target.value)}
      ></textarea>
      <button type="submit">Add Program</button>
    </form>
  );
};
