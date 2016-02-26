/**
 * This file is part of lup1.
 *
 * lup1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * lup1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with lup1.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.da2i.lup1.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.google.common.io.Resources;

/**
 * Fournit l'accès aux différents DAOs.
 */
public class DaoProvider {
	
	private static DaoProvider daoProvider;
	
	private DBI dbi;
	
	private DaoProvider() {
		try {
			URL url = Resources.getResource("config.properties");
			Properties props = IO.loadProperties(Paths.get(url.toURI()));
			Class.forName(props.getProperty("db.driver"));
			dbi = new DBI(props.getProperty("db.uri"), props.getProperty("db.username"), props.getProperty("db.password"));
		} catch (ClassNotFoundException | URISyntaxException e) {
			throw new DaoException(e);
		}
	}
	
	private static DaoProvider getInstance() {
		if (daoProvider == null) {
			daoProvider = new DaoProvider();
		}
		return daoProvider;
	}
	
	public static synchronized DBI getDBI() {
		return getInstance().dbi;
	}
	
	public static synchronized Handle openHandle() {
		return getDBI().open();
	}
	
	public static synchronized <T> T getDao(Class<T> daoObject) {
		try {
			return getInstance().dbi.onDemand(daoObject);
		} catch (Exception e) {
			throw new DaoException("No dao found for " + daoObject);
		}
	}

}
