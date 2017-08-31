package com.j4mt.app.gamecatolog.repository;

import com.j4mt.app.gamecatolog.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGameRepository_CreateGame() {

        Game saved = gameRepository.save(new Game("a", "b", "c", "d", 1, true));

        assertThat(saved.getId(), is(notNullValue()));
    }

    @Test
    public void testGameRepository_DeleteGame() {

        Game persisted = entityManager.persist(new Game("a", "b", "c", "d", 1, true));

        gameRepository.delete(persisted.getId());

        assertThat(gameRepository.findOne(persisted.getId()), is(nullValue()));
    }
}