package dev.Zerpyhis.VibeEvents.repositorys;

import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
