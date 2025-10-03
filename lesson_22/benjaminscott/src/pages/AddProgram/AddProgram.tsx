import styles from './AddProgram.module.scss';
import React, {useState} from 'react';

import Program from '../../components/Program';

export const AddProgram: React.FC = () => {
  const [programs, setPrograms] = useState<
    Array<{title: string; description: string}>
  >([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (title && description) {
      setPrograms([...programs, {title, description}]);
      setTitle('');
      setDescription('');
    }
  };

  return (
    <div className={styles['add-program-container']}>
      <h2>Add a New Program</h2>
      <form onSubmit={handleSubmit} className={styles['add-program-form']}>
        <div>
          <label htmlFor="title">Title:</label>
          <input
            id="title"
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            value={description}
            onChange={e => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit">Add Program</button>
      </form>
      <ul className={styles['program-list']}>
        {programs.map((program, idx) => (
          <Program
            key={idx}
            title={program.title}
            description={program.description}
          />
        ))}
      </ul>
    </div>
  );
};

export default AddProgram;
