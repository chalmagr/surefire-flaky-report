# Surefire Parameterizable Test Issue [SUREFIRE-2065](https://issues.apache.org/jira/projects/SUREFIRE/issues/SUREFIRE-2065)

This project shows the problem that exists when using the Parameterizable
option and the report/flake detection mechanism

There will be two main providers used in this project:

* **org.apache.maven.surefire.junitplatform.JUnitPlatformProvider** (org.apache.maven.surefire:surefire-junit-platform)
* **org.apache.maven.surefire.junit4.JUnit4Provider** (org.apache.maven.surefire:surefire-junit4)

