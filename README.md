chrome browser:
mvn test -Dui.browser=chrome

Firefox:
mvn test -Dui.browser=firefox

Headlessmode:
mvn test -Dui.headless=true


# Clean build and test
mvn clean test

# Generate reports
mvn verify


# Run with specific profile
mvn test -P api-only
mvn test -P ui-only
