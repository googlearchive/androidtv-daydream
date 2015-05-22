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
import android.content.SharedPreferences;
import android.service.dreams.DreamService;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.android.tvdaydream.R;

import java.util.TimeZone;

public class SimpleClockDreamService extends DreamService {
    private boolean mClock12hour = true;
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        // Exit dream upon user touch
        setInteractive(false);
        // Hide system UI
        setFullscreen(true);
        // Set the dream layout
        setScreenBright(false);
        setContentView(R.layout.fullscreen_clock);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();

        SharedPreferences prefs = getSharedPreferences(getString(R.string.setting_header),
                Context.MODE_PRIVATE);
        mClock12hour = prefs.getBoolean(getString(R.string.setting_12hour), true);

        TimeZone tz = TimeZone.getTimeZone(getString(R.string.pst_time_zone));
        TextClock tc = (TextClock) findViewById(R.id.text_clock);
        TextView tv = (TextView) findViewById(R.id.clock_format);
        if (mClock12hour) {
            tc.setFormat12Hour(getString(R.string.format_12hour));
            tv.setText(getString(R.string.setting_12hour));
        } else {
            tc.setFormat12Hour(getString(R.string.format_24hour));
            tv.setText(getString(R.string.setting_24hour));
        }
        tc.setTimeZone(tz.getID());
    }
}
