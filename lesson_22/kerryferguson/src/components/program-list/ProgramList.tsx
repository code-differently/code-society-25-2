import {Program, ProgramData} from '../program';

export const ProgramList = ({programs}: {programs: ProgramData[]}) => (
  <section className="programs-section">
    <h2>
      Our <em className="highlight">Programs</em>
    </h2>
    <ul className="programs">
      {programs.map(eachProgram => (
        <Program
          key={eachProgram.title}
          title={eachProgram.title}
          description={eachProgram.description}
        />
      ))}
    </ul>
  </section>
);
