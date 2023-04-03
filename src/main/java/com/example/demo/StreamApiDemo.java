package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.Date;

public class StreamApiDemo {

	public static void main(String[] args) {
		List<Employee> empList = getEmployeeList();
		
		//Q1 : how many male & female employee in organization?
		method1(empList);
		System.out.println("\n");
		
		//Q2: print name of all department of organization?
		method2(empList);
		System.out.println("\n");

		
		//Q3: average age of male and female employee?
		method3(empList);
		System.out.println("\n");

		
		//Q4: get employee who is having max salary?
		method4(empList);
		System.out.println("\n");
		
		
		//Q5: get names of all employees who joined after 2015?
		method5(empList);
		System.out.println("\n");
		
		
		//Q6: count the number of employee in each department?
		method6(empList);
		System.out.println("\n");
		
		//Q7: what is average salary for each department?
		method7(empList);
		System.out.println("\n");
		
		//Q8: get a male employee detail who has minimum age in IT department
		method8(empList);
		System.out.println("\n");
		
		//Q9: most experience employee in organization?
		method9(empList);
		System.out.println("\n");
		
		//Q10: how many male & female emp in IT team?
		method10(empList);
		System.out.println("\n");
		
		//Q11: average salary of male and female employee?
		method11(empList);
		System.out.println("\n");
		
		//Q12: get all employee name departmentwise
		method12(empList);
		System.out.println("\n");
		
		//Q13: what is average salary and total salary of whole organization?
		method13(empList);
		System.out.println("\n");
		
		//Q14: separate employee who are younger or equal to 25 year & older than 25 years?
		method14(empList);
		System.out.println("\n");
		
		//Q15: oldest employee in organization? what his age and what department he belongs to?
		method15(empList);
		System.out.println("\n");

		//Q16: find employee who is having max employee name length
		method16(empList);
		System.out.println("\n");

		//Q17: convert list of emp to Map , id as key & name as value
		method17(empList);
		System.out.println("\n");

		//Q18: get count of characters in given string
		method18(empList);
		System.out.println("\n");
		
		//Q19: find employee with same name. //find duplicate scenario.
		method19(empList);
		System.out.println("\n");
		
		//Q20: find first non repeat element
		method20();
		System.out.println("\n");
		
		//Q21: find second highest salary of employee
		method21(empList);
		System.out.println("\n");
		
		//Q22: find numbers starts with 1 from given list.
		method22();
		System.out.println("\n");
		
		//preDefinedFunctionalInterface();

	}

	private static void method22() {
		System.out.println("Q22: find numbers starts with 1 from given list.");
		int[] nos = {1,4,7,14,17,19,32,41,60};
		
		List<String> startsWithOne = Arrays.stream(nos).boxed()
				.map(String::valueOf).filter(s->s.startsWith("1"))
				.collect(Collectors.toList());
		
		System.out.println("startsWithOne : "+startsWithOne);
	}

	//Q21: find second highest salary of employee
	private static void method21(List<Employee> empList) {
		System.out.println("Q21: find second highest salary");
		
		Double sencondHightestSalary=empList.stream()
				.map(Employee::getSalary)
				.sorted(Comparator.reverseOrder())
				.skip(1).findFirst().get();
		
		System.out.println("sencondHightestSalary : "+sencondHightestSalary);
		System.out.println("\n");
		
		System.out.println("Q21.1: find second highest salary employee details");
		Employee sencondHightestSalaryEmp=empList.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.skip(1).findFirst().get();
		
		System.out.println("sencondHightestSalaryEmp : "+sencondHightestSalaryEmp);
	}

