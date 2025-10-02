const Program = ({ title, description }: { title: string; description: string; }) => (
  <li className="program">
    {' '}
    <h3>{title}</h3> <p>{description}</p>{' '}
  </li>
);