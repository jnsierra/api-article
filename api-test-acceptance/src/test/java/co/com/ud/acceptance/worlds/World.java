package co.com.ud.acceptance.worlds;

import org.springframework.test.web.reactive.server.WebTestClient;

public class World {
    private WebTestClient webTestClient;
    private WebTestClient.ResponseSpec responseSpec;

    public WebTestClient getWebTestClient() {
        return webTestClient;
    }

    public void setWebTestClient(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    public WebTestClient.ResponseSpec getResponseSpec() {
        return responseSpec;
    }

    public void setResponseSpec(WebTestClient.ResponseSpec responseSpec) {
        this.responseSpec = responseSpec;
    }
}
