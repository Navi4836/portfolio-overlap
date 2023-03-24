# What is Portfolio Overlap?
Portfolio Overlap program can be used by investors to check **Overlap** of given new fund with their existing funds in portfolio.

Program should take as input:
1. Name of current funds the user holds in his/her portfolio.
2. New funds to be added.
3. Stock to be added for existing fund.

The output should be
1. Overlapping stocks of the new fund with the current funds.
2. If a given fund name is not present while calculating overlap or adding stocks to existing fund, the output should be FUND_NOT_FOUND

Refer to details of **Coding Challenge** [here](https://codu.ai/coding-problem/portfolio-overlap)

**Example**

Input
```
CURRENT_PORTFOLIO UTI_NIFTY_INDEX AXIS_MIDCAP PARAG_PARIKH_FLEXI_CAP --> Command to Set Current Portfolio
CALCULATE_OVERLAP ICICI_PRU_NIFTY_NEXT_50_INDEX --> Command to calculate overlap
ADD_STOCK PARAG_PARIKH_FLEXI_CAP NOCIL --> Command to add stock to existing fund
```
Output
```

ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%
ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.81%
ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.41%
SBI_LARGE_&_MIDCAP AXIS_MIDCAP 9.09%
SBI_LARGE_&_MIDCAP PARAG_PARIKH_FLEXI_CAP 2.41%
ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%
ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.68%
ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.32%
FUND_NOT_FOUND
ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%
ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.68%
ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.23%

```

# How to Calculate Overlap

Overlap between funds A and B can be calculated using below formula

`Overlap (A,B) = 2 * (No of common stocks in A & B) / (No of stocks in A + No of stocks in B) * 100`

# Prerequisites
* Java 1.8
* Gradle 5.1

# How to run the code

* `./gradlew clean build -x test` - This will create a jar file `geektrust.jar` in the `build/libs` folder.
* `java -jar build/libs/geektrust.jar ./input/sample_input.txt` - This will execute the jar file by passing sample input file path as the command line argument

# How to execute the unit tests

`gradle clean test` will execute the unit test cases.

Another test commit - 1