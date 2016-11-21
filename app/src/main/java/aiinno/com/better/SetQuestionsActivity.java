package aiinno.com.better;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import aiinno.com.better.adapter.QuestionsAdapter;
import aiinno.com.better.adapter.RulesAdapter;
import aiinno.com.better.model.QuestionsBean;
import aiinno.com.better.model.RuleBean;
import aiinno.com.better.ui.AddPlanActivity;

public class SetQuestionsActivity extends AppCompatActivity {

    public static List<QuestionsBean> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_questions);
        ListView listView = (ListView) findViewById(R.id.add_questions_listview);
        Button button = (Button) findViewById(R.id.add_questions);

        datas = new ArrayList<>();
        datas.add(new QuestionsBean());
        final QuestionsAdapter adapter = new QuestionsAdapter(datas, this, SetQuestionsActivity.this);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add(new QuestionsBean());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
