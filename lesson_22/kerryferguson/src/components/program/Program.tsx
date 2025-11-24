export type ProgramData = {
  title: string;
  description: string;
};
export const Program = ({title, description}: ProgramData) => (
  <li className="program">
    {' '}
    <h3>{title}</h3> <p>{description}</p>{' '}
  </li>
);
