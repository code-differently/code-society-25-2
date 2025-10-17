import React, { useState } from 'react';
import { Form, redirect, useNavigate } from 'react-router-dom';
import './AddProgram.scss';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    
    // Here you would typically send the data to your backend API
    console.log('New Program:', { title, description });
    
    // For now, we'll just log it and redirect to home
    alert(`Program "${title}" has been added successfully!`);
    
    // Reset form
    setTitle('');
    setDescription('');
    
    // Navigate back to home
    navigate('/');
  };

  return (
    <article className="add-program-page">
      <section className="add-program-section">
        <h2>
          Add New <em className="highlight">Program</em>
        </h2>
        <Form onSubmit={handleSubmit} className="program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
            <input
              type="text"
              id="title"
              name="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              placeholder="Enter program title"
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              name="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
              placeholder="Enter program description"
              rows={6}
            />
          </div>
          
          <div className="form-actions">
            <button type="submit" className="submit-btn">
              Add Program
            </button>
            <button 
              type="button" 
              className="cancel-btn"
              onClick={() => navigate('/')}
            >
              Cancel
            </button>
          </div>
        </Form>
      </section>
    </article>
  );
};
