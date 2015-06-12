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

package com.example.android.tvdaydream;

import android.app.Activity;
import android.content.Intent;

public class DreamActivity extends Activity {

    @Override
    public void onStart() {
        super.onStart();

        final Intent intent = new Intent(Intent.ACTION_MAIN);
        // This API is unofficial, and its behavior may change in future.
        intent.setClassName(getString(R.string.dreamservice_package_context),
                            getString(R.string.dreamservice_class_name));
        startActivity(intent);
        finish();
    }
}
