package edu.miu.cs.cs489appsd.lab1b.employeePensionCLI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.miu.cs.cs489appsd.lab1b.employeePensionCLI.domain.Employee;
import edu.miu.cs.cs489appsd.lab1b.employeePensionCLI.domain.PensionPlan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PensionApp {
	private static List<Employee> employees = new ArrayList<>();

	public static void main(String[] args) {
		loadData();
		System.out.println("List of all employees in JSON format:");
		printAllEmployeesJSON();

		System.out.println("\nMonthly Upcoming Enrollees report in JSON format:");
		printMonthlyUpcomingEnrolleesReportJSON();
	}

	private static void loadData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			employees.add(new Employee(1, "Daniel", "Agar", sdf.parse("2018-01-17"), 105945.50,new PensionPlan("EX1089",sdf.parse("2023-01-17"),100)));
			employees.add(new Employee(2, "Benard", "Shaw", sdf.parse("2019-04-03"), 197750.00,null));
			employees.add(new Employee(3, "Carly", "Agar", sdf.parse("2014-05-16"), 842000.75,new PensionPlan("SM2307",sdf.parse("2019-11-04"),1555.50)));
			employees.add(new Employee(4, "Wesley", "Schneider", sdf.parse("2019-05-02"), 74500.00,null));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void printAllEmployeesJSON() {
		employees.sort(Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getYearlySalary).reversed());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(employees);
		System.out.println(json);
	}

	private static void printMonthlyUpcomingEnrolleesReportJSON() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		Date startDate = calendar.getTime();

		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = calendar.getTime();

		List<Employee> upcomingEnrollees = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getPensionPlan() == null && isWithinNextMonth(employee.getEmploymentDate(), startDate, endDate)) {
				upcomingEnrollees.add(employee);
			}
		}

		upcomingEnrollees.sort(Comparator.comparing(Employee::getEmploymentDate));

		for (Employee employee : upcomingEnrollees) {
			System.out.println("Employee ID: " + employee.getEmployeeId());
			System.out.println("First Name: " + employee.getFirstName());
			System.out.println("Last Name: " + employee.getLastName());
			System.out.println("Employment Date: " + new SimpleDateFormat("yyyy-MM-dd").format(employee.getEmploymentDate()));
			System.out.println("Yearly Salary: " + employee.getYearlySalary());
			System.out.println();
		}
	}

	private static boolean isWithinNextMonth(Date date, Date startDate, Date endDate) {
		return date.after(startDate) && date.before(endDate);
	}
}
