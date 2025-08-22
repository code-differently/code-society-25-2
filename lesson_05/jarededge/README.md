# User Stories (UTasks)

## User Story 1 — Quick task creation with details
As a busy professional managing daily work,  
I want to quickly add a new task with a title, due date, and label,  
so that I can capture important items  and stay organized.  

### Acceptance Criteria
- **Given** I’m on the Tasks page and logged in  
  **When** I enter a non-empty title (≤200 chars), optionally set a due date (today or future), and/or choose a label  
  **And** I click “Add Task”  
  **Then** the new task appears at the top of my list with status “incomplete”  
- Task **persists** on refresh and re-login  
- Empty title is blocked with an inline error; past due date is rejected with a clear message  
- Keyboard: pressing **Enter** in the title field creates the task  

---

## User Story 2 — Staying focused with filters
As someone juggling many priorities,  
I want to mark tasks complete and filter by All, Active, or Completed,  
so that I can focus only on the work that matters at the moment.  

### Acceptance Criteria
- **Given** a task is visible  
  **When** I toggle its checkbox  
  **Then** its visual style updates (strike-through or dim) and status flips between complete/incomplete  
- **Given** the filter = “Completed”  
  **When** I toggle a completed task back to active  
  **Then** it disappears from the Completed view immediately  
- The task completion count and filter badge update accurately  
- State persists across refresh and sessions  

---

## User Story 3 — Safe editing and deletion
As a user who sometimes makes mistakes,  
I want the ability to edit or delete a task safely,  
so that I can keep my task list accurate without losing information unintentionally.  

### Acceptance Criteria
- **Edit**
  - **Given** a task  
    **When** I click “Edit”  
    **Then** the title becomes an inline input with the current text focused  
  - Blank titles are invalid; cancel restores the original  
  - Saving updates immediately and persists on refresh  
- **Delete**
  - **Given** a task  
    **When** I click “Delete”  
    **Then** I see a confirmation (modal or inline) and can Cancel  
  - After confirming, the task is removed and a 5-second Undo appears; clicking Undo restores the task  

---

### Definition of Done (for all three)
- Stories are complete when the user can successfully perform the described action (create, filter, edit/delete).  
- Works on desktop and mobile without layout breakage  
- Accessible: all controls reachable via keyboard, inputs have labels 
- No console errors; operations persist after page reload  
