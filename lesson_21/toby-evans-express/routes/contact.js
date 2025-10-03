const path = require('path');
const express = require('express');
const router = express.Router();

router.get('/contact', (_req, res) => {
  res.sendFile(path.resolve(__dirname, '../public/contact.html'));
});

router.post('/contact', (req, res) => {
  const name = (req.body.name || '').trim();
  const email = (req.body.email || '').trim();
  const topic = (req.body.topic || '').trim();
  const message = (req.body.message || '').trim();
  const subscribe = req.body.subscribe === 'yes' ? 'yes' : 'no';

  const errors = [];
  if (!name) errors.push('Name is required.');
  if (!email) errors.push('Email is required.');
  if (!topic) errors.push('Topic is required.');
  if (!message) errors.push('Message is required.');

  if (errors.length) {
    res.status(400).send(`<!doctype html><html><head><meta charset="utf-8"><title>Contact Error</title><meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="/style.css"></head><body><main class="container" style="max-width:720px;margin:2rem auto;padding:1rem"><h1>Fix the following</h1><ul>${errors.map(e=>`<li>${e}</li>`).join('')}</ul><p><a href="/contact">Back to Contact</a></p></main></body></html>`);
    return;
  }

  res.status(200).send(`<!doctype html><html><head><meta charset="utf-8"><title>Contact Submitted</title><meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="/style.css"></head><body><main class="container" style="max-width:720px;margin:2rem auto;padding:1rem"><h1>Thanks for reaching out</h1><p><strong>Name:</strong> ${name}</p><p><strong>Email:</strong> ${email}</p><p><strong>Topic:</strong> ${topic}</p><p><strong>Subscribed:</strong> ${subscribe}</p><p><strong>Message:</strong></p><pre>${message}</pre><p><a href="/contact">Back to Contact</a> · <a href="/">Home</a></p></main></body></html>`);
});

module.exports = router;
