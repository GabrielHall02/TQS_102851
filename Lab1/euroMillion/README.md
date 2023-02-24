## Jacoco Report
```mvn test jacoco:report```

### Results

<img src="https://github.com/GabrielHall02/TQS_102851/blob/main/Lab1/euroMillion/Screenshot%202023-02-17%20at%2017.26.05.png">

<img src="https://github.com/GabrielHall02/TQS_102851/blob/main/Lab1/euroMillion/Screenshot%202023-02-17%20at%2017.41.43.png">

<img src="https://github.com/GabrielHall02/TQS_102851/blob/main/Lab1/euroMillion/Screenshot%202023-02-17%20at%2017.41.55.png">


### Which classes/methods offer less coverage? Are all possible [decision] branches being covered?

tqs.sets has low coverage (54% coverage on missed instrucions and 50% on missed branches).\
Also CuponEuromillions has only 34% coverage on missed instructions.

BoundedSetOfNaturals has 54% coverage on missed instructions and 50% on missed branches.


### What kind of unit test are worth writing for proper validation of BoundedSetOfNaturals?

Besides what is already being tested, we could test the following:
- Test if the set accepts duplicates (array and singular number)
- Test if size is correct
- Test intersection of sets