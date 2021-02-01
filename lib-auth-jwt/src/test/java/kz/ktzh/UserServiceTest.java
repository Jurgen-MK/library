package kz.ktzh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kz.ktzh.models.UserGroupsRepository;
import kz.ktzh.models.UserInfo;
import kz.ktzh.models.UserInfoRepository;
import kz.ktzh.models.UserRepository;
import kz.ktzh.models.UserRoleRepository;
import kz.ktzh.models.Users;
import kz.ktzh.models.Authorities;
import kz.ktzh.models.UserGroups;
import kz.ktzh.service.UserDAO;
import kz.ktzh.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserDAO userDaoMock;

	@MockBean
	PasswordEncoder passwordEncMock;

	@MockBean
	UserInfoRepository userInfoMock;

	@MockBean
	UserRepository userRepoMock;

	@MockBean
	UserRoleRepository userRoleMock;

	@MockBean
	UserGroupsRepository userGroupsMock;

	@Spy
	UserService userSpy;

	@Autowired
	UserService userImpl;

	private String teststring;
	private String hashedstring;
	private Users testuser;
	private UserInfo testuserinfo;
	private UserGroups testusergroups;
	private Authorities testauth;
	private List<Users> testuserlist;

	@Before
	public void init() {
		teststring = "1";
		hashedstring = "c4ca4238a0b923820dcc509a6f75849b";
		testuserinfo = new UserInfo(1, "1", "1", "1", "1", new Date(), "1", 1, 1, 1, 1, 1, 1, "1", "1", "1");
		testusergroups = new UserGroups(1, 1);
		testauth = new Authorities("1", "1");
		testuser = new Users("1", "1", "1", 1, 1, "1", "1", (byte) 1);
		testuserlist = new ArrayList<Users>();
	}

	@Test
	public void testRegUserCorrect() {
		when(userRepoMock.findByUsername(Mockito.anyString())).thenReturn(testuserlist);
		when(passwordEncMock.encode(Mockito.anyString())).thenReturn("password");
		when(userRepoMock.save(Mockito.any(Users.class))).thenReturn(testuser);
		when(userInfoMock.save(Mockito.any(UserInfo.class))).thenReturn(testuserinfo);
		when(userRoleMock.save(Mockito.any(Authorities.class))).thenReturn(testauth);
		when(userGroupsMock.save(Mockito.any(UserGroups.class))).thenReturn(testusergroups);
		assertEquals("200", userImpl.regUser(testuser, testuserinfo));
	}

	@Test
	public void testRegUserWrong() {
		testuserlist.add(testuser);
		when(userRepoMock.findByUsername(Mockito.anyString())).thenReturn(testuserlist);
		when(passwordEncMock.encode(Mockito.anyString())).thenReturn("password");
		when(userRepoMock.save(Mockito.any(Users.class))).thenReturn(testuser);
		when(userInfoMock.save(Mockito.any(UserInfo.class))).thenReturn(testuserinfo);
		when(userRoleMock.save(Mockito.any(Authorities.class))).thenReturn(testauth);
		when(userGroupsMock.save(Mockito.any(UserGroups.class))).thenReturn(testusergroups);
		assertEquals("100", userImpl.regUser(testuser, testuserinfo));
	}

	@Test(expected = Exception.class)
	public void testRegUserError() {
		when(userRepoMock.findByUsername(Mockito.anyString())).thenReturn(testuserlist);
		when(passwordEncMock.encode(Mockito.anyString())).thenReturn("password");
		when(userRepoMock.save(Mockito.any(Users.class))).thenThrow(Exception.class);
		assertEquals("3", userImpl.regUser(testuser, testuserinfo));
	}

	@Test
	public void testGetSecretQuestionCorrect() {
		when(userDaoMock.getSecretQuestion("user")).thenReturn("1");
		assertEquals("1", userImpl.getSecretQuestion("user"));
	}

	@Test
	public void testChangePasswordCorrect() {
		when(passwordEncMock.matches("password", "password")).thenReturn(true);
		when(userDaoMock.getPassword("user")).thenReturn("password");
		doNothing().when(userDaoMock).resetPassword("user", "password", "password");
		assertEquals("200", userImpl.changePassword("user", "password", "password"));
	}

	@Test
	public void testChangePasswordWrong() {
		when(passwordEncMock.matches("wrongpassword", "password")).thenReturn(true);
		when(userDaoMock.getPassword("user")).thenReturn("password");
		doNothing().when(userDaoMock).resetPassword("user", "password", "password");
		assertEquals("100", userImpl.changePassword("user", "password", "password"));
	}

	@Test
	public void testResetPasswordCorrect() throws NoSuchAlgorithmException {
		when(userDaoMock.getAnswer(Mockito.anyString())).thenReturn(hashedstring);
		doNothing().when(userDaoMock).resetPassword("user", "password", "password");
		when(passwordEncMock.encode(Mockito.anyString())).thenReturn("password");
		assertEquals("200", userImpl.resetPassword("user", teststring, "password"));
	}

	@Test
	public void testResetPasswordWrong() throws NoSuchAlgorithmException {
		when(userDaoMock.getAnswer(Mockito.anyString())).thenReturn("1");
		doNothing().when(userDaoMock).resetPassword("user", "password", "password");
		when(passwordEncMock.encode(Mockito.anyString())).thenReturn("password");
		assertEquals("100", userImpl.resetPassword("user", teststring, "password"));
	}

}
