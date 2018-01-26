## REST webservice with Gradle
A simple example associated with [my blog post](http://emo-pass.com/2017/12/12/setting-up-java-rest-webservice-with-gradle/).  

In this project, PostgreSQL database is used to persist data. 
So, make sure to install it and replace your own database credentials in `NoteDatabase.java` file.
That is my mistake to make it more completed. 

### Run
```bash
$ ./gradlew appRun
```

### APIs
* Get all notes
    - Method: `GET`
    - URL: `localhost:8080/api/v1/notes`
    - Response body data type: Plain Text
    - Success code: 200 Ok
* Add new note
    - Method: `POST`
    - URL: `localhost:8080/api/v1/notes`
    - Success code: 201 Created
* Get a note
    - Method: `GET`
    - URL: `localhost:8080/api/v1/notes/{noteId}`
    - Response body data type: Plain Text
    - Success code: 200 Ok
* Update a note
    - Method: `PUT`
    - URL: `localhost:8080/api/v1/notes/{noteId}`
    - Request body data type: Plain Text
    - Response body data type: Plain Text
    - Success code: 200 Ok
* Delete a note
    - Method: `DELETE`
    - URL: `localhost:8080/api/v1/notes/{noteId}`
    - Success code: 204 No Content