package aiinno.com.better;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    public static List<ItemBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_layout);

        ListView listView = (ListView) findViewById(R.id.listview);
        Button button = (Button) findViewById(R.id.add);

        datas = new ArrayList<>();
        datas.add(new ItemBean());
        final ListAdapter adapter = new ListAdapter(datas, this, TestActivity.this);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add(new ItemBean());
                adapter.notifyDataSetChanged();
            }
        });


    }
}
