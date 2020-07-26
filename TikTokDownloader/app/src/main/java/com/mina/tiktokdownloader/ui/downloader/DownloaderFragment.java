package com.mina.tiktokdownloader.ui.downloader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mina.tiktokdownloader.R;
import com.mina.tiktokdownloader.apiresponse.Apiresponse;

public class DownloaderFragment extends Fragment {

    private AdView mAdView;
    private TextView txtUrl;

    private Button btnDownloadOrg;
    private Button btnDownloadNoWater;
    private Button btnDownloadMusic;

    ProgressDialog progressDoalog;


    private static final String TAG = "MINA-MIMI";
    private static final String API_URL = "https://tidownloader.com/api/get-video?url=";

    private static final String DOWNLOAD_PATH = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_downloader, container, false);
        txtUrl = (TextView) root.findViewById(R.id.DownloadLink);
        btnDownloadOrg = (Button) root.findViewById(R.id.DownloadOrigin);
        btnDownloadNoWater = (Button) root.findViewById(R.id.DownloadNoWater);
        btnDownloadMusic = (Button) root.findViewById(R.id.DownloadMusic);
        btnDownloadOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUrl.getText() == null || txtUrl.getText().toString().trim().length() <= 0) {
                    Toast.makeText(getContext(), "No tiktok link found!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDoalog = new ProgressDialog(getContext());
                progressDoalog.setMax(100);
                progressDoalog.setMessage("It's loading....");
                progressDoalog.setTitle("Downloading your media");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show();
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.incrementProgressBy(1);
                Log.d("Mina", "Donwload clicked");
                AndroidNetworking.get(API_URL + txtUrl.getText().toString())
                        .build()
                        .getAsObject(Apiresponse.class, new ParsedRequestListener<Apiresponse>() {
                            @Override
                            public void onResponse(Apiresponse user) {
                                // do anything with response
                                Log.d(TAG, "id : " + user.getId());
                                Log.d(TAG, "video origin : " + user.getLink_download_original());
                                Log.d(TAG, "no water mark : " + user.getLink_download_without_watermark());
                                Log.d(TAG, "music : " + user.getLink_download_music());

                                String fileName = user.getAuthor_nickname() + "_" + user.getMusic_title() + "_" + user.getId() + ".mp4";
                                downloadFile(user.getLink_download_original(), fileName,
                                        Environment.getExternalStoragePublicDirectory(
                                                Environment.DIRECTORY_DOWNLOADS).getPath() + "/TiDownloader");
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e(TAG, anError.getMessage());
                            }
                        });

            }
        });

        btnDownloadNoWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUrl.getText() == null || txtUrl.getText().toString().trim().length() <= 0) {
                    Toast.makeText(getContext(), "No tiktok link found!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Mina", "Donwload clicked");
                progressDoalog = new ProgressDialog(getContext());
                progressDoalog.setMax(100);
                progressDoalog.setMessage("It's loading....");
                progressDoalog.setTitle("Downloading your media");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show();
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.incrementProgressBy(1);
                Log.d(TAG, "Starting download url: " + API_URL + txtUrl.getText().toString());
                AndroidNetworking.get(API_URL + txtUrl.getText().toString())
                        .build()
                        .getAsObject(Apiresponse.class, new ParsedRequestListener<Apiresponse>() {
                            @Override
                            public void onResponse(Apiresponse user) {
                                // do anything with response
                                Log.d(TAG, "id : " + user.getId());
                                Log.d(TAG, "video origin : " + user.getLink_download_original());
                                Log.d(TAG, "no water mark : " + user.getLink_download_without_watermark());
                                Log.d(TAG, "music : " + user.getLink_download_music());
                                String fileName = user.getAuthor_nickname() + "_" + user.getMusic_title() + "_" + user.getId() + ".mp4";
                                downloadFile(user.getLink_download_without_watermark(), fileName, Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS).getPath() + "/TiDownloader");
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e(TAG, anError.getMessage());
                            }
                        });

            }
        });

        btnDownloadMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUrl.getText() == null || txtUrl.getText().toString().trim().length() <= 0) {
                    Toast.makeText(getContext(), "No tiktok link found!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Mina", "Donwload clicked");
                progressDoalog = new ProgressDialog(getContext());
                progressDoalog.setMax(100);
                progressDoalog.setMessage("It's loading....");
                progressDoalog.setTitle("Downloading your media");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show();
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.incrementProgressBy(1);
                AndroidNetworking.get(API_URL + txtUrl.getText().toString())
                        .build()
                        .getAsObject(Apiresponse.class, new ParsedRequestListener<Apiresponse>() {
                            @Override
                            public void onResponse(Apiresponse user) {
                                // do anything with response
                                Log.d(TAG, "id : " + user.getId());
                                Log.d(TAG, "video origin : " + user.getLink_download_original());
                                Log.d(TAG, "no water mark : " + user.getLink_download_without_watermark());
                                Log.d(TAG, "music : " + user.getLink_download_music());
                                String fileName = user.getAuthor_nickname() + "_" + user.getMusic_title() + "_" + user.getId() + ".mp3";
                                downloadFile(user.getLink_download_music(), fileName, Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS).getPath() + "/TiDownloader");
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e(TAG, anError.getMessage());
                            }
                        });

            }
        });

        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }

    private void downloadFile(String urlOrg, final String fileName, final String dirPath) {


        urlOrg = urlOrg.replaceFirst("http", "https");
        Log.d(TAG, "URL DOWNLOAD:" + urlOrg);
        Log.d(TAG, "Download to:" + dirPath);
        Toast.makeText(getContext(), "Start downloading...", Toast.LENGTH_SHORT).show();
        AndroidNetworking.download(urlOrg, dirPath, fileName)
                .setTag("Mina")
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
                        Log.d(TAG, "Total:" + totalBytes);
                        Log.d(TAG, "Downloaded:" + bytesDownloaded);
                        Log.d(TAG, "Downloading : " + bytesDownloaded * 100 / totalBytes + "%...");
                        progressDoalog.incrementProgressBy(1);
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
                        String outputFile = dirPath + "/" + fileName;
                        Log.d(TAG, "Finish download : " + outputFile);
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), "Finish downloading", Toast.LENGTH_SHORT).show();
                        //getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + dirPath + "/" + fileName)));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            final Uri contentUri = Uri.parse("file://" + outputFile);
                            scanIntent.setData(contentUri);
                            getContext().sendBroadcast(scanIntent);
                        } else {
                            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
                            getContext().sendBroadcast(intent);
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e(TAG, error.getMessage());
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), "Error on downloading, please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}