	//Q20: find first non repeat element
	private static void method20() {
		System.out.println("Q20: find first non repeat element");
		String str = "thhe greatest of all time is Leo Messi";
		String firstNonRepeat=Arrays.asList(str.split(""))
				.stream().collect(Collectors.groupingBy(
				Function.identity(),LinkedHashMap::new,Collectors.counting()))//by default internally will use hashMap, so for insertion order used linkedHashMap
				.entrySet().stream().filter(s->s.getValue()==1)
				.findFirst().get().getKey();
		System.out.println("firstNonRepeat : "+firstNonRepeat);
		System.out.println("\n");
		
		System.out.println("Q20.1: find first repeat element");
		String firstRepeat=Arrays.asList(str.split(""))
				.stream().collect(Collectors.groupingBy(
				Function.identity(),LinkedHashMap::new,Collectors.counting()))//by default internally will use hashMap, so for insertion order used linkedHashMap
				.entrySet().stream().filter(s->s.getValue()>1)
				.findFirst().get().getKey();
		System.out.println("firstRepeat : "+firstRepeat);
	}

	//Q19: find employee with same name. //find duplicate scenario.
	private static void method19(List<Employee> empList) {
		System.out.println("Q19: find employee with same name. //find duplicate scenario.");
		
		List<String> duplicateWithNames =  empList.stream().collect(
				Collectors.groupingBy(Employee::getName,Collectors.counting())).entrySet().stream()
				.filter(s->s.getValue()>1).map(s->s.getKey()).collect(Collectors.toList());
		
		System.out.println("duplicateWithNames : "+duplicateWithNames);
		
	}

