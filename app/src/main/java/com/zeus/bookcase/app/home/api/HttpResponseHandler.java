package com.zeus.bookcase.app.home.api;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zeus_coder on 2016/1/23.
 */
public abstract class HttpResponseHandler extends JsonHttpResponseHandler {

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Log.i("zimou", response.toString());
        jsonSuccess(response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        jsonFail(errorResponse);
    }

    public abstract void jsonSuccess(JSONObject resp);
    public abstract void jsonFail(JSONObject resp);
}
