package facci.pm.ta2.poo.pra1;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.DataQuery;
import facci.pm.ta2.poo.datalevel.GetCallback;

public class DetailActivity extends AppCompatActivity {
    TextView descripcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR1 :: Detail");

        //Tomamos control de4l elemento grafico descripcion de la Activity_Detail
        descripcion= (TextView) findViewById(R.id.descripcion);


        // aqui establecemos el movimiento del elemento grafico ligado a esta variable.
        descripcion.setMovementMethod(LinkMovementMethod.getInstance());





        // INICIO - CODE6
        final String object_id = getIntent().getStringExtra("object_id");
       DataQuery query = DataQuery.get("item");
       query.getInBackground(object_id, new GetCallback<DataObject>() {
           @Override
           public void done(DataObject object, DataException e) {
               if(e==null){}


               // aqui tomamos control de los objetos name, price, descripion, image,
               // y posterior mente le asignamos dato de tipo Strin, String, String y Bimap reapectivamente
               // todos extraidos del Array object.
              TextView name= (TextView) findViewById(R.id.nombre);
               name.setText((String) object.get("name"));

               TextView price = (TextView) findViewById(R.id.precio);
               price.setText((String) object.get("price"));

               TextView description =(TextView) findViewById (R.id.descripcion);
               description.setText((String) object.get("description"));

               ImageView  image=  (ImageView) findViewById(R.id.thumbnail);
               image.setImageBitmap((Bitmap) object.get("image"));





               }

       });

                // FIN - CODE6




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
