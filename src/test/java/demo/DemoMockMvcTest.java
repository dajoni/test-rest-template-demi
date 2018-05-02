package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DemoRestController.class)
@RunWith(SpringRunner.class)
public class DemoMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void call_service() throws Exception {
        String dateToFormat = "2015-05-31T05:00:00+04:00";
        mockMvc.perform(
                MockMvcRequestBuilders.get("/demo")
                        .param("theDate", dateToFormat))
                .andExpect(status().isOk())
                .andExpect(content().string(dateToFormat));
    }
}