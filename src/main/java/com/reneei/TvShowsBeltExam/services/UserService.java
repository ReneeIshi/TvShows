package com.reneei.TvShowsBeltExam.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.reneei.TvShowsBeltExam.models.LoginUser;
import com.reneei.TvShowsBeltExam.models.User;
import com.reneei.TvShowsBeltExam.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// need to validate if the user has an email that already exists or password doesn't match
		public void validate(User newUser, BindingResult outcome) {
			// logic to check if the password is matching
			if(!newUser.getPassword().equals(newUser.getConfirm())) {
				outcome.rejectValue("password","mismatch","Password doesn't match"); // "password" is the field, "mismatch" is the key, message to display
			}
			
			// logic to check if email is a duplicate
			if(userRepository.findByEmail(newUser.getEmail())!=null) {
				outcome.rejectValue("email","distinct","Email is already taken");
			}
		}
		
		// Hash the user's password before adding to the database
		public User addUser(User newUser) {
			String hashPass=BCrypt.hashpw(newUser.getPassword(),BCrypt.gensalt()); // hash the password and giving it variable name hashPass
			newUser.setPassword(hashPass); // once the password is hash then setting it as the password for newUser
			userRepository.save(newUser);
			return null;
		}
		
		// get the user by email
		public User findByEmail(String email) {
			return userRepository.findByEmail(email);
		}
		
		// get the user by id
		public User findByID(Long id) {
			return userRepository.findById(id).orElse(null);
		}
		
		// authenticate user
		public boolean authUser(LoginUser login, BindingResult outcome) {
			// first find the user by email and if it exists
			
			User user = userRepository.findByEmail(login.getEmail());
			// if we can't find the user by email, return false
			if(user==null) {
				outcome.rejectValue("email", "missingEmail", "Email not found");
				return false;			
			} else {
				if(!BCrypt.checkpw(login.getPassword(), user.getPassword())) {
					outcome.rejectValue("password", "match", "Invalid Password");
					return false;
				}
			}
			// if user is found check if password matches, return true and if not then return false
			return true;
		}

}
