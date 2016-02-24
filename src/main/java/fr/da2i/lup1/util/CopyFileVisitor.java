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

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Aide à la copie de fichiers notamment pour la copie de dossiers.
 */
public class CopyFileVisitor extends SimpleFileVisitor<Path> {
	
	private final Path target;
	private Path source;
	
	public CopyFileVisitor(Path target) {
		this.target = target;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
		if (source == null) {
			source = dir;
		}
		else {
			Files.createDirectories(target.resolve(source.relativize(dir).toString()));
		}
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
		Files.copy(file, target.resolve(source.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
		return FileVisitResult.CONTINUE;
	}

}
