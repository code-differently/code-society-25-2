import './ProgramResults.scss';
import React from 'react';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export const ProgramResult: React.FC = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const program = location.state?.program;
    if (!program) {
        return (
            <div className="error">
                <p>No program found. Please go back and add one.</p>
                <button type="button" onClick={() => navigate('/add')}>Go Back</button>
            </div>
        );
    }
    
    return (
        <div className="result">
            <h2>New Program Added</h2>
            <h3>{program.title}</h3>
            <p>{program.description}</p>
            <button type="button" onClick={() => navigate('/add')}>Add Another Program</button>
        </div>
    );
};