# 3.1

### a) Identify a couple of examples that use AssertJ expressive methods chaining.
- ```assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());```
- ```assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());```
### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).
- ```@Mock( lenient = true) private EmployeeRepository employeeRepository;```
### c) What is the difference between standard @Mock and @MockBean?
- ```@Mock``` is used to mock a class that is not a Spring Bean. ```@MockBean``` is used to mock a class that is a Spring Bean.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
- ```application-integrationtest.properties``` is used to override the default properties of the application. It is used when the test is annotated with ```@SpringBootTest```.

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?
- C: Runs fast and light tests and doesn't require a database. It uses ```@WebMvcTest``` to test the controller.
- D & E: Runs slow and heavy tests and requires a database. It uses ```@SpringBootTest``` to test the controller.
- D: Mocks a servlet container
- E: Uses a REST client to test the API
- E: Serialization and deserialization are tested, but not in D
