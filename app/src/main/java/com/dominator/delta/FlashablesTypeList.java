/*
 * Copyright (C) 2016 Ritayan Chakraborty (out386)
 */
/*
 * This file is part of DominatorDelta.
 *
 * DominatorDelta is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DominatorDelta is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DominatorDelta. If not, see <http://www.gnu.org/licenses/>.
 */

package com.dominator.delta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlashablesTypeList implements Serializable {
    List<Flashables> roms=new ArrayList<>();
    List<Flashables> kernels=new ArrayList<>();
    List<Flashables> deltas=new ArrayList<>();
    List<Flashables> others=new ArrayList<>();

    public void addFlashable(Flashables flashables) {
        List<Flashables> search = null;
            if(flashables != null)
                if(flashables.type.equals("rom"))
                    search = roms;
                else if(flashables.type.equals("kernel"))
                    search = kernels;
                else if(flashables.type.equals("delta"))
                    search = deltas;
                else if(flashables.type.equals("other"))
                    search = others;
        if(search == null)
            return;
        for(Flashables current : search)
        {
            if(current.file.equals(flashables.file))
                return;
        }
        search.add(flashables);
    }
}
