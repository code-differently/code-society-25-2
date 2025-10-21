import Program from './Program.tsx';
import {FC} from 'react';

interface ProgramData {
  title: string;
  description: string;
}

interface ProgramListProps {
  programs: ProgramData[];
}

const ProgramList: FC<ProgramListProps> = ({programs}) => {
  return (
    <ul className="programs">
      {programs.map((program, index) => (
        <Program
          key={index}
          title={program.title}
          description={program.description}
        />
      ))}
    </ul>
  );
};

export default ProgramList;
