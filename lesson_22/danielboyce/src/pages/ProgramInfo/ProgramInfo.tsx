import './ProgramInfo.css';
import {ProgramType, programArray} from '@/types/types';
import {useParams} from 'react-router-dom';

import Program from '@/components/program/Program';

const ProgramInfo = () => {
  const {id} = useParams();

  const currentProgram: ProgramType = programArray[Number(id)];

  return (
    <div className="program-info-container">
      {currentProgram ? (
        <div className="program-info-content">
          <Program program={currentProgram} />
        </div>
      ) : (
        <div className="program-not-found">
          <div className="program-not-found-icon">❌</div>
          <h2 className="program-not-found-title">Program Not Found</h2>
          <p className="program-not-found-description">
            The program you're looking for doesn't exist or may have been
            removed.
          </p>
        </div>
      )}
    </div>
  );
};

export default ProgramInfo;
