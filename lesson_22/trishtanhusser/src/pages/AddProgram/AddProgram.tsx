import React from 'react';
import { Form, redirect, useNavigate } from 'react-router-dom';
import './AddProgram.scss';

export const AddProgram: React.FC = () => {
  const navigate = useNavigate();

  const handleCancel = () => {
    navigate('/');
  };

  return (
    <article className="add-program-page">
      <section className="form-section">
        <h2>Add New Program</h2>
        <Form method="post" className="program-form">
          <div className="form-group">
            <label htmlFor="title">Program Title:</label>
            <input
              type="text"
              id="title"
              name="title"
              required
              placeholder="Enter program title"
            />
          </div>
          
          <div className="form-group">
            <label htmlFor="description">Program Description:</label>
            <textarea
              id="description"
              name="description"
              required
              placeholder="Enter program description"
              rows={6}
            />
          </div>
          
          <div className="form-actions">
            <button type="submit" className="submit-btn">
              Add Program
            </button>
            <button type="button" className="cancel-btn" onClick={handleCancel}>
              Cancel
            </button>
          </div>
        </Form>
      </section>
    </article>
  );
};

// Action function to handle form submission
export async function action({ request }: { request: Request }) {
  const formData = await request.formData();
  const title = formData.get('title');
  const description = formData.get('description');
  
  // In a real application, you would save this to a database
  console.log('New program:', { title, description });
  
  // Redirect back to home page after submission
  return redirect('/');
}
