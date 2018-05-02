package demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class DemoRestController {

    @GetMapping(value = "/demo")
    public String getTrucksInDateRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    ZonedDateTime theDate) {
        return DateTimeFormatter.ISO_DATE_TIME.format(theDate);
    }

}
