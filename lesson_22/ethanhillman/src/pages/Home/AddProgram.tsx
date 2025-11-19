import React, {useState} from 'react';

export default function AddProgram() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    console.log('New Program:', {title, description});
  };

  return (
    <div>
      <h2>Add New Program</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Program Title:</label>
          <input
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Program Description:</label>
          <textarea
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit">Add Program</button>
      </form>
    </div>
  );
}
