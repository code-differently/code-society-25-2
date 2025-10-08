import './AddProgram.scss';
import { useState } from 'react';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    // Clear previous messages
    setSuccessMessage('');
    setErrorMessage('');
    
    // Validation
    if (!title.trim() || !description.trim()) {
      setErrorMessage('Please fill in both title and description');
      return;
    }

    setIsSubmitting(true);

    try {
      const response = await fetch('/api/programs', {
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
        throw new Error('Failed to add program');
      }

      await response.json();
      
      // Success - clear form and show success message
      setTitle('');
      setDescription('');
      setSuccessMessage('Program added successfully!');
      
    } catch (error) {
      setErrorMessage('Error adding program. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="add-program">
      <h1 className="add-program__title">Add New Program</h1>
      
      <form className="add-program__form" onSubmit={handleSubmit}>
        <div className="add-program__field">
          <label className="add-program__label" htmlFor="title">
            Program Title
          </label>
          <input
            type="text"
            id="title"
            className="add-program__input"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Enter program title"
            disabled={isSubmitting}
          />
        </div>

        <div className="add-program__field">
          <label className="add-program__label" htmlFor="description">
            Program Description
          </label>
          <textarea
            id="description"
            className="add-program__textarea"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter program description"
            disabled={isSubmitting}
          />
        </div>

        <button 
          type="submit" 
          className="add-program__button"
          disabled={isSubmitting}
        >
          {isSubmitting ? 'Adding Program...' : 'Add Program'}
        </button>
      </form>

      {successMessage && (
        <div className="add-program__success">
          {successMessage}
        </div>
      )}

      {errorMessage && (
        <div className="add-program__error">
          {errorMessage}
        </div>
      )}
    </div>
  );
};