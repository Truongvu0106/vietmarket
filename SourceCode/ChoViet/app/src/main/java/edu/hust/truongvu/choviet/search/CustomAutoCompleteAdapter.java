package edu.hust.truongvu.choviet.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 5/6/2018.
 */

public class CustomAutoCompleteAdapter extends ArrayAdapter<PopularSearch>{
    private ArrayList<PopularSearch> data, tempData, suggestionData;
    private TextView tvSearch;
    private ImageView imgSearch;
    private Context mContext;
    public CustomAutoCompleteAdapter(@NonNull Context context, ArrayList<PopularSearch> data) {
        super(context, android.R.layout.simple_list_item_1, data);
        this.mContext = context;
        this.data = data;
        this.tempData = new ArrayList<PopularSearch>(data);
        this.suggestionData = new ArrayList<PopularSearch>(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopularSearch popularSearch = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_popular_search_auto, parent, false);
        }
        tvSearch = convertView.findViewById(R.id.tv_search);
        imgSearch = convertView.findViewById(R.id.img_search);
        if (tvSearch != null)
            tvSearch.setText(popularSearch.getKeySearch());
        if (imgSearch != null)
            MyHelper.setImagePicasso(mContext, imgSearch, Constants.Path.MY_PATH + popularSearch.getImg());
        return convertView;
    }


    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            PopularSearch popularSearch = (PopularSearch) resultValue;
            return popularSearch.getKeySearch();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestionData.clear();
                for (PopularSearch popularSearch : tempData) {
                    if (popularSearch.getKeySearch().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestionData.add(popularSearch);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestionData;
                filterResults.count = suggestionData.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<PopularSearch> popularSearches = (ArrayList<PopularSearch>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (PopularSearch p : popularSearches) {
                    add(p);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
