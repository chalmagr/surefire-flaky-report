# Surefire Parameterizable Test Issue [SUREFIRE-2065](https://issues.apache.org/jira/projects/SUREFIRE/issues/SUREFIRE-2065)

This project shows the problem that exists when using the Parameterizable
option and the report/flake detection mechanism

There will be two main providers used in this project:

* **org.apache.maven.surefire.junitplatform.JUnitPlatformProvider** (org.apache.maven.surefire:surefire-junit-platform)
* **org.apache.maven.surefire.junit4.JUnit4Provider** (org.apache.maven.surefire:surefire-junit4)

## The Test

There are simple tests used to validate the validity. In all cases two inputs are given as parameters. 
One always passes the other one always fails. This introduces the 'flaky' issue when it really is not flaky.

### JUnitPlatformProvider

#### rerunFailingTestsCount=0
Running junit4 tests without re-run failed tests show below failures

```bash
$ mvn -Dsurefire.rerunFailingTestsCount=0 test
...
[ERROR] Failures:
[ERROR] WithDisplayNameTest.nonFlaky
[ERROR]   Run 1: Failed to validate input YLQ
[INFO]   Run 2: PASS
[INFO]
[ERROR] WithoutDisplayNameTest.nonFlaky
[ERROR]   Run 1: Failed to validate input YLQ
[INFO]   Run 2: PASS
[INFO]
[ERROR]   RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[INFO]
[ERROR] Tests run: 4, Failures: 3, Errors: 0, Skipped: 0
...
```

In this case it shows 4 tests run with 3 failures, but it is expected to have 6 tests run with 3 failures (each test class has 1 passing and 1 failing)

#### rerunFailingTestsCount=1

When running with rerunFailingTestsCount=1 the results can be seen below

```bash
$ mvn -Dsurefire.rerunFailingTestsCount=1 test
...
[ERROR] Failures:
[ERROR] org.example.junit4.RegularTest.nonFlakyYLQ
[ERROR]   Run 1: RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[ERROR]   Run 2: RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[INFO]
[WARNING] Flakes:
[WARNING] WithDisplayNameTest.nonFlaky
[ERROR]   Run 1: Failed to validate input YLQ
[INFO]   Run 2: PASS
[ERROR]   Run 3: Failed to validate input YLQ
[INFO]
[WARNING] WithoutDisplayNameTest.nonFlaky
[ERROR]   Run 1: Failed to validate input YLQ
[INFO]   Run 2: PASS
[ERROR]   Run 3: Failed to validate input YLQ
[INFO]
[INFO]
[ERROR] Tests run: 4, Failures: 1, Errors: 0, Skipped: 0, Flakes: 2
...
```

Again, total tests run shows as 4 when expected is 6. But now the parameterized failures show as flakes (if non-parameterized tests were not failing the run would succeed with flakes)

### JUnit4Provider

When using this provider results are as expected

#### rerunFailingTestsCount=0

```bash
$ mvn -Pjunit4 -Dsurefire.rerunFailingTestsCount=0 test
...
[ERROR] Failures:
[ERROR]   RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[ERROR]   WithDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[ERROR]   WithoutDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[INFO]
[ERROR] Tests run: 6, Failures: 3, Errors: 0, Skipped: 0
...
```

#### rerunFailingTestsCount=1

```bash
$ mvn -Pjunit4 -Dsurefire.rerunFailingTestsCount=1 test
...
[ERROR] Failures:
[ERROR] org.example.junit4.RegularTest.nonFlakyYLQ
[ERROR]   Run 1: RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[ERROR]   Run 2: RegularTest.nonFlakyYLQ:13 Failed to validate input YLQ
[INFO]
[ERROR] org.example.junit4.parameterized.WithDisplayNameTest.nonFlaky[displayNameYLQ]
[ERROR]   Run 1: WithDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[ERROR]   Run 2: WithDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[INFO]
[ERROR] org.example.junit4.parameterized.WithoutDisplayNameTest.nonFlaky[0]
[ERROR]   Run 1: WithoutDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[ERROR]   Run 2: WithoutDisplayNameTest.nonFlaky:41 Failed to validate input YLQ
[INFO]
[INFO]
[ERROR] Tests run: 6, Failures: 3, Errors: 0, Skipped: 0
...
```

## Other tests

### junit5

Additional tests available for junit5 to run both at the same time (for validation purposes, not shown on readme file)

### 'Bogus' display name

Having a constant name as a display name for a test is not a good idea, and issues happen when this is the case on junit4. 
Not shown in here as it was for 'fun' to see what it would happen.
