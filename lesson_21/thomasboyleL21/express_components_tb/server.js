import express from 'express';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Static assets
const publicDir = path.join(__dirname, 'public');
app.use(express.static(publicDir));

// Simple HTML layout helper
function page({ title, body }) {
  return `<!DOCTYPE html><html lang="en"><head><meta charset='UTF-8'/><meta name='viewport' content='width=device-width,initial-scale=1'/><title>${title}</title><link rel='stylesheet' href='/style.css'/></head><body><header class="header"><h1>${title}</h1><nav aria-label='Primary'><a href='/'>Home</a><a href='/components'>Components</a><a href='/contact'>Contact</a></nav></header><main class='wrapper'>${body}</main><footer class='footer'>&copy; 2025 Thomas Boyle</footer></body></html>`;
}

// Routes
app.get('/', (req,res)=>{
  res.sendFile(path.join(publicDir,'index.html'));
});

app.get('/components', (req,res)=>{
  res.sendFile(path.join(publicDir,'components.html'));
});

app.get('/contact', (req,res)=>{
  res.sendFile(path.join(publicDir,'contact.html'));
});

app.post('/contact', (req,res)=>{
  const { name='', email='', topic='', message='' } = req.body || {};
  const esc = str => String(str).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;','\'':'&#39;'}[c]));
  const submittedAt = new Date().toISOString();
  const body = `
    <section class='block'>
      <h2>Submission Received</h2>
      <p>Thank you, <strong>${esc(name)||'Anonymous'}</strong>. Below is a copy of your submission.</p>
      <dl>
        <dt>Name</dt><dd>${esc(name)}</dd>
        <dt>Email</dt><dd>${esc(email)}</dd>
        <dt>Topic</dt><dd>${esc(topic)}</dd>
        <dt>Message</dt><dd><pre style='white-space:pre-wrap;font-family:inherit;background:#f5f7fa;padding:.75rem;border:1px solid #d0d7de;border-radius:6px;'>${esc(message)}</pre></dd>
        <dt>Timestamp (UTC)</dt><dd>${submittedAt}</dd>
      </dl>
      <p><a href='/contact'>Submit another response</a></p>
    </section>`;
  res.send(page({ title: 'Contact Confirmation', body }));
});

// 404 handler
app.use((req,res)=>{
  res.status(404).send(page({ title: 'Not Found', body: `<h2>404 - Page Not Found</h2><p>The path <code>${req.path}</code> does not exist.</p>` }));
});

app.listen(PORT, ()=>{
  console.log(`Server listening on http://localhost:${PORT}`);
});
