package fr.da2i.lup1.dao;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.entity.Task;
import fr.da2i.lup1.security.Passwords;
import fr.da2i.lup1.security.SecurId;
import fr.da2i.lup1.util.DaoProvider;

public class DaoTestCase extends TestCase {
	
	@BeforeClass
	public static void createTable() throws SQLException {
		TableUtils.createTableIfNotExists(DaoProvider.getConnectionSource(), Task.class);
		TableUtils.clearTable(DaoProvider.getConnectionSource(), Task.class);
	}

//	@Test
//	public void test_taskDao() throws SQLException {
//		Dao<Task, Integer> taskDao = DaoProvider.getDao(Task.class);
//		//memberDao.clear();
//		Task t1 = new Task("Ceci est ma première tâche");
//		Task t2 = new Task(1, "Ceci est ma première tâche modifiée");
//		System.out.println(taskDao.countOf());
//		System.out.println(taskDao.create(t1));
//		System.out.println(t1);
//		System.out.println(taskDao.update(t2));
//	}
	
	@Before
	public void test_createToken() throws SQLException {
		Dao<SecurId, String> securidDao = DaoProvider.getDao(SecurId.class);
		SecurId securId = new SecurId("catteze");
		securidDao.create(securId);
	}
	
	@Test
	public void test_memberDao() throws SQLException {
		Dao<Member, Integer> memberDao = DaoProvider.getDao(Member.class);
		System.out.println(memberDao.queryForAll());
		assertTrue(true);
		
		System.out.println(Passwords.hash("catteze"));
	}

}
