package aiinno.com.better.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aiinno.com.better.R;
import aiinno.com.better.SetQuestionsActivity;
import aiinno.com.better.model.QuestionsBean;
import aiinno.com.better.model.RuleBean;
import aiinno.com.better.ui.AddPlanActivity;

/**
 * Created by lbk on 2016/11/21.
 */

public class QuestionsAdapter extends BaseAdapter {
    //private List<ItemBean> datas;
    private List<QuestionsBean> datas;
    private Context context;
    private LayoutInflater inflater;
    private Activity activity;

    List<Map<Integer, String>> p = new ArrayList<>();

    public QuestionsAdapter(List<QuestionsBean> datas, Context context, Activity activity) {
        this.datas = datas;
        this.context = context;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final QuestionsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new QuestionsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.questions_list_item, null);
            viewHolder.rule = (EditText) convertView.findViewById(R.id.rule_value);
            viewHolder.num = (TextView) convertView.findViewById(R.id.num);
            viewHolder.tvDelete = (TextView) convertView.findViewById(R.id.tv_detele);
            viewHolder.delete = (RelativeLayout) convertView.findViewById(R.id.detele);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (QuestionsAdapter.ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            viewHolder.tvDelete.setVisibility(View.GONE);
        } else {
            viewHolder.tvDelete.setVisibility(View.VISIBLE);
        }

        viewHolder.num.setText(String.valueOf(position + 1));


        //final ItemBean bean = datas.get(position);
        final QuestionsBean bean = datas.get(position);
        if (viewHolder.rule.getTag() instanceof TextWatcher) {
            viewHolder.rule.removeTextChangedListener((TextWatcher) (viewHolder.rule.getTag()));
        }

        if (TextUtils.isEmpty(bean.getQuestion())) {
            viewHolder.rule.setText("");
        } else {
            viewHolder.rule.setText(bean.getQuestion());
        }

        if (bean.isFocus()) {
            if (!viewHolder.rule.isFocused()) {
                viewHolder.rule.requestFocus();
            }
            CharSequence text = bean.getQuestion();
            viewHolder.rule.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
        } else {
            if (viewHolder.rule.isFocused()) {
                viewHolder.rule.clearFocus();
            }
        }

        viewHolder.rule.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    final boolean focus = bean.isFocus();
                    check(position);
                    if (!focus && !viewHolder.rule.isFocused()) {
                        viewHolder.rule.requestFocus();
                        viewHolder.rule.onWindowFocusChanged(true);
                    }
                }
                return false;
            }
        });

        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    bean.setQuestion(null);
                } else {
                    bean.setQuestion(String.valueOf(s));
                }
            }
        };

        viewHolder.rule.addTextChangedListener(watcher);
        viewHolder.rule.setTag(watcher);



        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0) {
                    new AlertDialog.Builder(context).setTitle("你确定要删除这个问题" + (position + 1) + "吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SetQuestionsActivity.datas.remove(position);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }

            }
        });


        return convertView;
    }


    private void check(int position) {
        for (QuestionsBean l : datas) {
            l.setFocus(false);
        }
        datas.get(position).setFocus(true);
    }

    class ViewHolder {
        EditText rule;
        TextView num;
        TextView tvDelete;
        RelativeLayout delete;
    }
}
