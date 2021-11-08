
Weather API : Consumes the Open Weather API using Spring Boot

This Weather API used to obtain the weather information for a location.
All this data is obtained from the [OpenWeather](https://openweathermap.org/api) open source API.

## The application in a nutshell
We use the Spring Boot [RestTemplate] class to make HTTP requests to the [OpenWeather API](https://openweathermap.org/api) for obtaining the weather data. 

Obtained weather data will be stored in the in-memory H2 Database Engine through the Java Persistance API (JPA).
Details will be fetched from db first ,. If not available API call will be made and details will be store in db

## API doc
The API has only a simple **GET Request** method which requires a parameter called **location**.

This parameter can take the data in the following format:`<location>,<country>`

* The **location** has to be the exact name of a city. 

* The **country** has to be the exact name of a country.

The API has been secured with API Key Scheme and rate limited to 5 calls per hour

### Request examples
http://localhost:8080/weather/?location=London,GB
http://localhost:8080/weather/?location=Delhi,IN

### Build and run detail
The application can be run using mvn. However the build jar has been created and saved in the target folder. 
To run it from cmd: GO to folder location
cd target 
java -jar weather-api.jar 
