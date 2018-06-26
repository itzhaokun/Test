package com.aixuetang.test;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> secondList = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondList.add("选项A");
        secondList.add("选项B");
        secondList.add("选项C");
        secondList.add("选项D");
        secondList.add("选项E");
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(secondList));
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
        private List<String> data;

        public RecyclerAdapter(ArrayList<String> secondList) {
            data = secondList;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, parent, false));
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            holder.textView.setText(data.get(position));
            holder.badge.setBadgeText("");
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.badge.hide(true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView textView;
            Badge badge;

            public Holder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_content);
                badge = new QBadgeView(MainActivity.this).bindTarget(itemView.findViewById(R.id.root));
                badge.setBadgeGravity(Gravity.CENTER | Gravity.END);
                badge.setBadgeTextSize(14, true);
                badge.setBadgePadding(6, true);
            }
        }
    }
}

//    private Button button;
//    String a = "2018-6-11 12:11:00";
//    private RecyclerView rv_test;
//    private ArrayList<String> firstList = new ArrayList<>();
//    private ArrayList<String> secondList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //选项模拟数据
//        secondList.add("选项A");
//        secondList.add("选项B");
//        secondList.add("选项C");
//        secondList.add("选项D");
//        secondList.add("选项E");
//        rv_test = findViewById(R.id.recy);
//        //RecyclerView适配器
//        rv_test.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
//        FirstAdapter firstAdapter = new FirstAdapter(MainActivity.this,secondList);
//        rv_test.setAdapter(firstAdapter);
//        button = (Button) findViewById(R.id.aaa);
//        final TextView tv = (TextView) findViewById(R.id.tv);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            private String s;
//
//            @Override
//            public void onClick(View view) {
//                boolean notificationEnabled = NotificationsUtils.isNotificationEnabled(MainActivity.this);
//                if (!notificationEnabled){
//                    Toast.makeText(MainActivity.this, "请手动打开通知", Toast.LENGTH_SHORT).show();
////                    requestPermission();
//                }else{
//                    Toast.makeText(MainActivity.this, "已经开启", Toast.LENGTH_SHORT).show();
//                }

//                try {
//                    s = dateToStamp(a);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                long l = Long.parseLong(s);
//                TimeFormat timeFormat = new TimeFormat(MainActivity.this, l);
//                tv.setText( timeFormat.getDetailTime());
//                String[] split = a.split("[:]");
//                String a = split[1];
//                int i = Integer.parseInt(a);
//                int b = i-15;
//                if (b<0){
//                    String s = split[0];
//                    int a1 = Integer.parseInt(s);
//                    int shi = a1-1;
//                    int fen = 60+b;
//                    tv.setText(shi+":"+fen);
//                }else if (b>0){
//                    String s = split[0];
//                    int a1 = Integer.parseInt(s);
//                    int shi = a1;
//                    int fen = b;
//                    tv.setText(shi+":0"+fen);
//                }else{
//                    String s = split[0];
//                    int a1 = Integer.parseInt(s);
//                    int shi = a1;
//                    int fen = b;
//                    tv.setText(shi+":0"+fen);
//                }


//
//            }
//        });
//
//    }

//    protected void requestPermission() {
//        // TODO Auto-generated method stub
//        // 6.0以上系统才可以判断权限
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
//            // 进入设置系统应用权限界面
//            Intent intent = new Intent(Settings.ACTION_SETTINGS);
//            startActivity(intent);
//            return;
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
//            // 进入设置系统应用权限界面
//            Intent intent = new Intent(Settings.ACTION_SETTINGS);
//            startActivity(intent);
//            return;
//        }
//        return;
//    }
//
//    /*
//     * 将时间转换为时间戳
//     */
//    public static String dateToStamp(String s) throws ParseException {
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse(s);
//        long ts = date.getTime();
//        res = String.valueOf(ts);
//        return res;
//    }
//    }
//}
