import {Db} from './db.js';
import {Program} from '@code-differently/types';
import cors from 'cors';
import express, {Express, Request, Response} from 'express';

const UUID_PATTERN =
  /^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i;

export const createServer = (db: Db): Express => {
  const app: Express = express();

  console.log('Setting up Express server...');

  app.use(express.static('public'));
  app.use(express.json());
  app.use(express.urlencoded({extended: true}));
  app.use(cors());

  console.log('Routes configured, starting server...');

  app.get('/programs', async (req: Request, res: Response) => {
    console.log('GET /programs called');
    try {
      const programs = await db.getPrograms();
      console.log('Returning programs:', programs.length);
      res.status(200).send(programs);
    } catch (error) {
      console.error('Error getting programs:', error);
      res.status(500).send({error: 'Failed to get programs'});
    }
  });

  app.post('/programs', async (req: Request, res: Response) => {
    console.log('POST /programs called with body:', req.body);
    try {
      await db.addProgram(req.body as Program);
      console.log('Program added successfully');
      res.status(201).send();
    } catch (error: unknown) {
      console.error('Error adding program:', error);
      res.status(500).send({error: 'Failed to add program.'});
    }
  });

  const port = process.env.port || 4001; // Changed from 4000 to 4001
  console.log('Attempting to listen on port:', port);
  const server = app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
  });

  // Handle server errors
  server.on('error', error => {
    console.error('Server error:', error);
  });

  return app;
};
