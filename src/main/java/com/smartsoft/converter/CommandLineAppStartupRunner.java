package com.smartsoft.converter;

import com.smartsoft.converter.services.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private InitService initService;

    public CommandLineAppStartupRunner(InitService initService) {
        this.initService = initService;
    }

    @Override
    public void run(String... args) throws Exception {
        initService.init();
    }
}
