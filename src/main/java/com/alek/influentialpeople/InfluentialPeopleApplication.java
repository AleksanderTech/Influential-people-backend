package com.alek.influentialpeople;

import com.alek.influentialpeople.hero.HeroScore.domain.HeroScore;
import com.alek.influentialpeople.hero.HeroScore.domain.HeroScoreKey;
import com.alek.influentialpeople.hero.HeroScore.persistence.HeroScoreRepository;
import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InfluentialPeopleApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private HeroScoreRepository heroScoreRepository;

    public static void main(String[] args) {

        SpringApplication.run(InfluentialPeopleApplication.class, args);

    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("olek","olek");
        Hero hero =new Hero("Stalin");
        HeroScore heroScore=new HeroScore(new HeroScoreKey("olek",1),hero,user,6);
        userRepository.save(user);
        heroRepository.save(hero);
        heroScoreRepository.save(heroScore);


    }
}
