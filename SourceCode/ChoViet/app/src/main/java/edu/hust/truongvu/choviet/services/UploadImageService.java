package edu.hust.truongvu.choviet.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 4/28/2018.
 */

public class UploadImageService {
    private UploadImageListener mListener;
    private Context mContext;
    private String PATH;

    public UploadImageService(Context context, UploadImageListener uploadImageListener, String PATH){
        this.mListener = uploadImageListener;
        this.mContext = context;
        this.PATH = PATH;
    }

    public void uploadImageVolley(final MyImage myImage){
        if (myImage == null){
            mListener.onResults(false, "image null");
        }else {
            final ProgressDialog pd = ProgressDialog.show(mContext, null, mContext.getString(R.string.please_wait));
            final RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            StringRequest request = new StringRequest(Request.Method.POST, PATH, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                    mListener.onResults(true, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("err_volley", error.toString());
                    pd.dismiss();
                    mListener.onResults(false, error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String imageData = bitmapToString(myImage.getBitmap());
                    String name = myImage.getName();
                    params.put("image", imageData);
                    params.put("name", name);
                    return params;
                }
            };
            requestQueue.add(request);
        }

    }

    private String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imgBytes = outputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imgBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
