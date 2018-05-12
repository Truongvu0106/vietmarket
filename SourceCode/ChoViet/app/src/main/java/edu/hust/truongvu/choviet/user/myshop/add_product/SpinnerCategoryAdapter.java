package edu.hust.truongvu.choviet.user.myshop.add_product;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;

/**
 * Created by truon on 4/30/2018.
 */

public class SpinnerCategoryAdapter extends ArrayAdapter<ChildCategory>{
    private ArrayList<ChildCategory> listCategories;

    public SpinnerCategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChildCategory> objects) {
        super(context, resource, objects);
        this.listCategories = objects;
    }

    @Override
    public int getCount() {
        return listCategories.size();
    }

    @Nullable
    @Override
    public ChildCategory getItem(int position) {
        return listCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(listCategories.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(listCategories.get(position).getName());

        return label;
    }
}
