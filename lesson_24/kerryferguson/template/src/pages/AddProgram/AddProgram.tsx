import './AddProgram.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!title.trim() || !description.trim()) {
      setError('Please fill in all fields');
      return;
    }

    setError('');

    try {
      const newProgram = {
        title: title.trim(),
        description: description.trim(),
      };

      const response = await fetch('http://localhost:4000/programs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProgram),
      });

      if (response.ok) {
        navigate('/');
      } else {
        throw new Error('Failed to add program');
      }
    } catch (error) {
      console.error('Error adding program:', error);
      setError('Failed to add program. Please try again.');
    }
  };

  return (
    <div className="add-program-page">
      <div className="container">
        <h1>Add New Program</h1>
        <p>Create a new program for Code Differently.</p>

        {error && <div className="error-message">{error}</div>}

        <form className="add-program-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="title">Program Title</label>
            <input
              type="text"
              id="title"
              name="title"
              placeholder="Enter program title"
              value={title}
              onChange={e => setTitle(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Program Description</label>
            <textarea
              id="description"
              name="description"
              rows={5}
              placeholder="Enter program description"
              value={description}
              onChange={e => setDescription(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="submit-btn">
            Add Program
          </button>
        </form>
      </div>
    </div>
  );
};
