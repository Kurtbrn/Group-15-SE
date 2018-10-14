package com.plantatree.login.login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TreeItem extends ArrayAdapter<String>  {
    private final Activity context;
    private final String[] treelist;
    private final ArrayList<Tree> trees;

    public TreeItem(Activity context, String[] treelist,  ArrayList<Tree> trees) {
        super(context, R.layout.itemlist, treelist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.treelist=treelist;
        this.trees=trees;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.itemlist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(treelist[position]);
        imageView.setImageResource(trees.get(position).imgId);
        extratxt.setText("$"+trees.get(position).price+" height: "+trees.get(position).height+" "+ trees.get(position).growth+" months");

        return rowView;

    };

}
