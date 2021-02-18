# Clients database demo
## _Document ID application_

The Clients database demo is a client ID handling application dealing with validity checking of a client by 2 external services and generating PDF from the result

## Features

- Adding a new client introducing the document id informations (all fields required)
- Deleting the client
- List the clients already added to the sistem
- Generate PDF from the client validation information, checking the responses of 2 external endpoints for verifing existance and validity of the new customer
- Download for signing the document
- Upload the document to database for further processing

## Tech

The app uses a number of open source projects to work properly:

- [Swagger] - api doc generator
- [Thymeleaf] - template engine
- [Flying Saucer] - pdf generator
- [jQuery]
- [JavaScript]

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
127.0.0.1:8080
```
External endpoints for validation need to be started at the following addresses.

```sh
127.0.0.1:8081
127.0.0.1:8082
```
## Further improvements

- Rewriting exception handling
- Swagger beautify 
- Upload file size checking
- Logging beatify
    








