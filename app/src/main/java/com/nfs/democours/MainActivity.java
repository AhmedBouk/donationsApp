package com.nfs.democours;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nfs.democours.beans.Quote;
import com.nfs.democours.beans.adapter.QuoteListAdapter;

import java.util.ArrayList;
import java.util.List;

/*
    FAIRE ATTENTION A LA CONSO DE LA BATTERIE : Resume, Pause, Stop puis Restart
 */
public class MainActivity extends AppCompatActivity {

    private static String tagLifeCycle = "Life Cycle";
    private static String quoteLog = "*** QUOTE ***";

    private List<Quote> quotes;

    private ListView quotesListView;
    private QuoteListAdapter qla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tagLifeCycle, "onCreate");
        setContentView(R.layout.activity_main);

        quotes = new ArrayList<>();
        quotes.add(new Quote(1,"RTML", 5));
        quotes.add(new Quote(2,"ouuuulaaaa", 1));
        quotes.add(new Quote(3,"1 + 1 ??????", 0));

        quotesListView = (ListView) findViewById(R.id.mainQuoteList);
        qla = new QuoteListAdapter(this, android.R.layout.simple_list_item_1  , android.R.id.text1, quotes);
        quotesListView.setAdapter(qla);
        quotesListView.setOnItemLongClickListener(this::onItemLongClick);

        final EditText quoteInput = (EditText) findViewById(R.id.mainQuoteInput);
        quoteInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                quoteInput.setHint("");
                return false;
            }
        });

    }

    public void addQuote(View view){
        Quote q = new Quote(quotes.size(), ((EditText) findViewById(R.id.mainQuoteInput)).getText().toString(), 0);
        quotes.add(q);
        Log.d(quoteLog, "quotes " + quotes.toString());
        resfrehList(); // uniquement car pas API ni DB ni autre
    }

    /*public void clickMe(View view){
        Log.d("click", "click");
        Toast toast = Toast.makeText( this,"on m'a clické", Toast.LENGTH_LONG);
        toast.show();
        TextView tv = findViewById(R.id.TVMain);
        tv.setText("on a cliqué");
    }*/

    private void resfrehList(){
        qla = new QuoteListAdapter(this, android.R.layout.simple_list_item_1  , android.R.id.text1, quotes);
        quotesListView.setAdapter(qla);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tagLifeCycle, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tagLifeCycle, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tagLifeCycle, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tagLifeCycle, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tagLifeCycle, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tagLifeCycle, "onRestart");
    }

    private boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Quote quote = (Quote) quotesListView.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
        intent.putExtra("quote", quote);
        startActivityForResult(intent, 1);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Log.d(quoteLog, "code retour " + resultCode);
            if(resultCode == RESULT_OK){
                Quote q = (Quote) data.getExtras().get("quote");
                for (int i = 0; i < quotes.size(); i++) {
                    if(quotes.get(i).getId() == q.getId()){
                        quotes.get(i).setQuote(q.getQuote());
                        quotes.get(i).setNotation(q.getNotation());
                        break;
                    }
                }
                resfrehList();
                //qla.notifyDataSetChanged();
            } else if(resultCode == RESULT_FIRST_USER){
                for (int i = 0; i < quotes.size(); i++) {
                    if(quotes.get(i).getId() == Integer.parseInt(data.getExtras().get("index").toString())){
                        quotes.remove(i);
                        break;
                    }
                }
                resfrehList();
            }
        }
    }
}