package com.lxg.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String URL = "http://v.juhe.cn/joke/content/list.php";

    private Button btnGet, btnPost,btnAll;
    private TextView tvContent;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = findViewById(R.id.btn_get);
        btnPost = findViewById(R.id.btn_post);
        tvContent = findViewById(R.id.tv_content);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();

            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
            }
        });

    }

    /**
     * 显示
     */
    private void showContent(String msg) {
        tvContent.setText(msg);

    }
    /**
     * post
     */
    private void postRequest() {
        HttpParams paramsPost = new HttpParams();
        paramsPost.put("sort", "desc");
        paramsPost.put("page", "1");
        paramsPost.put("pagesize", "5");
        paramsPost.put("time", "1514736923");


        OkGoBuilder.getInstance()
                .Builder(this)
                .url(URL)
                .method(OkGoBuilder.PSOT)
                .params(paramsPost)
                .cls(HttpDataBean.class)
                .callback(new Callback<HttpDataBean>() {
                    @Override
                    public void onSuccess(HttpDataBean response, int id) {
                        Log.i(TAG, "onSuccess1: " + response.getReason());
                        showContent("post:" + response.getReason());
                    }

                    @Override
                    public void onError(Throwable e, int id) {

                    }
                })
                .build();
    }

    /**
     * get
     */
    private void getRequest() {
        HttpParams paramsGet = new HttpParams();
        paramsGet.put("sort", "desc");
        paramsGet.put("page", "1");
        paramsGet.put("pagesize", "10");
        paramsGet.put("time", "1418816972");
        OkGoBuilder.getInstance()
                .Builder(MainActivity.this)
                .url(URL)
                .method(OkGoBuilder.GET)
                .params(paramsGet)
                .cls(HttpDataBean.class)
                .callback(new Callback<HttpDataBean>() {
                    @Override
                    public void onSuccess(HttpDataBean response, int id) {
                        Log.i(TAG, "onSuccess: " + response.getReason());
                        showContent("get:" + response.getReason());
                    }

                    @Override
                    public void onError(Throwable e, int id) {

                    }
                })
                .build();
    }


}
