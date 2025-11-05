// Minimal HTTP server (no external deps)
import http from 'http';
 
const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ ok: true, time: new Date().toISOString() }));
});
 
server.listen(3000, () => console.log('Listening on http://localhost:3000'));