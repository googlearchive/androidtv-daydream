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
import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tvdaydream.R;

/**
 * Fragment which shows a sample UI for configuring {@link .clock.SimpleClockDreamService}.
 */
public class SimpleClockDreamSettingsFragment extends BrowseFragment {
    private static final String TAG = "SimpleClockDreamSettingsFragment";

    private static final int GRID_ITEM_WIDTH = 500;
    private static final int GRID_ITEM_HEIGHT = 200;

    private String mClockFormat = "12-hour";

    private ArrayObjectAdapter mRowsAdapter;
    private SharedPreferences.Editor mEditor;
    private boolean mClock12hour;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUIElements();

        loadRows();

        setupEventListeners();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void loadRows() {

        GridItemPresenter mGridPresenter = new GridItemPresenter();

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        HeaderItem gridHeader = new HeaderItem(0, getString(R.string.setting_header));

        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add(getString(R.string.setting_12hour));
        gridRowAdapter.add(getString(R.string.setting_24hour));
        mRowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

        setAdapter(mRowsAdapter);
    }

    private void setupUIElements() {
        SharedPreferences pref = getActivity().getApplicationContext()
                .getSharedPreferences(getString(R.string.setting_header), Context.MODE_PRIVATE);

        mClock12hour = pref.getBoolean(getString(R.string.setting_12hour), true);

        mEditor = pref.edit();

        if (mClock12hour) {
            mClockFormat = getString(R.string.setting_12hour);
        } else {
            mClockFormat = getString(R.string.setting_24hour);
        }
        setTitle(getString(R.string.setting_header) + " : " + mClockFormat);
        setHeadersState(HEADERS_DISABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof String && ((String) item)
                    .contains(getString(R.string.setting_12hour))) {
                mEditor.putBoolean(getString(R.string.setting_12hour), true);
                mClockFormat = getString(R.string.setting_12hour);
            } else {
                mClockFormat = getString(R.string.setting_24hour);
                mEditor.putBoolean(getString(R.string.setting_12hour), false);
            }
            mEditor.commit();
            setTitle(getString(R.string.setting_header) + " : " + mClockFormat);
        }
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}
