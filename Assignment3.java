package com.coderscampus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment3 {
	

	class User {
	    String username;
	    String password;
	    String name;

	    public User(String username, String password, String name) {
	        this.username = username;
	        this.password = password;
	        this.name = name;
	    }
	}

	static class UserService {
	    private static List<User> users = new ArrayList<>();

	    public void loadUsers(String filename) {
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] userData = line.split(",");
	                users.add(new User(userData[0], userData[1], userData[2]));
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading file: " + e.getMessage());
	        }
	    }

	    public static boolean validateLogin(String username, String password) {
	        for (User user : users) {
	            if (user.username.equalsIgnoreCase(username) && user.password.equals(password)) {
	                System.out.println("Welcome: " + user.name);
	                return true;
	            }
	        }
	        return false;
	    }
	}

	public class UserLoginApplication {
	    public void main(String[] args) {
	        UserService userService = new UserService();
			userService.loadUsers("data.txt");
	        Scanner scanner = new Scanner(System.in);
	        int attempts = 0;
	        boolean loggedIn = false;

	        while (attempts < 5 && !loggedIn) {
	            System.out.print("Enter your email: ");
	            String username = scanner.nextLine();
	            System.out.print("Enter your password: ");
	            String password = scanner.nextLine();

	            loggedIn = UserService.validateLogin(username, password);

	            if (!loggedIn) {
	                System.out.println("Invalid login, please try again");
	                attempts++;
	            }
	        }

	        if (!loggedIn) {
	            System.out.println("Too many failed login attempts, you are now locked out.");
	        }

	        scanner.close();
	    }
	}



}
