package com.ag04smarts.sha;

import com.ag04smarts.sha.services.InitializeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.text.ParseException;

@SpringBootApplication
public class SHAApplication {

    private final ApplicationContext ctx;

    public SHAApplication(ApplicationContext ctx) {
        this.ctx = ctx;
    }

	public static void main(String[] args) {

	    SpringApplication.run(SHAApplication.class, args);

	}

	@PostConstruct
    public void initializationDev() throws ParseException {

        InitializeService initializeService = (InitializeService) ctx.getBean("InitializeService");
        initializeService.initialization();

    }

}
