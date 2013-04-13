package co.uk.flyingpiglabs.projectnom.dao.implementations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.uk.flyingpiglabs.projectnom.dao.datastructures.Ingredient;
import co.uk.flyingpiglabs.projectnom.dao.interfaces.IngredientDAOUtils;

public class IngredientDAOUtilsTest {

	@Mock
	Connection connection;

	private IngredientDAOUtils ingredientUtils;

	@Before
	public void setUp() throws Exception {
		connection = Mockito.mock(Connection.class);
		ingredientUtils = new IngredientDAOUtilsImpl(connection);
	}

	@Test
	public void testInsertPersistIngredient() {
		Ingredient ingredient = new Ingredient("Cheese");
		assertTrue(ingredientUtils.persistIngredient(ingredient));
		assertTrue(ingredient.getId() != -1);
	}

	@Test
	public void testDeleteIngredient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIngredientByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIngredientByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllIngredients() {
		fail("Not yet implemented");
	}

}
