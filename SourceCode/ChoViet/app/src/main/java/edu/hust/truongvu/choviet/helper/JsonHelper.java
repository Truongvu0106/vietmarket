package edu.hust.truongvu.choviet.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 3/9/2018.
 */

public class JsonHelper extends AsyncTask<String, Void, String> {
    private String path;
    private List<HashMap<String, String>> attrs;
    private StringBuilder data = new StringBuilder();
    private Context mContext;
    private ProgressDialog dialog;

    public JsonHelper(Context context, String path){
        this.path = path;
        this.mContext = context;
    }

    public JsonHelper(Context context, String path, List<HashMap<String, String>> attrs){
        this.path = path;
        this.attrs = attrs;
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(mContext, null, mContext.getString(R.string.please_wait));
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (attrs != null && attrs.size() != 0){
                doPost(httpURLConnection);
            }else {
                doGet(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private String doGet(HttpURLConnection httpURLConnection){
        String myData = "";
        try {
            InputStream inputStream = null;
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            data = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                data.append(line);
            }
            myData = data.toString();
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData;
    }

    private String doPost(HttpURLConnection httpURLConnection){
        String myData = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();
            int count = attrs.size();
            for (int i = 0; i < count; i++){
                for (Map.Entry<String, String> values : attrs.get(i).entrySet()){
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key, value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);
            writer.write(query);
            writer.flush();
            writer.close();
            streamWriter.close();
            outputStream.close();

            myData = doGet(httpURLConnection);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData;
    }
}
