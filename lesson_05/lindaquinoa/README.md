## Lesson 05 - User Stories

### Story 1: Wishlist Streamlining
**As a** Amazon user

**I want** items on my wishlists automatically cross referenced

**So that** there are no repeat items in any queue.

**Acceptance Criteria:**
- When a user adds an item to a wishlist, the system checks across all existing wishlists for duplicates.  
- If a duplicate item is found, the user receives a prompt with options to keep it in both lists or only one.  
- The system prevents identical items from being added twice to the same wishlist.  
- Users can manually review and merge duplicate items across wishlists through a “Manage Duplicates” option.  

### Story 2: Payment Failsafe
**As a** CashApp user

**I want** a one minute window to cancel or recall a payment

**So that** there are no payments sent in error or to the wrong recipient.

**Acceptance Criteria:**
- Countdown timer (60 seconds) populates indicating the cancellation window after sending a payment.
- “Cancel Payment” button within that window available to stop the transaction.
- Notification sent to user upon successful cancellation.
- After 60 seconds pass, the “Cancel Payment” option disappears and the transaction is final.

### Story 3: Document Review and Edits
**As a** Google Docs user

**I want** to leave comments and corrections in the margins and between lines

**So that** I can provide feedback and edits without altering the document

**Acceptance Criteria:**
- Users can highlight text or place the cursor between lines and insert a comment or suggested correction.  
- Comments appear visually distinct in the right-hand margin or inline suggestion mode, without changing the original text.  
- Other collaborators can reply to, resolve, or accept/reject suggested corrections.  
- Resolved comments and corrections are archived but still accessible in the document history.