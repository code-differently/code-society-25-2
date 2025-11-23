import {useState} from 'react';

const NewProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log('New program submitted:', {title, description});
    alert(`Program Created: ${title}`);
    setTitle('');
    setDescription('');
  };

  return (
    <div style={{padding: '2rem'}}>
      <h1>Create a New Program</h1>
      <form
        onSubmit={handleSubmit}
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '1rem',
          maxWidth: '400px',
        }}
      >
        <input
          type="text"
          placeholder="Program title"
          value={title}
          onChange={e => setTitle(e.target.value)}
          required
        />
        <textarea
          placeholder="Program description"
          value={description}
          onChange={e => setDescription(e.target.value)}
          required
        />
        <button type="submit">Save Program</button>
      </form>
    </div>
  );
};

export default NewProgram;
