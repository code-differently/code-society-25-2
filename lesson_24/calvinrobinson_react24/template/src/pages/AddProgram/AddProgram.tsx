import './AddProgram.scss';

import {useMutation, useQueryClient} from '@tanstack/react-query';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const addProgramMutation = useMutation({
    mutationFn: async (newProgram: { title: string; description: string }) => {
      const response = await fetch('http://localhost:4000/programs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProgram),
      });
      if (!response.ok) {
        throw new Error('Failed to add program');
      }
      return response.json();
    },
    onSuccess: () => {
      // Invalidate and refetch programs query
      queryClient.invalidateQueries({ queryKey: ['programs'] });
      // Navigate back to home page
      navigate('/');
    },
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (title.trim() && description.trim()) {
      addProgramMutation.mutate({ title: title.trim(), description: description.trim() });
    }
  };

  return (
    <article>
      <section className="add-program-section">
        <h2>Add New Program</h2>
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
              type="button"
              onClick={() => navigate('/')}
              className="cancel-button"
            >
              Cancel
            </button>
            <button
              type="submit"
              disabled={addProgramMutation.isPending || !title.trim() || !description.trim()}
              className="submit-button"
            >
              {addProgramMutation.isPending ? 'Adding...' : 'Add Program'}
            </button>
          </div>

          {addProgramMutation.isError && (
            <div className="error-message">
              Error adding program. Please try again.
            </div>
          )}
        </form>
      </section>
    </article>
  );
};