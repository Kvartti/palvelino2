package palvelino.harjoitustyo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 
import java.util.Locale;
 
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	 
	   @Bean
	   public LocaleResolver localeResolver() {
	       SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	       sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
	       return sessionLocaleResolver;
	   }
	 
	   @Bean
	   public LocaleChangeInterceptor localeChangeInterceptor() {
	       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	       lci.setParamName("lang");
	       return lci;
	   }
	   
	/*   @Bean
	    public MessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("message");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    } */
	   
	   /*   @Bean
	   public MessageSource messageSource() {
	       final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	       messageSource.setBasenames("classpath:/some-mvc-messages", "classpath:/some-other-mvc-messages", "classpath:/another-projects/mvc-messages");
	       messageSource.setUseCodeAsDefaultMessage(true);
	       messageSource.setDefaultEncoding("UTF-8");
	       messageSource.setCacheSeconds(5);
	       return messageSource;
	   }*/
	   
	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	       registry.addInterceptor(localeChangeInterceptor());
	   }
	}