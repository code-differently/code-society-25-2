import './ProgramForm.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const ProgramForm: React.FC = () => {
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<string | null>(null);
  const [messageType, setMessageType] = useState<'success' | 'error'>(
    'success'
  );

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!title.trim() || !description.trim()) {
      setMessage('Please fill in all fields');
      setMessageType('error');
      return;
    }

    setLoading(true);
    setMessage(null);

    try {
      const response = await fetch('http://localhost:4000/programs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          title: title.trim(),
          description: description.trim(),
        }),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      // Handle response - only try to parse JSON if there's content
      const contentType = response.headers.get('content-type');
      if (contentType && contentType.includes('application/json')) {
        await response.json();
      }

      setMessage('Program created successfully! Redirecting to home...');
      setMessageType('success');

      // Clear form after successful submission
      setTitle('');
      setDescription('');

      // Navigate to home page after a short delay
      setTimeout(() => {
        navigate('/');
      }, 1500);
    } catch (err) {
      setMessage(
        `Failed to create program: ${err instanceof Error ? err.message : 'Unknown error'}`
      );
      setMessageType('error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <article>
      <section className="form-section">
        <div className="form-content">
          <h2 className="form-title">
            Add a New <em className="highlight">Program</em>
          </h2>
          <div className="form-description">
            Create a new program to add to our offerings. Fill out the form
            below with the program details.
          </div>
        </div>
      </section>

      <section className="form-container">
        <form onSubmit={handleSubmit} className="program-form">
          {message && <div className={`message ${messageType}`}>{message}</div>}

          <div className="form-group">
            <label htmlFor="title" className="form-label">
              Program Title
            </label>
            <input
              type="text"
              id="title"
              value={title}
              onChange={e => setTitle(e.target.value)}
              className="form-input"
              placeholder="Enter program title..."
              disabled={loading}
            />
          </div>

          <div className="form-group">
            <label htmlFor="description" className="form-label">
              Program Description
            </label>
            <textarea
              id="description"
              value={description}
              onChange={e => setDescription(e.target.value)}
              className="form-textarea"
              placeholder="Enter program description..."
              rows={6}
              disabled={loading}
            />
          </div>

          <button type="submit" className="form-submit" disabled={loading}>
            {loading ? 'Creating Program...' : 'Create Program'}
          </button>
        </form>
      </section>
    </article>
  );
};
