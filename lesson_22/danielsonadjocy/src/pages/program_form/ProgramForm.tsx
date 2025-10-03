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
      <h1>Program Form</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title:</label>
          <input
            type="text"
            id="title"
            name="title"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>

        <button type="button" onClick={() => navigate('/')}>
          Cancel
        </button>
        <button type="submit">Submit</button>
      </form>
    </article>
  );
};
