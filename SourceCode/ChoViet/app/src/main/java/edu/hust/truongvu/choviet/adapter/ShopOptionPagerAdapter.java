package edu.hust.truongvu.choviet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/28/2018.
 */

public class ShopOptionPagerAdapter extends PagerAdapter{
    public interface ShopOpitonPagerListener{
        void onClick(int id);
    }
    private Context mContext;
    private ShopOpitonPagerListener mListener;



    public ShopOptionPagerAdapter(Context context, ShopOpitonPagerListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final int[] LIST_IMGS = new int[]{
                R.drawable.ic_list_product,
                R.drawable.ic_list_order,
                R.drawable.ic_report
        };

        final String[] LIST_TITLE = new String[]{
                mContext.getString(R.string.list_product),
                mContext.getString(R.string.list_order),
                mContext.getString(R.string.report)
        };

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, container, false);
        ImageView imageView = view.findViewById(R.id.img_item);
        TextView textView = view.findViewById(R.id.txt_item);

        imageView.setImageResource(LIST_IMGS[position]);
        textView.setText(LIST_TITLE[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
