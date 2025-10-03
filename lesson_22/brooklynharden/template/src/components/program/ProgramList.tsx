import React, {FC} from 'react';
import Program from './Program';


type ProgramType = {
    title:string;
    description: string;
};

type ProgramListProps = {
    programs: ProgramType[];
};
const ProgramList: FC<ProgramListProps> = ({programs}) => {
  return (
  
         <ul className="programs">
          {programs.map((program, idx) => (
            <Program
                key={idx}
                title={program.title}
                description={program.description}
            />
            ))}
        </ul>
  );
};

export default ProgramList