# Java Stream API Demo

A single-file Java project containing **41 commonly asked Stream API interview questions**, each solved with Java Streams and Collectors. The examples run against a small in-memory `Employee` dataset and a few standalone strings and lists.

## Requirements

- **Java 16 or higher** (uses `Stream.toList()`)
- Any IDE (IntelliJ IDEA, Eclipse, VS Code) or just the JDK on the command line
- No external dependencies

## Project Structure

```
src/main/java/com/example/demo/
└── StreamApiDemo.java    # main class + Employee model class
```

| Component | Description |
|-----------|-------------|
| `StreamApiDemo` | Contains `main()` and one method per question (`method1` to `method41`) |
| `Employee` | Simple POJO: `id`, `name`, `age`, `gender`, `yearJoining`, `salary`, `department` |
| `getEmployeeList()` | Builds the sample dataset of 14 employees across IT, HR, Admin and Account & finance |
| `preDefinedFunctionalInterface()` | Demo of `Predicate`, `Function`, `Consumer`, `Supplier` (commented out in `main`) |

## How to Run

**From an IDE:** open the project and run `StreamApiDemo.main()`.

**From the command line:**

```bash
javac -d out src/main/java/com/example/demo/StreamApiDemo.java
java -cp out com.example.demo.StreamApiDemo
```

Each question prints its title followed by its output.

## Sample Data

| ID | Name | Age | Gender | Joined | Salary | Department |
|----|------|-----|--------|--------|--------|------------|
| 111 | John | 26 | Male | 2018 | 25000 | IT |
| 112 | Sarah | 23 | Female | 2016 | 22000 | HR |
| 113 | Emily | 27 | Female | 2014 | 42000 | IT |
| 114 | Michael | 22 | Male | 2020 | 21000 | Admin |
| 115 | David | 24 | Male | 2015 | 24000 | HR |
| 116 | Sophia | 26 | Female | 2017 | 29000 | Account & finance |
| 117 | Robert | 32 | Male | 2011 | 55000 | Account & finance |
| 118 | mae | 23 | Female | 2016 | 22000 | Admin |
| 119 | ish | 27 | Female | 2014 | 42000 | IT |
| 120 | James | 35 | Male | 2020 | 38000 | HR |
| 121 | William | 40 | Male | 2015 | 34000 | Account & finance |
| 122 | Olivia | 34 | Female | 2017 | 41000 | IT |
| 123 | Sarah | 29 | Female | 2017 | 44000 | IT |
| 124 | Sophia | 31 | Female | 2017 | 46000 | Admin |

## Questions Covered

### Employee-based (grouping, aggregation, filtering)

| # | Question | Key APIs |
|---|----------|----------|
| 1 | Count of male and female employees | `groupingBy`, `counting` |
| 2 | All distinct department names | `map`, `distinct` |
| 3 | Average age by gender | `averagingInt` |
| 4 | Employee with the max salary | `maxBy`, `Comparator.comparing` |
| 5 | Names of employees who joined after 2015 | `filter`, `map` |
| 6 | Headcount per department | `groupingBy`, `counting` |
| 7 | Average salary per department | `averagingDouble` |
| 8 | Youngest male employee in IT | `filter`, `min` |
| 9 | Most experienced employee | `min`, `sorted` |
| 10 | Male/female count in IT | `filter`, `groupingBy` |
| 11 | Average salary by gender | `averagingDouble` |
| 12 | Employee names department-wise | `groupingBy` |
| 13 | Average and total salary of the organization | `summarizingDouble` |
| 14 | Split employees into age ≤ 25 and > 25 | `partitioningBy` |
| 15 | Oldest employee, their age and department | `maxBy` |
| 16 | Employee with the longest name | `maxBy`, `reduce` |
| 17 | Convert list to `Map<id, name>` | `toMap` |
| 19 | Employees with duplicate names | `groupingBy`, `counting`, `filter` |
| 21 | Second highest salary (and employee) | `sorted`, `skip`, `findFirst` |
| 27 | Total salary per department | `summingDouble` |
| 28 | Comma-separated employee names | `joining` |
| 31 | Parallel stream printing of names | `parallelStream`, `forEach` |
| 32 | Parallel total salary | `parallelStream`, `reduce` |
| 34 | Most common first letter of names | `groupingBy`, `max` |
| 38 | Department with the highest total salary | `summingDouble`, `max` |
| 39 | Categorize salaries as HIGH / MEDIUM / LOW | `groupingBy` with lambda classifier |
| 40 | Employees appearing in more than one department | `groupingBy`, `filter` |

