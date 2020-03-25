package ru.mail.migus_nikita.bot.web.app;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mockMvc;

    @BeforeClass
    public static void initialize() {
        ApiContextInitializer.init();
    }

    @Before
    public void init() throws SQLException {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/travel;user=test;password=test;");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM T_CITY WHERE F_ID = 100");
            statement.executeUpdate("INSERT INTO T_CITY (F_ID, F_NAME, F_INFO)" +
                    " VALUES (100, 'Avtozavodsk', 'Leave this city!')");
        }
    }

    @Test
    public void shouldGetSucceedWith200AfterGettingCities() throws Exception {
        this.mockMvc.perform(get("/api/v1/cities"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSucceedWith200AfterGettingTheCity() throws Exception {
        this.mockMvc.perform(get("/api/v1/cities/100"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSucceedWith200AfterAddingTheCity() throws Exception {
        this.mockMvc.perform(post("/api/v1/cities")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"name\": \"Moscow\"," +
                        "\"info\": \"You need to visit Red Square\"" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSucceedWith200AfterChangingTheCity() throws Exception {
        this.mockMvc.perform(put("/api/v1/cities/100")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"name\": \"Moscows\"," +
                        "\"info\": \"You need to visit another place\"" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSucceedWith200AfterDeletingTheCity() throws Exception {
        this.mockMvc.perform(delete("/api/v1/cities/100"))
                .andExpect(status().is2xxSuccessful());
    }

}
