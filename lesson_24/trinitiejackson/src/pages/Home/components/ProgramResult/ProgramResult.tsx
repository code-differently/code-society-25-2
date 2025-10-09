import './ProgramResult.scss';
import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import { Program } from '@code-differently/types';

export const ProgramResult: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const id = location.state?.id;
  const [program, setProgram] = useState<Program | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProgram = async () => {
      try {
        const result = await fetch(`http://localhost:4000/programs/${id}`);
        if (!result.ok) {
          throw new Error(`Error fetching program: ${result.statusText}`);
        }
        const data = await result.json();
        setProgram(data);
      } catch (error) {
        console.error('Error fetching program:', error);
      } finally {
        setLoading(false);
      }
    };

    if (id) fetchProgram();
  }, [id]);

  if (!id) {
    return (
      <div className='error'>
        <p>No program ID found. Please go back and add one.</p>
        <button type="button" onClick={() => navigate('/add')}> Go back</button>
      </div>
    );
  }

   if (loading) {
    return <div>Loading...</div>;
  }

  if (!program) {
    return (
      <div className="error">
        <p>Program not found. Please check the ID and try again.</p>
        <button type="button" onClick={() => navigate('/add')}> Go back</button>
      </div>
    );
  }

  return (
    <div className="result">
      <h2>New Program Added</h2>
      <h3>{program.title}</h3>
      <p>{program.description}</p>
      <div className="button-container">
        <button type="button" onClick={() => navigate('/add')}>
          Add Another Program
        </button>
        <button type="button" onClick={() => navigate('/')}>
          Go Back Home
        </button>
      </div>
    </div>
  );
};
