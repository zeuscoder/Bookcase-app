package com.zeus.bookcase.app.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by zeus_coder on 2015/10/8.
 */
public interface AppNavigator {

    void goHome(Activity activity);

    void goUserCenter(Activity activity);

    void openUiFromNotification(SubIntent subIntent);

    class SubIntent {
        public final Intent intent;
        public final boolean loginNeed;
        public final String accountId;

        public SubIntent(Intent intent, boolean loginNeed, String accountId) {
            this.intent = intent;
            this.loginNeed = loginNeed;
            this.accountId = accountId;
        }

        public static SubIntent from(Bundle bundle) {
            return new SubIntent(bundle.<Intent>getParcelable("intent"),bundle.getBoolean("loginNeed"),bundle.getString("accountId"));
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("intent",intent);
            bundle.putBoolean("loginNeed",loginNeed);
            bundle.putString("accountId",accountId);
            return bundle;
        }
    }
}
