import * as React from 'react'
import { useQuery } from '@tanstack/react-query'
import { getPrograms } from '@/lib/api' // if alias fails, change to ../lib/api

export const ProgramList: React.FC = () => {
  const { data, isLoading, isError } = useQuery({
    queryKey: ['programs'],
    queryFn: getPrograms,
  })

  if (isLoading) return <p>Loading programs…</p>
  if (isError)   return <p>Could not load programs.</p>
  if (!data?.length) return <p>No programs yet.</p>

  return (
    <ul className="program-list">
      {data.map(p => (
        <li key={p.id} className="program-card">
          <h3>{p.title}</h3>
          <p>{p.description}</p>
        </li>
      ))}
    </ul>
  )
}
