import React, { createContext, useContext, useState, ReactNode } from 'react';

export interface Program {
  id: string;
  title: string;
  description: string;
  dateAdded: Date;
}

interface ProgramsContextType {
  programs: Program[];
  addProgram: (title: string, description: string) => void;
}

const ProgramsContext = createContext<ProgramsContextType | undefined>(undefined);

interface ProgramsProviderProps {
  children: ReactNode;
}

export const ProgramsProvider: React.FC<ProgramsProviderProps> = ({ children }) => {
  // Initial programs data
  const [programs, setPrograms] = useState<Program[]>([
    {
      id: '1',
      title: "1000 Kids Coding",
      description: "The Code Differently 1000 Kids Coding program was created to expose New Castle County students to computing and programming. The 1000 Kids Coding courses are designed for all experience levels, no experience required.",
      dateAdded: new Date('2024-01-01')
    },
    {
      id: '2',
      title: "Return Ready",
      description: "The Code Differently Workforce Training Initiatives were created to help individuals underrepresented in tech reinvent their skills to align with the changing workforce market. If you are ready to start your tech journey, join our talent community today.",
      dateAdded: new Date('2024-02-01')
    },
    {
      id: '3',
      title: "Pipeline DevShops",
      description: "Pipeline DevShop is a youth work-based learning program. Youth participants experience working in a real software development environment while sharpening their technology and soft skills.",
      dateAdded: new Date('2024-03-01')
    },
    {
      id: '4',
      title: "Platform Programs",
      description: "Platform programs are designed for high school graduates, college students, career changers, or professionals looking to develop the technology job readiness skills for today's workforce.",
      dateAdded: new Date('2024-04-01')
    }
  ]);

  const addProgram = (title: string, description: string) => {
    const newProgram: Program = {
      id: Date.now().toString(), // Simple ID generation
      title,
      description,
      dateAdded: new Date()
    };
    
    setPrograms(prevPrograms => [...prevPrograms, newProgram]);
  };

  return (
    <ProgramsContext.Provider value={{ programs, addProgram }}>
      {children}
    </ProgramsContext.Provider>
  );
};

export const usePrograms = () => {
  const context = useContext(ProgramsContext);
  if (context === undefined) {
    throw new Error('usePrograms must be used within a ProgramsProvider');
  }
  return context;
};
