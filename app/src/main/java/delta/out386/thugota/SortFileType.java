/*
 * Copyright (C) 2016 Ritayan Chakraborty (out386)
 */
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

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;

public class SortFileType
{
	final String TAG = Constants.TAG;
	File file;

	public String sort(File file)
	{
		this.file = file;
		boolean other = false;
		Enumeration zipTypeList = null;
		try {
			zipTypeList = new ZipFile(file.toString()).entries();
		}
		catch(IOException e) {
			Log.e(TAG, e.toString());
		}
		
		if(zipTypeList == null)
			return "emptylist";
		while(zipTypeList.hasMoreElements()){
			String line = zipTypeList.nextElement().toString();
			if(line.contains("system.new.dat"))
				return "rom";
			else if(line.contains("zImage"))
				return "kernel";
			else if(line.contains("deltaconfig"))
				return "delta";
			else if(line.contains("update-binary"))
				other = true;
		}
		if(other)
			return "other";
		return "noFlash";
	}

}
