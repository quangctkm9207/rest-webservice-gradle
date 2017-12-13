## REST webservice with Gradle
A simple example associated with [my blog post](http://emo-pass.com/2017/12/12/setting-up-java-rest-webservice-with-gradle/).

### Run
```bash
$ ./gradlew appRun
```

### Test API
* Get all notes
    - Method: `GET`
    - URL: `localhost:8080/api/notes`
    - Response body data type: Plain Text
* Add new note
    - Method: `POST`
    - URL: `localhost:8080/api/notes`
    - Request body data type: Plain Text
    - Response body data type: Plain Text