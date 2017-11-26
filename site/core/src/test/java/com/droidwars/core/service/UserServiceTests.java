package com.droidwars.core.service;

import com.droidwars.core.context.DataSourceTestContext;
import com.droidwars.core.entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

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

    @Test
    public void findOneTest() {
        User one = userService.findOne(1L);

        assertEquals("user1", one.getUsername());
        assertEquals(1, one.getId());
        assertEquals("testpass", one.getPassword());
        assertEquals(LocalDateTime.of(2017, 11, 26, 10, 0, 0), one.getRegisterDate());
    }

    @Test
    public void findAllTest() {
        List<User> all = userService.findAll();

        assertEquals(2, all.size());
        assertEquals(User.class, all.get(0).getClass());
    }

    @Test
    public void findByUsernameTest() {
        UserDetails one = userService.loadUserByUsername("user2");

        assertEquals("user2", one.getUsername());
        assertEquals(true, one.isEnabled());
        assertEquals(true, one.isAccountNonLocked());
        assertEquals("testpass", one.getPassword());
        assertEquals(null, one.getAuthorities());
    }

}
