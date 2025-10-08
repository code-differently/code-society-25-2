import './AddProgram.scss';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { usePrograms } from '../../contexts/ProgramsContext';

export const AddProgram: React.FC = () => {
  const navigate = useNavigate();
  const { addProgram } = usePrograms();
  const [formData, setFormData] = useState({
    title: '',
    description: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [message, setMessage] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);
    setMessage('');

    try {
      // Simulate form submission (in a real app, this would be an API call)
      await new Promise(resolve => setTimeout(resolve, 1000));
      
      // Add the program to the global state
      addProgram(formData.title, formData.description);
      
      console.log('Program submitted:', formData);
      setMessage(`Successfully added program: "${formData.title}"`);
      
      // Reset form
      setFormData({ title: '', description: '' });
      
      // Navigate back to home after a delay to show the new program
      setTimeout(() => {
        navigate('/');
      }, 2000);
      
    } catch (error) {
      setMessage('Error submitting program. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <article>
      <section className="add-program-section">
        <div className="add-program-container">
          <h1 className="add-program-title">
            Add New <em className="highlight">Program</em>
          </h1>
          <p className="add-program-subtitle">
            Help us expand our offerings by adding a new program to Code Differently.
          </p>

          {message && (
            <div className={`message ${message.includes('Error') ? 'error' : 'success'}`}>
              {message}
            </div>
          )}

          <form className="add-program-form" onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="title" className="form-label">
                Program Title
              </label>
              <input
                type="text"
                id="title"
                name="title"
                value={formData.title}
                onChange={handleInputChange}
                className="form-input"
                placeholder="Enter the program title"
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="description" className="form-label">
                Program Description
              </label>
              <textarea
                id="description"
                name="description"
                value={formData.description}
                onChange={handleInputChange}
                className="form-textarea"
                placeholder="Describe what this program offers and who it's designed for"
                rows={6}
                required
              />
            </div>

            <div className="form-actions">
              <button
                type="submit"
                className="btn btn-primary"
                disabled={isSubmitting || !formData.title.trim() || !formData.description.trim()}
              >
                {isSubmitting ? 'Adding Program...' : 'Add Program'}
              </button>
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => navigate('/')}
                disabled={isSubmitting}
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      </section>
    </article>
  );
};
