package edu.hust.truongvu.choviet.admin.promotion;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 5/31/2018.
 */

public class PromotionDialog extends AlertDialog implements View.OnClickListener{

    public interface PromotionDialogListener{
        void onApply(Promotion promotion);
        void onCancel();
    }
    private PromotionDialogListener mListener;
    private String mInitCode = "";
    private Context mContext;
    private EditText edtCode, edtAmount, edtNumber;
    private View btnCancel, btnApply, btnFrom, btnTo;
    private TextView tvFrom, tvTo;
    private static long mFrom, mTo;

    public PromotionDialog(Context context, String code, PromotionDialogListener listener) {
        super(context);
        this.mContext = context;
        this.mInitCode = code;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_promotion);
        initView();
        edtCode.setText(mInitCode);
    }

    

    private void initView(){
        edtCode = findViewById(R.id.edt_code);
        edtAmount = findViewById(R.id.edt_amount);
        edtNumber = findViewById(R.id.edt_number);
        tvFrom = findViewById(R.id.tv_from);
        tvTo = findViewById(R.id.tv_to);

        btnCancel = findViewById(R.id.btn_cancel);
        btnApply = findViewById(R.id.btn_apply);
        btnFrom = findViewById(R.id.btn_from);
        btnTo = findViewById(R.id.btn_to);

        btnCancel.setOnClickListener(this);
        btnApply.setOnClickListener(this);
        btnFrom.setOnClickListener(this);
        btnTo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_apply:
                apply();
                break;
            case R.id.btn_from:
                setDate(true);
                break;
            case R.id.btn_to:
                setDate(false);
                break;
        }
    }

    public void setDate(final boolean isFrom){
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar mCalendar = Calendar.getInstance();
                        mCalendar.set(year, monthOfYear, dayOfMonth);
                        long selectedTime = mCalendar.getTimeInMillis();
                        if (isFrom){
                            if (mTo != 0 && selectedTime >= mTo){
                                Toast.makeText(mContext, mContext.getString(R.string.start_must_before_end), Toast.LENGTH_SHORT).show();
                            }else {
                                mFrom = selectedTime;
                                tvFrom.setText(MyHelper.convertDateToString(mFrom));
                            }
                        }else {
                            if (mFrom != 0 && selectedTime <= mFrom){
                                Toast.makeText(mContext, mContext.getString(R.string.end_must_after_start), Toast.LENGTH_SHORT).show();
                            }else {
                                mTo = mCalendar.getTimeInMillis();
                                tvTo.setText(MyHelper.convertDateToString(mTo));
                            }
                        }


                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void apply(){
        String code = edtCode.getText().toString().trim();
        long amount = Long.parseLong(edtAmount.getText().toString().trim());
        int number = Integer.parseInt(edtNumber.getText().toString().trim());

        if (code.matches("") || amount == 0 || number == 0 || mFrom == 0 || mTo == 0){
            Toast.makeText(mContext, mContext.getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
        }else {
            Promotion promotion = new Promotion(0, code, amount, number, mFrom, mTo);
            mListener.onApply(promotion);
            dismiss();
        }
    }

}
