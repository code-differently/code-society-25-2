import './AddProgram.scss';
import {Program} from '@code-differently/types';
import {useMutation, useQueryClient} from '@tanstack/react-query';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const addProgram = async (program: Omit<Program, 'id'>): Promise<void> => {
  const response = await fetch('http://localhost:4000/programs', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(program),
  });

  if (!response.ok) {
    throw new Error('Failed to add program');
  }
};

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const mutation = useMutation({
    mutationFn: addProgram,
    onSuccess: () => {
      // Invalidate and refetch programs
      queryClient.invalidateQueries({queryKey: ['programs']});
      // Navigate back to home page
      navigate('/');
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (title.trim() && description.trim()) {
      mutation.mutate({title: title.trim(), description: description.trim()});
    }
  };

  return (
    <article className="add-program-page">
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
              required
              placeholder="Enter program title"
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
              placeholder="Enter program description"
              rows={6}
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
          
          {mutation.error && (
            <div className="error-message">
              Error: {mutation.error.message}
            </div>
          )}
        </form>
      </section>
    </article>
  );
};