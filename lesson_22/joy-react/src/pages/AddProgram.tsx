import React from "react";
import { useNavigate } from "react-router-dom";
import type { ProgramItem } from "../components/ProgramList";

const STORAGE_KEY = "programs";

const AddProgram: React.FC = () => {
  const nav = useNavigate();
  const [title, setTitle] = React.useState("");
  const [description, setDescription] = React.useState("");

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    const newProgram: ProgramItem = { title, description };

    const saved = localStorage.getItem(STORAGE_KEY);
    const list: ProgramItem[] = saved ? JSON.parse(saved) : [];
    localStorage.setItem(STORAGE_KEY, JSON.stringify([...list, newProgram]));

    nav("/");
  }

  return (
    <main className="container">
      <h2>
        Add <em className="highlight">Program</em>
      </h2>

      <form onSubmit={handleSubmit} className="form" style={{ maxWidth: 700 }}>
        <div className="form-row">
          <label htmlFor="title">Program Title</label>
          <input
            id="title"
            type="text"
            placeholder="e.g., Web Foundations"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>

        <div className="form-row">
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            placeholder="Short summary of the program..."
            rows={6}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>

        <div className="form-row" style={{ display: "flex", gap: "0.75rem" }}>
          <button type="submit">Save Program</button>
          <button type="button" onClick={() => nav("/")}>Cancel</button>
        </div>
      </form>
    </main>
  );
};

export default AddProgram;
