// Simple medicine reminder demo
const STORAGE_KEY = 'medicine_reminder_v1';

const clientForm = document.getElementById('clientForm');
const clientId = document.getElementById('clientId');
const clientName = document.getElementById('clientName');
const medication = document.getElementById('medication');
const dosage = document.getElementById('dosage');
const doseTime = document.getElementById('doseTime');
const addTime = document.getElementById('addTime');
const timesList = document.getElementById('timesList');
const clientsEl = document.getElementById('clients');
const logEl = document.getElementById('log');

const modal = document.getElementById('reminderModal');
const reminderMsg = document.getElementById('reminderMsg');
const takenBtn = document.getElementById('takenBtn');
const missedBtn = document.getElementById('missedBtn');

let store = { clients: [], log: [], pending: [] };
let currentReminder = null; // {clientId, time, timerId, escalateId}

function saveStore() { localStorage.setItem(STORAGE_KEY, JSON.stringify(store)); }
function loadStore() { const raw = localStorage.getItem(STORAGE_KEY); if (raw) store = JSON.parse(raw); }

function requestNotificationPermission() {
	if (!('Notification' in window)) return;
	if (Notification.permission === 'default') Notification.requestPermission();
}

function formatTime(t) { return t; }

function renderTimes(client) {
	// returns HTML for times
	return client.times.map(t => `<li>${t} <button data-remove='${t}' class='muted'>Remove</button></li>`).join('')
}

function render() {
	// clients
	clientsEl.innerHTML = '';
	store.clients.forEach(c => {
		const li = document.createElement('li');
		li.className = 'client-card';
		li.innerHTML = `
			<div class='client-meta'>
				<strong>${c.name}</strong> — ${c.medication}<br>
				<small>${c.dosage || ''}</small>
				<div>Times: ${c.times.join(', ')}</div>
			</div>
			<div class='client-actions'>
				<button data-edit='${c.id}'>Edit</button>
				<button data-delete='${c.id}' class='muted'>Delete</button>
			</div>`;
		clientsEl.appendChild(li);
	});

	// log
	logEl.innerHTML = '';
	store.log.slice().reverse().forEach(entry => {
		const li = document.createElement('li');
		li.className = 'log-item';
		li.textContent = `${new Date(entry.time).toLocaleString()}: ${entry.clientName} — ${entry.medication} — ${entry.action}`;
		logEl.appendChild(li);
	});
}

function addClient(obj) {
	store.clients.push(obj);
	scheduleClientNotifications(obj);
	saveStore();
	render();
}

function updateClient(id, patch) {
	const idx = store.clients.findIndex(c => c.id === id);
	if (idx === -1) return;
	// cancel existing scheduled jobs for that client
	cancelScheduledForClient(id);
	store.clients[idx] = { ...store.clients[idx], ...patch };
	scheduleClientNotifications(store.clients[idx]);
	saveStore();
	render();
}

function removeClient(id) {
	cancelScheduledForClient(id);
	store.clients = store.clients.filter(c => c.id !== id);
	saveStore();
	render();
}

function logAction({ clientId, clientName, medication, action }) {
	store.log.push({ clientId, clientName, medication, action, time: Date.now() });
	saveStore();
	render();
}

function scheduleClientNotifications(client) {
	// for each time, compute next occurrence and set timeout
	client.times.forEach(time => {
		const next = nextOccurrenceTodayOrTomorrow(time);
		const ms = next - Date.now();
		const timerId = setTimeout(() => triggerReminder(client, time), ms);
		store.pending.push({ clientId: client.id, time, timerId });
	});
}

function cancelScheduledForClient(clientId) {
	store.pending.filter(p => p.clientId === clientId).forEach(p => clearTimeout(p.timerId));
	store.pending = store.pending.filter(p => p.clientId !== clientId);
}

