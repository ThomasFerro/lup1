package fr.da2i.lup1.formation;

import java.sql.SQLException;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Formation;
import fr.da2i.lup1.util.DaoProvider;
import junit.framework.TestCase;

public class FormationTest extends TestCase {

	@Test
	public void tests() {
		System.out.println("Test");
		Dao<Formation, Integer> dao = DaoProvider.getDao(Formation.class);
		try {
			dao.create(new Formation(100, "FormationTest"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
