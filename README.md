# ✈️ Air Tickets System

A simple system for managing air ticket data. This guide will help you set up the project locally for development and testing.

---

## 🧰 1. Clone the Repository

Open your terminal and run:

```bash
git clone git@github.com:Misha2007/air-tickets-system.git
```

![image](https://github.com/user-attachments/assets/6c8eae47-6b4b-4b21-922e-90a5c72616f5)


---

## 💻 Frontend Setup

### 2. Install Dependencies

Navigate to the frontend directory (if necessary) and install the required packages:

```bash
npm install
```

![image](https://github.com/user-attachments/assets/73830c9e-9806-4328-9ddd-8ecbed3de674)

_This will download all the necessary modules to get the project running._

### 3. Run Frontend

For local development:

```bash
npx vite
```

![image](https://github.com/user-attachments/assets/1a94d574-7269-45f8-b5d4-f39c602e7ab0)


To access it from another device on the same network:

```bash
npx vite --host
```

![image](https://github.com/user-attachments/assets/233ad9d2-bbf7-475c-961e-add25ecdc682)

---

## 🔧 Backend Setup

### 4. Create a MySQL Database

Log in to MySQL and create a new database:

```sql
create database andmebaasi_nimi;
```

Replace `andmebaasi_nimi` with your desired database name.

---

### 5. Create `resources` Folder and `application.properties` File

Navigate to the backend folder:

```bash
cd air-tickets-system-backend
```

![image](https://github.com/user-attachments/assets/c457df4e-61cd-4b33-9b37-e48097eb86fe)


_If not already present, create a folder named `resources` and a file `application.properties` inside it._

---

### 6. Configure `application.properties`

Add the following content to the `application.properties` file:

```properties
spring.application.name=air-tickets-system
server.port=8090

spring.datasource.url=jdbc:mysql://IP:PORT/ANDMEBAASI_NIMI
spring.datasource.username=ANDMEBAASI_KASUTAJANIMI
spring.datasource.password=ANDMEBAASI_PAROOL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

**Replace the placeholders:**

* `IP` → typically `localhost`
* `PORT` → usually `3306`
* `ANDMEBAASI_NIMI` → your database name
* `ANDMEBAASI_KASUTAJANIMI` → usually `root`
* `ANDMEBAASI_PAROOL` → your MySQL password

---

### 7. Run Backend

In the backend directory, start the Spring Boot application:

```bash
mvn spring-boot:run
```

![image](https://github.com/user-attachments/assets/01535c2c-90e7-4f91-af13-2123e19a38ff)

---

## 📋 Testing and Data Insertion

### 1. Manual SQL Insert

You can manually add a flight with:

```sql
INSERT INTO flight (
    Id, flightnumber, hind, istmed, lahkumiseaeg, saabumiseaeg,
    saabumiskoht, saabumiskohtcode, sihtkoht, sihtkohtcode
)
VALUES (
    1, 'QWS140', 32.29, 125, '2025-04-09 09:00:00', '2025-04-09 10:30:00',
    'Tallinn', 'TLL', 'Paris', 'CDG'
);
```

💡 Make sure the `Id` is unique.

---

### 2. View Data

Run:

```sql
SELECT * FROM flight;
```

![image](https://github.com/user-attachments/assets/467a2b28-1e9a-4982-abe0-307426203ef6)


---

### 3. Add Flights via Postman

You can use Postman to send a POST request to your backend:

1. Open Postman.
2. Select the **POST** method.
3. Enter the URL (for example, if the backend is running locally on port 8090):

   ```
   http://localhost:8090/add
   ```
4. Go to the **Body** section → select **x-www-form-urlencoded**.
5. Click **Send**.

![image](https://github.com/user-attachments/assets/3c662ca0-1583-4c6f-bfdb-590f022391d5)


---
