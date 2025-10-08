import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await fetch('/api/programs', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({title, description}),
      });
      if (!response.ok) {
        throw new Error('Failed to add program');
      }
      setLoading(false);
      navigate('/');
    } catch (err: any) {
      setError(err.message || 'Something is wrong');
      setLoading(false);
    }
  };

  return (
    <section className="add-program-section">
      <h2>Add a New Program</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title:</label>
          <input
            id="title"
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? 'Adding...' : 'Add Program'}
        </button>
        {error && <div className="error">{error}</div>}
      </form>
    </section>
  );
};
