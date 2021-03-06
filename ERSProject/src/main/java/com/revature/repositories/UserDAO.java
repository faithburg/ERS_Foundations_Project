package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

	/**
	 * Should retrieve a User from the DB with the corresponding username or an
	 * empty optional if there is no match.
	 */
	public Optional<User> getByUsername(String username) {
		Optional<User> optionalUser = Optional.empty();
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from staff where username='" + username + "'");
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passcode"));
				if (rs.getString("role").equalsIgnoreCase("finance manager")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (rs.getString("role").equalsIgnoreCase("employee")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setAddress(rs.getString("address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));
				optionalUser = Optional.of(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return optionalUser;
	}

	public Optional<User> getByEmail(String email) {
		Optional<User> optionalUser = Optional.empty();
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from staff where email='" + email + "'");
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passcode"));
				if (rs.getString("role").equalsIgnoreCase("finance manager")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (rs.getString("role").equalsIgnoreCase("employee")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setAddress(rs.getString("address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));
				optionalUser = Optional.of(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return optionalUser;
	}

	public List<User> getAllUsers() {
		List<User> usersList = new ArrayList<User>();
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from staff");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passcode"));
				if (rs.getString("role").equalsIgnoreCase("finance manager")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (rs.getString("role").equalsIgnoreCase("employee")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setAddress(rs.getString("address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));
				usersList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usersList;
	}

	public Optional<User> getByUserId(int id) {
		Optional<User> optionalUser = Optional.empty();
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from staff where id=" + id);
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				if (rs.getString("role").equalsIgnoreCase("finance manager")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (rs.getString("role").equalsIgnoreCase("employee")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setAddress(rs.getString("address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));
				optionalUser = Optional.of(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return optionalUser;
	}

	/**
	 * <ul>
	 * <li>Should Insert a new User record into the DB with the provided
	 * information.</li>
	 * <li>Should throw an exception if the creation is unsuccessful.</li>
	 * <li>Should return a User object with an updated ID.</li>
	 * </ul>
	 *
	 * Note: The userToBeRegistered will have an id=0, and username and password
	 * will not be null. Additional fields may be null.
	 */
	public User create(User userToBeRegistered) {
		User user = new User();
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			String registerQuery = "insert into staff (username, passcode,role,first_name,last_name,address,email,phone) values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(registerQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, userToBeRegistered.getUsername());
			pstmt.setString(2, userToBeRegistered.getPassword());
			pstmt.setObject(3, userToBeRegistered.getRole().toString());
			pstmt.setString(4, userToBeRegistered.getFirstName());
			pstmt.setString(5, userToBeRegistered.getLastName());
			pstmt.setString(6, userToBeRegistered.getAddress());
			pstmt.setString(7, userToBeRegistered.getEmail());
			pstmt.setLong(8, userToBeRegistered.getPhone());
			pstmt.execute();

			ResultSet rs = pstmt.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}

			user.setId(id);
			user.setUsername(userToBeRegistered.getUsername());
			user.setPassword(userToBeRegistered.getPassword());
			user.setRole(userToBeRegistered.getRole());
			user.setFirstName(userToBeRegistered.getFirstName());
			user.setLastName(userToBeRegistered.getLastName());
			user.setAddress(userToBeRegistered.getAddress());
			user.setEmail(userToBeRegistered.getEmail());
			user.setPhone(userToBeRegistered.getPhone());
	

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return user;
	}
}
