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

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FilesCategorize {
    List<File> zips;

    public FlashablesTypeList run(Collection<File> fileCollection) {
        zips=(List<File>) fileCollection;
        return sortSize();

    }

    public FlashablesTypeList sortSize(){
		if(zips == null)
			return null;
		SortFileType sortFile = new SortFileType();
        FlashablesTypeList flashablesTypeList = new FlashablesTypeList();
		String zipType;
        long size=0;
		
        for(File current:zips){
            size=FileUtils.sizeOf(current);
            if((zipType=sortFile.sort(current)) != null)
                flashablesTypeList.addFlashable(new Flashables(current, zipType, size));
        }
        return flashablesTypeList;
    }
}
