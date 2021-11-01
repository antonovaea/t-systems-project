package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.TopCategory;


@Repository
public interface TopCategoryRepository extends JpaRepository<TopCategory, Integer> {

    TopCategory findTopCategoryByCategory_Id(int categoryId);
}
