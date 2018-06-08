package edu.hust.truongvu.choviet.user.myshop.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;

/**
 * Created by truon on 6/4/2018.
 */

public class AddDiscountDialog extends AlertDialog{
    public interface AddDiscountListener{
        void onApply(int number);
    }
    public AddDiscountListener mListener;
    private Context mContext;
    private EditText edtNumber;
    private View btnCancel, btnApply;
    private int currentDiscount;
    public AddDiscountDialog(Context context, int currentDiscount, AddDiscountListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
        this.currentDiscount = currentDiscount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_discount);

        edtNumber = findViewById(R.id.edt_number);
        btnCancel = findViewById(R.id.btn_cancel);
        btnApply = findViewById(R.id.btn_apply);

        edtNumber.setText(currentDiscount + "");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apply();
            }
        });
    }

    private void apply(){
        String s = edtNumber.getText().toString().trim();
        if (s.matches("")){
            MyHelper.showToast(mContext, mContext.getString(R.string.please_enter_all), FancyToast.WARNING);
            return;
        }
        try {
            int number = Integer.parseInt(s);
            if (number > 100){
                MyHelper.showToast(mContext, mContext.getString(R.string.discount_must_under_100), FancyToast.WARNING);

                return;
            }
            mListener.onApply(number);
            dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
