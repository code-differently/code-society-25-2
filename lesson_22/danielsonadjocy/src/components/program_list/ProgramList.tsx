import './ProgramList.css';
import {useOutletContext} from 'react-router-dom';

import Program from '../program/Program';

export default function ProgramList() {
  const {programs} = useOutletContext<{
    programs: Array<{id: string; title: string; description: string}>;
    addProgram: (title: string, description: string) => void;
  }>();

  return (
    <section className="programs-section">
      <h2>
        Our <em className="highlight">Programs</em>
      </h2>
      <ul className="programs">
        {programs.map(program => (
          <li className="program" key={program.id}>
            <Program {...program} />
          </li>
        ))}
      </ul>
    </section>
  );
}
