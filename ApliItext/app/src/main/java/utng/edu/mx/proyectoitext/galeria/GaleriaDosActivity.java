package utng.edu.mx.proyectoitext.galeria;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import utng.edu.mx.proyectoitext.R;

public class GaleriaDosActivity extends Activity implements View.OnClickListener {

    ImageView foto;
    TextView tv;
    int[] fotoId = {R.drawable.bici1, R.drawable.bici2, R.drawable.bici1, R.drawable.bici1,R.drawable.bici1,R.drawable.bici6,R.drawable.bici7,R.drawable.bici8,
            R.drawable.bici9};
    String[] textos = {"BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA", "BICICLETA"};
    int i = 0;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_uno);

        Button anterior = (Button) findViewById(R.id.button1);
        Button siguiente = (Button) findViewById(R.id.button2);
        anterior.setOnClickListener(this);
        siguiente.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.textView1);
        foto = (ImageView) findViewById(R.id.imageView1);
        total = fotoId.length;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == R.id.button2) {
            i++;
            if(i == total){
                i = 0;
            }
        }

        if(id == R.id.button1) {
            i--;
            if(i == -1){
                i = total -1;
            }
        }
       foto.setImageResource(fotoId[i]);
        tv.setText(textos[i]);

    }

}
