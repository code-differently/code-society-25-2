import { useState } from "react";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { createProgram } from "../../lib/api";
import { useNavigate } from "react-router-dom";
export default function AddProgram() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const qc = useQueryClient();
  const navigate = useNavigate();
  const mutation = useMutation({
    mutationFn: createProgram,
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ["programs"] });
      navigate("/");
    },
  });
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        mutation.mutate({ title, description });
      }}
      className="add-program-form"
    >
      <h2>Add Program</h2>
      <label>
        Title
        <input value={title} onChange={(e) => setTitle(e.target.value)} />
      </label>
      <label>
        Description
        <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
      </label>
      <button type="submit" disabled={mutation.isLoading}>Create</button>
      {mutation.isError && <div>Failed to create program</div>}
    </form>
  );
}
