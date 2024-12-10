# Apollo system

Apollo is a vehicle tracking system. 

### Features

![image](https://github.com/user-attachments/assets/fffea45b-3ee4-43e9-a456-8f921ef248a4)

For the version pre-alpha we were able to implement:
- Tracking notifications
- Vehicle tracker
- Almost the whole non-functional requirements package except scaling.

## Architecture

Apollo uses an event-driven approach with a event broker topology here is the sequence diagram of the topics and events.
![image](https://github.com/user-attachments/assets/1a6e2a7d-62c6-474b-a6c9-d83eede1d2a6)

### Level 2
![image](https://github.com/user-attachments/assets/857d52da-7d77-49df-ac98-43e08edd1ccb)
### Level 3
![image](https://github.com/user-attachments/assets/8572777e-50de-4889-b38a-46e9638de438)

### Level 4
1. Apollo server
![image](https://github.com/user-attachments/assets/a7df539c-f2b2-40a0-b40f-495f54008f10)

2. Apollo client
![image](https://github.com/user-attachments/assets/5f4627cb-2c71-4c56-becd-0b3675fe74a7)
![image](https://github.com/user-attachments/assets/ef1f0d25-2001-413b-a991-50d6e0f42b87)