function nextOccurrenceTodayOrTomorrow(timeStr) {
	const [hh, mm] = timeStr.split(':').map(Number);
	const now = new Date();
	const candidate = new Date(now.getFullYear(), now.getMonth(), now.getDate(), hh, mm, 0, 0);
	if (candidate.getTime() <= Date.now()) candidate.setDate(candidate.getDate() + 1);
	return candidate.getTime();
}

function triggerReminder(client, time) {
	const msg = `${client.name}: ${client.medication} — ${client.dosage || ''}`;
	// show notification
	if (Notification.permission === 'granted') {
		new Notification('Medication due', { body: msg, tag: `${client.id}-${time}` });
	}
	// show in-app modal
	currentReminder = { client, time, escalateId: null };
	reminderMsg.textContent = msg;
	modal.classList.remove('hidden');

	// start escalation after 10 minutes (600k ms): repeat every minute until acknowledged
	const grace = 10 * 60 * 1000;
	const escalate = setTimeout(() => {
		currentReminder.escalateId = setInterval(() => {
			if (Notification.permission === 'granted') new Notification('Medication NOT acknowledged', { body: msg });
		}, 60 * 1000);
	}, grace);
	currentReminder.escalateId = escalate;
}

function acknowledgeReminder(action) {
	if (!currentReminder) return;
	const { client, time } = currentReminder;
	logAction({ clientId: client.id, clientName: client.name, medication: client.medication, action });
	// clear escalation
	if (currentReminder.escalateId) {
		clearTimeout(currentReminder.escalateId);
		clearInterval(currentReminder.escalateId);
	}
	currentReminder = null;
	modal.classList.add('hidden');
}

// wire modal buttons
takenBtn.addEventListener('click', () => acknowledgeReminder('Taken'));
missedBtn.addEventListener('click', () => acknowledgeReminder('Missed'));

// form handling
addTime.addEventListener('click', () => {
	if (!doseTime.value) return;
	const li = document.createElement('li');
	li.textContent = doseTime.value;
	const btn = document.createElement('button'); btn.textContent = 'Remove'; btn.className = 'muted';
	btn.addEventListener('click', () => li.remove());
	li.appendChild(btn);
	timesList.appendChild(li);
	doseTime.value = '';
});

clientForm.addEventListener('submit', (e) => {
	e.preventDefault();
	const times = Array.from(timesList.children).map(li => li.firstChild.textContent.trim());
	if (!clientName.value || !medication.value || times.length === 0) {
		alert('Please provide name, medication, and at least one time.');
		return;
	}
	const id = clientId.value || `c_${Date.now()}`;
	const obj = { id, name: clientName.value.trim(), medication: medication.value.trim(), dosage: dosage.value.trim(), times };
	if (clientId.value) updateClient(id, obj); else addClient(obj);
	// reset
	clientForm.reset(); timesList.innerHTML = '';
});

document.getElementById('clearForm').addEventListener('click', () => { clientForm.reset(); timesList.innerHTML = ''; clientId.value = ''; });

// clients list click handlers (edit/delete)
clientsEl.addEventListener('click', (e) => {
	const edit = e.target.closest('[data-edit]');
	const del = e.target.closest('[data-delete]');
	if (edit) {
		const id = edit.getAttribute('data-edit');
		const c = store.clients.find(x => x.id === id);
		if (!c) return;
		clientId.value = c.id; clientName.value = c.name; medication.value = c.medication; dosage.value = c.dosage;
		timesList.innerHTML = '';
		c.times.forEach(t => { const li = document.createElement('li'); li.textContent = t; const btn = document.createElement('button'); btn.textContent='Remove'; btn.className='muted'; btn.addEventListener('click',()=>li.remove()); li.appendChild(btn); timesList.appendChild(li); });
	}
	if (del) {
		const id = del.getAttribute('data-delete');
		if (confirm('Delete client?')) removeClient(id);
	}
});

// initialize
loadStore();
requestNotificationPermission();
// schedule existing clients
store.clients.forEach(c => scheduleClientNotifications(c));
render();

