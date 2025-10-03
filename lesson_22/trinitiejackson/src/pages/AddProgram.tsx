import React from 'react';
import { useNavigate } from 'react-router-dom';

export const AddProgram: React.FC = () => {
    const [title, setTitle] = React.useState('');
    const [description, setDescription] = React.useState('');
    const navigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const newProgram = { title, description };
        if (!title || !description) {
            alert('Please fill in all fields');
            return;
        }
        navigate('/result', { state: { program: newProgram } });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Program Title" value={title} onChange={e => setTitle(e.target.value)} />
            <textarea placeholder="Program Description" value={description} onChange={e => setDescription(e.target.value)}></textarea>
            <button type="submit">Add Program</button>
        </form>
    );
};