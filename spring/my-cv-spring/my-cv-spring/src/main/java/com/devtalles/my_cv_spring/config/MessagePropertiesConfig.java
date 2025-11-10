package com.devtalles.my_cv_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration()
 @PropertySources({
  @PropertySource("classpath:message.properties")
})
public class MessagePropertiesConfig {

}
