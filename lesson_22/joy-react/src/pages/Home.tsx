import "./Home.scss";
import React from "react";
import ProgramList, { ProgramItem } from "../components/ProgramList";
import { Link } from "react-router-dom";

const DEFAULT_PROGRAMS: ProgramItem[] = [
  {
    title: "Swine Short Loin",
    description:
      "Swine short loin burgdoggen ball tip, shank ham hock bacon. Picanha strip steak pork...",
  },
  {
    title: "Bacon Ipsum",
    description:
      "Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin bresaola rump...",
  },
  {
    title: "Picanha Swine Jowl",
    description:
      "Picanha swine jowl meatball boudin pastrami bresaola fatback shankle pork belly cow...",
  },
  {
    title: "Kevin Chicken T-Bone",
    description:
      "Kevin chicken t-bone spare ribs shankle bacon drumstick kielbasa cow jowl doner...",
  },
];

const Home: React.FC = () => {
  const [programs, setPrograms] = React.useState<ProgramItem[]>(() => {
    const saved = localStorage.getItem("programs");
    if (saved) {
      try {
        const parsed = JSON.parse(saved) as ProgramItem[];
        return parsed.length ? parsed : DEFAULT_PROGRAMS;
      } catch {
        return DEFAULT_PROGRAMS;
      }
    }
    return DEFAULT_PROGRAMS;
  });

  React.useEffect(() => {
    localStorage.setItem("programs", JSON.stringify(programs));
  }, [programs]);

  return (
    <article>
      <section className="hero-section">
        <div className="hero-overlay"></div>
        <div className="hero-content">
          <h2 className="hero-title">
            Together we can move the needle of{" "}
            <em className="highlight">diversity in tech.</em>
          </h2>
          <div className="hero-text">
            <span>Code Differently</span> provides hands on training and
            education through coding classes that gives participants the
            technical and cognitive skills they need to excel in
            technology-driven workplaces.
          </div>
        </div>
      </section>

      <section className="programs-section">
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center"}}>
          <h2>
            Our <em className="highlight">Programs</em>
          </h2>
          <Link to="/add" className="btn">+ Add Program</Link>
        </div>

        <ProgramList programs={programs} />
      </section>
    </article>
  );
};

export default Home;
