# trade-position-book-system

### Sample POST /tradeEvent API request
```
{
    "eventId": 22,
    "eventType": "BUY",
    "accountNumber": "ACC1",
    "securityName": "SEC1",
    "quantity": 50
}
```

### Sample GET /tradeEvents API request
```
[
    {
        "eventId": 21,
        "accountNumber": "ACC1",
        "securityName": "SEC1",
        "eventType": "BUY",
        "quantity": 50
    },
    {
        "eventId": 21,
        "accountNumber": "ACC1",
        "securityName": "SEC1",
        "eventType": "CANCEL",
        "quantity": 50
    },
    {
        "eventId": 22,
        "accountNumber": "ACC1",
        "securityName": "SEC1",
        "eventType": "BUY",
        "quantity": 100
    }
]
```
