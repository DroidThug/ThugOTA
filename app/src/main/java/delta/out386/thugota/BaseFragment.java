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

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    static int section = 1;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BaseFragment newInstance(int sectionNumber) {
        section = sectionNumber;
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public BaseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        final Context cont=getActivity();

        switch (section) {
            case 2:
                new SearchZips(cont, false, rootView, "roms", null).execute();
            break;
            case 3:
                new SearchZips(cont, false, rootView, "deltas", null).execute();
            break;
            case 4:
                new SearchZips(cont, false, rootView, "kernels", null).execute();
            break;
            case 5:
                new SearchZips(cont, false, rootView, "others", null).execute();
            break;
        }
        return rootView;
    }

}
