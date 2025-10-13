import React from 'react';
import { ProgramList } from '../../components/ProgramList';
import './Home.scss';

// Sample program data - in a real app this would come from an API
const samplePrograms = [
  {
    id: 1,
    title: 'Full-Stack Web Development',
    description: 'Learn modern web development with JavaScript, React, Node.js, and databases. This comprehensive program covers front-end and back-end development, teaching you to build complete web applications from scratch. You\'ll master HTML, CSS, JavaScript ES6+, React hooks, Express.js, and MongoDB while working on real-world projects.'
  },
  {
    id: 2,
    title: 'Data Structures & Algorithms',
    description: 'Master fundamental computer science concepts essential for technical interviews and efficient programming. Cover arrays, linked lists, stacks, queues, trees, graphs, sorting algorithms, and dynamic programming. Learn to analyze time and space complexity while solving coding challenges used by top tech companies.'
  },
  {
    id: 3,
    title: 'Mobile App Development',
    description: 'Build native and cross-platform mobile applications for iOS and Android. Learn React Native, Flutter, or native development with Swift and Kotlin. Understand mobile UI/UX principles, device capabilities, app store deployment, and mobile-specific performance optimization techniques.'
  },
  {
    id: 4,
    title: 'Software Engineering Principles',
    description: 'Learn industry best practices for building scalable, maintainable software systems. Cover software design patterns, SOLID principles, test-driven development, version control with Git, agile methodologies, and DevOps practices. Understand system design, API development, and collaborative software development workflows.'
  }
];

export const Home: React.FC = () => {
  return (
    <article>
      <section className="hero-section">
        <div className="hero-overlay"></div>
        <div className="hero-content">
          <h2 className="hero-title">
            Together we can move the needle of{' '}
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
        <h2>
          Our <em className="highlight">Programs</em>
        </h2>
        <ProgramList programs={samplePrograms} />
      </section>
    </article>
  );
};
