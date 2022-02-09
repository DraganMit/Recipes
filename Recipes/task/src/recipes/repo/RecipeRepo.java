package recipes.repo;

import org.springframework.data.repository.CrudRepository;
import recipes.entities.Recipe;

import java.util.List;

public interface RecipeRepo  extends CrudRepository<Recipe, Integer> {

    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<Recipe> findAllByNameContainingIgnoreCaseOrderByDateDesc(String name);

}
