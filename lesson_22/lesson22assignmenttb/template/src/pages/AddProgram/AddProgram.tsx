import './AddProgram.scss';
import React, {useState} from 'react';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (title.trim() && description.trim()) {
      setIsSubmitted(true);
      // In a real app, this would send data to a backend
      console.log('New program:', {title, description});
    }
  };

  const handleReset = () => {
    setTitle('');
    setDescription('');
    setIsSubmitted(false);
  };

  return (
    <article>
      <section className="form-section">
        <div className="form-container">
          <h2>
            Add a New <em className="highlight">Program</em>
          </h2>

          {isSubmitted ? (
            <div className="success-message">
              <h3>Program Added Successfully!</h3>
              <div className="program-preview">
                <h4>{title}</h4>
                <p>{description}</p>
              </div>
              <button type="button" onClick={handleReset} className="reset-btn">
                Add Another Program
              </button>
            </div>
          ) : (
            <form onSubmit={handleSubmit} className="program-form">
              <div className="form-group">
                <label htmlFor="title">Program Title</label>
                <input
                  type="text"
                  id="title"
                  value={title}
                  onChange={e => setTitle(e.target.value)}
                  placeholder="Enter program title"
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
                  rows={6}
                  required
                />
              </div>

              <div className="form-actions">
                <button type="submit" className="submit-btn">
                  Add Program
                </button>
                <button
                  type="button"
                  onClick={handleReset}
                  className="reset-btn"
                >
                  Reset Form
                </button>
              </div>
            </form>
          )}
        </div>
      </section>
    </article>
  );
};
