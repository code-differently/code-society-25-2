import {ProgramList} from './ProgramList/ProgramList';
import React from 'react';

// Test component to demonstrate edge cases
export const EdgeCaseTest: React.FC = () => {
  // Test edge cases
  const edgeCasePrograms = [
    // Normal case
    {
      title: 'Normal Program',
      description: 'This is a normal program with both title and description.',
    },
    // Empty title
    {
      title: '',
      description: 'This program has an empty title.',
    },
    // Empty description
    {
      title: 'Program with Empty Description',
      description: '',
    },
    // Very long text
    {
      title:
        'Very Long Title That Should Wrap Gracefully If The CSS Handles It Properly',
      description:
        'This is a very long description that contains a lot of text to test how well the component handles overflow and text wrapping. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    },
    // Null-like values (testing our edge case handling)
    {
      title: '   ', // whitespace only
      description: '   ', // whitespace only
    },
  ];

  const emptyPrograms: any[] = [];

  return (
    <div>
      <h2>Edge Case Tests</h2>

      <h3>Normal Programs:</h3>
      <ProgramList programs={edgeCasePrograms} />

      <h3>Empty Programs Array:</h3>
      <ProgramList programs={emptyPrograms} />
    </div>
  );
};
