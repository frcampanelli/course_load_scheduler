package org.dselent.course_load_scheduler.client.action;

public class SendCreateAccountAction 
{
	private String firstName;
	private String lastName;
	private String facultyType;
	private String email;
	private String userName;
	private String password;



	public SendCreateAccountAction(String firstName, String lastName, String facultyType, String email, String userName, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.facultyType = facultyType;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFacultyType() {
		return facultyType;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
