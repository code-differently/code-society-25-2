# Express Components Demo (Thomas Boyle - Lesson 21)

This project serves the Lesson 19 accessible UI components (Tabs, Accordion, Rotating Gallery) plus a Contact form via an Express server.

## Features
- Static pages: `/` (home), `/components`, `/contact`
- Contact form (POST /contact) echoes sanitized submission
- All component assets are offline-friendly (no external fonts/libraries/images)
- SVG gallery images generated client-side via data URIs
- Accessible patterns (ARIA roles, keyboard navigation) preserved from Lesson 19 work

## Project Structure
```
express_components_tb/
  package.json
  server.js
  README.md
  public/
    index.html
    components.html
    contact.html
    style.css
    script.js
```

## Install & Run
From repository root (or inside the project folder):

```bash
cd lesson_21/thomasboyleL21/express_components_tb
npm install
npm start
```
Server listens by default on: `http://localhost:3000`

## Routes
| Method | Path        | Description                                  |
|--------|-------------|----------------------------------------------|
| GET    | /           | Home page overview                           |
| GET    | /components | Components demo (tabs, accordion, gallery)   |
| GET    | /contact    | Contact form                                 |
| POST   | /contact    | Process form and display confirmation        |

## Contact Form
Fields: `name`, `email`, `topic`, `message`.
All returned values are HTML‑escaped to mitigate XSS. Timestamp (UTC) added.

Sample cURL:
```bash
curl -X POST -d 'name=Test&email=test%40example.com&topic=Hi&message=Hello+world' http://localhost:3000/contact
```

## Accessibility
- Tabs: `role=tablist`, `role=tab`, `role=tabpanel`, roving tabindex, arrow/Home/End keys.
- Accordion: `aria-expanded`, `aria-controls`, `role=region`, arrow + Enter/Space keys.
- Gallery: Responsive (1/2/3 images), `aria-live=polite`, pause control with `aria-pressed`.

## Customization
- Adjust port: set `PORT` environment variable before starting.
- Add new static pages: drop HTML into `public/` and add route if needed.
- Modify gallery palette in `public/script.js` (the `images` array generation colors).

## License
Inherits repository license.
