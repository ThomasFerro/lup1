package fr.da2i.lup1.organization;

import java.sql.SQLException;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.organization.Organization;
import fr.da2i.lup1.util.DaoProvider;

public class OrganizationTest {

	@Test
	public void test() {
		Dao<Organization, String> organizationDao = DaoProvider.getDao(Organization.class);
		
		try {
			System.out.println("All : " + organizationDao.queryForAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
