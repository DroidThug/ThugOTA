/*
 * This file is part of ThugOTA.
 *
 * ThugOTA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ThugOTA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ThugOTA. If not, see <http://www.gnu.org/licenses/>.
 */

package delta.out386.thugota;

import java.io.File;
import java.io.Serializable;

public class Flashables implements Serializable
{
	File file;
	String type;
    long size;
	public Flashables(File file, String type, long size) {
		this.file = file;
		this.type = type;
		this.size=size;
	}
}
