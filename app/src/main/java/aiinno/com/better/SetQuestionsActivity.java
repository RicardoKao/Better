package aiinno.com.better;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
    private MaterialListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_questions);

       listView = (MaterialListView) findViewById(R.id.add_questions_listview);
        Button button = (Button) findViewById(R.id.add_questions);
        List<Card> cards = new ArrayList<>();
        Card card = new Card.Builder(this)
                .withProvider(new QuestionProvider())
                .endConfig()
                .build();
        cards.add(card);
        listView.getAdapter().addAll(cards);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
