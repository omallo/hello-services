package io.staek.helloservices;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

@SpringBootApplication
@RestController
public class HelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }

  @SneakyThrows
  @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  public String greet(@Value("${hello.greeting:}") String greeting, @Value("${hello.secret:}") String secret) {
    StringBuilder sb = new StringBuilder();
    sb.append("hostname : ").append(InetAddress.getLocalHost().getHostName()).append('\n');
    sb.append("greeting : ").append(greeting).append('\n');
    sb.append("secret   : ").append(secret).append('\n');
    return sb.toString();
  }

}
