package com.example.apprenticeassignment2;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

// Overriding the AppCompatAutoCompleteTextView to show previous search list
// on focus instead of showing it once user types few letters
public class OnFocusSearchView extends AppCompatAutoCompleteTextView {

    public OnFocusSearchView(Context context) {
        super(context);
    }

    public OnFocusSearchView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public OnFocusSearchView(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        // This will show the list of previous searches which user can select
        // As user types, the search list will be filter out automaticaly
        if (focused && getAdapter() != null) {
            performFiltering(getText(), 0);
        }
    }

}