document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".sign_up-form");

  form.addEventListener("submit", async (e) => {
    e.preventDefault(); 
    const formData = {
      name: document.getElementById("name").value.trim(),
      email: document.getElementById("email").value.trim(),
      message: document.getElementById("message").value.trim(),
    };

    try {
      const response = await fetch("/sign_up", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("✅ Sign up successful! Thanks for joining.");
        form.reset();
      } else {
        const error = await response.json();
        alert("⚠️ Error: " + error.message);
      }
    } catch (err) {
      console.error(err);
      alert("⚠️ Network error. Please try again later.");
    }
  });
});
