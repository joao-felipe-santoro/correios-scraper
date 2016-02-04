package br.com.concretesolutions.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

/**
 * The Class ApplicationConfig.
 *
 * @author jfelipesp
 */
@Configuration
@ComponentScan(basePackages = { "br.com.concretesolutions" }, excludeFilters = { @Filter(Configuration.class) })
@EnableAutoConfiguration
public class ApplicationConfig extends SpringBootServletInitializer {

  private static Class<ApplicationConfig> applicationClass = ApplicationConfig.class;
  
  public static void main(final String[] args) {
    final SpringApplication app = new SpringApplication(applicationClass);
    app.run(args);
  }
  
}
