package kz.ktzh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import kz.ktzh.models.UserInfo;
import kz.ktzh.repo.UserInfoRepository;
import kz.ktzh.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserInfoRepository userInfoMock;

	@Autowired
	UserService userImpl;

	private UserInfo ui;

	@Before
	public void init() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1990);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date bd = cal.getTime();
		ui = new UserInfo(1, "user", "Гадя", "Хренова", "Петрович", bd, "улица пушкина дом колотушкина", 1, 1, 1, 1, 1,
				1, "88005553535", "makaka@programmistov.net", "ученный 3 класса образования");
	}

	@Test
	public void testGetUserInfo() {
		when(userInfoMock.findByUsername("user")).thenReturn(ui);
		assertEquals(ui, userImpl.getUserInfo("user"));
	}

}
