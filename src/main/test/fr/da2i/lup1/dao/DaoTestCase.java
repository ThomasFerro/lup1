package fr.da2i.lup1.dao;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.formation.Formation;
import fr.da2i.lup1.entity.formation.Promotion;
import fr.da2i.lup1.entity.note.Mark;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.util.DaoProvider;

public class DaoTestCase extends TestCase {

	@Test
	public void test_marks() throws SQLException {
		Dao<Mark, Integer> dao = DaoProvider.getDao(Mark.class);
		System.out.println("Std:" + dao.queryBuilder().where().eq("student_id", 3).and().eq("semester", 5).queryForFirst());
	}
	
	@Test
	public void test_credentials() throws SQLException {
		Dao<Credential, String> dao = DaoProvider.getDao(Credential.class);
		Credential c = dao.queryForId("hauspiem");
		System.out.println(c);
	}
	
	@Test
	public void test_promotions() throws SQLException {
		Dao<Promotion, Integer> dao = DaoProvider.getDao(Promotion.class);
		Promotion promo = dao.queryBuilder().where().eq("formation_id", 1).and().eq("year", "2015-2016").queryForFirst();
		System.out.println(promo);
	}
	
	@Test
	public void test_formations() throws SQLException {
		Dao<Formation, Integer> dao = DaoProvider.getDao(Formation.class);
		Formation f = dao.queryForId(1);
		System.out.println(f);
	}

}
