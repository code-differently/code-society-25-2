import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newProgram = { title, description };

    // Navigate back to home page and pass the new program through state
    navigate('/', { state: { newProgram } });
  };

  return (
    <section className="add-program">
      <h2>Add a New Program</h2>

      <form onSubmit={handleSubmit}>
        <label htmlFor="title">Program Title</label>
        <input
          id="title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Enter program title"
          required
        />

        <label htmlFor="description">Program Description</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Enter program description"
          required
        ></textarea>

        <button type="submit">Add Program</button>
      </form>
    </section>
  );
};
