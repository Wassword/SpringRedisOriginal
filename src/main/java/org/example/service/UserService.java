package org.example.service;

import org.example.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Map<Long, User> userStore = new HashMap<>();
    private long currentId = 1;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userStore.get(id);
    }

    @CachePut(value = "users", key = "#result.id")
    public User createUser(User user) {
        user.setId(currentId++);
        userStore.put(user.getId(), user);
        return user;
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userStore.remove(id);
    }

    public List<User> getAllUsers() {
        return userStore.values().stream().collect(Collectors.toList());
    }

}