	//Q18: get count of characters in given string
	private static void method18(List<Employee> empList) {
		System.out.println("Q18: get count of characters in given string");
		String abc = "hello optimus prime here !!!";
		List<Character>b= abc.chars().mapToObj(c->(char)c).collect(Collectors.toList());		
		Map<Character,Long>m=b.stream().collect(Collectors.groupingBy(c->c,Collectors.counting()));
		System.out.println(m);
		
		//2nd solution
		Map<String,Long>grpBy=
		Arrays.stream(abc.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println("grpBy : "+grpBy);
		
	}

	//Q17: convert list of emp to Map , id as key & name as value
	private static void method17(List<Employee> empList) {
		System.out.println("Q17: convert list of emp to Map , id as key & name as value");
		Map<Integer,String>m=
		empList.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
		System.out.println(m);
		
	}

	//Q16: find employee who is having max employee name length
	private static void method16(List<Employee> empList) {
		System.out.println("Q16: find employee who is having max employee name length");
		String name=
		empList.stream().map(Employee::getName).collect(Collectors.maxBy(Comparator.comparing(s->s.length()))).orElse(null);
		System.out.println("max by employee name length : " +name);
		System.out.println("\n");
		
		String name1=
		empList.stream().map(Employee::getName).reduce((emp1,emp2)->emp1.length()>emp2.length()?emp1:emp2).get();
		System.out.println("max by employee name length using reduce method :  " +name1);
		
	}
	
	//Q15: oldest employee in organization? what his age and what department he belongs to?
	private static void method15(List<Employee> empList) {
		System.out.println("Q15: oldest employee in organization? what his age and what department he belongs to?");
		Employee oldestByAge=empList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getAge))).orElse(null);
		System.out.println("oldest emp name : "+oldestByAge.getName() + " department "+oldestByAge.getDepartment());
		
		Employee oldestByAge1=empList.stream().max(Comparator.comparing(Employee::getAge)).orElse(null);
		
	}

	//Q14: separate employee who are younger or equal to 25 year & older than 25 years?
	private static void method14(List<Employee> empList) {
		System.out.println("Q14: separate employee who are younger or equal to 25 year & older than 25 years?");	
		Map<Boolean,List<Employee>> partitioningByAge25=
		empList.stream().collect(Collectors.partitioningBy(e->e.getAge() > 25));
		
		partitioningByAge25.entrySet().forEach(data->{
			if(data.getKey()) {
				System.out.println("older than 25 years");
				data.getValue()
				.stream()
				.forEach(emp->System.out.println("empName : "+emp.getName() + "emp age : "+emp.getAge()));
				System.out.println("");
			}else {
				System.out.println("younger than 25 years");
				data.getValue()
				.stream()
				.forEach(emp->System.out.println("empName : "+emp.getName() + "emp age : "+emp.getAge()));
				System.out.println("");
			}
		});
		
	}

	//Q13: what is average salary and total salary of whole organization?
	private static void method13(List<Employee> empList) {
		System.out.println("//Q13: what is average salary and total salary of whole organization?");		
		DoubleSummaryStatistics summary=
				empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));		
		System.out.println("average salary "+summary.getAverage());
		System.out.println("sum salary "+summary.getSum());
		System.out.println("min salary "+summary.getMin());
		System.out.println("max salary "+summary.getMax());
		
	}

	//Q12: get all employee name departmentwise
	private static void method12(List<Employee> empList) {
		System.out.println("//Q12: get all employee name departmentwise ?");
		
		Map<String,List<Employee>> empByDepartment = empList.stream()
						.collect(Collectors.groupingBy(Employee::getDepartment));
		
		empByDepartment.entrySet().forEach(data->{
			String departmentName = data.getKey();
			List<Employee> empListgrpBy = data.getValue();
			System.out.println("departmentName : "+departmentName);
			empListgrpBy.forEach(empDeparmentwise -> System.out.println("employee name : "+empDeparmentwise.getName()));
			System.out.println("");
		});
		
		
	}

	//Q11: average salary of male and female employee?
	private static void method11(List<Employee> empList) {
		System.out.println("Q11: average salary of male and female employee?");
		
		Map<String,Double>genderWiseAvgSalary=
		empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(" genderWiseAvgSalary "+genderWiseAvgSalary);
		
	}

	//Q10: how many male & female emp in IT team?
	private static void method10(List<Employee> empList) {
		System.out.println("Q10: how many male & female emp in IT team?");
		Map<String,Long> iTAndHrGenderWiseHeadCount=
		empList.stream().filter(s->"IT".equalsIgnoreCase(s.getDepartment()))
		.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		System.out.println("iTAndHrGenderWiseHeadCount "+iTAndHrGenderWiseHeadCount);

		
	}

	//Q9: most experience employee in organization?
	private static void method9(List<Employee> empList) {
		System.out.println("Q9: most experience employee in organization?");
		
		//using min.
		Employee mostExperienceEmp=
		empList.stream().min(Comparator.comparingInt(Employee::getYearJoining)).orElse(null);
		System.out.println("mostExperienceEmp using min "+mostExperienceEmp);
		
		//using sorting
		Employee mostExperienceEmployee = empList.stream()
					.sorted(Comparator.comparingInt(Employee::getYearJoining)).findFirst().orElse(null);
		System.out.println("mostExperienceEmp sorted "+mostExperienceEmployee);
		
	}

	//Q8: get a male employee who has minimum age in IT department
	private static void method8(List<Employee> empList) {
		System.out.println("Q8: get a male employee detail who has minimum age in IT department");
		Employee youngestEmployee=
		empList.stream().filter(s->"male".equalsIgnoreCase(s.getGender()) 
				&& "IT".equalsIgnoreCase(s.getDepartment())).min(Comparator.comparingInt(Employee::getAge)).orElse(null);
		System.out.println("youngestEmployee :"+youngestEmployee);
		
	}


	//Q7: what is average salary for each department?
	private static void method7(List<Employee> empList) {
		System.out.println("Q7: what is average salary for each department?");
		Map<String,Double> avgSalaryPerDepartment =
				empList.stream()
				.collect(Collectors
						.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("avgSalaryPerDepartment : "+avgSalaryPerDepartment);
		
	}

	//Q6: count the number of employee in each department?
	private static void method6(List<Employee> empList) {
		System.out.println("Q6: count the number of employee in each department?");
		Map<String,Long>headCountByDepartment= empList.stream()
		.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
		System.out.println("headCountByDepartment "+headCountByDepartment);
		
	}

	//Q5: get names of all employees who joined after 2015?
	private static void method5(List<Employee> empList) {
		System.out.println("Q5: get names of all employees who joined after 2015?");
		empList.stream().filter(s-> s.getYearJoining() > 2015)
		.map(Employee::getName).forEach(System.out::println);
		
	}

	//Q4: get employee who is having max salary?
	private static void method4(List<Employee> empList) {
		System.out.println("Q4: get employee who is having max salary?");
		String highestpaidEmpName=empList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).map(Employee::getName).orElse(null);		
		System.out.println("highestpaidEmpName "+highestpaidEmpName);
		
		Employee highestpaidEmp=empList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).orElse(null);
		System.out.println("highestpaidEmp "+highestpaidEmp);
		
	}

	//"Q3: average age of male and female employee?"
	private static void method3(List<Employee> empList) {
		System.out.println("Q3: average age of male and female employee?");
		Map<String, Double> averageAgeByGender = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println("averageAgeByGender : "+averageAgeByGender);
		
	}

	//Q2 : print name of all department of organization?
	private static void method2(List<Employee> empList) {
		System.out.println("Q2 : print name of all department of organization?");
		 empList.stream()
				.map(Employee::getDepartment)
				.distinct()
				.forEach(System.out::println);
		
	}

	//Q1 : how many male & female employee in organization?
	private static void method1(List<Employee> empList) {
		System.out.println("Q1 : how many male & female employee in organization?");
		Map<String,Long>countByGender= empList.stream()
				.collect(Collectors.groupingBy(s->s.getGender(),Collectors.counting()));
		
		System.out.println("countByGender "+countByGender);
		
	}
	
	private static void preDefinedFunctionalInterface() {
		System.out.println("#### preDefinedFunctionalInterface #### ");
		System.out.println("predicate will take some input perform some conditional check and return boolean ");
		//predicate will take some input perform some conditional check and return boolean
		System.out.println("Predicate to check if even value : test value is 2 ");
		Predicate<Integer> checkIfEven = i->i%2==0;
		if(checkIfEven.test(2)) {
			System.out.println(" input is even");
		}else {
			System.out.println(" input is odd");
		}

		//take some input, perform some operation and return result in any type then use function.
		System.out.println("Function :: take some input, perform some operation and return result in any type then use function. ");
		Function<Integer,Integer> squareIt = i->i*i;
		System.out.println("Function to find square. input is 5 ");
		System.out.println("squareIt "+squareIt.apply(5));

		//Consumer take some input & no return or void return then use consumer
		Consumer<String> printValue = i->System.out.println("printed "+i);
		printValue.accept("hello");

		//Supplier don't accept any input only return output
		Supplier<Date> currentDate = ()-> new Date(); 
		System.out.println("date "+currentDate.get());

	}

	private static List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(111, "abc", 26,"Male" , 2018, 25000.0,"IT"));
		empList.add(new Employee(112, "cde", 23,"Female" , 2016, 22000.0,"HR"));
		empList.add(new Employee(113, "fgh", 27,"Female" , 2014, 42000.0,"IT"));
		empList.add(new Employee(114, "pqr", 22,"Male" , 2020, 21000.0,"Admin"));
		empList.add(new Employee(115, "xyz", 24,"Male" , 2015, 24000.0,"HR"));
		empList.add(new Employee(116, "mno", 26,"Female" , 2017, 29000.0,"Account & finance"));
		empList.add(new Employee(117, "par", 32,"Male" , 2011, 55000.0,"Account & finance"));
		empList.add(new Employee(118, "mae", 23,"Female" , 2016, 22000.0,"Admin"));
		empList.add(new Employee(119, "ish", 27,"Female" , 2014, 42000.0,"IT"));
		empList.add(new Employee(120, "res", 35,"Male" , 2020, 38000.0,"HR"));
		empList.add(new Employee(121, "zen", 40,"Male" , 2015, 34000.0,"Account & finance"));
		empList.add(new Employee(122, "vsrrere", 34,"Female" , 2017, 41000.0,"IT"));
		empList.add(new Employee(123, "abc", 29,"Female" , 2017, 44000.0,"IT"));
		empList.add(new Employee(124, "mno", 31,"Female" , 2017, 46000.0,"Admin"));
		return empList;
	}

}


class Employee{
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
