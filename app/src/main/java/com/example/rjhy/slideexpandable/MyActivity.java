package com.example.rjhy.slideexpandable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tjerkw.slideexpandable.library.AbstractSlideExpandableListAdapter;
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;


public class MyActivity extends Activity {
    private ListView listView;
    private ActionSlideExpandableListView slideExpandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initAdapter3();
    }

    public void initAdapter(){
        listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.text);
        for (int i=0; i<10; i++){
            adapter.add("text"+i);
        }
        listView.setAdapter(new SlideExpandableListAdapter(adapter, R.id.expandable_toggle_button, R.id.expandable));
    }

    public void initAdapter2(){
        slideExpandableListView = (ActionSlideExpandableListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.text);
        for (int i=0; i<10; i++){
            adapter.add("text"+i);
        }
        //如果用这个方法设置adapter，list_item的layout布局文件中button和LinearLayout的id一定要是R.id.expandable_toggle_button和R.id.expandable
        //因为未指定id的情况下，ListView默认会找id为R.id.expandable_toggle_button和R.id.expandable的对象，然后进行一些必要操作，否则抛空指针异常
        slideExpandableListView.setAdapter(adapter);
        //指定button和LinearLayout的id的setAdapter方法
//        slideExpandableListView.setAdapter(adapter, R.id.expandable_toggle_button0, R.id.expandable);

    }

    public void initAdapter3(){
        slideExpandableListView = (ActionSlideExpandableListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.text);
        for (int i=0; i<10; i++){
            adapter.add("text"+i);
        }
        //添加button点击事件监听器
        ActionSlideExpandableListView.OnActionClickListener onActionClickListener = new ActionSlideExpandableListView.OnActionClickListener() {
            @Override
            public void onClick(View itemView, View clickedView, int position) {
                Toast.makeText(MyActivity.this, clickedView.getId()+"", Toast.LENGTH_SHORT).show();
            }
        };
        slideExpandableListView.setItemActionListener(onActionClickListener, R.id.buttonA, R.id.buttonB);

        slideExpandableListView.setAdapter(adapter, R.id.expandable_toggle_button, R.id.expandable);
        //添加监听器
        SlideExpandableListAdapter slideExpandableListAdapter = (SlideExpandableListAdapter) slideExpandableListView.getAdapter();
        slideExpandableListAdapter.setItemExpandCollapseListener(new AbstractSlideExpandableListAdapter.OnItemExpandCollapseListener() {
            @Override
            public void onExpand(View itemView, int position) {
                Toast.makeText(MyActivity.this, "onExpand", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCollapse(View itemView, int position) {
                Toast.makeText(MyActivity.this, "onCollapse", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
