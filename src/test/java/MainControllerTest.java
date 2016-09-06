/**
 * Created by tshiamotaukobong on 2016/09/03.
 */

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.javaspringmvc.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString(" action=\"/login\" method=\"POST\"")));
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform((post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "admin"))
                .param("password","admin"))
                .andExpect(redirectedUrl("Home"));
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform((get("/Home")
                .sessionAttr("token", "71456dbd15de0c0b6d2b4b44e5a92ad94c6def97")))
                .andExpect(content().string(containsString("<th>Title</th>\n" +
                        "        <th>Description</th>\n" +
                        "        <th>Start date</th>\n" +
                        "        <th>End date</th>")));
    }

    @Test
    public void testNotLoggedIn() throws Exception {
        mockMvc.perform(get("/Home"))
                .andExpect(content().string(containsString(" action=\"/login\" method=\"POST\"")));
    }
}
