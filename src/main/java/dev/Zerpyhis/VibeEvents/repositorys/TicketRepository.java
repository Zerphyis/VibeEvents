package dev.Zerpyhis.VibeEvents.repositorys;

import dev.Zerpyhis.VibeEvents.entitys.Ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
