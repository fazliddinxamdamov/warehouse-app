package pdp.uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query(value = "select t from Category t where t.parentCategory is null and t.active = true")
    List<Category> findAllParentCategories();

    @Query(value = "select t from Category t where t.parentCategory = :parentCategory and t.active = true")
    List<Category> findAllChildren(Category parentCategory);

    Optional<Category> findByName(String name);

}
