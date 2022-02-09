package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entities.Recipe;
import recipes.services.RecipeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found!");
        }
        return recipe.get();
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Integer> postRecipe(@Valid @RequestBody Recipe rec) {
        Recipe newRecipe = new Recipe(rec.getName(), rec.getCategory(), LocalDateTime.now(), rec.getDescription(),
                rec.getIngredients(), rec.getDirections());
        newRecipe.setEmail(getLoggedInUserEmail());
        recipeService.saveRecipe(newRecipe);
        return Map.of("id", newRecipe.getId());
    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found!");
        } else if (!recipe.get().getEmail().equals(getLoggedInUserEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own recipes!");
        }
        recipeService.deleteRecipe(id);
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putRecipe(@PathVariable int id, @Valid @RequestBody Recipe newRecipe) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found!");
        } else if (!recipe.get().getEmail().equals(getLoggedInUserEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own recipes!");
        }
        recipeService.updateRecipe(newRecipe, id);
    }

    @GetMapping(value = "/api/recipe/search", params = "name")
    public List<Recipe> getRecipeByName(@RequestParam("name") String name) {
        return recipeService.findByName(name);
    }

    @GetMapping(value = "/api/recipe/search", params = "category")
    public List<Recipe> getRecipeByCategory(@RequestParam("category") String category) {
        return recipeService.findByCategory(category);
    }

    public String getLoggedInUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }

}
