package fr.da2i.lup1.dao;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import fr.da2i.lup1.entity.Task;
import fr.da2i.lup1.util.DaoProvider;

public class DaoTestCase {
	
	@BeforeClass
	public static void createTable() throws SQLException {
		TableUtils.dropTable(DaoProvider.getConnectionSource(), Task.class, true);
		TableUtils.createTableIfNotExists(DaoProvider.getConnectionSource(), Task.class);
	}

	@Test
	public void test() throws SQLException {
		Dao<Task, Integer> taskDao = DaoProvider.getDao(Task.class);
		//memberDao.clear();
		Task t1 = new Task("Ceci est ma première tâche");
		Task t2 = new Task(null, "Ceci est ma première tâche modifiée");
		System.out.println(taskDao.countOf());
		System.out.println(taskDao.create(t1));
		System.out.println(t1);
		System.out.println(taskDao.update(t2));
	}

}
