import React from 'react';
import './Program.scss'; // optional if you wanna style each card

interface ProgramProps {
  title: string;
  description: string;
}

export const Program: React.FC<ProgramProps> = ({ title, description }) => {
  return (
    <li className="program">
      <h3>{title}</h3>
      <p>{description}</p>
    </li>
  );
};
