package jhb.dvt.co.za.stackapp.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jhb.dvt.co.za.stackapp.constants.Constants;

public class HttpUtils {

    public static String makeServiceCall(String urlString) {
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = StringUtils.convertStreamToString(in, Constants.DEFAULT_STREAM_ENCODING);
        } catch (Exception e) {
            Log.e(Constants.APP_NAME, "Exception: " + e.getMessage());
        }
        return response;
    }

}