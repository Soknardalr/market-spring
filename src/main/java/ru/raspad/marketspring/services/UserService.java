package ru.raspad.marketspring.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.CustomerDto;
import ru.raspad.marketspring.entity.Role;
import ru.raspad.marketspring.entity.User;
import ru.raspad.marketspring.mappers.CustomerMapper;
import ru.raspad.marketspring.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomerDto getCustomer(Long id){
        return CustomerMapper.INSTANCE.toDto(userRepository.findById(id).orElseThrow());
    }
    public List<CustomerDto> getAllCustomers(){
        return userRepository.findAll().stream().map(CustomerMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
    public void addCustomer(Long id, String name){
    }
    public void addCustomer(CustomerDto customerDto) {
        userRepository.save(CustomerMapper.INSTANCE.toDao(customerDto));
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    private Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}


