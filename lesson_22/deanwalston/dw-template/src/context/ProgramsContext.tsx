import React, {ReactNode, createContext, useContext, useState} from 'react';

export interface Program {
  id: string;
  title: string;
  description: string;
}

interface ProgramsContextType {
  programs: Program[];
  addProgram: (title: string, description: string) => void;
}

const ProgramsContext = createContext<ProgramsContextType | undefined>(
  undefined
);

export const useProgramsContext = () => {
  const context = useContext(ProgramsContext);
  if (!context) {
    throw new Error(
      'useProgramsContext must be used within a ProgramsProvider'
    );
  }
  return context;
};

interface ProgramsProviderProps {
  children: ReactNode;
}

export const ProgramsProvider: React.FC<ProgramsProviderProps> = ({
  children,
}) => {
  const [programs, setPrograms] = useState<Program[]>([
    {
      id: '1',
      title: 'Swine Short Loin',
      description:
        'Swine short loin burgdoggen ball tip, shank ham hock bacon. Picanha strip steak pork, swine boudin ham meatball meatloaf leberkas sausage. Turkey beef andouille kielbasa rump pastrami biltong chislic alcatra buffalo prosciutto jowl. Pastrami chicken sirloin swine capicola landjaeger jowl boudin pork chop shankle bresaola turducken leberkas ham.',
    },
    {
      id: '2',
      title: 'Bacon Ipsum',
      description:
        'Bacon ipsum dolor amet leberkas chuck biltong pork loin sirloin bresaola rump frankfurter. Shoulder doner strip steak chuck. Short ribs venison salami chuck sirloin jowl chislic cupim swine cow. Corned beef chuck frankfurter tenderloin venison biltong andouille leberkas kielbasa sausage t-bone turducken fatback. Pork picanha buffalo bacon tail salami meatball, jowl chislic.',
    },
    {
      id: '3',
      title: 'Picanha Swine Jowl',
      description:
        'Picanha swine jowl meatball boudin pastrami bresaola fatback shankle pork belly cow. Frankfurter ground round shank corned beef chuck. Jerky frankfurter fatback capicola hamburger, pork prosciutto bresaola ham porchetta rump t-bone pancetta tenderloin. Fatback ham hock prosciutto, tenderloin shoulder salami tri-tip leberkas bacon turducken chislic venison sausage frankfurter.',
    },
    {
      id: '4',
      title: 'Kevin Chicken T-Bone',
      description:
        'Kevin chicken t-bone spare ribs shankle bacon drumstick kielbasa cow jowl doner salami chuck andouille. Rump spare ribs bresaola frankfurter shankle. Bresaola ribeye turducken, cow leberkas boudin biltong sirloin filet mignon ham pork belly shank. Tenderloin sirloin pancetta pork loin shankle venison turducken boudin. Brisket tenderloin salami ham bresaola burgdoggen.',
    },
  ]);

  const addProgram = (title: string, description: string) => {
    const newProgram: Program = {
      id: Date.now().toString(), // Simple ID generation
      title,
      description,
    };
    setPrograms(prev => [...prev, newProgram]);
  };

  return (
    <ProgramsContext.Provider value={{programs, addProgram}}>
      {children}
    </ProgramsContext.Provider>
  );
};
