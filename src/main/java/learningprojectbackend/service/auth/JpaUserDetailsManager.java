package learningprojectbackend.service.auth;

import learningprojectbackend.config.SecurityUser;
import learningprojectbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsManager implements UserDetailsService {
    private final UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsernameIgnoreCase(username).map(SecurityUser::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(email).map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));

    }
}
