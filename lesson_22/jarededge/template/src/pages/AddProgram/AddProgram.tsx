import './AddProgram.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

import {usePrograms} from '../../context/ProgramsContext';

interface FormData {
  title: string;
  description: string;
}

interface FormErrors {
  title?: string;
  description?: string;
}

export const AddProgram: React.FC = () => {
  const navigate = useNavigate();
  const {addProgram, programs} = usePrograms();
  const [formData, setFormData] = useState<FormData>({
    title: '',
    description: '',
  });
  const [errors, setErrors] = useState<FormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Handle input changes
  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const {name, value} = e.target;

    // Handle max length for inputs
    const maxTitleLength = 100;
    const maxDescriptionLength = 1000;

    if (name === 'title' && value.length > maxTitleLength) {
      return; // Prevent typing beyond limit
    }

    if (name === 'description' && value.length > maxDescriptionLength) {
      return; // Prevent typing beyond limit
    }

    setFormData(prev => ({
      ...prev,
      [name]: value,
    }));

    // Clear errors when user starts typing
    if (errors[name as keyof FormErrors]) {
      setErrors(prev => ({
        ...prev,
        [name]: undefined,
      }));
    }
  };

  // Validate form
  const validateForm = (): boolean => {
    const newErrors: FormErrors = {};

    // Title validation
    if (!formData.title.trim()) {
      newErrors.title = 'Title is required';
    } else if (formData.title.trim().length < 3) {
      newErrors.title = 'Title must be at least 3 characters long';
    } else {
      // Check for duplicate titles (case-insensitive)
      const existingTitles = programs.map(p => p.title.toLowerCase());
      if (existingTitles.includes(formData.title.trim().toLowerCase())) {
        newErrors.title = 'A program with this title already exists';
      }
    }

    // Description validation
    if (!formData.description.trim()) {
      newErrors.description = 'Description is required';
    } else if (formData.description.trim().length < 10) {
      newErrors.description = 'Description must be at least 10 characters long';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setIsSubmitting(true);

    try {
      // Add the program to the global state using context
      addProgram(formData.title, formData.description);

      console.log('New program added successfully');

      // Show success message
      alert('Program added successfully!');

      // Navigate back to home page
      navigate('/');
    } catch (error) {
      console.error('Error adding program:', error);
      alert('Error adding program. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  // Handle cancel
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
            <label htmlFor="title">Program Title *</label>
            <input
              type="text"
              id="title"
              name="title"
              value={formData.title}
              onChange={handleInputChange}
              className={errors.title ? 'error' : ''}
              placeholder="Enter program title"
              maxLength={100}
            />
            <div className="char-count">
              {formData.title.length}/100 characters
            </div>
            {errors.title && (
              <div className="error-message">{errors.title}</div>
            )}
          </div>

          <div className="form-group">
            <label htmlFor="description">Program Description *</label>
            <textarea
              id="description"
              name="description"
              value={formData.description}
              onChange={handleInputChange}
              className={errors.description ? 'error' : ''}
              placeholder="Enter program description"
              rows={6}
              maxLength={1000}
            />
            <div className="char-count">
              {formData.description.length}/1000 characters
            </div>
            {errors.description && (
              <div className="error-message">{errors.description}</div>
            )}
          </div>

          <div className="form-actions">
            <button
              type="button"
              onClick={handleCancel}
              className="btn btn-secondary"
              disabled={isSubmitting}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="btn btn-primary"
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
