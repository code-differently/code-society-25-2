import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './AddProgram.module.scss';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    const addProgram = async () => {
      try {
        const response = await fetch('http://localhost:4000/programs', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ title, description }),
        });
        if (!response.ok) {
          console.error('Failed to add program');
          return;
        }
      } catch (err) {
        console.error('Error adding program:', err);
        console.error('Error details:', err instanceof Error ? err.message : err);
      }
    };
    addProgram();

    // Go back to home
    navigate('/');
  };
//-----------------------------------
  return (
    <div className={styles['add-program-container']}>
      <h2>Add a New Program</h2>
      <form onSubmit={handleSubmit} className={styles['add-program-form']}>
        <div>
          <label htmlFor="title">Title:</label>
          <input
            id="title"
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit">Add Program</button>
      </form>
    </div>
  );
};

export default AddProgram;