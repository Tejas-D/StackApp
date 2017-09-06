package jhb.dvt.co.za.stackapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import jhb.dvt.co.za.stackapp.R;
import jhb.dvt.co.za.stackapp.adapters.QuestionsAdapter;
import jhb.dvt.co.za.stackapp.asynctasks.QuestionsAsyncTask;
import jhb.dvt.co.za.stackapp.asynctasks.QuestionsAsyncTask.QuestionsResultListener;

public class MainActivity extends Activity implements QuestionsResultListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.stackRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        getDataAndPopulateRecycerView();
    }

    private void getDataAndPopulateRecycerView() {
        String url = "https://api.stackexchange.com/2.2/questions?pagesize=50&todate=" +
                (System.currentTimeMillis() / 1000L) +
                "&order=desc&sort=creation&tagged=android&site=stackoverflow";

        (new QuestionsAsyncTask(this, url, this)).execute(this);
    }

    @Override
    public void setQuestionsResult(String result) {

        QuestionsAdapter adapter = new QuestionsAdapter(this, result);

        recyclerView.setAdapter(adapter);
    }
}