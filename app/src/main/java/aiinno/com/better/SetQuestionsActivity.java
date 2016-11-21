package aiinno.com.better;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import aiinno.com.better.adapter.QuestionsAdapter;
import aiinno.com.better.adapter.RulesAdapter;
import aiinno.com.better.card.UserPlanProvider;
import aiinno.com.better.model.QuestionsBean;
import aiinno.com.better.model.RuleBean;
import aiinno.com.better.ui.AddPlanActivity;
import aiinno.com.better.card.QuestionProvider;

public class SetQuestionsActivity extends AppCompatActivity {

    public static List<QuestionsBean> datas;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_questions);

       listView = (ListView) findViewById(R.id.add_questions_listview);
        datas = new ArrayList<>();
        //datas.add(new ItemBean());
        datas.add(new QuestionsBean());
        //final ListAdapter adapter = new ListAdapter(datas, this, AddPlanActivity.this);
        final QuestionsAdapter adapter = new QuestionsAdapter(datas, this, SetQuestionsActivity.this);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.add_questions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add(new QuestionsBean());
                adapter.notifyDataSetChanged();

            }
        });
    }
}
