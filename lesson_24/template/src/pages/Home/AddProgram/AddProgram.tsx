import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

export const AddProgram: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [message, setMessage] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);
    setMessage('');

    if (!title || !description) {
      setMessage('Please fill in both the Title and Description.');
      setIsSubmitting(false);
      return;
    }

    try {
      const newProgramData = {
        title,
        description,
      };

      const response = await fetch('http://localhost:4000/programs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProgramData),
      });

      if (!response.ok) {
        throw new Error('Failed to create program: ' + response.statusText);
      }

      setMessage('Program successfully added! Redirecting...');

      setTimeout(() => {
        navigate('/');
      }, 1000);
    } catch (error) {
      console.error('Submission error:', error);
      setMessage(
        `Error adding program. Check API status. ${error instanceof Error ? error.message : 'Unknown error'}`
      );
    } finally {
    }
  };

  return (
    <div className="add-program-container p-8 max-w-xl mx-auto bg-white shadow-lg rounded-lg my-10">
      <h2 className="text-3xl font-bold mb-6 text-gray-800">Add New Program</h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label
            htmlFor="title"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Program Title
          </label>
          <input
            id="title"
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-md focus:ring-indigo-500 focus:border-indigo-500"
            required
            disabled={isSubmitting}
          />
        </div>

        <div>
          <label
            htmlFor="description"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Description
          </label>
          <textarea
            id="description"
            value={description}
            onChange={e => setDescription(e.target.value)}
            rows={4}
            className="w-full p-3 border border-gray-300 rounded-md focus:ring-indigo-500 focus:border-indigo-500"
            required
            disabled={isSubmitting}
          ></textarea>
        </div>

        <button
          type="submit"
          disabled={isSubmitting}
          className={`w-full py-3 px-4 rounded-md text-white font-semibold transition duration-150 ease-in-out ${
            isSubmitting
              ? 'bg-gray-400 cursor-not-allowed'
              : 'bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500'
          }`}
        >
          {isSubmitting ? 'Adding Program...' : 'Add Program'}
        </button>
      </form>

      {message && (
        <p
          className={`mt-4 text-center p-2 rounded ${message.includes('Error') ? 'bg-red-100 text-red-700' : 'bg-green-100 text-green-700'}`}
        >
          {message}
        </p>
      )}
    </div>
  );
};
