package com.droidwars.core.entity;

import com.droidwars.core.context.JsonTestContext;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {JsonTestContext.class})
@JsonTest
@Slf4j
public class UserJsonTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private JacksonTester<User> json;

    @Test
    public void serialize() throws IOException {
        User user = new User(1L, "user1", "pass1", "email1",
            LocalDateTime.of(2017, 1, 1, 10, 0, 0), true, Sets.newSet(Role.ADMIN),
            LocalDateTime.of(2017, 2, 1, 10, 0, 0));

        JsonContent<User> jsonContent = this.json.write(user);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(jsonContent).extractingJsonPathStringValue("@.username").isEqualTo("user1");
        assertThat(jsonContent).extractingJsonPathBooleanValue("@.enabled").isEqualTo(true);
        assertThat(jsonContent).extractingJsonPathArrayValue("@.registerDate").containsExactly(2017, 1, 1, 10, 0);
        assertThat(jsonContent).extractingJsonPathStringValue("@.pass").isNullOrEmpty();
        assertThat(jsonContent).extractingJsonPathStringValue("@.password").isNullOrEmpty();
        assertThat(jsonContent).extractingJsonPathStringValue("@.email").isNullOrEmpty();
        assertThat(jsonContent).doesNotHaveJsonPathValue("@.roles");
        assertThat(jsonContent).doesNotHaveJsonPathValue("@.passwordResetDate");
    }

    /**
     * Check that deserialization of User entity leads to unusable result
     */
    @Test
    public void deserialize() throws IOException {

        User user = this.json.parseObject("{\n" +
            "  \"id\": 1,\n" +
            "  \"username\": \"user2\",\n" +
            "  \"pass\": \"testpass\",\n" +
            "  \"password\": \"testpass2\",\n" +
            "  \"email\": \"email2\",\n" +
            "  \"registerDate\": [2017,11,26,21,10,42,719000000],\n" +
            "  \"passwordResetDate\": [2017,12,26,21,10,42,719000000],\n" +
            "  \"roles\": [\"ROLE_ADMIN\"],\n" +
            "  \"enabled\": \"true\"\n" +
            "}");

        assertEquals(0, user.getId());
        assertEquals(false, user.isEnabled());
        assertThat(user.getEmail()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getPass()).isNull();
        assertThat(user.getPass()).isNull();
        assertThat(user.getRegisterDate()).isNull();
        assertThat(user.getPasswordResetDate()).isNull();
        assertThat(user.getRoles()).isNullOrEmpty();
    }

}
