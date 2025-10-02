import React, { FC } from 'react'

type ProgramType = {
    title:string;
    description:string;
}

const Program:FC<ProgramType> = ({title, description}) => {
  return (
    <div>
        <h3>Swine Short Loin</h3>
        <p>
              Swine short loin burgdoggen ball tip, shank ham hock bacon.
              Picanha strip steak pork, swine boudin ham meatball meatloaf
              leberkas sausage. Turkey beef andouille kielbasa rump pastrami
              biltong chislic alcatra buffalo prosciutto jowl. Pastrami chicken
              sirloin swine capicola landjaeger jowl boudin pork chop shankle
              bresaola turducken leberkas ham.
        </p>
          
    </div>

  )
}

export default Program