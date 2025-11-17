# Supplemental 4: Integration Testing ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/supplemental_4))

## Overview

This lesson explores integration testing—verifying that multiple components of your application work together correctly, going beyond unit tests to catch real-world issues.

## Topics Covered

### Understanding Integration Tests
- Difference between unit tests and integration tests
- Why integration tests are essential
- Common bugs that only integration tests catch

### Types of Integration Tests
- **API Testing**: Testing HTTP endpoints and responses
- **Database Testing**: Verifying data persistence and queries
- **UI Testing**: Testing user interactions in browsers
- **End-to-End Testing**: Testing complete user workflows

### Integration Testing Tools
- **Backend**: SuperTest, REST Assured, Postman
- **Database**: TestContainers, in-memory databases
- **Frontend**: Playwright, Cypress, Selenium
- **Mocking**: WireMock, Mock Service Worker (MSW)

### Best Practices
- Test realistic scenarios
- Use test databases and environments
- Balance speed vs. coverage
- Implement continuous integration (CI)

## Additional Resources

### Testing Frameworks
- [Playwright](https://playwright.dev/) - Modern browser automation
- [Cypress](https://www.cypress.io/) - JavaScript end-to-end testing
- [Jest](https://jestjs.io/) - JavaScript testing framework
- [JUnit](https://junit.org/) - Java testing framework
- [SuperTest](https://github.com/ladjs/supertest) - HTTP assertion library

### Learning Resources
- [Testing JavaScript (Article)](https://testingjavascript.com/)
- [Martin Fowler - Integration Testing](https://martinfowler.com/bliki/IntegrationTest.html)
- [The Practical Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html)
- [Playwright Tutorial (Video)](https://www.youtube.com/watch?v=Xz6lhEzgI5I)

### Tools
- [TestContainers](https://testcontainers.com/) - Docker containers for testing
- [Postman](https://www.postman.com/) - API testing and documentation
- [Docker](https://www.docker.com/) - Containerization for test environments
