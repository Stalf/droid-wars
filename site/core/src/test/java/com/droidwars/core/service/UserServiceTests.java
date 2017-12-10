package com.droidwars.core.service;

import com.droidwars.core.context.DataSourceTestContext;
import com.droidwars.core.entity.Role;
import com.droidwars.core.entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DataSourceTestContext.class})
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DatabaseSetup("UserServiceTests-dataset.xml")
@TestExecutionListeners({
    DbUnitTestExecutionListener.class
})
@Slf4j
public class UserServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;


    public void findOneTest() {
        User one = userService.findOne(1L);

        assertEquals("user1", one.getUsername());
        assertEquals(1, one.getId());
        assertEquals("testpass", one.getPass());
        assertEquals(LocalDateTime.of(2017, 11, 26, 10, 0, 0), one.getRegisterDate());
        assertThat(one.getRoles(), hasItem(Role.ADMIN));
        assertThat(one.getRoles(), hasItem(Role.USER));
    }

    public void findSecondTest() {
        User second = userService.findOne(2L);

        assertEquals("user2", second.getUsername());
        assertEquals(2, second.getId());
        assertEquals("testpass", second.getPass());
        assertThat(second.getRoles(), not(hasItem(Role.ADMIN)));
        assertThat(second.getRoles(), hasItem(Role.USER));
    }


    public void findAllTest() {
        List<User> all = userService.findAll();

        assertEquals(2, all.size());
        assertEquals(User.class, all.get(0).getClass());
    }


    public void findByUsernameTest() {
        UserDetails one = userService.loadUserByUsername("user2");

        assertNotNull(one);
        assertEquals(one.getUsername(), "user2");
        assertEquals(one.isEnabled(), true);
        assertEquals(one.isAccountNonLocked(), true);
        assertEquals(one.getPassword(), "testpass");
        assertFalse(one.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.getCode().name())));
        assertTrue(one.getAuthorities().contains(new SimpleGrantedAuthority(Role.USER.getCode().name())));
    }

}
