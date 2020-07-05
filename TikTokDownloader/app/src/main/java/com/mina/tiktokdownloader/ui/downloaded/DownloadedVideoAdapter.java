package com.mina.tiktokdownloader.ui.downloaded;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mina.tiktokdownloader.BuildConfig;
import com.mina.tiktokdownloader.R;

import java.io.File;
import java.net.URLConnection;
import java.util.List;

import androidx.core.content.FileProvider;
import de.hdodenhof.circleimageview.CircleImageView;

public class DownloadedVideoAdapter extends BaseAdapter {
    private List<DownloadedVideoModel> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public DownloadedVideoAdapter(List<DownloadedVideoModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.videoImage = (CircleImageView) convertView.findViewById(R.id.videoIcon);
            holder.videoNameTxtView = (TextView) convertView.findViewById(R.id.videoName);
            holder.btnDelete = (ImageView) convertView.findViewById(R.id.btnDelete);
            holder.btnShare = (ImageView) convertView.findViewById(R.id.btnShare);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DownloadedVideoModel country = this.listData.get(position);
        holder.videoNameTxtView.setText(country.getVideoName());
        if (country.getVideoType() == 0) {
            //Video:
            try {
                Glide.with(context)
                        .asBitmap()
                        .load(country.getVideoPath()) // or URI/path
                        .into(holder.videoImage); //imageview to set thumbnail to
            } catch (Exception ex) {
                //......
            }
        }

        //ACTION PROCESS:
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this media file?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listData.remove(position);
                                notifyDataSetChanged();
                                File file = new File(country.getVideoPath());
                                file.delete();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShareFile = new Intent(Intent.ACTION_SEND);

                intentShareFile.setType(URLConnection.guessContentTypeFromName(country.getVideoName()));
                Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", new File(country.getVideoPath()));
                intentShareFile.putExtra(Intent.EXTRA_STREAM, uri);

                context.startActivity(Intent.createChooser(intentShareFile, "Share File"));
            }
        });

        return convertView;
    }

    static class ViewHolder {
        CircleImageView videoImage;
        TextView videoNameTxtView;
        ImageView btnShare;
        ImageView btnDelete;
    }
}