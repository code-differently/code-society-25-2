import './ProgramList.scss';
import React, { useState, useEffect } from 'react';

import {Program} from '../Program';

interface ProgramData {
  id: string;
  title: string;
  description: string;
}

export const ProgramList: React.FC = () => {
  const [programs, setPrograms] = useState<ProgramData[]>([]);

  useEffect(() => {
    const fetchPrograms = async () => {
      try {
        const response = await fetch('/programs');
        if (!response.ok) {
          console.error('Failed to fetch programs');
          return;
        }
        console.log("Fetching programs...");
        const data = await response.json();
        setPrograms(data);
      } catch (err) {
        console.error('Unknown error');
      }
    };
    fetchPrograms();
  }, []);

  return (
    <ul className="programs">
      {programs.map((program) => (
        <Program key={program.id} title={program.title}>
          <p>{program.description}</p>
        </Program>
      ))}
      <Program title="Swine Short Loin">
        <p>
          Swine short loin burgdoggen ball tip, shank ham hock bacon. Picanha
          strip steak pork, swine boudin ham meatball meatloaf leberkas sausage.
          Turkey beef andouille kielbasa rump pastrami biltong chislic alcatra
          buffalo prosciutto jowl. Pastrami chicken sirloin swine capicola
          landjaeger jowl boudin pork chop shankle bresaola turducken leberkas
          ham.
        </p>
      </Program>
      <Program title="Bacon Ipsum">
        <p>
          Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin
          bresaola rump frankfurter. Shoulder doner strip steak chuck. Short
          ribs venison salami chuck sirloin jowl chislic cupim swine cow. Corned
          beef chuck frankfurter tenderloin venison biltong andouille leberkas
          kielbasa sausage t-bone turducken fatback. Pork picanha buffalo bacon
          tail salami meatball, jowl chislic.
        </p>
      </Program>
      <Program title="Picanha Swine Jowl">
        <p>
          Picanha swine jowl meatball boudin pastrami bresaola fatback shankle
          pork belly cow. Frankfurter ground round shank corned beef chuck.
          Jerky frankfurter fatback capicola hamburger, pork prosciutto bresaola
          ham porchetta rump t-bone pancetta tenderloin. Fatback ham hock
          prosciutto, tenderloin shoulder salami tri-tip leberkas bacon
          turducken chislic venison sausage frankfurter.
        </p>
      </Program>
      <Program title="Kevin Chicken T-Bone">
        <p>
          Kevin chicken t-bone spare ribs shankle bacon drumstick kielbasa cow
          jowl doner salami chuck andouille. Rump spare ribs bresaola
          frankfurter shankle. Bresaola ribeye turducken, cow leberkas boudin
          biltong sirloin filet mignon ham pork belly shank. Tenderloin sirloin
          pancetta pork loin shankle venison turducken boudin. Brisket
          tenderloin salami ham bresaola burgdoggen.
        </p>
      </Program>
    </ul>
  );
};
