import { Program, ProgramProps } from './Program';

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