package co.com.ud.acceptance.steps;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class StepRoot {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected WebTestClient webTestClient;
}
