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

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SettingsFragment extends Fragment {
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final EditText location = (EditText) rootView.findViewById(R.id.storageLocation);
        final TextView freeSpace = (TextView) rootView.findViewById(R.id.locationSpace);
        Button locationButton = (Button) rootView.findViewById(R.id.locationButton);
        final SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor prefEdit = preferences.edit();
        final String defaultDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String loc = preferences.getString("location", defaultDir);

        freeSpace.setText(new Tools().sizeFormat(new File(loc).getFreeSpace()) + " free");
        location.setText(loc);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedLocation;
                String storage = location.getText().toString();
                File storageFile = new File(storage);
                if ((storage.equals(""))) {
                    prefEdit.putString("location", defaultDir);
                    prefEdit.apply();
                    selectedLocation = defaultDir;
                    Toast.makeText(getContext(), "Storage location changed to default", Toast.LENGTH_SHORT).show();
                    location.setText(defaultDir);
                } else if (!storageFile.exists()) {
                    Toast.makeText(getContext(), "Storage location does not exist", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    prefEdit.putString("location", storage);
                    prefEdit.apply();
                    selectedLocation = storage;
                    Toast.makeText(getContext(), "Storage location changed", Toast.LENGTH_SHORT).show();
                }
                freeSpace.setText(new Tools().sizeFormat(new File(selectedLocation).getFreeSpace()) + " free");
            }
        });
        return rootView;
    }

}
