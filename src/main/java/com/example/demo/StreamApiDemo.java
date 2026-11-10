package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamApiDemo {

	public static void main(String[] args) {
		List<Employee> empList = getEmployeeList();

		// Q1 : how many male & female employee in organization?
		method1(empList);
		System.out.println("\n");

		// Q2: print name of all department of organization?
		method2(empList);
		System.out.println("\n");

		// Q3: average age of male and female employee?
		method3(empList);
		System.out.println("\n");

		// Q4: get employee who is having max salary?
		method4(empList);
		System.out.println("\n");

		// Q5: get names of all employees who joined after 2015?
		method5(empList);
		System.out.println("\n");

		// Q6: count the number of employee in each department?
		method6(empList);
		System.out.println("\n");

		// Q7: what is average salary for each department?
		method7(empList);
		System.out.println("\n");

		// Q8: get a male employee detail who has minimum age in IT department
		method8(empList);
		System.out.println("\n");

		// Q9: most experience employee in organization?
		method9(empList);
		System.out.println("\n");

		// Q10: how many male & female emp in IT team?
		method10(empList);
		System.out.println("\n");

		// Q11: average salary of male and female employee?
		method11(empList);
		System.out.println("\n");

		// Q12: get all employee name departmentwise
		method12(empList);
		System.out.println("\n");

		// Q13: what is average salary and total salary of whole organization?
		method13(empList);
		System.out.println("\n");

		// Q14: separate employee who are younger or equal to 25 year & older than 25
		// years?
		method14(empList);
		System.out.println("\n");

		// Q15: oldest employee in organization? what his age and what department he
		// belongs to?
		method15(empList);
		System.out.println("\n");

		// Q16: find employee who is having max employee name length
		method16(empList);
		System.out.println("\n");

		// Q17: convert list of emp to Map , id as key & name as value
		method17(empList);
		System.out.println("\n");

		// Q18: get count of characters in given string
		method18(empList);
		System.out.println("\n");

		// Q19: find employee with same name. //find duplicate scenario.
		method19(empList);
		System.out.println("\n");

		// Q20: find first non repeat element
		method20();
		System.out.println("\n");

		// Q21: find second highest salary of employee
		method21(empList);
		System.out.println("\n");

		// Q22: find numbers starts with 1 from given list.
		method22();
		System.out.println("\n");

		// Q23 : return list of with elements have only letters
		method23();
		System.out.println("\n");

		// Q24 : remove duplicates and sort them in descending order
		method24();
		System.out.println("\n");

		// Q25 : find all odd numbers and square them
		method25();
		System.out.println("\n");

		// Q26 : find longest string in a list
		method26();
		System.out.println("\n");

		// Q27 : find total salary per department
		method27(empList);
		System.out.println("\n");

		// Q28 : from list of employee, return employee name comma separated
		method28(empList);
		System.out.println("\n");

		// Q29 : given two lists, find common elements between them
		method29();
		System.out.println("\n");

		// Q30 : given list of lists, convert it into single list and remove duplicates
		method30();
		System.out.println("\n");

		// Q31 : use parallel processing to print employee name, and order of names
		// doen't matter.
		method31(empList);
		System.out.println("\n");

		// Q32 : use parallel processing to calculate total salary of all the employees
		method32(empList);
		System.out.println("\n");

		// Q33 : find top three frequent words
		method33();
		System.out.println("\n");

		// Q34 : find most common first letter among all the employee
		method34(empList);
		System.out.println("\n");

		// Q35 : Given list of integers, compute average of every 3-element sliding
		// window
		method35();
		System.out.println("\n");

		// Q36 : find the longest word in the sentence ignoring case and punctuation
		method36();
		System.out.println("\n");

		// Q37 : reverse each word of sentence
		method37();
		System.out.println("\n");

		// Q38 : find the highest department who is having max salary for employee in
		// total
		method38(empList);
		System.out.println("\n");

		// Q39 : categories employees based on their salary as HIGH, MEDIUM, LOW
		method39(empList);
		System.out.println("\n");

		// Q40 : find employee who is working in more than 1 department
		method40(empList);
		System.out.println("\n");

		// Q41 : find bigraph frequencies in paragraph. (bigraph means : pairs of
		// consecutive words)
		method41();
		System.out.println("\n");
		// preDefinedFunctionalInterface();

	}

	private static void method41() {
		System.out.println("Q41 : find bigraph frequencies in paragraph. (bigraph means : pairs of consecutive words)");
		String s = "Java is great, java is fun and java is powerful!";
		System.out.println("input "+s);
		
		String[] words = s.toLowerCase().replaceAll("[^a-z\\s]", "").split(" ");
		
		List<String> list=
		IntStream.range(0, words.length -1).mapToObj(i -> words[i]+" "+words[i+1]).toList();
		
		Map<String,Long> output=
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(Arrays.toString(words));
		System.out.println(list);
		System.out.println("output "+output);

	}

	private static void method40(List<Employee> empList) {
		System.out.println("Q40 : find employee who is working in more than 1 department");

//		Map<String, List<Employee>> map = empList.stream().collect(Collectors.groupingBy(Employee::getName));
//
//		List<String> output = map.entrySet().stream().filter(e -> e.getValue().size() > 1).map(Map.Entry::getKey)
//				.toList();
		
		Map<String, Set<String>> deptsByName = empList.stream()
				.collect(Collectors.groupingBy(Employee::getName,
						Collectors.mapping(Employee::getDepartment, Collectors.toSet())));

		List<String> output = deptsByName.entrySet().stream()
				.filter(e -> e.getValue().size() > 1)
				.map(Map.Entry::getKey)
				.toList();

		System.out.println(output);

	}

	private static void method39(List<Employee> empList) {

		System.out.println("Q39 : categories employees based on their salary as HIGH, MEDIUM, LOW");

		Map<String, List<Employee>> map = empList.stream().collect(Collectors.groupingBy(e -> {
			if (e.getSalary() < 30000) {
				return "LOW";
			}
			if (e.getSalary() <= 45000) {
				return "MEDIUM";
			} else {
				return "HIGH";
			}
		}));

		System.out.println("output : " + map);
	}

	private static void method38(List<Employee> empList) {
		System.out.println("Q38 : find the highest department who is having max salary for employee in total");

		Map<String, Double> map = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));

		Map.Entry<String, Double> output = map.entrySet().stream().max(Map.Entry.comparingByValue()).get();

		System.out.println(output);

	}

	private static void method37() {
		System.out.println("Q37 : reverse each word of sentence");
		String input = "java streams are powerful";
		System.out.println("input " + input);

		String output = Arrays.stream(input.split(" ")).map(word -> new StringBuilder(word).reverse().toString())
				.collect(Collectors.joining(" "));

		System.out.println("output " + output);

	}

	private static void method36() {
		System.out.println("Q36 : find the longest word in the sentence ignoring case and punctuation");
		String input = "The quick, brown fox jumped over the lazy dog !";
		System.out.println("input :" + input);

		String[] strArr = input.toLowerCase().replaceAll("[^a-z\\s]", "").split(" ");

		String output = Arrays.stream(strArr).max(Comparator.comparingInt(String::length)).orElse(null);
		System.out.println("output : " + output);

	}

	private static void method35() {
		System.out.println("Q35 : Given list of integers, compute average of every 3-element sliding window");

		List<Integer> list = List.of(4, 8, 15, 16, 23, 43);
		System.out.println("input : " + list);

		List<Double> output = IntStream.range(0, list.size() - 2)
				.mapToObj(i -> ((list.get(i) + list.get(i + 1) + list.get(i + 2)) / 3.0)).toList();
		System.out.println("output : " + output);

		// make this logic generic based on window size .mapToObj(i -> (list.get(i) +
		// list.get(i + 1) + list.get(i + 2)/3.0)
		int window = 3;

		List<Double> output1 = IntStream.range(0, ((list.size() - (window - 1))))
				.mapToObj(i -> list.subList(i, i + window))
				.map(w -> w.stream().mapToInt(Integer::intValue).average().orElse(0.0)).toList();

		System.out.println("output1 using generic window size approach : " + output1);

	}

	private static void method34(List<Employee> empList) {
		System.out.println("Q34 : find most common first letter among all the employee");

		Map<Character, Long> map = empList.stream().map(s -> s.getName().charAt(0))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map.Entry<Character, Long> res = map.entrySet().stream().max(Map.Entry.comparingByValue()).get();

		System.out.println("frequency : " + map);
		System.out.println("output : " + res);

	}

	private static void method33() {
		System.out.println("Q33 : find top three top 3 frequent words");

		String s = "java is great. java is object oriented and java streams are powerful, and java is fun";
		String[] strArr = s.toLowerCase().replaceAll("[^a-z\\s]", "").split(" ");
		
		List<Map.Entry<String, Long>> map = Arrays.stream(strArr)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.limit(3)
				.toList();

		System.out.println(map);

	}

	private static void method32(List<Employee> empList) {
		System.out.println("Q32 : use parallel processing to calculate total salary of all the employees");

		Optional<Double> output = empList.parallelStream().map(Employee::getSalary).reduce((a, b) -> a + b);

		if (output.isPresent()) {
			System.out.println("output : " + output);
		} else {
			System.out.println("No result found");
		}

	}

	private static void method31(List<Employee> empList) {
		System.out.println("Q31 : use parallel processing to print employee name, and order of names doen't matter.");

		empList.parallelStream().forEach(emp -> {
			System.out.println(emp.getName() + " : " + Thread.currentThread().getName());
		});

		// this will give exception
//		List<Integer>list2 = new ArrayList<>();
//		IntStream.range(1, 10000).parallel().forEach(ele -> list2.add(ele));

	}

	private static void method30() {
		System.out.println("Q30 : given list of lists, convert it into single list and remove duplicates");
		List<List<Integer>> list = Arrays.asList(List.of(1, 2, 3, 4), List.of(3, 4, 5, 6), List.of(7, 8, 1, 2),
				List.of(7, 11, 12, 8));

		List<Integer> output = list.stream().flatMap(List::stream).distinct().toList();
		System.out.println("output :" + output);

	}

	private static void method29() {
		System.out.println("Q29 : given two lists, find common elements between them");

		List<Integer> list1 = List.of(1, 3, 5, 7, 9);
		List<Integer> list2 = List.of(11, 13, 17, 7, 9, 15);

		List<Integer> output = list1.stream().filter(s -> list2.contains(s)).toList();
		System.out.println("output :" + output);

		// Optimize solution use Set
		Set<Integer> set1 = new HashSet<>(list1);
		List<Integer> output2 = list2.stream().filter(s -> set1.contains(s)).toList();
		System.out.println("optimize using set output2 :" + output2);

	}

	private static void method28(List<Employee> empList) {
		System.out.println("Q28 : from list of employee, return employee name comma separated");

		String output = empList.stream().map(Employee::getName).collect(Collectors.joining(","));

		System.out.println("output :" + output);

	}

	private static void method27(List<Employee> empList) {
		System.out.println("Q27 : find total salary per department");

		Map<String, Double> output = empList.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.summingDouble(e -> e.getSalary())));

		System.out.println("output :" + output);

	}

	private static void method26() {
		System.out.println("Q26 : find longest string in a list");

		List<String> list = List.of("java", "angular", "springboot", "kafka", "databases");
		System.out.println("Input : " + list);

		Optional<String> output = list.stream().max(Comparator.comparing(e -> e.length()));

		if (output.isPresent()) {
			System.out.println("output :" + output.get());
		} else {
			System.out.println("output : no data found");
		}

	}

	private static void method25() {
		System.out.println("Q25 : find all odd numbers and square them");

		List<Integer> list = List.of(1, 2, 3, 4, 5);
		System.out.println("Input : " + list);

		List<Integer> output = list.stream().filter(s -> s % 2 != 0).map(s -> s * s).toList();

		System.out.println("output :" + output);

	}

	private static void method24() {
		System.out.println("Q24 : remove duplicates and sort them in descending order");

		List<Integer> list = List.of(5, 3, 1, 3, 2, 5, 4);
		System.out.println("Input : " + list);

		List<Integer> output = list.stream().distinct().sorted(Comparator.reverseOrder()).toList();

		System.out.println("output :" + output);

	}

	private static void method23() {
		System.out.println("Q23 : return list of with elements have only letters");

		String[] str = { "13623", "hasjajs", "sdfh1232", "shjdf%^$" };
		System.out.println("input " + Arrays.toString(str));

		List<String> strList = Arrays.stream(str).filter(s -> s.chars().allMatch(Character::isLetter)).toList();

		System.out.println("output " + strList);

	}

	private static void method22() {
		System.out.println("Q22: find numbers starts with 1 from given list.");
		int[] nos = { 1, 4, 7, 14, 17, 19, 32, 41, 60 };

		List<String> startsWithOne = Arrays.stream(nos).mapToObj(s -> String.valueOf(s))
				.filter(ele -> ele.startsWith("1")).toList();

		System.out.println("startsWithOne : " + startsWithOne);
	}

	// Q21: find second highest salary of employee
	private static void method21(List<Employee> empList) {
		System.out.println("Q21: find second highest salary");

		Double secondHightestSalary = empList.stream().map(Employee::getSalary).sorted(Comparator.reverseOrder())
				.skip(1).findFirst().get();

		System.out.println("sencondHightestSalary : " + secondHightestSalary);
		System.out.println("\n");

		System.out.println("Q21.1: find second highest salary employee details");
		Employee sencondHightestSalaryEmp = empList.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().get();

		System.out.println("sencondHightestSalaryEmp : " + sencondHightestSalaryEmp);
	}

	// Q20: find first non repeat element
	private static void method20() {
		System.out.println("Q20: find first non repeat element");
		String str = "thhe greatest of all time is Leo Messi";
		
		String cleaned = str.replaceAll("\\s", "").toLowerCase();
		
		String firstNonRepeat = Arrays.asList(cleaned.split("")).stream()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(s -> s.getValue() == 1).findFirst().get().getKey();
		System.out.println("firstNonRepeat : " + firstNonRepeat);
		System.out.println("\n");

		System.out.println("Q20.1: find first repeat element");
		String firstRepeat = Arrays.asList(cleaned.split("")).stream()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(s -> s.getValue() > 1).findFirst().get().getKey();
		System.out.println("firstRepeat : " + firstRepeat);

		// LinkedHashMap::new, Collectors.counting())) ->
		// by default it will use hashmap internally, so to have insertion order, use
		// LinkedHashMap
	}

	// Q19: find employee with same name. //find duplicate scenario.
	private static void method19(List<Employee> empList) {
		System.out.println("Q19: find employee with same name. //find duplicate scenario.");

		List<String> duplicateWithNames = empList.stream()
				.collect(Collectors.groupingBy(Employee::getName, Collectors.counting())).entrySet().stream()
				.filter(s -> s.getValue() > 1).map(s -> s.getKey()).collect(Collectors.toList());

		System.out.println("duplicateWithNames : " + duplicateWithNames);

	}

	// Q18: get count of characters in given string
	private static void method18(List<Employee> empList) {
		System.out.println("Q18: get count of characters in given string");
		String abc = "hello optimus prime here !!!";
		List<Character> b = abc.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Map<Character, Long> m = b.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		System.out.println(m);

		// 2nd solution
		Map<String, Long> grpBy = Arrays.stream(abc.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("grpBy : " + grpBy);

	}

	// Q17: convert list of emp to Map , id as key & name as value
	private static void method17(List<Employee> empList) {
		System.out.println("Q17: convert list of emp to Map , id as key & name as value");
		Map<Integer, String> m = empList.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
		System.out.println(m);

	}

	// Q16: find employee who is having max employee name length
	private static void method16(List<Employee> empList) {
		System.out.println("Q16: find employee who is having max employee name length");
		
		Employee emp = empList.stream()
				.collect(Collectors.maxBy(Comparator.comparingInt(e -> e.getName().length())))
				.orElse(null);
		System.out.println("max by employee name length : " + emp);
		System.out.println("\n");

		Employee emp1 = empList.stream()
				.reduce((emp1a, emp2a) -> emp1a.getName().length() >= emp2a.getName().length() ? emp1a : emp2a)
				.orElse(null);
		System.out.println("max by employee name length using reduce method :  " + emp1);

	}

	// Q15: oldest employee in organization? what his age and what department he
	// belongs to?
	private static void method15(List<Employee> empList) {
		System.out.println("Q15: oldest employee in organization? what his age and what department he belongs to?");
		Employee oldestByAge = empList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getAge)))
				.orElse(null);
		System.out.println("oldest emp name : " + oldestByAge.getName() + " department " + oldestByAge.getDepartment());

		Employee oldestByAge1 = empList.stream().max(Comparator.comparing(Employee::getAge)).orElse(null);

	}

	// Q14: separate employee who are younger or equal to 25 year & older than 25
	// years?
	private static void method14(List<Employee> empList) {
		System.out.println("Q14: separate employee who are younger or equal to 25 year & older than 25 years?");
		Map<Boolean, List<Employee>> partitioningByAge25 = empList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 25));

		partitioningByAge25.entrySet().forEach(data -> {
			if (data.getKey()) {
				System.out.println("older than 25 years");
				data.getValue().stream()
						.forEach(emp -> System.out.println("empName : " + emp.getName() + "emp age : " + emp.getAge()));
				System.out.println("");
			} else {
				System.out.println("25 years or younger than 25 years");
				data.getValue().stream()
						.forEach(emp -> System.out.println("empName : " + emp.getName() + "emp age : " + emp.getAge()));
				System.out.println("");
			}
		});

	}

	// Q13: what is average salary and total salary of whole organization?
	private static void method13(List<Employee> empList) {
		System.out.println("//Q13: what is average salary and total salary of whole organization?");
		DoubleSummaryStatistics summary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("average salary " + summary.getAverage());
		System.out.println("sum salary " + summary.getSum());
		System.out.println("min salary " + summary.getMin());
		System.out.println("max salary " + summary.getMax());

	}

	// Q12: get all employee name departmentwise
	private static void method12(List<Employee> empList) {
		System.out.println("//Q12: get all employee name departmentwise ?");

		Map<String, List<Employee>> empByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		empByDepartment.entrySet().forEach(data -> {
			String departmentName = data.getKey();
			List<Employee> empListgrpBy = data.getValue();
			System.out.println("departmentName : " + departmentName);
			empListgrpBy
					.forEach(empDeparmentwise -> System.out.println("employee name : " + empDeparmentwise.getName()));
			System.out.println("");
		});

	}

	// Q11: average salary of male and female employee?
	private static void method11(List<Employee> empList) {
		System.out.println("Q11: average salary of male and female employee?");

		Map<String, Double> genderWiseAvgSalary = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(" genderWiseAvgSalary " + genderWiseAvgSalary);

	}

	// Q10: how many male & female emp in IT team?
	private static void method10(List<Employee> empList) {
		System.out.println("Q10: how many male & female emp in IT team?");
		Map<String, Long> itAndHrGenderWiseHeadCount = empList.stream()
				.filter(s -> "IT".equalsIgnoreCase(s.getDepartment()))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println("itAndHrGenderWiseHeadCount " + itAndHrGenderWiseHeadCount);

	}

	// Q9: most experience employee in organization?
	private static void method9(List<Employee> empList) {
		System.out.println("Q9: most experience employee in organization?");

		// using min.
		Employee mostExperienceEmp = empList.stream().min(Comparator.comparingInt(Employee::getYearJoining))
				.orElse(null);
		System.out.println("mostExperienceEmp using min " + mostExperienceEmp);

		// using sorting
		Employee mostExperienceEmployee = empList.stream().sorted(Comparator.comparingInt(Employee::getYearJoining))
				.findFirst().orElse(null);
		System.out.println("mostExperienceEmp sorted " + mostExperienceEmployee);

	}

	// Q8: get a male employee who has minimum age in IT department
	private static void method8(List<Employee> empList) {
		System.out.println("Q8: get a male employee detail who has minimum age in IT department");
		Employee youngestEmployee = empList.stream()
				.filter(s -> "male".equalsIgnoreCase(s.getGender()) && "IT".equalsIgnoreCase(s.getDepartment()))
				.min(Comparator.comparingInt(Employee::getAge)).orElse(null);
		System.out.println("youngestEmployee :" + youngestEmployee);

	}

	// Q7: what is average salary for each department?
	private static void method7(List<Employee> empList) {
		System.out.println("Q7: what is average salary for each department?");
		Map<String, Double> avgSalaryPerDepartment = empList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("avgSalaryPerDepartment : " + avgSalaryPerDepartment);

	}

	// Q6: count the number of employee in each department?
	private static void method6(List<Employee> empList) {
		System.out.println("Q6: count the number of employee in each department?");
		Map<String, Long> headCountByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("headCountByDepartment " + headCountByDepartment);

	}

	// Q5: get names of all employees who joined after 2015?
	private static void method5(List<Employee> empList) {
		System.out.println("Q5: get names of all employees who joined after 2015?");
		empList.stream().filter(s -> s.getYearJoining() > 2015).map(Employee::getName).forEach(System.out::println);

	}

	// Q4: get employee who is having max salary?
	private static void method4(List<Employee> empList) {
		System.out.println("Q4: get employee who is having max salary?");
		String highestpaidEmpName = empList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).map(Employee::getName)
				.orElse(null);
		System.out.println("highestpaidEmpName " + highestpaidEmpName);

		Employee highestpaidEmp = empList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)))
				.orElse(null);
		System.out.println("highestpaidEmp " + highestpaidEmp);

	}

	// "Q3: average age of male and female employee?"
	private static void method3(List<Employee> empList) {
		System.out.println("Q3: average age of male and female employee?");
		Map<String, Double> averageAgeByGender = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println("averageAgeByGender : " + averageAgeByGender);

	}

	// Q2 : print name of all department of organization?
	private static void method2(List<Employee> empList) {
		System.out.println("Q2 : print name of all department of organization?");
		empList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

	}

	// Q1 : how many male & female employee in organization?
	private static void method1(List<Employee> empList) {
		System.out.println("Q1 : how many male & female employee in organization?");
		Map<String, Long> countByGender = empList.stream()
				.collect(Collectors.groupingBy(s -> s.getGender(), Collectors.counting()));

		System.out.println("countByGender " + countByGender);

	}

	private static void preDefinedFunctionalInterface() {
		System.out.println("#### preDefinedFunctionalInterface #### ");
		System.out.println("predicate will take some input perform some conditional check and return boolean ");
		// predicate will take some input perform some conditional check and return
		// boolean
		System.out.println("Predicate to check if even value : test value is 2 ");
		Predicate<Integer> checkIfEven = i -> i % 2 == 0;
		if (checkIfEven.test(2)) {
			System.out.println(" input is even");
		} else {
			System.out.println(" input is odd");
		}

		// take some input, perform some operation and return result in any type then
		// use function.
		System.out.println(
				"Function :: take some input, perform some operation and return result in any type then use function. ");
		Function<Integer, Integer> squareIt = i -> i * i;
		System.out.println("Function to find square. input is 5 ");
		System.out.println("squareIt " + squareIt.apply(5));

		// Consumer take some input & no return or void return then use consumer
		Consumer<String> printValue = i -> System.out.println("printed " + i);
		printValue.accept("hello");

		// Supplier don't accept any input only return output
		Supplier<Date> currentDate = () -> new Date();
		System.out.println("date " + currentDate.get());

	}

	private static List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(111, "John", 26, "Male", 2018, 25000.0, "IT"));
		empList.add(new Employee(112, "Sarah", 23, "Female", 2016, 22000.0, "HR"));
		empList.add(new Employee(113, "Emily", 27, "Female", 2014, 42000.0, "IT"));
		empList.add(new Employee(114, "Michael", 22, "Male", 2020, 21000.0, "Admin"));
		empList.add(new Employee(115, "David", 24, "Male", 2015, 24000.0, "HR"));
		empList.add(new Employee(116, "Sophia", 26, "Female", 2017, 29000.0, "Account & finance"));
		empList.add(new Employee(117, "Robert", 32, "Male", 2011, 55000.0, "Account & finance"));
		empList.add(new Employee(118, "mae", 23, "Female", 2016, 22000.0, "Admin"));
		empList.add(new Employee(119, "ish", 27, "Female", 2014, 42000.0, "IT"));
		empList.add(new Employee(120, "James", 35, "Male", 2020, 38000.0, "HR"));
		empList.add(new Employee(121, "William", 40, "Male", 2015, 34000.0, "Account & finance"));
		empList.add(new Employee(122, "Olivia", 34, "Female", 2017, 41000.0, "IT"));
		empList.add(new Employee(123, "Sarah", 29, "Female", 2017, 44000.0, "IT"));
		empList.add(new Employee(124, "Sophia", 31, "Female", 2017, 46000.0, "Admin"));
		return empList;
	}

}

class Employee {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private Integer yearJoining;
	private double salary;
	private String department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getYearJoining() {
		return yearJoining;
	}

	public void setYearJoining(Integer yearJoining) {
		this.yearJoining = yearJoining;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Employee(Integer id, String name, Integer age, String gender, Integer yearJoining, double salary,
			String department) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.yearJoining = yearJoining;
		this.salary = salary;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", yearJoining="
				+ yearJoining + ", salary=" + salary + ", department=" + department + "]";
	}

}
