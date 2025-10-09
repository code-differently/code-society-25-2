import './ProgramList.scss';
import { Program } from '@code-differently/types';
import React, { useEffect, useState } from 'react';


async function getDataFromAPI(url) {
  try {
    const response = await fetch(url); 
    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error('Failed to load data', response.status);
    }
  } catch (error) {
    console.error("Couldn't load data", error);
    throw error;
  }
}

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<Program[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

 
};