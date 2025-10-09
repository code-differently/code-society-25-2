import './NewProgram.css';
import {ProgramType, programArray} from '@/types/types';
import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const NewProgram = () => {
  const [titleValue, setTitleValue] = useState('');
  const [descValue, setDescValue] = useState('');
  const [createdProgram, setCreatedProgram] = useState<ProgramType | null>(
    null
  );

  const navigate = useNavigate();
  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitleValue(event.target.value);
  };

  const handleDescChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescValue(event.target.value);
  };
  const subimtNewProgram = () => {
    const newProgram: ProgramType = {
      title: titleValue,
      description: descValue,
    };
    programArray.push(newProgram);
  };

  return (
    <div className="new-program-container">
      <h1 className="new-program-title">Add a New Program</h1>

      <form
        className="new-program-form"
        onSubmit={e => {
          e.preventDefault();
          subimtNewProgram();
          navigate(`/${programArray.length - 1}`);
        }}
      >
        <div className="form-group">
          <input
            type="text"
            name="title"
            className="form-input"
            placeholder="Enter a program title"
            value={titleValue}
            onChange={handleTitleChange}
          />
        </div>

        <div className="form-group">
          <input
            type="text"
            name="description"
            className="form-input"
            placeholder="Enter a program description"
            value={descValue}
            onChange={handleDescChange}
          />
        </div>

        <button type="submit" className="submit-button">
          Create Program
        </button>
      </form>
    </div>
  );
};

export default NewProgram;
