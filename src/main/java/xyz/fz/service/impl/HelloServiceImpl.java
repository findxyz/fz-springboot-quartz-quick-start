package xyz.fz.service.impl;

import org.springframework.stereotype.Service;
import xyz.fz.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
