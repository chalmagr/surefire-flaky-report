# Surefire Parameterizable Test Issue

This project shows the problem that exists when using the Parameterizable
option and the report/flake detection mechanism

There will be two main providers used in this project:

* **org.apache.maven.surefire.junitplatform.JUnitPlatformProvider** (org.apache.maven.surefire:surefire-junit-platform)
* **org.apache.maven.surefire.junit4.JUnit4Provider** (org.apache.maven.surefire:surefire-junit4)

## Executions (without rerun failed tests)

### JUnit Platform

```bash
$ mvn test
...
12:58:51,376 [INFO] Running org.example.UtilityTest
12:58:51,389 [ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.016 s <<< FAILURE! - in org.example.UtilityTest
12:58:51,390 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0.005 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:58:51,390 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:58:51,403 [INFO]
12:58:51,404 [INFO] Results:
12:58:51,404 [INFO]
12:58:51,404 [ERROR] Failures:
12:58:51,404 [ERROR] org.example.UtilityTest.testHashMod5
12:58:51,404 [ERROR]   Run 1: UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:58:51,404 [INFO]   Run 2: PASS
12:58:51,405 [ERROR]   Run 3: UtilityTest.testHashMod5:42 Failed to validate input PRR
12:58:51,405 [INFO]   Run 4: PASS
12:58:51,405 [INFO]   Run 5: PASS
12:58:51,405 [INFO]
12:58:51,405 [INFO]
12:58:51,405 [ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
12:58:51,406 [INFO]
12:58:51,420 [INFO] ------------------------------------------------------------------------
12:58:51,420 [INFO] BUILD FAILURE
12:58:51,420 [INFO] ------------------------------------------------------------------------
...
```

### JUnit4

```bash
$ mvn -P junit4 test
...
12:58:54,555 [INFO] Running org.example.UtilityTest
12:58:54,603 [ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.041 s <<< FAILURE! - in org.example.UtilityTest
12:58:54,604 [ERROR] org.example.UtilityTest.testHashMod5[0: testHashMod5YLQ]  Time elapsed: 0.004 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:58:54,604 [ERROR] org.example.UtilityTest.testHashMod5[2: testHashMod5PRR]  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:58:54,617 [INFO]
12:58:54,617 [INFO] Results:
12:58:54,617 [INFO]
12:58:54,617 [ERROR] Failures:
12:58:54,617 [ERROR]   UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:58:54,617 [ERROR]   UtilityTest.testHashMod5:42 Failed to validate input PRR
12:58:54,617 [INFO]
12:58:54,618 [ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
12:58:54,618 [INFO]
12:58:54,625 [INFO] ------------------------------------------------------------------------
12:58:54,626 [INFO] BUILD FAILURE
12:58:54,626 [INFO] ------------------------------------------------------------------------
...
```

In both cases the build fails, but the reported tests differ quite a bit

## Executions (with rerun failed tests)

### JUnit Platform

```bash
$ mvn -Dsurefire.rerunFailingTestsCount=1 test
...
12:59:02,888 [INFO] Running org.example.UtilityTest
12:59:02,901 [ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.015 s <<< FAILURE! - in org.example.UtilityTest
12:59:02,901 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0.005 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:02,902 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:02,903 [INFO] Running org.example.UtilityTest
12:59:02,911 [ERROR] Tests run: 2, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.001 s <<< FAILURE! - in org.example.UtilityTest
12:59:02,912 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:02,912 [ERROR] org.example.UtilityTest.testHashMod5  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:02,936 [INFO]
12:59:02,936 [INFO] Results:
12:59:02,936 [INFO]
12:59:02,936 [WARNING] Flakes:
12:59:02,936 [WARNING] org.example.UtilityTest.testHashMod5
12:59:02,936 [ERROR]   Run 1: UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:59:02,936 [INFO]   Run 2: PASS
12:59:02,936 [ERROR]   Run 3: UtilityTest.testHashMod5:42 Failed to validate input PRR
12:59:02,937 [INFO]   Run 4: PASS
12:59:02,937 [INFO]   Run 5: PASS
12:59:02,937 [ERROR]   Run 6: UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:59:02,937 [ERROR]   Run 7: UtilityTest.testHashMod5:42 Failed to validate input PRR
12:59:02,937 [INFO]
12:59:02,937 [INFO]
12:59:02,938 [WARNING] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Flakes: 1
12:59:02,938 [INFO]
12:59:02,945 [INFO] ------------------------------------------------------------------------
12:59:02,949 [INFO] BUILD SUCCESS
12:59:02,949 [INFO] ------------------------------------------------------------------------
...
```

### JUnit4

```bash
$ mvn -P junit4 -Dsurefire.rerunFailingTestsCount=1 test
...
12:59:07,215 [INFO] Running org.example.UtilityTest
12:59:07,273 [ERROR] Tests run: 7, Failures: 4, Errors: 0, Skipped: 0, Time elapsed: 0.051 s <<< FAILURE! - in org.example.UtilityTest
12:59:07,274 [ERROR] org.example.UtilityTest.testHashMod5[0: testHashMod5YLQ]  Time elapsed: 0.004 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:07,274 [ERROR] org.example.UtilityTest.testHashMod5[2: testHashMod5PRR]  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:07,275 [ERROR] org.example.UtilityTest.testHashMod5[0: testHashMod5YLQ]  Time elapsed: 0.001 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input YLQ
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:07,275 [ERROR] org.example.UtilityTest.testHashMod5[2: testHashMod5PRR]  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError: Failed to validate input PRR
	at org.example.UtilityTest.testHashMod5(UtilityTest.java:42)

12:59:07,287 [INFO]
12:59:07,287 [INFO] Results:
12:59:07,287 [INFO]
12:59:07,287 [ERROR] Failures:
12:59:07,301 [ERROR] org.example.UtilityTest.testHashMod5[0: testHashMod5YLQ]
12:59:07,302 [ERROR]   Run 1: UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:59:07,302 [ERROR]   Run 2: UtilityTest.testHashMod5:42 Failed to validate input YLQ
12:59:07,303 [INFO]
12:59:07,303 [ERROR] org.example.UtilityTest.testHashMod5[2: testHashMod5PRR]
12:59:07,303 [ERROR]   Run 1: UtilityTest.testHashMod5:42 Failed to validate input PRR
12:59:07,304 [ERROR]   Run 2: UtilityTest.testHashMod5:42 Failed to validate input PRR
12:59:07,304 [INFO]
12:59:07,304 [INFO]
12:59:07,304 [ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
12:59:07,305 [INFO]
12:59:07,313 [INFO] ------------------------------------------------------------------------
12:59:07,314 [INFO] BUILD FAILURE
12:59:07,314 [INFO] ------------------------------------------------------------------------
...
```

In this case the platform solution is returning SUCCESS in the build but
the JUnit4 is making it fail.