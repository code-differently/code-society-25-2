import './AddProgram.scss';
import React from 'react';
import { Form, redirect, useActionData } from 'react-router-dom';

// Action function to handle form submission
export async function action({ request }: { request: Request }) {
  const formData = await request.formData();
  const title = formData.get('title') as string;
  const description = formData.get('description') as string;

  // In a real app, this would save to an API/database
  console.log('New program:', { title, description });

  // For now, we'll redirect back to home with success
  return redirect('/?success=true');
}

export const AddProgram: React.FC = () => {
  const actionData = useActionData();

  return (
    <article>
      <section className="add-program-section">
        <h2>Add a New <em className="highlight">Program</em></h2>
        
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
            <button type="button" className="cancel-btn">
              <a href="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                Cancel
              </a>
            </button>
          </div>
        </Form>
        
        {actionData && (
          <div className="success-message">
            Program added successfully!
          </div>
        )}
      </section>
    </article>
  );
};
