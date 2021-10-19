package com.mycompany.mywebapp1;

import com.mycompany.mywebapp1.user.User;
import com.mycompany.mywebapp1.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setBirthday("112121");
        user.setPassword("dsfsfdfds");
        user.setFirstname("Sasukdsffdasdfs");
        user.setLastname("Uchihsdadssd");
        user.setEmail("killItachi@akatdsdsaffsdafsdi.jp");
        user.setPosition("Ninjdsasfdfsasdfaa");
        user.setPhone("1234567891111");

        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void findAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users ){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user  = optionalUser.get();
        user.setPosition("7Hokage");
        repo.save(user);

        User updateUser = repo.findById(userId).get();
        Assertions.assertThat(updateUser.getPosition()).isEqualTo("7Hokage");

    }

    @Test
    public void testGet() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete () {
        Integer userId = 4;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();

    }
}
