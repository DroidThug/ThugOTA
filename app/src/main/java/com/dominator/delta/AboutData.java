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

import java.io.File;
import java.io.Serializable;

public class AboutData {
    String name, link;
    int license;
    public AboutData(String name,  int license, String link) {
        this.name = name;
        this.license = license;
        this.link = link;
    }
}
