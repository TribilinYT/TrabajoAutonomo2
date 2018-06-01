package facci.pm.ta2.poo.pra1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.DataQuery;
import facci.pm.ta2.poo.datalevel.FindCallback;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements  ListView.OnItemClickListener {

    private View mProgressView;
    private ListView mListView;
    public ResultListAdapter m_adapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String user_email = getIntent().getStringExtra("user_email");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR1 :: Results");

        mListView = (ListView) findViewById(R.id.listView);
        mProgressView = findViewById(R.id.progress);


        mListView.setOnItemClickListener(this);

        showProgress(true);

        // ************************************************************************
        // INICIO - CODE3
        //
        DataQuery query = DataQuery.get("item");
        query.findInBackground("", "", DataQuery.OPERATOR_ALL, new FindCallback<DataObject>() {
            @Override
            public void done(ArrayList<DataObject> dataObjects, DataException e) {
                if (e == null) {
                    if (dataObjects.size() != 0) {
                        m_adapter = new ResultListAdapter(ResultsActivity.this, null);

                        m_adapter.m_array = dataObjects;
                        m_adapter.mActivity = ResultsActivity.this;

                        showProgress(false);
                        mListView.setAdapter(m_adapter);
                    }
                } else {
                    // Error

                }
            }
        });
        // FIN - CODE3
        // ************************************************************************


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {


        // INICIO - CODE5
        //
        DataObject object = (DataObject) m_adapter.m_array.get(position);

        intent = new Intent(ResultsActivity.this, DetailActivity.class); // Aqui por medio de un Intent hacemos la conexion del ResultsActivity
                                                                                        // hacia el DetailActivity

        // Pasamos los parametro hacia la nueva actividad
        intent.putExtra("object_id", object.m_objectId);
        startActivity(intent);
        finish();


        // FIN - CODE5


    }

    private void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mListView.setVisibility(show ? View.GONE : View.VISIBLE);

    }
//

    // En este metodo lo que hacemos es que cuando el usuario pulse el boton BACK, este lo Regrese al activity anterior
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
