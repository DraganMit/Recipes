package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.repo.RecipeRepo;
import recipes.entities.Recipe;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Optional<Recipe> findRecipeById(int id) {
        return recipeRepo.findById(id);
    }

    public void saveRecipe(Recipe rec) {
        recipeRepo.save(rec);
    }

    public void deleteRecipe(int id) {
        recipeRepo.deleteById(id);
    }

    public void updateRecipe(Recipe newRecipe, int id) {
        Recipe oldRecipe = recipeRepo.findById(id).get();
        oldRecipe.setName(newRecipe.getName());
        oldRecipe.setCategory(newRecipe.getCategory());
        oldRecipe.setDate(LocalDateTime.now());
        oldRecipe.setDescription(newRecipe.getDescription());
        oldRecipe.setIngredients(newRecipe.getIngredients());
        oldRecipe.setDirections(newRecipe.getDirections());
        saveRecipe(oldRecipe);
    }

    public List<Recipe> findByCategory(String category) {
        return recipeRepo.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByName(String name) {
        return recipeRepo.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

}
