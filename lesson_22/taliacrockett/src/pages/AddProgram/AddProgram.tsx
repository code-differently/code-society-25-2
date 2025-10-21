import './AddProgram.scss';
import React, {useState} from 'react';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    const newProgram = {
      title: title,
      description: description,
    };

    console.log('New program created:', newProgram);

    setTitle('');
    setDescription('');

    alert('Program added successfully!');
  };

  return (
    <article>
      <section className="add-program-section">
        <h1>Add New Program</h1>

        <form onSubmit={handleSubmit} className="program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
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
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              value={description}
              onChange={e => setDescription(e.target.value)}
              placeholder="Enter program description"
              rows={6}
              required
            />
          </div>

          <button type="submit" className="submit-btn">
            Add Program
          </button>
        </form>
      </section>
    </article>
  );
};
