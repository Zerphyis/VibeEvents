package dev.Zerpyhis.VibeEvents.repositorys;

import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventsEntity,Long> {
}
