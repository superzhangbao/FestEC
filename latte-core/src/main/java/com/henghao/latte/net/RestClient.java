package com.henghao.latte.net;

import android.content.Context;

import com.henghao.latte.net.callback.IError;
import com.henghao.latte.net.callback.IFailure;
import com.henghao.latte.net.callback.IRequest;
import com.henghao.latte.net.callback.ISuccess;
import com.henghao.latte.net.callback.RequestCallBacks;
import com.henghao.latte.ui.LatteLoader;
import com.henghao.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zb on 2017/11/3.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (httpMethod) {
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }

    }
    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
