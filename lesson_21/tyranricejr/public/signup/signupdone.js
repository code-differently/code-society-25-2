function getQueryParams() {
    const params = {};
    window.location.search.replace(/^[?]/, '').split('&').forEach(pair => {
        const [key, value] = pair.split('=');
        if (key) params[key] = decodeURIComponent(value || '');
    });
    return params;
}
const params = getQueryParams();
const resultsList = document.getElementById('resultsList');
if (params.name) {
    resultsList.innerHTML += `<li><strong>Name:</strong> ${params.name}</li>`;
}
if (params.email) {
    resultsList.innerHTML += `<li><strong>Email:</strong> ${params.email}</li>`;
}
if (params.password) {
    resultsList.innerHTML += `<li><strong>Password:</strong> ${params.password}</li>`;
}
