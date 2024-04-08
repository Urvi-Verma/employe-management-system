package com.railworld.Project;

import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class App {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("╔══════════════════════════════════════════╗");
		System.out.println("  ***************************************");
		System.out.print(" Enter 📊 MySQL database username: ");
		String username = scanner.nextLine();
		System.out.print(" Enter 📊 MySQL database password: ");
		String password = scanner.nextLine();

		if (isValidCredentials(username, password)) {
			System.out.println("        Successful Database login!");
			System.out.println("  ***************************************");
			System.out.println("╚══════════════════════════════════════════╝");
			HibernateUtil.setDatabaseCredentials(username, password);

			boolean exit = false;
			while (!exit) {
				System.out.println(
						"\n|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|*|");
				System.out.println("\n╔══════════════════════════════════════════╗");
				System.out.println("*💼👨👩💼 Employee Management System 💼👨👩💼*");
				System.out.println("╚══════════════════════════════════════════╝");
				System.out.println("  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓");
				System.out.println("╔══════════════════════════════╗");
				System.out.println("║------- Menu Dashboard -------║");
				System.out.println("║══════════════════════════════║");
				System.out.println("║	1. 🧑 Admin	       ║");
				System.out.println("║	2. 👤 User	       ║");
				System.out.println("║	3. 🚪 Exit	       ║");
				System.out.println("╚══════════════════════════════╝");
				System.out.print("Enter your choice → : ");

				int choice = getIntInput();
				scanner.nextLine();

				switch (choice) {
				case 1:
					if (adminLogin()) {
						adminMenu();
					}
					break;
				case 2:
					if (userLogin()) {
						userMenu();
					}
					break;
				case 3:
					System.out.println("Thank You !!!");
					exit = true;
					break;
				default:
					System.out.println("Invalid choice!");
				}
			}
			HibernateUtil.shutdown();
		} else {
			System.out.println("Incorrect username or password. Program terminated.");
			System.out.println("  ***************************************");
			System.out.println("╚══════════════════════════════════════════╝");
		}
	}

	private static int getIntInput() {
		while (true) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			} else {
				System.out.println("\nInvalid input.");
				System.out.print("Please enter a valid integer: ");
				scanner.nextLine();
			}
		}
	}

	private static double getDoubleInput() {
		while (true) {
			if (scanner.hasNextDouble()) {
				return scanner.nextDouble();
			} else {
				System.out.println("\nInvalid input.");
				System.out.print("Please enter a valid integer: ");
				scanner.nextLine();
			}
		}
	}

	private static long getLongInput() {
		while (true) {
			if (scanner.hasNextLong()) {
				return scanner.nextLong();
			} else {
				System.out.println("\nInvalid input.");
				System.out.print("Please enter a valid integer: ");
				scanner.nextLine();
			}
		}
	}

	private static boolean adminLogin() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print("  ************* Admin Dashboard *****************\n");
		System.out.print(" Enter username: ");
		String username = scanner.nextLine();
		System.out.print(" Enter password: ");
		String password = scanner.nextLine();

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Admin admin = (Admin) session
					.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class)
					.setParameter("username", username).setParameter("password", password).uniqueResult();
			session.close();

			if (admin != null) {
				System.out.println("	        Successful Admin login!");
				System.out.println("   ***********************************************");
				System.out.println("╚═══════════════════════════════════════════════════╝");
				System.out.println("        ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓");
				return true;

			} else {
				System.out.println("	 Incorrect Admin username or password");
				System.out.println("   ***********************************************");
				System.out.println("╚═══════════════════════════════════════════════════╝");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void adminMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
			System.out.println("  |*********************************************************|");
			System.out.println("  |***********   Employee Management System     ************|");
			System.out.println("  |*********************************************************|");
			System.out.println("  |------------***** Admin Panel *****----------------------|");
			System.out.println("  |Employee :                                               |");
			System.out.println("  |--------------1. Add Employee ---------------------------|");
			System.out.println("  |--------------2. Update Employee ------------------------|");
			System.out.println("  |--------------3. Delete Employee ------------------------|");
			System.out.println("  |--------------4. Display All Employees ------------------|");
			System.out.println("  |User :                                                   |");
			System.out.println("  |--------------5. Add User -------------------------------|");
			System.out.println("  |--------------6. Update User ----------------------------|");
			System.out.println("  |--------------7. Delete User ----------------------------|");
			System.out.println("  |--------------8. Display All Users ----------------------|");
			System.out.println("  |---------------------------------------------------------|");
			System.out.println("  |--------------9. Back to Dashboard ----------------------|");
			System.out.println("  |---------------------------------------------------------|");
			System.out.println("╚═════════════════════════════════════════════════════════════╝");
			System.out.println(" 	 ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓");
			System.out.print("Enter your choice → : ");

			int choice = getIntInput();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				updateEmployee();
				break;
			case 3:
				deleteEmployee();
				break;
			case 4:
				viewAllEmployees();
				break;
			case 5:
				addUser();
				break;
			case 6:
				updateUser();
				break;
			case 7:
				deleteUser();
				break;
			case 8:
				viewAllUsers();
				break;
			case 9:
				System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
				System.out.print("             Back to Menu Dashboard... ↑  ↑  ↑  ↑  ↑ \n");
				System.out.println("╚═════════════════════════════════════════════════════════════╝");
				exit = true;
				break;
			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	private static boolean isValidCredentials(String username, String password) {
		return username.equals("root") && password.equals("root");
	}

	private static void addEmployee() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter employee name: ");
		String name = scanner.nextLine();

		System.out.print(" Enter employee gender: ");
		String gender = scanner.nextLine();

		System.out.print(" Enter employee designation: ");
		String designation = scanner.nextLine();

		System.out.print(" Enter employee contact_no: ");
		long contactNo = getLongInput();
		scanner.nextLine();

		System.out.print(" Enter employee email_id: ");
		String emailId = scanner.nextLine();

		System.out.print(" Enter employee address: ");
		String address = scanner.nextLine();

		System.out.print(" Enter employee join_date: ");
		String joinDate = scanner.nextLine();

		System.out.print(" Enter employee attendance: ");
		String attendance = scanner.nextLine();

		System.out.print(" Enter employee salary: ");
		double salary = getDoubleInput();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = new Employee();

		employee.setName(name);
		employee.setGender(gender);
		employee.setDesignation(designation);
		employee.setContactNo(contactNo);
		employee.setEmailId(emailId);
		employee.setAddress(address);
		employee.setJoinDate(joinDate);
		employee.setAttendance(attendance);
		employee.setSalary(salary);

		session.save(employee);
		transaction.commit();
		session.close();

		System.out.println(" Employee added successfully!");
		System.out.println("╚═══════════════════════════════════════════════════╝");
	}

	private static void updateEmployee() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter employee id to update: ");
		int id = getIntInput();
		scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = session.get(Employee.class, id);

		if (employee != null) {
			System.out.print(" Enter employee update name: ");
			String newName = scanner.nextLine();

			System.out.print(" Enter employee update gender: ");
			String newGender = scanner.nextLine();

			System.out.print(" Enter employee update designation: ");
			String newDesignation = scanner.nextLine();

			System.out.print(" Enter employee update contact_no: ");
			long newContactNo = getLongInput();
			scanner.nextLine();

			System.out.print(" Enter employee update email_id: ");
			String newEmailId = scanner.nextLine();

			System.out.print(" Enter employee update address: ");
			String newAddress = scanner.nextLine();

			System.out.print(" Enter employee update join_date: ");
			String newJoinDate = scanner.nextLine();

			System.out.print(" Enter employee update attendance: ");
			String newAttendance = scanner.nextLine();

			System.out.print(" Enter employee update salary: ");
			double newSalary = getDoubleInput();

			employee.setName(newName);
			employee.setGender(newGender);
			employee.setDesignation(newDesignation);
			employee.setContactNo(newContactNo);
			employee.setEmailId(newEmailId);
			employee.setAddress(newAddress);
			employee.setJoinDate(newJoinDate);
			employee.setAttendance(newAttendance);
			employee.setSalary(newSalary);

			session.update(employee);
			transaction.commit();

			System.out.println(" Employee details updated successfully!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		} else {
			System.out.println(" Employee not found!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		}
		session.close();
	}

	private static void deleteEmployee() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter employee id to delete: ");
		int id = getIntInput();
		scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = session.get(Employee.class, id);

		if (employee != null) {
			session.delete(employee);
			transaction.commit();

			System.out.println(" Employee deleted successfully!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		} else {
			System.out.println(" Employee not found!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		}
		session.close();
	}

	private static void searchEmployeeById() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter employee ID to search: ");
		int id = getIntInput();
		scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Employee employee = session.get(Employee.class, id);
		session.close();

		if (employee != null) {
			System.out.println(
					"\n╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
			System.out.println("\t\t\t\t\tEmployee found:\n");
			// System.out.println(employee);
			System.out.println(
					"ID 	NAME 	GENDER	DESIGNATION	CONTACT_NO	EMAIL_ID     ADDRESS	JOINING_DATE	ATTENDANCE	 SALARY");
			System.out.println(employee.getId() + "\t" + employee.getName() + "\t" + employee.getGender() + "\t"
					+ employee.getDesignation() + "\t" + employee.getContactNo() + "\t" + employee.getEmailId() + "\t"
					+ employee.getAddress() + "\t" + employee.getJoinDate() + "\t\t" + employee.getAttendance() + "\t\t"
					+ employee.getSalary());
			System.out.println(
					"╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
		} else {
			System.out.println(" Employee not found with ID: " + id);
			System.out.println("╚═══════════════════════════════════════════════════╝");
		}
	}

	private static void viewAllEmployees() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
		session.close();

		System.out.println(
				"\n╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
		System.out.println("\t\t\t\t\tAll Employees Details:\n");
		System.out.println(
				"ID 	NAME 	GENDER	DESIGNATION	CONTACT_NO	EMAIL_ID     ADDRESS	JOINING_DATE	ATTENDANCE	 SALARY");

		for (Employee employee : employees) {
			System.out.println(employee.getId() + "\t" + employee.getName() + "\t" + employee.getGender() + "\t"
					+ employee.getDesignation() + "\t" + employee.getContactNo() + "\t" + employee.getEmailId() + "\t"
					+ employee.getAddress() + "\t" + employee.getJoinDate() + "\t\t" + employee.getAttendance() + "\t\t"
					+ employee.getSalary());
		}
		System.out.println(
				"╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
	}

	private static void viewAttendance() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
		session.close();

		System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
		System.out.println("\t  All Employee Attendance Report:\n");
		System.out.println("ID 	NAME 	   	DESIGNATION	    ATTENDANCE");

		for (Employee employee : employees) {
			System.out.println(employee.getId() + "\t" + employee.getName() + "\t\t" + employee.getDesignation()
					+ "\t\t" + employee.getAttendance());
		}
		System.out.println("╚═════════════════════════════════════════════════════════════╝");
	}

	private static void addUser() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter username: ");
		String username = scanner.nextLine();

		System.out.print(" Enter password: ");
		String passsword = scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(passsword);

		session.save(user);
		transaction.commit();
		session.close();

		System.out.println(" User added successfully!");
		System.out.println("╚═══════════════════════════════════════════════════╝");
	}

	private static void updateUser() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter user id to update: ");
		int id = getIntInput();
		scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Users user = session.get(Users.class, id);

		if (user != null) {
			System.out.print(" Enter user update username: ");
			String newUsername = scanner.nextLine();

			System.out.print(" Enter user update password: ");
			String newPassword = scanner.nextLine();

			user.setUsername(newUsername);
			user.setPassword(newPassword);

			session.update(user);
			transaction.commit();

			System.out.println(" User details updated successfully!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		} else {
			System.out.println(" User not found!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		}
		session.close();
	}

	private static void deleteUser() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print(" Enter user id to delete: ");
		int id = getIntInput();
		scanner.nextLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Users user = session.get(Users.class, id);

		if (user != null) {
			session.delete(user);
			transaction.commit();
			System.out.println(" User deleted successfully!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		} else {
			System.out.println(" User not found!");
			System.out.println("╚═══════════════════════════════════════════════════╝");
		}
		session.close();
	}

	private static void viewAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Users> users = session.createQuery("from Users", Users.class).list();
		session.close();

		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.println("\t\tAll Users Details:\n");
		System.out.println("ID 	USERNAME 	PASSWORD");

		for (Users user : users) {
			System.out.println(user.getId() + "\t" + user.getUsername() + "\t\t" + user.getPassword());
		}
		System.out.println("╚═══════════════════════════════════════════════════╝");
	}

	private static boolean userLogin() {
		System.out.println("\n╔═══════════════════════════════════════════════════╗");
		System.out.print("  *************** User Dashboard ******************\n");
		System.out.print(" Enter username: ");
		String username = scanner.nextLine();
		System.out.print(" Enter password: ");
		String password = scanner.nextLine();

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Users user = (Users) session
					.createQuery("FROM Users WHERE username = :username AND password = :password", Users.class)
					.setParameter("username", username).setParameter("password", password).uniqueResult();
			session.close();

			if (user != null) {
				System.out.println("	        Successful User login!");
				System.out.println("   ***********************************************");
				System.out.println("╚═══════════════════════════════════════════════════╝");
				System.out.println("        ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓");
				return true;
			} else {
				System.out.println("	 Incorrect User username or password");
				System.out.println("   ***********************************************");
				System.out.println("╚═══════════════════════════════════════════════════╝");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void userMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
			System.out.println("  |*********************************************************|");
			System.out.println("  |***********   Employee Management System     ************|");
			System.out.println("  |*********************************************************|");
			System.out.println("  |---------------***** User Panel *****--------------------|");
			System.out.println("  |--------------1. Search Employee by Id ------------------|");
			System.out.println("  |--------------2. Display All Employees ------------------|");
			System.out.println("  |--------------3. Display Attendance Report --------------|");
			System.out.println("  |--------------4. Back to Dashboard ----------------------|");
			System.out.println("  |---------------------------------------------------------|");
			System.out.println("╚═════════════════════════════════════════════════════════════╝");
			System.out.println(" 	 ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓");
			System.out.print("Enter your choice → : ");

			int choice = getIntInput();
			scanner.nextLine();

			switch (choice) {
			case 1:
				searchEmployeeById();
				break;
			case 2:
				viewAllEmployees();
				break;
			case 3:
				viewAttendance();
				break;
			case 4:
				System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
				System.out.print("             Back to Menu Dashboard... ↑  ↑  ↑  ↑  ↑ \n");
				System.out.println("╚═════════════════════════════════════════════════════════════╝");
				exit = true;
				break;
			default:
				System.out.println("Invalid choice!");
			}
		}
	}

}
