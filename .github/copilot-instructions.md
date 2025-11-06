## Quick context

This is a small educational Java project (Eclipse-style layout) that reads a plain text file of symptoms and produces a simple count/output file. Keep in mind this is a learning repo with intentionally simple code and a few known problems.

## Big-picture architecture

- Language: Java (no build system: plain sources under `src/`).
- Package root: `src/com/hemebiotech/analytics`.
- Key responsibilities:
  - `ISymptomReader` — interface contract: `List<String> GetSymptoms()` (note: the method name uses PascalCase here).
  - `ReadSymptomDataFromFile` — file-based implementation of `ISymptomReader`: reads a file, one symptom per line, returns raw List<String>.
  - `AnalyticsCounter` — contains a `main()` that reads `symptoms.txt` and writes `result.out`. It currently bypasses the `ISymptomReader` abstraction and contains logic bugs (see “Known quirks”).

Data flow: symptoms.txt (one symptom per line, found at `Project02Eclipse/symptoms.txt`) -> reader -> in-memory counts -> `result.out`.

## Where to look first (code examples)

- `src/com/hemebiotech/analytics/ISymptomReader.java` — contract for data sources.
- `src/com/hemebiotech/analytics/ReadSymptomDataFromFile.java` — the intended file reader; use this from `main` instead of reimplementing file I/O.
- `src/com/hemebiotech/analytics/AnalyticsCounter.java` — example `main` and the place students typically edit/debug; currently contains ad-hoc file reading.
- `Project02Eclipse/symptoms.txt` — sample input data used by the app.

## Project-specific conventions and gotchas

- Method naming: `GetSymptoms()` is PascalCase (non-standard for Java). When writing new code, match the existing signature exactly.
- No build system: assume developers import this project into an IDE (Eclipse/IntelliJ) or compile with `javac` from the root.
- Relative paths matter: `AnalyticsCounter` opens "symptoms.txt" with a plain filename (no path). Run the app with current working directory set to `Project02Eclipse/` or update the code to use an absolute/explicit path.
- Minimal error handling: many classes print stack traces instead of throwing structured exceptions.

## Known issues you may want to address (useful for PR suggestions)

- `AnalyticsCounter` inconsistently uses `headCount` vs `headacheCount` (only `headCount` is incremented; `headacheCount` is used when writing output). This causes counts to be zero in output.
- Typo/logic: it checks `else if (line.equals("rush"))` but expected symptom name likely `"rash"` (typo).
- `AnalyticsCounter` duplicates file-reading logic that `ReadSymptomDataFromFile` already implements. Prefer wiring the `ISymptomReader` implementation into `main` and counting via a Map.

## Useful quick commands (PowerShell on Windows)

Compile all source files into `out/` and run `AnalyticsCounter` (set working dir to Project02Eclipse):

```powershell
cd Project02Eclipse
javac -d out src\com\hemebiotech\analytics\*.java
java -cp out com.hemebiotech.analytics.AnalyticsCounter
```

If you add more packages or files, compile everything recursively:

```powershell
cd Project02Eclipse
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

IDE workflow:
- Import as an existing Eclipse project or create a new Java project and add `src/` as the source folder.
- Make sure `Project02Eclipse/` is the working directory so `AnalyticsCounter` can find `symptoms.txt` by its relative filename.

## How to approach changes (practical checklist for an AI/agent)

1. Prefer using `ReadSymptomDataFromFile` through the `ISymptomReader` interface instead of reimplementing file reads in `main`.
2. Normalize symptom strings (trim, lowercase) before counting to avoid duplicates caused by spacing/case.
3. Use a `Map<String,Integer>` to aggregate counts, sort keys alphabetically when writing `result.out`.
4. Fix the bugs in `AnalyticsCounter` (typo `rush` -> `rash`; use the same variable when writing counts).
5. Keep changes minimal and well-scoped; add small unit tests if you add a build system (Maven/Gradle) later.

## Where to add tests or CI

- There are currently no tests or CI configs. If you add a build system, put unit tests under `src/test/java` (JUnit 5 recommended) and add a simple GitHub Actions workflow to run `mvn -DskipTests=false test` or `gradle test`.

## Quick pointers for debugging

- To reproduce behavior locally: run the project from `Project02Eclipse/` so `symptoms.txt` is on the working dir.
- Insert `System.out.println` traces in `ReadSymptomDataFromFile` to confirm file lines; prefer logging only when necessary.
- When changing `GetSymptoms()` signature or behavior, update the interface and all implementations (this project has only one implementation).

---

If you want, I can:
- apply a small refactor that (1) fixes `AnalyticsCounter` bugs, (2) rewrites `main` to use `ReadSymptomDataFromFile`, and (3) add a simple test harness or a tiny Gradle wrapper. Tell me which option you prefer.
