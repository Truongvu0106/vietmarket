package edu.hust.truongvu.choviet.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
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
public class MyPwEditText extends EditText {
    Drawable drawableHide, drawableShow;
    boolean isVisible = false;
    boolean useHide = true;
    Drawable myDrawable;
    int ALPHA = (int) (255 * .55f);
    public MyPwEditText(Context context) {
        super(context);
        construct(null);
    }

    public MyPwEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        construct(attrs);
    }

    public MyPwEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        construct(attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyPwEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        construct(attrs);

    }

    private void construct(AttributeSet attrs){
        if (attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MyPwEditText, 0, 0);
            this.useHide = array.getBoolean(R.styleable.MyPwEditText_hide, true);
        }
        drawableHide = ContextCompat.getDrawable(getContext(), R.drawable.ic_not_visible).mutate();
        drawableShow = ContextCompat.getDrawable(getContext(), R.drawable.ic_visible).mutate();
        setup();
    }

    private void setup(){
        setInputType(InputType.TYPE_CLASS_TEXT | (isVisible?InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD:InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        myDrawable = (useHide && !isVisible)?drawableHide:drawableShow;
//        myDrawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], myDrawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - myDrawable.getBounds().width() )){
            isVisible = !isVisible;
            setup();
            invalidate();
        }
        return super.onTouchEvent(event);

    }
}
