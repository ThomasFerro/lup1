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

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.skife.jdbi.v2.Handle;

public class AbstractDao<ID extends Serializable, T> implements Dao<ID, T> {
	
	protected String keyName;
	protected String keyType;
	protected String table;
	
	/**
	 * Constructeur des DAO avec une seule clef primaire
	 * 
	 * @param keyName	Le nom de la clef primaire
	 * @param keyType	Le type de la clef primaire
	 * @param table		Le nom de la table
	 */
	public AbstractDao(String keyName, String keyType, String table) {
		this.keyName = keyName;
		this.keyType = keyType;
		this.table = table;
	}
	
	@Override
	public void clear() {
		Handle h = DaoProvider.openHandle();
		h.update("DELETE FROM " + this.table);
		h.close();
	}

	@Override
	public boolean containsKey(Object key) {
		return !select("SELECT * FROM " + this.table + " WHERE " + this.keyName + " = '" + key + "'").isEmpty();
	}

	@Override
	public boolean containsValue(Object value) {
		return select("SELECT * FROM " + this.table).contains(value);
	}

	@Override
	public Set<java.util.Map.Entry<ID, T>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(Object key) {
		return select("SELECT * FROM " + this.table + " WHERE " + this.keyName + " = '" + key + "'").get(0).get(key);
	}

	@Override
	public boolean isEmpty() {
		return select("SELECT 1 FROM " + this.table).isEmpty();
	}

	@Override
	public Set<ID> keySet() {
		throw new UnsupportedOperationException();
		//return select("SELECT " + this.keyName + " FROM " + this.table);
	}

	@Override
	public T put(ID key, T value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends ID, ? extends T> m) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public T remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		// A corriger après avoir corrigé select()
		//return Integer.parseInt((String) select("SELECT COUNT(*) FROM " + this.table).get(0).get("count"));
		Handle h = DaoProvider.openHandle();
		int ret = Integer.parseInt(	h.select("SELECT COUNT(*) FROM " + this.table).get(0).get("count").toString());
		h.close();
		return ret;
	}

	@Override
	public Collection<T> values() {
		//return select("SELECT * FROM " + this.table);
		return null;
	}

	@Override
	public void close() throws Exception {}
	
	private List<Map<ID, T>> select(String query) {
		Handle h = DaoProvider.openHandle();
		List<Map<String, Object>> ret = h.select(query);
		h.close();
		//return ret;
		return null;
	}

}
