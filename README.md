# Gym Membership System

## How to run

Requirements: Java 21+

```bash
cd gym-system
.\mvnw.cmd spring-boot:run
```

Application runs on `http://localhost:8080`

## REST API

### 1. Create a gym
```bash
curl -X POST http://localhost:8080/api/gyms \
  -H "Content-Type: application/json" \
  -d '{"name": "FitLife Center", "address": "ul. Sportowa 1, Warszawa", "phoneNumber": "+48 100 200 300"}'
```

### 2. List all gyms
```bash
curl http://localhost:8080/api/gyms
```

### 3. Create a membership plan for a gym
```bash
curl -X POST http://localhost:8080/api/gyms/1/membership-plans \
  -H "Content-Type: application/json" \
  -d '{"name": "Premium Plan", "planType": "PREMIUM", "monthlyPrice": 199.99, "currency": "EUR", "durationInMonths": 12, "maxMembers": 10}'
```

### 4. List all membership plans for a gym
```bash
curl http://localhost:8080/api/gyms/1/membership-plans
```

### 5. Register a member to a membership plan
```bash
curl -X POST http://localhost:8080/api/plans/1/members \
  -H "Content-Type: application/json" \
  -d '{"firstName": "Jan", "lastName": "Kowalski", "email": "jan@example.com"}'
```

### 6. List all members (includes plan name, gym name and status)
```bash
curl http://localhost:8080/api/members
```

### 7. Cancel a membership
```bash
curl -X PUT http://localhost:8080/api/members/1/cancel
```

### 8. Revenue report – total monthly revenue per gym grouped by currency 
```bash
curl http://localhost:8080/api/gyms/revenue-report
```
