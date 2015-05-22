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

package com.example.android.tvdaydream.bouncer;

import android.graphics.Color;
import android.service.dreams.DreamService;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.android.tvdaydream.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class BouncerDreamService extends DreamService {
    private static final int IMAGE_COUNT = 5;
    private static final int SPEED = 200;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private Bouncer mBouncer;

    @Override
    public void onDreamingStarted() {
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(WIDTH, HEIGHT);

        mBouncer = new Bouncer(this);
        mBouncer.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        mBouncer.setSpeed(SPEED);

        for (int i=0; i<IMAGE_COUNT; i++) {
            final ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.android);
            image.setBackgroundColor(getResources().getColor(R.color.default_background));
            mBouncer.addView(image, lp);
        }

        setContentView(mBouncer);
    }

}
