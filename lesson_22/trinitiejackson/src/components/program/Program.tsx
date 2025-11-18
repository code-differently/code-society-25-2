export type ProgramProps = {
  title: string;
  description: string;
};
export const Program = ({ title, description, }: ProgramProps) => (
  <li className="program">
    {' '}
    <h3>{title}</h3> <p>{description}</p>{' '}
  </li>
);
