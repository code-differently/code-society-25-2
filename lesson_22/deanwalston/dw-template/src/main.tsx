import App from './App.tsx';
import {Home} from './pages/Home/Home.tsx';
import NewProgramForm from './pages/Program/NewProgramForm.tsx';
import {Programs} from './pages/Programs/Programs.tsx';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';
import React from 'react';
import ReactDOM from 'react-dom/client';
import {RouterProvider, createBrowserRouter} from 'react-router-dom';

import {ProgramsProvider} from './context/ProgramsContext.tsx';

import './index.scss';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '/',
        element: <Home />,
      },
      {
        path: '/programs',
        element: <Programs />,
      },
      {
        path: '/add-program',
        element: <NewProgramForm />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <ProgramsProvider>
        <RouterProvider router={router} />
      </ProgramsProvider>
    </QueryClientProvider>
  </React.StrictMode>
);
