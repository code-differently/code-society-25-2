## User Story - Daily Medicine Reminder App 

## User Story ##
As a Medical Care Giver, I want a medicine reminder app to send me notifications when medicines are due for my clients so that I can ensure timely administration and avoid missed doses. Also so that my clients maintain their medication schedules even when I'm not physically present, improving their treatment adherence and health outcomes.

As a Medical Care Giver, I want to receive alerts when clients miss their medications
So that I can follow up immediately and ensure they take missed doses safely.

As a Medical Care Giver, I want to customize notification messages for each client
So that the reminders are personalized and include any specific instructions relevant to their condition.

## Acceptance Criteria
Given I’ve configured a client’s medication schedule, when a dose time arrives, then I receive a push notification on my device.

Given a notification appears, when I tap “Taken or Missed” then the app logs the administration time against that client’s record.

Given a notification isn’t acknowledged within 10 minutes, when the grace period elapses, then the app escalates with a repeating alert until I mark it as taken.

Given I update a client’s dosage or timing, when I save changes, then future notifications reflect the updated schedule.
