package com.andersonmorales.kinalapp.config;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final KinalUserDetailsService userDetailsService;

    public SecurityConfig(KinalUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth

                        // Rutas públicas
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/login",
                                "/registro"
                        ).permitAll()

                        // Ventas autenticadas
                        .requestMatchers(
                                HttpMethod.POST,
                                "/ventas/**",
                                "/detalleVentas/**"
                        ).authenticated()

                        // Solo ADMIN puede crear
                        .requestMatchers(
                                HttpMethod.POST,
                                "/clientes/**",
                                "/productos/**",
                                "/usuarios/**"
                        ).hasRole("ADMIN")

                        // Solo ADMIN puede actualizar
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/clientes/**",
                                "/productos/**",
                                "/ventas/**",
                                "/detalleVentas/**",
                                "/usuarios/**"
                        ).hasRole("ADMIN")

                        // Solo ADMIN puede eliminar
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/clientes/**",
                                "/productos/**",
                                "/ventas/**",
                                "/detalleVentas/**",
                                "/usuarios/**"
                        ).hasRole("ADMIN")

                        // Usuarios autenticados pueden consultar
                        .requestMatchers(
                                HttpMethod.GET,
                                "/clientes/**",
                                "/productos/**",
                                "/ventas/**",
                                "/detalleVentas/**",
                                "/usuarios/**"
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner inicializarUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder encoder
    ) {

        return args -> {

            // Usuario normal
            if (!usuarioRepository.existsByUsername("user")) {

                Usuario usuario = new Usuario();
                usuario.setUsername("user");
                usuario.setPassword(encoder.encode("12345"));
                usuario.setNombreCompleto("Usuario Predeterminado");
                usuario.setEmail("user@kinalapp.com");
                usuario.setRol("USER");
                usuario.setEstado(1);
                usuario.setFechaRegistro(LocalDateTime.now());

                usuarioRepository.save(usuario);
            }

            // Administrador
            if (!usuarioRepository.existsByUsername("admin")) {

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin"));
                admin.setNombreCompleto("Administrador");
                admin.setEmail("admin@kinalapp.com");
                admin.setRol("ADMIN");
                admin.setEstado(1);
                admin.setFechaRegistro(LocalDateTime.now());

                usuarioRepository.save(admin);
            }
        };
    }

    @Service
    public static class KinalUserDetailsService implements UserDetailsService {

        private final UsuarioRepository usuarioRepository;

        public KinalUserDetailsService(
                UsuarioRepository usuarioRepository
        ) {
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {

            Usuario usuario = usuarioRepository
                    .findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "Usuario no encontrado: " + username
                            )
                    );

            // Validar estado del usuario
            if (usuario.getEstado() == null
                    || usuario.getEstado() != 1) {

                throw new UsernameNotFoundException(
                        "Usuario inactivo: " + username
                );
            }

            // Obtener rol
            String rol = (usuario.getRol() == null
                    || usuario.getRol().isBlank())
                    ? "USER"
                    : usuario.getRol().trim().toUpperCase();

            String authority = rol.startsWith("ROLE_")
                    ? rol
                    : "ROLE_" + rol;

            return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword())
                    .authorities(
                            List.of(
                                    new SimpleGrantedAuthority(authority)
                            )
                    )
                    .build();
        }
    }
}