package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DemoRestTemplateTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void test_using_uri_components() throws Exception {
        String dateToFormat = "2015-05-31T18:30:00+04:00";
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/demo")
                .queryParam("theDate", dateToFormat).build();

        String formattedDate = restTemplate.getForObject(uriComponents.toUriString(), String.class);

        assertThat(formattedDate).isEqualTo(dateToFormat);
    }

    @Test
    public void test_using_uri_components_with_encode() throws Exception {
        String dateToFormat = "2015-05-31T18:30:00+04:00";
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/demo")
                .queryParam("theDate", dateToFormat).build(false).encode();

        String formattedDate = restTemplate.getForObject(uriComponents.toUriString(), String.class);

        assertThat(formattedDate).isEqualTo(dateToFormat);
    }

    @Test
    public void test_using_uri_components_with_explicit_encode() throws Exception {
        String dateToFormat = "2015-05-31T18:30:00+04:00";
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/demo")
                .queryParam("theDate", URLEncoder.encode(dateToFormat, "utf-8")).build(true);

        String formattedDate = restTemplate.getForObject(uriComponents.toUriString(), String.class);

        assertThat(formattedDate).isEqualTo(dateToFormat);
    }


    @Test
    public void test_using_uri_string() throws Exception {
        String dateToFormat = "2015-05-31T18:30:00+04:00";
        String uri = "/demo?theDate=" + dateToFormat;

        String formattedDate = restTemplate.getForObject(uri, String.class);

        assertThat(formattedDate).isEqualTo(dateToFormat);
    }

    @Test
    public void test_using_uri_escaped_string() throws Exception {
        String dateToFormat = "2015-05-31T18:30:00+04:00";
        String escapedDateToFormat = "2015-05-31T18:30:00%2B04:00";
        String uri = "/demo?theDate=" + escapedDateToFormat;

        String formattedDate = restTemplate.getForObject(uri, String.class);

        assertThat(formattedDate).isEqualTo(dateToFormat);
    }

}
