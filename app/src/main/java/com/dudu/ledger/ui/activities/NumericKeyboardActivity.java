package com.dudu.ledger.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dudu.ledger.R;
import com.dudu.ledger.adapters.KeyBoardAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * @author : Code23
 * @time : 2021/1/13
 * @module : NumericKeyboardActivity
 * @describe :数字键盘界面
 */
public class NumericKeyboardActivity extends AppCompatActivity {
    private TextView numerickeyboardInputtext;
    private ImageView numerickeyboardClear;
    private RecyclerView numerickeyboardKeyboardlist;

    private int width;//屏幕宽度
    private KeyBoardAdapter keyBoardAdapter;
    private List<String> stringList = new ArrayList<>();

    private String inputnumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerickeyboard);
        initView();
    }

    private void initView() {
        //获取屏幕宽度
        Resources resources = this.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        width = px2dip(this, displayMetrics.widthPixels)-1;

        numerickeyboardInputtext = (TextView) findViewById(R.id.numerickeyboard_inputtext);
        numerickeyboardClear = (ImageView) findViewById(R.id.numerickeyboard_clear);
        numerickeyboardKeyboardlist = (RecyclerView) findViewById(R.id.numerickeyboard_keyboardlist);

        //获取资源文件数据
        String[] keyboards = getResources().getStringArray(R.array.keyboard);
        for (String key : keyboards) {
            stringList.add(key);
        }

        //数字键盘列表适配器实例化
        keyBoardAdapter = new KeyBoardAdapter(this,stringList, width);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//控制行数
            @Override
            public int getSpanSize(int position) {//最后一行占两位置，其余占一个位置
                return position != 10 ? 1 : 2;
            }
        });
        numerickeyboardKeyboardlist.setLayoutManager(gridLayoutManager);
        numerickeyboardKeyboardlist.setAdapter(keyBoardAdapter);

        /**
         * 数字键盘列表item时间监听
         */
        keyBoardAdapter.setOnClickKeyBoardListener(new KeyBoardAdapter.OnClickKeyBoardListener() {
            @Override
            public void OnClickKeyBoard(String key) {//数字按钮
                int lens = (inputnumber + key).length();
                if (lens < 9) {
                    inputnumber = inputnumber + key;
                    numerickeyboardInputtext.setText(inputnumber);
                }
            }

            @Override
            public void OnClickSure() {//确定按钮
                Intent intent = new Intent();
                intent.putExtra("keyboard_data",inputnumber);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        /**
         * 清除
         */
        numerickeyboardClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputnumber = "";
                numerickeyboardInputtext.setText(inputnumber);
            }
        });
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}

