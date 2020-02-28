package kz.ktzh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kz.ktzh.service.UserDAO;
import kz.ktzh.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserDAO userDaoMock;

	@MockBean
	PasswordEncoder passwordEncMock;

	@Spy
	UserService userSpy;

	@Autowired
	UserService userImpl;

	@Test
	public void testGetSecretQuestion() {
		when(userDaoMock.getSecretQuestion("user")).thenReturn("1");
		assertEquals("1", userImpl.getSecretQuestion("user"));
	}

	@Test
	public void testChangePassword() {
		when(passwordEncMock.matches("password", "password")).thenReturn(true);
		when(userDaoMock.getPassword("user")).thenReturn("password");
		doNothing().when(userDaoMock).resetPassword("user", "password", "password");
		assertEquals("1", userImpl.changePassword("user", "password", "password"));
	}

	@Test
	public void testResetPassword() throws NoSuchAlgorithmException {
		// TODO : нагуглить как мокать if condition
		doReturn("1").when(userSpy).md5String("1");
		when(userDaoMock.getAnswer("user")).thenReturn("1");
		doNothing().when(userDaoMock).resetPassword("user", "password", "1");
		when(passwordEncMock.encode("password")).thenReturn("password");
		assertEquals("1", userImpl.resetPassword("user", "1", "password"));
	}

}
