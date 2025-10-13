import { Program } from '@code-differently/types';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddProgram.scss';

const addProgram = async (program: Omit<Program, 'id'>): Promise<void> => {
  const response = await fetch('http://localhost:4000/programs', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      id: crypto.randomUUID(),
      ...program,
    }),
  });
  
  if (!response.ok) {
    throw new Error('Failed to add program');
  }
};

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: addProgram,
    onSuccess: () => {
      // Invalidate and refetch programs
      queryClient.invalidateQueries({ queryKey: ['programs'] });
      // Navigate back to home
      navigate('/');
    },
    onError: (error) => {
      console.error('Error adding program:', error);
      alert('Failed to add program. Please try again.');
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!title.trim() || !description.trim()) {
      alert('Please fill in both title and description');
      return;
    }

    mutation.mutate({
      title: title.trim(),
      description: description.trim(),
    });
  };

  return (
    <article className="add-program">
      <section className="add-program-section">
        <h2>Add New <em className="highlight">Program</em></h2>
        
        <form onSubmit={handleSubmit} className="add-program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
            <input
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="Enter program title"
              required
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Enter program description"
              rows={6}
              required
            />
          </div>
          
          <div className="form-actions">
            <button 
              type="submit" 
              disabled={mutation.isPending}
              className="submit-button"
            >
              {mutation.isPending ? 'Adding...' : 'Add Program'}
            </button>
            <button 
              type="button" 
              onClick={() => navigate('/')}
              className="cancel-button"
            >
              Cancel
            </button>
          </div>
        </form>
      </section>
    </article>
  );
};
