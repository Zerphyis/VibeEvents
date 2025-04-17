package dev.Zerpyhis.VibeEvents.repositorys;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

}
