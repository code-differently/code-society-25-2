# User Stories

## Hulu

**Story ID:** HULU-001

**Title:** View comments while watching

**Priority:** High

**User Story:**
*As a user who doesn’t watch traditional TV, I want to view comments on the content I watch so that I can see how others feel about it while watching.*

**Acceptance Criteria:**

* Given a playing video, when I open the Comments panel, then comments load without stopping playback.
* Comments show count and sort options (Top, Newest).
* I can post, like, and report a comment; actions update without page reload.
* Scrolling loads more comments (infinite or paginated).
* The panel is keyboard-accessible and screen-reader friendly (ARIA labels, focus states).
* Latency for loading the first 20 comments is ≤ 2s on a typical connection.

---

## TaskBird

**Story ID:** TB-001

**Title:** Coordinate cleanings across locations

**Priority:** High

**User Story:**
*As a cleaning business owner managing multiple locations, I want to coordinate cleanings and store data so that I can easily access the history of completed cleanings.*

**Acceptance Criteria:**

* I can create locations with name, address, and contact.
* I can schedule a cleaning with date/time, assignee, checklist, and location.
* I can mark a cleaning as completed with notes, photos, and timestamp.
* I can filter history by location, assignee, date range, and status.
* Export history to CSV and JSON.
* Role-based access: owners see all locations; staff see assigned locations only.

---

## Marriott Bonvoy

**Story ID:** MB-001

**Title:** Mobile key on landing page

**Priority:** High

**User Story:**
*As a frequent traveler, I want my mobile key displayed on the app’s landing page so that I can quickly access my room without searching through the app.*

**Acceptance Criteria:**

* If an active stay with mobile key exists, the landing page shows a prominent "Open Door" card.
* Biometric/Passcode confirmation occurs before key activation.
* If multiple rooms/keys exist, I can switch rooms from the card.
* The key card shows hotel name, room number (if policy allows), and key status.
* Offline mode: if cached key is valid, the button is available and status indicates offline.
* If no active mobile key exists, the card is hidden and a contextual prompt explains how to enable it.

