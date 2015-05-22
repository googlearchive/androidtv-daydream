/*
 * Copyright 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.tvdaydream.clock;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextClock;

import com.example.android.tvdaydream.R;

import java.util.Random;

public class Drifter extends FrameLayout {
    private static final int COLOR_MAX = 255;
    private static final int DRIFT_DELAY = 5000;

    Runnable mRunnable = new Runnable() {
        public void run() {
            final View parent = (View) getParent();
            if (parent == null) return;

            // reposition in parent using setX() and setY()
            final float width = getMeasuredWidth();
            final float height = getMeasuredHeight();
            final float parentw = parent.getMeasuredWidth();
            final float parenth = parent.getMeasuredHeight();
            setX((float) Math.random() * width + (parentw/2 - width));
            setY((float) Math.random() * height + (parenth/2 - height));

            Random rand = new Random();
            // generate the random integers for r, g and b value
            int r = rand.nextInt(COLOR_MAX);
            int g = rand.nextInt(COLOR_MAX);
            int b = rand.nextInt(COLOR_MAX);
            int randomColor = Color.rgb(r, g, b);
            TextClock tc = (TextClock) findViewById(R.id.text_clock);
            tc.setTextColor(randomColor);

            postDelayed(this, DRIFT_DELAY); // let’s do this again, soon
        }
    };

    public Drifter(Context context) {
        super(context);
    }

    public Drifter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Drifter(Context context, AttributeSet attrs, int flags) {
        super(context, attrs, flags);
    }

    @Override
    public void onAttachedToWindow() {
        getHandler().post(mRunnable);
    }

}

