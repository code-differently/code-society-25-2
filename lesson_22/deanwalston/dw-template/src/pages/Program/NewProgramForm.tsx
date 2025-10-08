import './NewProgramForm.scss';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

import {useProgramsContext} from '../../context/ProgramsContext';

export const NewProgramForm: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const navigate = useNavigate();
  const {addProgram} = useProgramsContext();

  const handleSubmitNewProgram = (e: React.FormEvent) => {
    e.preventDefault();

    // Add the new program to the global state
    addProgram(title, description);

    // Clear the form
    setTitle('');
    setDescription('');

    // Navigate to programs page
    navigate('/programs');
  };

  return (
    <article className="add-program">
      <section className="hero-section">
        <div className="hero-overlay"></div>
        <div className="hero-content">
          <h2 className="hero-title">
            Add a New <em className="highlight">Program</em>
          </h2>
          <div className="hero-text">
            Share your program with others by filling out the form below.
          </div>
        </div>
      </section>

      <section className="form-section">
        <div className="form-container">
          <h2>Program Details</h2>
          <form onSubmit={handleSubmitNewProgram} className="program-form">
            <div className="form-group">
              <label htmlFor="title">Program Title</label>
              <input
                type="text"
                name="title"
                id="title"
                placeholder="Enter program title"
                value={title}
                onChange={e => setTitle(e.target.value)}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="description">Program Description</label>
              <textarea
                name="description"
                id="description"
                placeholder="Enter program description"
                value={description}
                onChange={e => setDescription(e.target.value)}
                rows={5}
                required
              />
            </div>

            <div className="form-actions">
              <button type="button" onClick={() => navigate('/programs')}>
                Cancel
              </button>
              <button type="submit">Add Program</button>
            </div>
          </form>
        </div>
      </section>
    </article>
  );
};

export default NewProgramForm;
