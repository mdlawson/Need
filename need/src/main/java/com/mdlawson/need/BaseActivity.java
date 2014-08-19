package com.mdlawson.need;

import android.app.Activity;
import android.os.Bundle;
import de.greenrobot.event.EventBus;

public class BaseActivity extends Activity {

    protected EventBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bus = EventBus.getDefault();
    }
}