### String and number problems

| # | Question | Key APIs |
|---|----------|----------|
| 18 | Character frequency in a string | `chars`, `groupingBy` |
| 20 | First non-repeating and first repeating character | `LinkedHashMap::new`, `counting` |
| 22 | Numbers starting with 1 | `mapToObj`, `startsWith` |
| 23 | Strings containing only letters | `allMatch(Character::isLetter)` |
| 24 | Remove duplicates, sort descending | `distinct`, `sorted(reverseOrder())` |
| 25 | Square all odd numbers | `filter`, `map` |
| 26 | Longest string in a list | `max(Comparator)` |
| 29 | Common elements of two lists (plus `Set` optimization) | `filter`, `HashSet` |
| 30 | Flatten list of lists and remove duplicates | `flatMap`, `distinct` |
| 33 | Top 3 most frequent words | `groupingBy`, `sorted`, `limit` |
| 35 | Moving average with a 3-element window (and generic window size) | `IntStream.range`, `subList`, `average` |
| 36 | Longest word ignoring case and punctuation | `replaceAll`, `max` |
| 37 | Reverse each word in a sentence | `StringBuilder.reverse`, `joining` |
| 41 | Bigram (consecutive word pair) frequencies | `IntStream.range`, `groupingBy` |

## Key Concepts Illustrated

- **Collectors:** `groupingBy`, `partitioningBy`, `counting`, `averagingX`, `summingX`, `summarizingX`, `joining`, `toMap`, `maxBy`
- **Intermediate operations:** `filter`, `map`, `flatMap`, `distinct`, `sorted`, `skip`, `limit`
- **Terminal operations:** `forEach`, `reduce`, `findFirst`, `min`, `max`, `toList`
- **Parallel streams:** using `parallelStream()` safely (a commented-out example shows why adding to a non-thread-safe `ArrayList` from a parallel stream fails)
- **Ordering:** using `LinkedHashMap` to preserve insertion order when grouping
- **Functional interfaces:** `Predicate`, `Function`, `Consumer`, `Supplier`

## Known Issues and Suggested Improvements

A few solutions behave differently than their question implies:

1. **Q39 (salary categories):** the condition `salary > 30000 && salary > 45000` is redundant, so salaries between 30000 and 45000 fall into `HIGH`. Likely intended: `LOW` < 30000, `MEDIUM` 30000 to 45000, `HIGH` > 45000.
2. **Q40 (employee in more than 1 department):** groups by **name**, so it actually finds duplicate names (same as Q19), not employees in multiple departments. The sample data has no employee ID repeated across departments.
3. **Q30 (flatten and deduplicate):** `distinct()` is applied inside each inner list, so duplicates across lists remain. Use `list.stream().flatMap(List::stream).distinct().toList()`.
4. **Q33 (top 3 words):** the `distinct()` call after `sorted()` has no effect; it can be removed.
5. **Q16:** returns the longest **name** rather than the full `Employee` object.
6. **Q20:** operates on a string with spaces, so a space character can be reported as the first repeating character.
7. **Q14:** the output label says "younger than 25" but the partition includes age = 25.
8. **Q29:** `new HashSet(list1)` uses a raw type; use `new HashSet<>(list1)`.
9. **Cosmetic:** variable names such as `sencondHightestSalary` and `iTAndHrGenderWiseHeadCount` contain typos or are misleading. `method10` only handles IT.

## Possible Extensions

- Split `Employee` into its own file and move each question into a separate class or test
- Add JUnit tests asserting expected output for each question
- Replace `double` salary with `BigDecimal` for monetary accuracy
- Add a Maven or Gradle build file

## License

This project is intended for learning and interview preparation. Use and modify it freely.
