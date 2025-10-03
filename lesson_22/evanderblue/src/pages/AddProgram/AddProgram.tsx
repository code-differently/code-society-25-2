import './AddProgram.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  // 1. STATE MANAGEMENT - React hooks to track form data
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  // 2. NAVIGATION HOOK - To redirect after form submission
  const navigate = useNavigate();

  // 3. FORM SUBMISSION HANDLER
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    // Prevent page refresh (default form behavior)
    event.preventDefault();

    // Basic validation
    if (!title.trim() || !description.trim()) {
      alert('Please fill in both title and description');
      return;
    }

    setIsSubmitting(true);

    try {
      // Simulate API call (in real app, you'd send to backend)
      console.log('Submitting new program:', {
        title: title.trim(),
        description: description.trim(),
      });

      // Simulate loading time
      await new Promise(resolve => setTimeout(resolve, 1000));

      // Success! Clear form and redirect
      setTitle('');
      setDescription('');
      alert(`Program "${title}" has been added successfully!`);

      // Navigate back to home page
      navigate('/');
    } catch (error) {
      console.error('Failed to add program:', error);
      alert('Failed to add program. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  // 4. RENDER THE FORM
  return (
    <article>
      <section className="add-program-section">
        <h2>
          Add New <em className="highlight">Program</em>
        </h2>

        <form className="add-program-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="program-title">Program Title</label>
            <input
              type="text"
              id="program-title"
              name="title"
              value={title}
              onChange={e => setTitle(e.target.value)}
              placeholder="Enter program title..."
              required
              disabled={isSubmitting}
            />
          </div>

          <div className="form-group">
            <label htmlFor="program-description">Program Description</label>
            <textarea
              id="program-description"
              name="description"
              value={description}
              onChange={e => setDescription(e.target.value)}
              placeholder="Enter program description..."
              rows={6}
              required
              disabled={isSubmitting}
            />
          </div>

          <div className="form-actions">
            <button
              type="button"
              className="btn-secondary"
              onClick={() => navigate('/')}
              disabled={isSubmitting}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="btn-primary"
              disabled={isSubmitting}
            >
              {isSubmitting ? 'Adding...' : 'Add Program'}
            </button>
          </div>
        </form>
      </section>
    </article>
  );
};
