package zenlife.nox.nox.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Nhan on 11/14/2017.
 */

public class SeeAllFontTextView extends AppCompatTextView {
    public SeeAllFontTextView(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/noyhr.otf");
        this.setTypeface(face);
    }

    public SeeAllFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/noyhr.otf");
        this.setTypeface(face);
    }

    public SeeAllFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/noyhr.otf");
        this.setTypeface(face);
    }
}