package edu.hust.truongvu.choviet.helper.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 3/12/2018.
 */

@SuppressLint("AppCompatCustomView")
public class MyClearEditText extends EditText{
    Drawable clear, nonClear;
    boolean isVisible;
    Drawable myDrawable;
    int ALPHA = (int) (255 * .55f);
    public MyClearEditText(Context context) {
        super(context);
        construct();
    }

    public MyClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        construct();
    }

    public MyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        construct();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        construct();
    }

    private void construct(){
        clear = ContextCompat.getDrawable(getContext(), R.drawable.clear).mutate();
        nonClear = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        setup();
    }

    private void setup(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        myDrawable = isVisible ? clear : nonClear;
//        myDrawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], myDrawable, drawables[3]);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0){
            isVisible = false;
            setup();
        }else {
            isVisible = true;
            setup();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getWidth() - myDrawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }
}
