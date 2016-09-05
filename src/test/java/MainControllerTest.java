/**
 * Created by tshiamotaukobong on 2016/09/03.
 */

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.javaspringmvc.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexPage() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(content().string(containsString("<form action=\"/login\" method=\"POST\">")));
    }

    //@Test
    public void homePage() throws Exception {
        mockMvc.perform((post("/login").param("username", "tshiamo")).param("password","pass123"))
                .andExpect(content().string(containsString("Successfully logged in, tshiamo!")));
    }

}
