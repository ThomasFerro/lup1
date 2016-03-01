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
package fr.da2i.lup1.io;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Offre des fonctionnalités pour manipuler les fichiers via leurs chemins d'accès.
 */
public final class IO {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private IO() {}
	
	/**
	 * Crée l'ensemble des dossiers formant le chemin d'accès passé en paramètre
	 * 
	 * @param	dir
	 * 			les dossiers à créer sous forme d'un chemin d'accès
	 * 
	 * @return	le chemin d'accès du dossier à créer
	 */
	public static Path mkdirs(Path dir) {
		try {
			 Files.createDirectories(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dir;
	}
	
	/**
	 * Archive un fichier ou un dossier
	 * 
	 * @param	src
	 * 			le chemin d'accès du fichier à archiver
	 * @param	dest
	 * 			le chemin d'accès du répertoire temporaire
	 * 
	 * @return	le chemin d'accès de l'archive
	 */
	public static Path mkzip(Path src, Path dest) {
		Map<String, Object> env = new HashMap<>();
		env.put("create", "true");
		env.put("useTempFile", true);
		
		Path destParent = dest.getParent();
		if (!Files.exists(destParent)) {
			mkdirs(destParent);
		}
		
		URI uri = URI.create("jar:" + dest.toUri());
		
		try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
			Iterable<Path> roots = fs.getRootDirectories();
			Path root = roots.iterator().next();
			Files.walkFileTree(src, new CopyFileVisitor(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest;
	}
	
	/**
	 * Supprime le fichier accessible via le chemin passé en paramètre.
	 * Attention: si le fichier est un dossier, on supprime le dossier récursivement.
	 * 
	 * @param	src
	 * 			le chemin d'accès du fichier à supprimer
	 * 
	 * @return	le chemin d'accès du fichier à supprimer
	 */
	public static Path delete(Path src) {
		try {
			Files.walkFileTree(src, new DeleteFileVisitor());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return src;
	}
	
	/**
	 * Copie un fichier à un emplacement sur le disque
	 * 
	 * @param	src
	 * 			le chemin du fichier à copier stocké dans la zone temporaire du disque
	 * @param	dest
	 * 			le chemin du fichier de destination
	 * 
	 * @return	vrai si la copie s'est bien déroulée, faux sinon
	 */
	public static boolean copy(Path src, Path dest) {
		Path destParent = dest.getParent();
		if (!Files.exists(destParent)) {
			mkdirs(destParent);
		}
		try (FileChannel fchin = FileChannel.open(src);
				FileChannel fchout = FileChannel.open(dest, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			return fchin.transferTo(0, fchin.size(), fchout) > 0;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Charge un fichier de propriétés
	 * 
	 * @param 	src
	 * 			le chemin d'accès du fichier de propriétés
	 * 
	 * @return	les propriétés stockées dans le fichier
	 */
	public static Properties loadProperties(Path src) {
		if (Files.isRegularFile(src)) {
			try {
				Properties properties = new Properties();
				properties.load(Files.newBufferedReader(src, Charset.forName("UTF-8")));
				return properties;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new NullPointerException("Could not load properties from " + src);
	}

}
