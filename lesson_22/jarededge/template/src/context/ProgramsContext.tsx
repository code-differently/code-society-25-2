import React, {ReactNode, createContext, useContext, useState} from 'react';

// Define the Program interface
export interface Program {
  id: string;
  title: string;
  description: string;
  createdAt: Date;
}

// Define the context interface
interface ProgramsContextType {
  programs: Program[];
  addProgram: (title: string, description: string) => void;
  removeProgram: (id: string) => void;
  updateProgram: (id: string, title: string, description: string) => void;
}

// Create the context
const ProgramsContext = createContext<ProgramsContextType | undefined>(
  undefined
);

// Custom hook to use the programs context
export const usePrograms = () => {
  const context = useContext(ProgramsContext);
  if (context === undefined) {
    throw new Error('usePrograms must be used within a ProgramsProvider');
  }
  return context;
};

// Provider component props
interface ProgramsProviderProps {
  children: ReactNode;
}

// Provider component
export const ProgramsProvider: React.FC<ProgramsProviderProps> = ({
  children,
}) => {
  // Initialize with the existing hardcoded programs
  const [programs, setPrograms] = useState<Program[]>([
    {
      id: '1',
      title: 'Swine Short Loin',
      description:
        'Swine short loin burgdoggen ball tip, shank ham hock bacon. Picanha strip steak pork, swine boudin ham meatball meatloaf leberkas sausage. Turkey beef andouille kielbasa rump pastrami biltong chislic alcatra buffalo prosciutto jowl. Pastrami chicken sirloin swine capicola landjaeger jowl boudin pork chop shankle bresaola turducken leberkas ham.',
      createdAt: new Date('2024-01-01'),
    },
    {
      id: '2',
      title: 'Bacon Ipsum',
      description:
        'Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin bresaola rump frankfurter. Shoulder doner strip steak chuck. Short ribs venison salami chuck sirloin jowl chislic cupim swine cow. Corned beef chuck frankfurter tenderloin venison biltong andouille leberkas kielbasa sausage t-bone turducken fatback. Pork picanha buffalo bacon tail salami meatball, jowl chislic.',
      createdAt: new Date('2024-01-02'),
    },
    {
      id: '3',
      title: 'Picanha Swine Jowl',
      description:
        'Picanha swine jowl meatball boudin pastrami bresaola fatback shankle pork belly cow. Frankfurter ground round shank corned beef chuck. Jerky frankfurter fatback capicola hamburger, pork prosciutto bresaola ham porchetta rump t-bone pancetta tenderloin. Fatback ham hock prosciutto, tenderloin shoulder salami tri-tip leberkas bacon turducken chislic venison sausage frankfurter.',
      createdAt: new Date('2024-01-03'),
    },
    {
      id: '4',
      title: 'Kevin Chicken T-Bone',
      description:
        'Kevin chicken t-bone spare ribs shankle bacon drumstick kielbasa cow jowl doner salami chuck andouille. Rump spare ribs bresaola frankfurter shankle. Bresaola ribeye turducken, cow leberkas boudin biltong sirloin filet mignon ham pork belly shank. Tenderloin sirloin pancetta pork loin shankle venison turducken boudin. Brisket tenderloin salami ham bresaola burgdoggen.',
      createdAt: new Date('2024-01-04'),
    },
  ]);

  // Add a new program
  const addProgram = (title: string, description: string) => {
    const newProgram: Program = {
      id: Date.now().toString(), // Simple ID generation (in production, use uuid or similar)
      title: title.trim(),
      description: description.trim(),
      createdAt: new Date(),
    };

    setPrograms(prevPrograms => [newProgram, ...prevPrograms]); // Add to the beginning
  };

  // Remove a program
  const removeProgram = (id: string) => {
    setPrograms(prevPrograms =>
      prevPrograms.filter(program => program.id !== id)
    );
  };

  // Update a program
  const updateProgram = (id: string, title: string, description: string) => {
    setPrograms(prevPrograms =>
      prevPrograms.map(program =>
        program.id === id
          ? {...program, title: title.trim(), description: description.trim()}
          : program
      )
    );
  };

  const value: ProgramsContextType = {
    programs,
    addProgram,
    removeProgram,
    updateProgram,
  };

  return (
    <ProgramsContext.Provider value={value}>
      {children}
    </ProgramsContext.Provider>
  );
};
