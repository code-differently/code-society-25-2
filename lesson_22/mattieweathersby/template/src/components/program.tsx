export type programProps = {
  title: string;
  description: string;
};

export const Program: React.FC<programProps> = ({ title, description }: programProps) => (
    <li className="program">
        {' '}
        <h3>{title}</h3>
        <p>{description}</p>{' '}
    </li>   
);
