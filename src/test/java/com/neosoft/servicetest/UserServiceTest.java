package com.neosoft.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.neosoft.task1.entity.Gender;
import com.neosoft.task1.entity.User;
import com.neosoft.task1.exception.UserNotFoundException;
import com.neosoft.task1.repository.UserRepository;
import com.neosoft.task1.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository usereRepository;

	private User u1;
	private User u2;
	private List<User> userList;

	@Before
	public void setup() {
		this.userList = new ArrayList<>();
		this.u1 = new User(125, "sachin", "bhale", "sachin@gmail.com", Gender.MALE, "ISFSGSGSUWEGE", "9850323536",
				"XYZ", "pune", "412756", LocalDate.now(), LocalDate.now(), false);
		this.u2 = new User(125, "sachin", "bhale", "sachin@gmail.com", Gender.FEMALE, "ISFSGSGSUWEGE", "9850323536",
				"XYZ", "pune", "412756", LocalDate.now(), LocalDate.now(), false);
		this.userList.add(this.u1);
		this.userList.add(this.u2);
	}

	@Test
	public void getAllUsersTest() {

		// when  
		when(usereRepository.findAll()).thenReturn(userList);

		List<User> result = userService.getAllUsers();

		// then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(1).getFirstName()).isEqualTo(u2.getFirstName());
		assertEquals(2, result.size());
	}

	@Test
	public void addNewUserTest() {

		when(usereRepository.save(u1)).thenReturn(u1);

		User result = userService.addNewUser(u1);
		assertEquals(u1, result);

		verify(usereRepository, times(1)).save(u1);

	}

	@Test(expected = UserNotFoundException.class)
	public void updateUserTest() throws Exception{

		System.err.println(Optional.of(u1));

		// when(usereRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

		when(usereRepository.save(u1)).thenReturn(u2);
		User result = userService.updateUser(121, u1);
		System.err.println(result);
		System.err.println(u1);

		assertNotEquals(u1.getGender(), result.getGender());

	}

	@Test(expected = UserNotFoundException.class)
	public void getUserBynameTestException() {

		when(usereRepository.findByfirstName("sachin")).thenReturn(null);

		User result = userService.getUserByName("sachin");

	}

	@Test(expected = UserNotFoundException.class)
	public void getUserBynameTest() {

		when(usereRepository.findByfirstName("satish")).thenReturn(null);

		User result = userService.getUserByName("sachin");
		Assert.assertNull(result);
		assertNotEquals(u1.getFirstName(), result.getFirstName());

	}

	@Test(expected = UserNotFoundException.class)
	public void getUserBySurnameTest() {

		User user = new User(121, "sachin", "bhale", "sachin@gmail.com", Gender.MALE, "ISFSGSGSUWEGE", "9850323536",
				"XYZ", "pune", "412756", LocalDate.now(), LocalDate.now(), false);

		when(userService.getUserBySurName("bhale")).thenReturn(user);

		User result = usereRepository.findBysurName("bhale");
		Assert.assertNotNull(result);
		assertEquals(user.getFirstName(), result.getFirstName());

	}

	@Test(expected = UserNotFoundException.class)
	public void getUserBySurnameTest1() {

		when(usereRepository.findByfirstName("satish")).thenReturn(null);

		User result = userService.getUserByName("satish");
		Assert.assertNull(result);
		assertNotEquals(u1.getFirstName(), result.getFirstName());

	}

	@Test(expected = UserNotFoundException.class)
	public void getUserByPincodeTest() {

		when(userService.getUserByPincode("412756")).thenReturn(u1);
		User result = usereRepository.findBypincode("412756");
		Assert.assertNotNull(result);
		assertEquals(u1.getFirstName(), result.getFirstName());
	}

	@Test(expected = UserNotFoundException.class)
	public void getUserByPincodeTest2() {
		when(userService.getUserByPincode("523695")).thenReturn(null);
		User result = usereRepository.findBypincode("523695");
		Assert.assertNull(result);
		assertNotEquals(u1.getFirstName(), result.getFirstName());
	}

	@Test
	public void deleteUserByIdTest() {

		long id = u1.getUserId();
		userService.deleteUserById(id);
		verify(usereRepository, times(1)).deleteById(id);

	}

	@Test
	public void sortByJoiningDateTest() {

		when(usereRepository.findByOrderByJoiningDateAsc()).thenReturn(userList);

		List<User> result = userService.sortByJoiningDate();

		assertThat(result.size()).isEqualTo(2);

		assertThat(result.get(0).getFirstName()).isEqualTo(u1.getFirstName());

	}

	@Test
	public void sortByDobTest() {

		when(usereRepository.findByOrderByDOBAsc()).thenReturn(userList);

		List<User> result = userService.sortByJoiningDOB();

		assertThat(result.size()).isEqualTo(2);

		assertThat(result.get(0).getFirstName()).isEqualTo(u1.getFirstName());

	}

}
