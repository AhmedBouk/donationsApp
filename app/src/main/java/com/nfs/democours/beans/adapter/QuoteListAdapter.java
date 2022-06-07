package com.nfs.democours.beans.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nfs.democours.R;
import com.nfs.democours.beans.Quote;

import java.util.List;

public class QuoteListAdapter extends ArrayAdapter<Quote> {

    private static String logTag = "QUOTE ADAPTER";

    private int resource;

    private int textViewResourceId;

    public QuoteListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Quote> objects) {
        super(context, resource, textViewResourceId, objects);
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        Log.d(logTag, "adapter created with " + objects.toString());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // crée la vue si aps créée
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        // recup la bonne quote à afficher
        Quote quote = getItem(position);

        Log.d(logTag, "postion " + position);
        if(quote != null){
            Log.d(logTag, quote.getQuote());
            final TextView tv = (TextView) convertView.findViewById(textViewResourceId);
            tv.setTextSize(32);
            if(position % 2 == 0){
                tv.setBackgroundResource(R.color.teal_200);
            }else{
                tv.setBackgroundResource(R.color.teal_700);
            }
        }
        return super.getView(position, convertView, parent);
    }
}
