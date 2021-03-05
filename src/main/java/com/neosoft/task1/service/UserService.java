package com.neosoft.task1.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.task1.entity.User;
import com.neosoft.task1.exception.UserNotFoundException;
import com.neosoft.task1.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userepoRepository;

	@Transactional
	public User addNewUser(User user) {
		return userepoRepository.save(user);
	}

	public User updateUser(long id, User user) {
		User existing = userepoRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user Not found"));
		existing.setFirstName(user.getFirstName());
		existing.setSurName(user.getSurName());
		existing.setAddress(user.getAddress());
		existing.setCity(user.getCity());
		existing.setMobile(user.getMobile());
		existing.setPassword(user.getPassword());
		existing.setGender(user.getGender());
		existing.setDOB(user.getDOB());
		existing.setJoiningDate(user.getJoiningDate());
		existing.setDeleted(user.getDeleted());
		existing.setEmail(user.getEmail());
		existing.setPincode(user.getPincode());

		return userepoRepository.save(existing);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userepoRepository.findAll();
	}

	public User getUserByName(String name) {

		User user = userepoRepository.findByfirstName(name);
		if (user != null) {
			return user;

		}
		throw new UserNotFoundException("user with the specified name " + name + " not found");

	}

	public User getUserBySurName(String surname) {
		User user = userepoRepository.findBysurName(surname);
		if (user != null) {
			return user;

		}
		throw new UserNotFoundException("user with the specified surname " + surname + " not found");
	}

	public User getUserByPincode(String pin) {
		User user = userepoRepository.findBypincode(pin);
		if (user != null) {
			return user;

		}
		throw new UserNotFoundException("user with the specified pin " + pin + " not found");

	}

	public void deleteUserById(Long id) {
		userepoRepository.deleteById(id);

	}

	public List<User> sortByJoiningDate() {
		return userepoRepository.findByOrderByJoiningDateAsc();

	}

	public List<User> sortByJoiningDOB() {
		return userepoRepository.findByOrderByDOBAsc();

	}

}
