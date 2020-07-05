package com.mina.tiktokdownloader.ui.downloaded;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mina.tiktokdownloader.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DownloadedFragment extends Fragment {

    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_downloaded, container, false);

        List<DownloadedVideoModel> image_details = getListFiles();
        final ListView listView = (ListView) root.findViewById(R.id.listView);
        listView.setAdapter(new DownloadedVideoAdapter(image_details, this.getContext()));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                DownloadedVideoModel country = (DownloadedVideoModel) o;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(country.getVideoPath()), "video/mp4");
                startActivity(intent);
            }
        });

        return root;
    }


    public static ArrayList<DownloadedVideoModel> getListFiles() {
        File parentDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getPath() + "/TiDownloader");
        ArrayList<DownloadedVideoModel> inFiles = new ArrayList<DownloadedVideoModel>();
        File[] files;
        files = parentDir.listFiles();
        int id = 0;
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".mp4") || file.getName().endsWith(".mp3")) {
                    DownloadedVideoModel downloadedVideoModel = new DownloadedVideoModel();
                    downloadedVideoModel.setId(id++);
                    downloadedVideoModel.setVideoName(file.getName());
                    downloadedVideoModel.setVideoPath(file.getPath());
                    if (file.getName().endsWith(".mp4")) {
                        downloadedVideoModel.setVideoType(0);
                    } else {
                        downloadedVideoModel.setVideoType(1);
                    }
                    inFiles.add(downloadedVideoModel);
                }
            }
        }
        return inFiles;
    }
}