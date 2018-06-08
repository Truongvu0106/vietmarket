package edu.hust.truongvu.choviet.product.rate;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Rate;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 3/22/2018.
 */

public class RateProductDialog extends AlertDialog implements RatingBar.OnRatingBarChangeListener{

    public interface RateProductListener{
        void onRate(Rate rate);
    }
    private Context context;
    private RateProductListener myListener;
    private RatingBar ratingBar;
    private TextView tvTitle, tvContent;
    private View btnCancel, btnRate;
    private float star = 0;
    private User user;
    private int id_product;

    public RateProductDialog(Context context, int id_product, User user, RateProductListener listener) {
        super(context);
        this.context = context;
        this.myListener = listener;
        this.user = user;
        this.id_product = id_product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rate_product);
        ratingBar = findViewById(R.id.rating_product);
        tvTitle = findViewById(R.id.title_rate);
        tvContent = findViewById(R.id.content_rate);
        btnCancel = findViewById(R.id.btn_cancel);
        btnRate = findViewById(R.id.btn_rate);

        ratingBar.setOnRatingBarChangeListener(this);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (star == 0){
                    MyHelper.showToast(context, context.getString(R.string.please_add_star), FancyToast.WARNING);
                } else if (tvTitle.getText().toString().trim().matches("")){
                    MyHelper.showToast(context, context.getString(R.string.please_write_title_rate), FancyToast.WARNING);
                } else {
                    Rate rate = new Rate(id_product, user.getUsername(), tvTitle.getText().toString().trim(),
                            tvContent.getText().toString(), star, "");
                    myListener.onRate(rate);
                    dismiss();
                }

            }
        });
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        star = v;
    }

}
