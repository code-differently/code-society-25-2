import { Program, ProgramProps } from '../program/Program';
import './ProgramList.scss';

export const ProgramList = ({ programs }: { programs: ProgramProps[] }) => (
  <ul className="programs">
    {programs.map(eachProgram => (
      <Program
        key={eachProgram.title}
        title={eachProgram.title}
        description={eachProgram.description}
      />
    ))}{' '}
  </ul>
);