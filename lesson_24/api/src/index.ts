import {DbImpl} from './db.js';
import {createServer} from './server.js';
import path from 'path';
import {fileURLToPath} from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const PROGRAMS_FILE = path.resolve(__dirname, './data/programs.json');

console.log('Database file path:', PROGRAMS_FILE);

const db = new DbImpl(PROGRAMS_FILE);
createServer(db);
