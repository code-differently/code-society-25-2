import * as React from 'react'
import { useNavigate } from 'react-router-dom'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { createProgram } from '../lib/api'

export const AddProgram: React.FC = () => {
  const qc = useQueryClient()
  const navigate = useNavigate()

  const { mutateAsync, isPending, error } = useMutation({
    mutationFn: createProgram,
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: ['programs'] })
      navigate('/')
    },
  })

  async function onSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    const fd = new FormData(e.currentTarget)
    const title = String(fd.get('title') || '').trim()
    const description = String(fd.get('description') || '').trim()
    if (!title || !description) return
    await mutateAsync({ title, description })
  }

  return (
    <section className="add-program">
      <h2>Add a Program</h2>
      <form onSubmit={onSubmit}>
        <label>
          Title
          <input name="title" placeholder="React Fundamentals" />
        </label>
        <label>
          Description
          <textarea name="description" placeholder="Intro to components, props, and state…" />
        </label>
        <button type="submit" disabled={isPending}>
          {isPending ? 'Saving…' : 'Add Program'}
        </button>
      </form>
      {error ? <p role="alert">Could not save. Try again.</p> : null}
    </section>
  )
}
