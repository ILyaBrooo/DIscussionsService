package com.project.discussion.repository;

import com.project.discussion.entity.Discussions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussions, Long> {

    // Сюда можно сразу добавить полезные методы.
    // Spring Data JPA сам превратит их в SQL-запросы по имени метода.

    // Найти обсуждения по части заголовка
    List<Discussions> findByTitleContainingIgnoreCase(String titlePart);

    // Найти все обсуждения конкретного автора (creator_id)
    List<Discussions> findByCreatorId(Long creatorId);
}