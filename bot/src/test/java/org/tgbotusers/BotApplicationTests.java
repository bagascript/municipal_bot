package org.tgbotusers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles
@TestPropertySource(locations = "classpath:./application.yml")
class BotApplicationTests {

	@Test
	void contextLoads() {
	}

}
