package fr.da2i.lup1.dao;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.note.Mark;
import fr.da2i.lup1.entity.security.Credential;
import fr.da2i.lup1.util.DaoProvider;

public class DaoTestCase extends TestCase {

	@Test
	public void test_marks() throws SQLException {
		Dao<Mark, Integer> dao = DaoProvider.getDao(Mark.class);
		System.out.println("All:" + dao.queryForAll());
		System.out.println("Std:" + dao.queryBuilder().where().eq("student_id", 3).and().eq("semester", 5).query());
	}
	
	@Test
	public void test_credentials() throws SQLException {
		Dao<Credential, String> dao = DaoProvider.getDao(Credential.class);
		Credential c = dao.queryForId("hauspiem");
		System.out.println(c);
	}

}
