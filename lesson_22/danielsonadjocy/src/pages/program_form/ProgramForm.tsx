import './ProgramForm.css';
import React, {useState} from 'react';
import {useNavigate, useOutletContext} from 'react-router-dom';

export const ProgramForm: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const {addProgram} = useOutletContext<{
    programs: Array<{id: string; title: string; description: string}>;
    addProgram: (title: string, description: string) => void;
  }>();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    addProgram(title, description);

    // Clear form
    setTitle('');
    setDescription('');

    // Go back to home
    navigate('/');
  };

  return (
    <article className="program-form">
      <h1 className="form-title">Program Form</h1>
      <form onSubmit={handleSubmit} className="program-form-container">
        <div className="form-group">
          <label htmlFor="title" className="form-label">
            Title:
          </label>
          <input
            type="text"
            id="title"
            name="title"
            className="form-input"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="description" className="form-label">
            Description:
          </label>
          <textarea
            id="description"
            name="description"
            className="form-textarea"
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>

        <div className="form-buttons">
          <button
            type="button"
            className="btn btn-cancel"
            onClick={() => navigate('/')}
          >
            Cancel
          </button>
          <button type="submit" className="btn btn-submit">
            Submit
          </button>
        </div>
      </form>
    </article>
  );
};
