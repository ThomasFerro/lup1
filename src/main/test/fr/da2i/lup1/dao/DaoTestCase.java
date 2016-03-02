package fr.da2i.lup1.dao;

import java.sql.SQLException;
import java.text.ParseException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.nimbusds.jwt.SignedJWT;

import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.security.Passwords;
import fr.da2i.lup1.security.SecureKey;
import fr.da2i.lup1.util.DaoProvider;

public class DaoTestCase extends TestCase {
	
	@Before
	public void test_createToken() throws SQLException {
		TableUtils.createTableIfNotExists(DaoProvider.getConnectionSource(), SecureKey.class);
		TableUtils.clearTable(DaoProvider.getConnectionSource(), SecureKey.class);
		Dao<SecureKey, String> secureKeyDao = DaoProvider.getDao(SecureKey.class);
		SecureKey secureKey = new SecureKey("catteze");
		System.out.println(secureKey.regenerate().serialize());
		secureKeyDao.create(secureKey);
		
		
		SignedJWT signedJWT = secureKey.regenerate();
		try {
			System.out.println(signedJWT.getJWTClaimsSet().getExpirationTime());
			System.out.println(secureKey.hasExpire(signedJWT));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_memberDao() throws SQLException {
		Dao<Member, Integer> memberDao = DaoProvider.getDao(Member.class);
		System.out.println(memberDao.queryForAll());
		assertTrue(true);
		
		System.out.println(Passwords.hash("catteze"));
	}

}
