function getFormDataFromQuery() {
  const params = new URLSearchParams(window.location.search);
  const data = {};
  for (const [key, value] of params.entries()) {
    data[key] = value;
  }
  return data;
}

function renderFormData(data) {
  const container = document.getElementById('form-data');
  if (!container) return;
  if (Object.keys(data).length === 0) {
    container.innerHTML = '<em>No form data found.</em>';
    return;
  }
  const html = Object.entries(data)
    .map(([key, value]) => `<div><strong>${key}:</strong> ${decodeURIComponent(value)}</div>`)
    .join('');
  container.innerHTML = html;
}

document.addEventListener('DOMContentLoaded', () => {
  const formData = getFormDataFromQuery();
  renderFormData(formData);
});
