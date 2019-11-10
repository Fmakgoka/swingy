package za.co.wethinkcode;


import za.co.wethinkcode.heroes.Hero;
import za.co.wethinkcode.heroes.HeroFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;

public class Swingy {
	// public static EntityManagerFactory ENTITY_MANAGER_FACTORY =
	// Persistence.createEntityManagerFactory("za.co.wethinkcode.Swingy");
	public static void main(String[] args) {
		createTable();
		// List<Hero> heroes = getHeroesFromDB();
		// Hero player = heroes.get(0);
		// player.setHeroLevel(19);
		// player.setHeroExperience(player.getHeroExperience() + 20);
		// updateHero(player);
	}

	public static void createTable() {
		Connection c = null;
		Statement statement = null;
		try {
			c = connect();
			statement = c.createStatement();
			String sql = "CREATE TABLE Heroes (h_id INTEGER PRIMARY KEY AUTOINCREMENT," + "heroName TEXT NOT NULL,"
					+ "heroClass TEXT NOT NULL," + "heroLevel TEXT NOT NULL," + "heroAttack INTEGER,"
					+ "heroExperience INTEGER," + "heroDefense INTEGER," + "heroHitPoints INTEGER)";
					System.out.print("new table");
			statement.executeUpdate(sql);
			statement.close();
			c.close();
		} catch (Exception e) {

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
	}

	public static Connection connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:heroes.db";
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void createDatabase() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:heroes.db";
			conn = DriverManager.getConnection(url);
			if (conn != null)
				System.out.println("created a new database");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Hero> getHeroesFromDB() {
		Connection conn = null;
		List<Hero> heroes = null;
		// em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
		// try{
		// }
		// EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		// try {
		// em.getTransaction().begin();
		// List<Hero> heroes = em.createQuery("SELECT h FROM Hero h",
		// Hero.class).getResultList();
		// return heroes;
		// } catch (HibernateException e) {
		// e.printStackTrace();
		// } finally {
		// em.close();
		// }
		return null;
	}

	public static void addHero(String name, String heroClass) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Hero newHero = HeroFactory.newHero(name, heroClass);
		Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(newHero);
		if (!isEmpty(constraintViolations)) {
			System.out.printf("%s %s\n", constraintViolations.iterator().next().getConstraintDescriptor(),
					constraintViolations.iterator().next().getMessage());
			return;
		}

		String sql = "INSERT INTO Heroes (heroName, heroClass) VALUES(" +name +"," +heroClass + ")";

		// EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		// entityManager.getTransaction().begin();
		// entityManager.persist(newHero);
		// entityManager.getTransaction().commit();
		// entityManager.close();
	}

	// public static void updateHero(Hero player) {
	// 	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
	// 	entityManager.getTransaction().begin();
	// 	entityManager.merge(player);
	// 	entityManager.getTransaction().commit();
	// 	entityManager.close();
	// }
}
