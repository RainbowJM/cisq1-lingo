# Vulnerability Analysis

## Injection (A1:2017)

### Description

Injection is when someone sends code disguised as data input to the backend application. The most common form is SQL
injection. SQL statements are sent as data input, as forms of data to the backend. The API controllers expect data such
as a ID, if the attacker sends a malicious SQL query instead of form data, this input will be executed on the database.
This can lead to unwanted situations in the application.

### Risk

Attacks can happen when:

* The data submitted by the user is not validated, filtered, or sanitized by the application.
* Non-parameterized input without context-aware are used directly in the interpreter.
* Hostile data is used within the search parameters to extract additional, sensitive records.

### Counter-measures

There are ways to prevent this from happening. To prevent injection requires keeping data separate from commands and
queries.

* Send as little user input as possible to an interpreter or storage runtime
* Allow user input only via parameterized
    - Note: Even when parameterized, stored procedures can still introduce SQL injection
* Use positive or “whitelist” server-side input validation.
* Use escape special character

## Using Components with known Vulnerabilities (A9:2017)

### Description

Hackers often have knowledge about common vulnerabilities within systems and websites. If a system does not monitor
these types of vulnerabilities, a hacker may use this vulnerability for malicious purposes. Such vulnerabilities include
bugs or other errors in both the written source code and underlying libraries and architectures used by the application.

### Risk

An application is likely vulnerable:

* If the versions of all components used in the application is unknown.
    - Note: This includes components you directly use as well as nested dependencies.
* If you do not scan for vulnerabilities regularly and subscribe to security bulletins related to the components you
  use.
* If you do not fix or upgrade the underlying platform, frameworks, and dependencies in a risk-based, timely fashion.

### Counter-measures

How to prevent it:

* Remove unused dependencies, unnecessary features, components, files, and documentation.
* Continuously checks of the versions of the components (e.g. frameworks, libraries) and their dependencies
* Only obtain components from official sources over secure links.
* Monitor for libraries and components that are unmaintained or do not create security patches for older versions.

## Insufficient Logging & Monitoring (A10:2017)

### Description

Failure to log and monitor user's actions can lead to successful attacks in the application. Attackers rely on the lack
of monitoring and timely response to achieve their goals without being detected.

### Risk

Insufficient logging, detection, monitoring can lead to:

* Late discovering of attackers
* Information leakage
* Malware that enters the code unnoticed

### Counter-measures

How to prevent it:

* Ensure all login
    - Access control failures
* Validate server-side input
* Ensure that logs are generated in a format that can be easily consumed by a centralized log management solutions.
* Effective monitoring and alerting such that suspicious activities are detected and responded to in a timely fashion.
