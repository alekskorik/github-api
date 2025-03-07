# GitHub API Repository Fetcher

This is a **Quarkus** application that fetches **non-forked** GitHub repositories of a given user, along with their branches and the latest commit SHA.

## Features

- Fetches **public repositories** of a user (excluding forks).
- Retrieves **branches** and the latest **commit SHA** for each repository.
- Handles **errors gracefully** with appropriate HTTP responses.
- Uses **Quarkus Rest Client** for making GitHub API calls.
- Provides a **REST API endpoint** for fetching repositories.

## Technologies Used

- **Java 17+**
- **Quarkus 3.7.2**
- **RESTEasy Reactive (REST API)**
- **Quarkus Rest Client**
- **Maven (for dependency management)**

---

## Project Structure

```
src
 ├── main
 │   ├── java
 │   │   ├── org.example
 │   │   │   ├── client
 │   │   │   │   ├── GithubClient.java
 │   │   │   ├── exception
 │   │   │   │   ├── ExceptionHandler.java
 │   │   │   ├── model
 │   │   │   │   ├── Branch.java
 │   │   │   │   ├── GithubRepository.java
 │   │   │   ├── resource
 │   │   │   │   ├── GithubResource.java
 │   │   │   ├── service
 │   │   │   │   ├── GithubService.java
 │   │   │   ├── Main.java
 │   ├── resources
 │   │   ├── application.properties
```

---

## API Endpoints

### **1⃣ Get GitHub Repositories (Non-Forked)**
**URL:**
```http
GET /api/github/repos/{username}
```

**Example Request:**
```http
GET http://localhost:8080/api/github/repos/alekskorik
```

**Example Response:**
```json
[
  {
    "name": "2324L_UWP_03_s25301",
    "owner": {
      "login": "alekskorik"
    },
    "branches": [
      {
        "name": "main",
        "commit": {
          "sha": "210f5b851a54e49199619b5ac3187b9cb4c2ce3c"
        }
      },
      {
        "name": "s25301",
        "commit": {
          "sha": "e22dc16b9b592d808d9c713ba476aa74a825ca5c"
        }
      }
    ]
  },
  {
    "name": "byt7-8",
    "owner": {
      "login": "alekskorik"
    },
    "branches": [
      {
        "name": "main",
        "commit": {
          "sha": "762f26d3158c543db41c91f07f7f05669506f243"
        }
      }
    ]
  }
]
```

### **2⃣ Handling Non-Existing Users**
**Example Request:**
```http
GET http://localhost:8080/api/github/repos/invalid-user
```

**Response:**
```json
{
  "status": 404,
  "message": "User not found"
}
```

---

## How to Run


### **1. Build and Run the Application**
```sh
mvn clean install
mvn quarkus:dev
```

### **2. Access the API**
Visit: `http://localhost:8080/api/github/repos/{username}`

---

## Testing
Run the following command to execute tests:
```sh
mvn test
```

---


