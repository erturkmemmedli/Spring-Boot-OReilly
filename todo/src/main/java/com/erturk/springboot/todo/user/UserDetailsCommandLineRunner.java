package com.erturk.springboot.todo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
    public UserDetailsCommandLineRunner(UserDetailsRepository userDetailsRepository) {
        super();
        this.userDetailsRepository = userDetailsRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserDetailsRepository userDetailsRepository;


    @Override
    public void run(String... args) throws Exception {
        logger.info(Arrays.toString(args));
        userDetailsRepository.save(new UserDetails("Ali", "Admin"));
        userDetailsRepository.save(new UserDetails("Veli", "Admin"));
        userDetailsRepository.save(new UserDetails("Deli", "User"));

        //System.out.println(userDetailsRepository.findAll());
    }
}
