package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
