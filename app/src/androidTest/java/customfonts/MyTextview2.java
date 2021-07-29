package customfonts;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by wolfsoft1 on 30/3/17.
 */

public class MyTextview2 extends AppCompatTextView {
   /* public MyTextview2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextview2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextview2(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/MaterialDesignIcons.ttf");

            setTypeface(tf);
        }
    }*/

    private static Typeface sMaterialDesignIcons;

    public MyTextview2(Context context) {
        this(context, null);
    }

    public MyTextview2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextview2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;//Won't work in Eclipse graphical layout
        setTypeface();
    }

    private void setTypeface() {
        if (sMaterialDesignIcons == null) {
            sMaterialDesignIcons = Typeface.createFromAsset(getContext().getAssets(), "fonts/MaterialDesignIcons.ttf");
        }
        setTypeface(sMaterialDesignIcons);
    }



}
