package co.uk.flyingpiglabs.projectnom.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.uk.flyingpiglabs.projectnom.dao.datastructures.Ingredient;
import co.uk.flyingpiglabs.projectnom.dao.interfaces.IngredientDAOUtils;

public class IngredientDAOUtilsImpl implements IngredientDAOUtils {

	private final Connection connection;

	private static final String INSERT_SQL = "insert into ingredients values (default,?)";
	private static final String UPDATE_SQL = "update ingredients set ingredient_name = ? where ingredient_id = ?";
	private static final String DELETE_SQL = "delete from ingredients where ingredient_id = ?";
	private static final String SELECT_SINGLE_SQL_ID = "select ingredient_id, ingredient_name from ingredients where ingredient_id = ?";
	private static final String SELECT_SINGLE_SQL_NAME = "select ingredient_id, ingredient_name from ingredients where ingredient_name = ?";
	private static final String SELECT_ALL_SQL = "select ingredient_id, ingredient_name from ingredients";

	public IngredientDAOUtilsImpl(Connection connection) {
		this.connection = connection;
	}

	public boolean persistIngredient(Ingredient ingredient) {
		if (ingredient.getId() == -1) {
			return insertIngredient(ingredient);
		} else {
			return updateIngredient(ingredient);
		}
	}

	private boolean updateIngredient(Ingredient ingredient) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_SQL);
			statement.setString(1, ingredient.getName());
			statement.setLong(2, ingredient.getId());
			statement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	private boolean insertIngredient(Ingredient ingredient) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_SQL);
			statement.setString(1, ingredient.getName());
			statement.executeUpdate();
			ingredient = getIngredientByName(ingredient.getName());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public boolean deleteIngredient(Ingredient ingredient) {
		if (ingredient.getId() != -1) {
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(DELETE_SQL);
				statement.setLong(1, ingredient.getId());
				statement.executeUpdate();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				return false;
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return true;
	}

	public Ingredient getIngredientByName(String name) {
		Ingredient ingredient = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_SINGLE_SQL_NAME);
			statement.setString(1, name);
			rs = statement.executeQuery();
			if (rs.next()) {
				ingredient = new Ingredient(rs.getLong(1), rs.getString(2));
			}
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ingredient;
	}

	public Ingredient getIngredientByID(long id) {
		Ingredient ingredient = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_SINGLE_SQL_ID);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				ingredient = new Ingredient(rs.getLong(1), rs.getString(2));
			}
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ingredient;
	}

	public List<Ingredient> getAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_SQL);
			rs = statement.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient(rs.getLong(1),
						rs.getString(2));
				ingredients.add(ingredient);
			}
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return ingredients;
	}

}
