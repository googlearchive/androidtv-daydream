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

package com.example.android.tvdaydream.jumper;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class Jumper3 extends FrameLayout {
    private static final int MIN_SPEED = 1000;
    private static final int MAX_SPEED = 3000;
    private static final int DURATION = 2000;
    private static final int ROTATION = 90;

    Runnable mRunnable = new Runnable() {
        public void run() {
            final View parent = (View) getParent();
            if (parent == null) return;

            // reposition in parent using setX() and setY()
            final float width = getMeasuredWidth();
            final float height = getMeasuredHeight();
            final float parentw = parent.getMeasuredWidth();
            final float parenth = parent.getMeasuredHeight();
            final float scale = 1 + (int) Math.round(Math.random() * 2);

            animate().x((float) Math.random() * (parentw - width))
                     .y((float) Math.random() * (parenth - height))
                     .rotation(ROTATION * (2 - (int)(Math.random() * 4)))
                     .scaleX(scale).scaleY(scale)
                     .setDuration(DURATION);

            postDelayed(this, DURATION + (int) (Math.random() * MAX_SPEED) + MIN_SPEED);
        }
    };

    public Jumper3(Context context) {
        super(context);
    }

    public Jumper3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Jumper3(Context context, AttributeSet attrs, int flags) {
        super(context, attrs, flags);
    }

    @Override
    public void onAttachedToWindow() {
        getHandler().post(mRunnable);
    }

}

