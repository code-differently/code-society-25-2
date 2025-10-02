import { Program, ProgramProps } from './Program';

const ProgramList = ({ programs }: { programs: Array<ProgramProps>; }) => (
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