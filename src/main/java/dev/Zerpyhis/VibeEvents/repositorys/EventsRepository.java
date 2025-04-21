package dev.Zerpyhis.VibeEvents.repositorys;

import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<EventsEntity,Long> {
}
