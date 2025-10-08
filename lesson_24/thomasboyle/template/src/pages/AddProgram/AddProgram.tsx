import './AddProgram.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

import {useAddProgram} from '../../hooks';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();
  const addProgramMutation = useAddProgram();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!title.trim() || !description.trim()) {
      return;
    }

    try {
      await addProgramMutation.mutateAsync({
        title: title.trim(),
        description: description.trim(),
      });

      setSuccess(true);
      setTitle('');
      setDescription('');

      setTimeout(() => {
        navigate('/');
      }, 2000);
    } catch (err) {
      console.error('Error adding program:', err);
    }
  };

  return (
    <article className="add-program-page">
      <section className="add-program-section">
        <div className="add-program-content">
          <h2>
            Add New <em className="highlight">Program</em>
          </h2>

          {addProgramMutation.error && (
            <div className="error-message">
              <p>{addProgramMutation.error.message}</p>
            </div>
          )}

          {success && (
            <div className="success-message">
              <p>Program added successfully! Redirecting to home page...</p>
            </div>
          )}

          <form onSubmit={handleSubmit} className="add-program-form">
            <div className="form-group">
              <label htmlFor="title">Program Title</label>
              <input
                type="text"
                id="title"
                value={title}
                onChange={e => setTitle(e.target.value)}
                placeholder="Enter program title"
                disabled={addProgramMutation.isPending}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="description">Program Description</label>
              <textarea
                id="description"
                value={description}
                onChange={e => setDescription(e.target.value)}
                placeholder="Enter program description"
                rows={5}
                disabled={addProgramMutation.isPending}
                required
              />
            </div>

            <div className="form-actions">
              <button
                type="button"
                onClick={() => navigate('/')}
                className="cancel-button"
                disabled={addProgramMutation.isPending}
              >
                Cancel
              </button>
              <button
                type="submit"
                className="submit-button"
                disabled={addProgramMutation.isPending}
              >
                {addProgramMutation.isPending ? 'Adding...' : 'Add Program'}
              </button>
            </div>
          </form>
        </div>
      </section>
    </article>
  );
};
