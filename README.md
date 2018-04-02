# FR_AUTO_TEST_2017.rev02

Automated booking up to a declined payment On

## Languages

Java(Maven, JUnit) - This is the language I have more experience and it is the platform I am working right now.
I worked also with Ruby with Watir, Parallel, Capybara,
Jasmine and Chai, and I would be very happy to jump and learn Ruby on Rails and RSpec.
I am always happy to learn and grow my skills.
I also like Cucumber and BDD as a human and closer to the business language.(Gherkin syntax)

### Prerequisites

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Last version used
* [Maven](https://maven.apache.org/) - Dependency Management

# Instructions

Clone the repo:

Git:
```
$ git clone git@github.com:guoper59/ryanair.git
```

## Running the tests

    mvn test -Dcucumber.options="--tags @declined"

## Notes

Don't interact with the browser during tests executions


## Reporting

*Generated Reports

    ./target/index.html


## Built With

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Last version used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Cucumber](https://github.com/cucumber/cucumber-jvm) - Used to BDD

## Authors

* **Alfonso Fuertes Fuentes** - *Initial work* - [guoper59](https://github.com/guoper59)


