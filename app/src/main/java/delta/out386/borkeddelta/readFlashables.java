package delta.out386.borkeddelta;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by J-PC on 3/30/2016.
 */
public class readFlashables extends AsyncTask<Void, Void, FlashablesTypeList> {
    Context context;
    View view;
    public readFlashables(Context context, View view) {
        this.context = context;
        this.view = view;
    }
    @Override
    public FlashablesTypeList doInBackground(Void... v) {
        File f = new File(context.getFilesDir().toString() + "/queue");
        FlashablesTypeList flashablesTypeList = null;
        if(f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                flashablesTypeList = (FlashablesTypeList) ois.readObject();
                ois.close();
            }
            catch(FileNotFoundException e) {
                Log.e("borked", e.toString());
            }
            catch(IOException e) {
                Log.e("borked", e.toString());
            }
            catch(ClassNotFoundException e) {
                Log.e("borked", e.toString());
            }
        }
        else
            return null;
        return flashablesTypeList;
    }
    @Override
    protected void onPostExecute(final FlashablesTypeList output){
        TextView romName = (TextView)view.findViewById(R.id.queueRomNameText);
        TextView romPath = (TextView)view.findViewById(R.id.queueRomPathText);
        TextView deltaName = (TextView)view.findViewById(R.id.queueDeltaNameText);
        TextView deltaPath = (TextView)view.findViewById(R.id.queueDeltaPathText);
        TextView queueEmptyTextviw = (TextView)view.findViewById(R.id.queueEmptyTextview);
        RelativeLayout queueEmptyLayout = (RelativeLayout)view.findViewById(R.id.queueEmptyLayout);
        RelativeLayout queueReadyLayout = (RelativeLayout)view.findViewById(R.id.queueReadyLayout);
        Button queueClearButton = (Button)view.findViewById(R.id.queueClearButton);

        if(output == null || output.roms.isEmpty() && output.deltas.isEmpty()) {
            queueEmptyTextviw.setText("No target ROM and no deltas selected. Please select a target ROM and a delta from the ROMs and deltas sections respectively.");
            queueReadyLayout.setVisibility(RelativeLayout.GONE);
            queueEmptyLayout.setVisibility(RelativeLayout.VISIBLE);
            return;
        }
        if(output.roms.isEmpty()) {
            queueEmptyTextviw.setText("No target ROM selected. Please select a target ROM from the ROMs section.");
            queueReadyLayout.setVisibility(RelativeLayout.GONE);
            queueEmptyLayout.setVisibility(RelativeLayout.VISIBLE);
            return;
        }
        if(output.deltas.isEmpty()) {
            queueEmptyTextviw.setText("No deltas selected. Please select a delta to apply from the deltas section.");
            queueReadyLayout.setVisibility(RelativeLayout.GONE);
            queueEmptyLayout.setVisibility(RelativeLayout.VISIBLE);
            return;
        }
        queueEmptyLayout.setVisibility(RelativeLayout.GONE);
        queueClearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                File f = new File(context.getFilesDir().toString() + "/queue");
                if(f.exists())
                    f.delete();
                new readFlashables(context, view).execute();
            }
        });
        romName.setText(output.roms.get(0).file.getName());
        romPath.setText(output.roms.get(0).file.getParent());
        deltaName.setText(output.deltas.get(0).file.getName());
        deltaPath.setText(output.deltas.get(0).file.getParent());
        queueReadyLayout.setVisibility(RelativeLayout.VISIBLE);
    }
}