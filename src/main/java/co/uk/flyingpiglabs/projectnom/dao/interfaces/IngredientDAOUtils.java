package co.uk.flyingpiglabs.projectnom.dao.interfaces;

import java.util.List;

import co.uk.flyingpiglabs.projectnom.dao.datastructures.Ingredient;

public interface IngredientDAOUtils {

	public boolean persistIngredient(Ingredient ingredient);

	public boolean deleteIngredient(Ingredient ingredient);

	public Ingredient getIngredientByName(String name);

	public Ingredient getIngredientByID(long id);

	public List<Ingredient> getAllIngredients();

}
