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
			employees.add(new Employee(2, "Benard", "Shaw", sdf.parse("2019-04-03"), 197750.00,new PensionPlan()));
			employees.add(new Employee(3, "Carly", "Agar", sdf.parse("2014-05-16"), 842000.75,new PensionPlan("SM2307",sdf.parse("2019-11-04"),1555.50)));
			employees.add(new Employee(4, "Wesley", "Schneider", sdf.parse("2023-05-02"), 74500.00,new PensionPlan()));
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
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = calendar.getTime();

		List<Employee> upcomingEnrollees = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getPensionPlan() == null && employee.getEmploymentDate().before(endDate) && employee.getEmploymentDate().after(startDate)) {
				upcomingEnrollees.add(employee);
			}
		}

		upcomingEnrollees.sort(Comparator.comparing(Employee::getEmploymentDate));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(upcomingEnrollees);
		System.out.println(json);
	}
}
