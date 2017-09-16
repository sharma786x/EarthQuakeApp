package example.com.android.asynctaskloaderr;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;

/**
 * Created by Sharma786 on 03/05/2017.
 */

public class Loader implements LoaderManager.LoaderCallbacks<String> {
    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

    }
}
