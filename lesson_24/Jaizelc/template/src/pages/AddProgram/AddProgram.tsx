import './AddProgram.scss';
import {Program as ProgramType} from '@code-differently/types';
import {useMutation, useQueryClient} from '@tanstack/react-query';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const API_BASE_URL = 'http://localhost:4000';

const addProgram = async (program: Omit<ProgramType, 'id'>): Promise<void> => {
  const response = await fetch(`${API_BASE_URL}/programs`, {
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
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: addProgram,
    onSuccess: () => {
      // Invalidate and refetch programs list
      queryClient.invalidateQueries({queryKey: ['programs']});
      // Navigate back to home
      navigate('/');
    },
    onError: error => {
      alert(`Error adding program: ${error.message}`);
    },
  });

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!title.trim() || !description.trim()) {
      alert('Please fill in both title and description');
      return;
    }

    mutation.mutate({
      title: title.trim(),
      description: description.trim(),
    });
  };

  const handleCancel = () => {
    navigate('/');
  };

  return (
    <article className="add-program-page">
      <section className="add-program-section">
        <h2>
          Add New <em className="highlight">Program</em>
        </h2>
        <form onSubmit={handleSubmit} className="program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
            <input
              type="text"
              id="title"
              name="title"
              value={title}
              onChange={e => setTitle(e.target.value)}
              required
              placeholder="Enter program title"
              disabled={mutation.isPending}
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              name="description"
              value={description}
              onChange={e => setDescription(e.target.value)}
              required
              placeholder="Enter program description"
              rows={6}
              disabled={mutation.isPending}
            />
          </div>

          <div className="form-actions">
            <button
              type="submit"
              className="submit-btn"
              disabled={mutation.isPending}
            >
              {mutation.isPending ? 'Adding...' : 'Add Program'}
            </button>
            <button
              type="button"
              className="cancel-btn"
              onClick={handleCancel}
              disabled={mutation.isPending}
            >
              Cancel
            </button>
          </div>
        </form>
      </section>
    </article>
  );
};
