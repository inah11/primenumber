# primenumber
This generates the list of n prime number using Trial Division and Miller Rabin algorithm

# how to use
* This is a maven based spring boot application. It can be accessed using http://localhost:8080/primes
* http://localhost:8080/primes?until=0 will give a blank screen but in the developer's console the http status 400 Bad Request can be seen

# assumptions
* There are no performance requirements
* At present the system works only with integer values
* A bit of basic caching mechanism is used (very vanilla). Which warms up as we start using the application

# code coverage
At present the project has 96% line coverage. The only thing I have skipped is the Application class

# further improvements
* Enhancing the application to able to calculate primes of greater magnitude
* There is no threshold and max value what the application support
* Exception handling


