package link.grooverdev.web.api.employeesecurity.service.impl;

import link.grooverdev.web.api.employeesecurity.entity.GUser;
import link.grooverdev.web.api.employeesecurity.repository.GUserRepository;
import link.grooverdev.web.api.employeesecurity.service.IGUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GUserServiceImpl implements IGUserService {

    private final GUserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<GUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GUser> findAllEnabled(boolean enabled) {
        return userRepository.findAllByEnabledOrderByUserIdAsc(enabled);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GUser> findByUserID(long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public GUser save(GUser newGUser) {
        return userRepository.save(newGUser);
    }

    @Override
    @Transactional
    public GUser update(long id, GUser updGUser) {

        var updatedGUser = userRepository.findByUserId(id);

        if (updatedGUser.isPresent()) {
            updatedGUser.get().setUsername(updGUser.getUsername());
            updatedGUser.get().setPassword(updGUser.getPassword());
            updatedGUser.get().setEmail(updGUser.getEmail());
            updatedGUser.get().setRoles(updGUser.getRoles());
            updatedGUser.get().setEnabled(updGUser.isEnabled());
        }
        return userRepository.save(updatedGUser.get());
    }

    @Override
    @Transactional
    public GUser delete(long id) {

        var deletedGUser = userRepository.findByUserId(id);
        deletedGUser.ifPresent(delGUser -> deletedGUser.get().setEnabled(false));
        return userRepository.save(deletedGUser.get());
    }
}
