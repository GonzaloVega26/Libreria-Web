package com.libreriaWebApp.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libreriaWebApp.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class ConfiguracionesSeguridad extends WebSecurityConfigurerAdapter {
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll().and().formLogin()
				.loginPage("/login") // Que formulario esta mi login
				.loginProcessingUrl("/logincheck").usernameParameter("username") // Como viajan los datos del logueo
				.passwordParameter("password")// Como viajan los datos del logueo
				.defaultSuccessUrl("/inicio") // A que URL viaja
				.permitAll().and().logout() // Aca configuro la salida
				.logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}
	
	*/
	
	@Autowired
	private UsuarioServicio userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    /*@Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
    	.withUser("admin") //este es tu nombre de usuario de admin
    	.password("{noop} 123") // 123 es la clave 
    	.roles("USER","ADMIN")
    	.and()
    	.withUser("user") // este ees tu nombre de usuario de user comun
    	.password("{noop} 123") // 123 es la clave 
    	.roles("USER")
    	;
    }
	
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/autor/modificarAutor/**"
				,"/editorial/modificarEditorial/**"
				,"/libro/modificarLibro/**").hasRole("ADMIN")
		.antMatchers("/css/*", "/js/*", "/img/*"
				,"/editorial/listaEditoriales/**"
				,"/autor/listaAutores/**"
				).hasAnyRole("USER","ADMIN")
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		
		
		
		;
	}
}
