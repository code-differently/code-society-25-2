import './NewProgram.css';
import {Program as ProgramType} from '@code-differently/types';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const NewProgram = () => {
  const [titleValue, setTitleValue] = useState('');
  const [descValue, setDescValue] = useState('');
  const [idValue, setIdValue] = useState('');
  const [createdProgram, setCreatedProgram] = useState<ProgramType | null>(
    null
  );

  const navigate = useNavigate();

  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitleValue(event.target.value);
  };
  const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIdValue(event.target.value);
  };

  const handleDescChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescValue(event.target.value);
  };
  const subimtNewProgram = () => {};

  return (
    <div className="new-program-container">
      <h1 className="new-program-title">Add a new Program</h1>
      <form
        className="new-program-form"
        onSubmit={e => {
          e.preventDefault();
        }}
      >
        <div className="form-group">
          <input
            type="text"
            name="id"
            className="form-input"
            placeholder="Enter an Id"
            value={idValue}
            onChange={handleIdChange}
          />
        </div>
        <div className="form-group">
          <input
            type="text"
            name="title"
            className="form-input"
            placeholder="Enter a title"
            value={titleValue}
            onChange={handleTitleChange}
          />
        </div>
        <div className="form-group">
          <input
            type="text"
            name="description"
            className="form-input"
            placeholder="Enter a description"
            value={descValue}
            onChange={handleDescChange}
          />
        </div>
        <button
          type="submit"
          className="submit-button"
          onClick={subimtNewProgram}
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default NewProgram;
