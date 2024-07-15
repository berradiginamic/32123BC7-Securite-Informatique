package fr.diginamic.webmvc01;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fr.diginamic.webmvc01.providers.AppAuthProvider;
import fr.diginamic.webmvc01.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter{
	
	/**
	 * Ajout de l'implémentation du Provider
	 */
	@Autowired
	UserService userDetailsService;
	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	/**
	 * Une des méthodes de configuration de la sécurité de 
	 * mon application Web
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * J'autorise tous les accés à mon app web
		 */
		//http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		http.csrf().disable().
		authenticationProvider(getProvider()).
		formLogin().loginProcessingUrl("/login").and()
		.logout().logoutUrl("/logout").invalidateHttpSession(true).and()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.anyRequest().authenticated();
	}

}
