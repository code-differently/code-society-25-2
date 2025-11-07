import React, { useState } from 'react';
import { Program } from '@code-differently/types';
import './AddProgram.scss';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!title.trim() || !description.trim()) {
      setError('Please fill in both title and description');
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const newProgram: Omit<Program, 'id'> = {
        title: title.trim(),
        description: description.trim()
      };

      const response = await fetch('http://localhost:4000/programs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProgram),
      });

      if (!response.ok) {
        throw new Error('Failed to create program');
      }

      setSuccess(true);
      setTitle('');
      setDescription('');
      
      // Reset success message after 3 seconds
      setTimeout(() => setSuccess(false), 3000);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setLoading(false);
    }
  };

  return (
    <article>
      <section className="add-program-section">
        <h2>Add a New <em className="highlight">Program</em></h2>
        
        <form onSubmit={handleSubmit} className="program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
            <input
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="Enter program title"
              disabled={loading}
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
              disabled={loading}
            />
          </div>

          {error && (
            <div className="error-message">
              {error}
            </div>
          )}

          {success && (
            <div className="success-message">
              Program added successfully!
            </div>
          )}

          <div className="form-actions">
            <button type="submit" className="submit-btn" disabled={loading}>
              {loading ? 'Adding...' : 'Add Program'}
            </button>
            <button type="button" className="cancel-btn" onClick={() => window.history.back()}>
              Cancel
            </button>
          </div>
        </form>
      </section>
    </article>
  );
};
