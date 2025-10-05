import {Program, ProgramData} from '../program';

export const ProgramList = ({programs}: {programs: ProgramData[]}) => (
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
