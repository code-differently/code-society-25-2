import React from "react";
import Program from "./Program";
export default function ProgramList()
{
    return (
        <section className="programs-section">
        <h2>
          Our <em className="highlight">Programs</em>
        </h2>
        
        <Program />
        <Program />
        <Program />
        <ul className="programs">

          <li className="program">
            <Program title="Bacon Ipsum" description="Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin." />
          </li>
          <li className="program">
            <Program title="Bacon Ipsum" description="Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin." />
          </li>
          <li className="program">
            <h3>Picanha Swine Jowl</h3>
            <p>
              Picanha swine jowl meatball boudin pastrami bresaola fatback
              shankle pork belly cow. Frankfurter ground round shank corned beef
              chuck. Jerky frankfurter fatback capicola hamburger, pork
              prosciutto bresaola ham porchetta rump t-bone pancetta tenderloin.
              Fatback ham hock prosciutto, tenderloin shoulder salami tri-tip
              leberkas bacon turducken chislic venison sausage frankfurter.
            </p>
          </li>
          <li className="program">
            <h3>Kevin Chicken T-Bone</h3>
            <p>
              Kevin chicken t-bone spare ribs shankle bacon drumstick kielbasa
              cow jowl doner salami chuck andouille. Rump spare ribs bresaola
              frankfurter shankle. Bresaola ribeye turducken, cow leberkas
              boudin biltong sirloin filet mignon ham pork belly shank.
              Tenderloin sirloin pancetta pork loin shankle venison turducken
              boudin. Brisket tenderloin salami ham bresaola burgdoggen.
            </p>
          </li>
        </ul>
      </section>
    );
};