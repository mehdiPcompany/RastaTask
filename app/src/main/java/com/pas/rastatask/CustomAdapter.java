package com.pas.rastatask;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> statusAsliTask;
    ArrayList<String> onvanAsliTask;
    ArrayList<String> contentAsliTask;
    ArrayList<Integer> statusTask;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> statusAsliTask, ArrayList<String> onvanAsliTask, ArrayList<String> contentAsliTask, ArrayList<Integer> statusTask) {
        this.context = context;
        this.statusAsliTask = statusAsliTask;
        this.onvanAsliTask = onvanAsliTask;
        this.contentAsliTask = contentAsliTask;
        this.statusTask = statusTask;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /* infalte the item Layout */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // set the data in items
        SpannableStringBuilder sb = new SpannableStringBuilder(statusAsliTask.get(position));
        Pattern p = Pattern.compile("وضعیت:", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(statusAsliTask.get(position));
        while (m.find()){
            sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        holder.status.setText(sb);
        holder.onvan.setText(onvanAsliTask.get(position));
        holder.content.setText(contentAsliTask.get(position));
        holder.lb_status.setBackgroundResource(statusTask.get(position));

        holder.content.setTypeface(Typeface.createFromAsset(this.context.getAssets(),"fonts/Shabnam.ttf"));
        holder.onvan.setTypeface(Typeface.createFromAsset(this.context.getAssets(),"fonts/Shabnam.ttf"));
        holder.status.setTypeface(Typeface.createFromAsset(this.context.getAssets(),"fonts/Shabnam.ttf"));
        holder.lb_status.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        Log.d("TAG", String.valueOf(holder.content.getHeight()+holder.content.getTop()+10));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(view -> {
            // display a toast with person name on item click
        });

    }


    @Override
    public int getItemCount() {
        return statusAsliTask.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView status, onvan, content ,lb_status;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            status = itemView.findViewById(R.id.txt_status_task);
            onvan = itemView.findViewById(R.id.txt_onvan_task);
            content = itemView.findViewById(R.id.txt_content_task);
            lb_status = itemView.findViewById(R.id.lab_status);

        }
    }
}