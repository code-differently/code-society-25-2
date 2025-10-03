document.addEventListener("DOMContentLoaded", () => {
  const resultsEl = document.getElementById("results");
  const data = JSON.parse(localStorage.getItem("sign_upData") || "{}");

  if (data.name && data.email && data.message) {
    resultsEl.innerHTML = `
      <li><strong>Name:</strong> ${data.name}</li>
      <li><strong>Email:</strong> ${data.email}</li>
      <li><strong>Message:</strong> ${data.message}</li>
    `;

    localStorage.removeItem("sign_upData");
  }
});
