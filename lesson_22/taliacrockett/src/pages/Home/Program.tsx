import {FC} from 'react';

interface ProgramProps {
  title: string;
  description: string;
}

const Program: FC<ProgramProps> = ({title, description}) => {
  return (
    <li className="program">
      <h3>{title}</h3>
      <p>{description}</p>
    </li>
  );
};

export default Program;
