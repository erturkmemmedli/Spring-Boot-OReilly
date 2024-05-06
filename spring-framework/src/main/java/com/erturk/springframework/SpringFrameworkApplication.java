package com.erturk.springframework;

import com.erturk.springframework.enterprise.example.web.MyWebController;
import com.erturk.springframework.game.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringFrameworkApplication.class, args);
		// tightly-coupling
		//GamingConsole game = new PacmanGame();
		//GameRunner runner = new GameRunner(game);
		//runner.run();

		//loosely-coupling
		GameRunner runner = context.getBean(GameRunner.class);
		runner.run();

		MyWebController controller = context.getBean(MyWebController.class);
		System.out.println(controller.returnValueFromBusinessService());
	}

}
