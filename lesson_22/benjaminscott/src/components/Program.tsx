import './Program.scss';

interface ProgramProps {
  title: string;
  description: string;
}

export default function Program({title, description}: ProgramProps) {
  return (
    <li className="program">
      <h3>{title}</h3>
      <p>{description}</p>
    </li>
  );
}
