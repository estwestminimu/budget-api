REST API do zarządzania budżetem osobistym. Aplikacja ma umożliwiać śledzenie przychodów i wydatków przypisanych do kont.


### Wymagania
- Java 21
- Maven 
- PostgreSQL 14+
- Docker i Docker Compose

### Technologie
- Java 21
- Spring Boot
- PostgreSQL 

## Uruchamianie lokalne
### 1. Zainstauj PostgreSQL i utwórz bazę
```sql
CREATE DATABASE budgetdb;
CREATE USER budgetuser WITH PASSWORD 'budgetpass';
GRANT ALL PRIVILEGES ON DATABASE budgetdb TO budgetuser;
```

### 2. Uruchomienie aplikacji 
```bash
./mvnw spring-boot:run
```


### 3. Uruchomienie przez Docker
```bash
docker compose up --build
```


## Dokumentacja API
Po uruchomieniu aplikacji dokumentacja dostępna pod adresem:
http://localhost:8080/swagger-ui.html

## Testy

```bash
./mvnw test
```
## Endpointy
### Konta

| Metoda | URL | Opis |
|--------|-----|------|
| GET | /accounts | Lista wszystkich kont |
| POST | /accounts | Utwórz nowe konto |
| GET | /accounts/{id} | Szczegóły konta |
| DELETE | /accounts/{id} | Usuń konto (tylko bez transakcji) |

### Transakcje

| Metoda | URL | Opis |
|--------|-----|------|
| GET | /accounts/{id}/transactions | Lista transakcji |
| GET | /accounts/{id}/transactions?from=2024-01-01&to=2024-12-31 | Filtrowanie po dacie |
| GET | /accounts/{id}/transactions?category=Jedzenie | Filtrowanie po kategorii |
| POST | /accounts/{id}/transactions | Dodaj transakcję |
| DELETE | /accounts/{id}/transactions/{tid} | Usuń transakcję |
| GET | /accounts/{id}/transactions/export | Eksport do CSV |

### Podsumowanie

| Metoda | URL | Opis |
|--------|-----|------|
| GET | /accounts/{id}/summary | Przychody, wydatki, wydatki per kategoria |

## Przykłady zapytań

### Utwórz konto

```json
POST /accounts
{
    "name": "Konto główne"
}
```

### Dodaj transakcję

```json
POST /accounts/1/transactions
{
    "amount": 3000.00,
    "type": "INCOME",
    "category": "Wynagrodzenie",
    "description": "Pensja czerwiec",
    "date": "2024-06-01"
}
```

### Odpowiedź z podsumowania

```json
GET /accounts/1/summary
{
    "totalIncome": 3000.00,
    "totalExpense": 500.00,
    "expensesByCategory": {
        "Jedzenie": 300.00,
        "Transport": 200.00
    }
}
```

## Kody HTTP

| Kod | Opis |
|-----|------|
| 200 | OK |
| 201 | Zasób utworzony |
| 400 | Błędne dane wejściowe |
| 404 | Zasób nie istnieje |
| 409 | Konflikt (np. usunięcie konta z transakcjami) |


## AI
AI wykorzystane przy tworzeniu tabel w readme.md oraz w kodzie przy nazywaniu zmiennych w celu większej klarowności w czytaniu. Naprawianie błędów przy konfiguracji.