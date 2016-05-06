package utng.edu.mx.proyectoitext.galeria;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import utng.edu.mx.proyectoitext.R;

public class GaleriaUnoActivity extends Activity implements View.OnClickListener {

    ImageView foto;
    TextView tv;
    int[] fotoId = {R.drawable.foto1, R.drawable.foto22, R.drawable.foto3, R.drawable.foto4,R.drawable.foto5,R.drawable.foto6,R.drawable.foto7,R.drawable.foto8,
            R.drawable.foto9,R.drawable.foto10,R.drawable.foto11,R.drawable.foto12,R.drawable.foto13,R.drawable.foto14};
    String[] textos = {"CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS", "CARROS"};
    int E = 0;
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
            E++;
            if(E == total){
                E = 0;
            }
        }

        if(id == R.id.button1) {
            E--;
            if(E == -1){
                E = total -1;
            }
        }
        foto.setImageResource(fotoId[E]);
        tv.setText(textos[E]);

    }

}
