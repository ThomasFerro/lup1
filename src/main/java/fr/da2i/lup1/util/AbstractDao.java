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
import java.util.Map;
import java.util.Set;

public class AbstractDao<ID extends Serializable, T> implements Dao<ID, T> {
	
	protected String keyName;
	protected String table;
	
	public AbstractDao(String keyName, String table) {
		this.keyName = keyName;
		this.table = table;
	}
	
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<java.util.Map.Entry<ID, T>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<ID> keySet() {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<T> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws Exception {}

}
