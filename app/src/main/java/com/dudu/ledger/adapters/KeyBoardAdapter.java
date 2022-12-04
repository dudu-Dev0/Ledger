package com.dudu.ledger.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dudu.ledger.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @author : Code23
 * @time : 2021/1/13
 * @module : KeyBoardAdapter
 * @describe :
 */
public class KeyBoardAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> stringList = new ArrayList<>();
    private int width;//屏幕宽度

    public KeyBoardAdapter(Context context, List<String> stringList, int width) {
        this.context = context;
        this.stringList = stringList;
        this.width = width;
    }

    class KeyBoardHolder extends RecyclerView.ViewHolder {
        private TextView keyboardKey;

        public KeyBoardHolder(@NonNull View itemView) {
            super(itemView);
            keyboardKey = (TextView) itemView.findViewById(R.id.keyboard_key);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_keyboard, null, false);
        KeyBoardHolder keyBoardHolder = new KeyBoardHolder(view);
        return keyBoardHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final KeyBoardHolder keyBoardHolder = (KeyBoardHolder) holder;
        keyBoardHolder.keyboardKey.setText(stringList.get(position));

        ViewGroup.LayoutParams layoutParams = keyBoardHolder.keyboardKey.getLayoutParams();
        if (position != 10) {//数字键盘宽度和背景设置
            keyBoardHolder.keyboardKey.setBackgroundResource(R.drawable.btn_gray_bg);
            layoutParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width / 3, context.getResources().getDisplayMetrics()));
        } else {//确定键盘宽度和背景设置
            keyBoardHolder.keyboardKey.setBackgroundResource(R.drawable.btn_blue_bg);
            layoutParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width - (width / 3), context.getResources().getDisplayMetrics()));
        }
        layoutParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width / 3, context.getResources().getDisplayMetrics()));
        keyBoardHolder.keyboardKey.setLayoutParams(layoutParams);

        /**
         * 键盘item点击
         */
        keyBoardHolder.keyboardKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != 10) {//数字键盘监听
                    onClickKeyBoardListener.OnClickKeyBoard(stringList.get(position));
                } else {//确认键盘监听
                    onClickKeyBoardListener.OnClickSure();
                }
            }
        });

        /**
         * 键盘item触目监听
         */
        keyBoardHolder.keyboardKey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        if (position != 10) {
                            keyBoardHolder.keyboardKey.setBackgroundResource(R.drawable.btn_blue_bg);
                        }
                        break;
                    case MotionEvent.ACTION_UP://取消or离开
                    case MotionEvent.ACTION_CANCEL:
                        if (position != 10) {
                            keyBoardHolder.keyboardKey.setBackgroundResource(R.drawable.btn_gray_bg);
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList != null ? stringList.size() : 0;
    }

    public void setOnClickKeyBoardListener(OnClickKeyBoardListener onClickKeyBoardListener) {
        this.onClickKeyBoardListener = onClickKeyBoardListener;
    }

    private OnClickKeyBoardListener onClickKeyBoardListener;

    public interface OnClickKeyBoardListener {
        void OnClickKeyBoard(String key);

        void OnClickSure();
    }
}

