import * as React from 'react'
import { Outlet } from 'react-router-dom'
import { Header } from './components/Header'

export default function App() {
  return (
    <>
      <Header />
      <main className="container">
        <Outlet />
      </main>
    </>
  )
}
