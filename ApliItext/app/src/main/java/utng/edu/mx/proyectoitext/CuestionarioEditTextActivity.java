package utng.edu.mx.proyectoitext;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import utng.edu.mx.proyectoitext.util.DBAdapter;


public class CuestionarioEditTextActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtRespuesta;
    private Button btnComprobarEscrito;
    private TextView txvOracionParteUno,txvOracionParteDos;
    private String oracionUno,oracionDos;
    private Bundle valoresResividos;
    private DBAdapter dbAdapter;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_edit_text_layout);
        initComponents();
    }

    private void initComponents() {
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        edtRespuesta= (EditText) findViewById(R.id.edt_respuesta);
        btnComprobarEscrito= (Button) findViewById(R.id.btn_comprobar_escrito);

        txvOracionParteUno= (TextView) findViewById(R.id.txv_oracion_parte_uno);
        txvOracionParteDos= (TextView) findViewById(R.id.txv_oracion_parte_dos);


        valoresResividos=getIntent().getExtras();

        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 2://Sintaxis
                        oracionUno="Que método se utiliza para añadir celdas a una tabla?";
                        oracionDos=".";
                        break;
                    case 5://Operadores
                        oracionUno="Para utilizar itext necesitas añadir atus librerias: ";
                        oracionDos=".";
                        break;
                    default:
                        break;
                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Bloques
                        oracionUno="En la actualidad a que evoluciono PostScript (IPS):";
                        oracionDos=".";
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Hashes
                        oracionUno="Completa la sintaxis para abrir un document: document. ";
                        oracionDos=".";
                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Directorios
                        oracionUno="En la actualidad a que evoluciono PostScript (IPS) ";
                        oracionDos=".";
                        break;
                    case 5://Expreciónes Regulares
                        oracionUno="Para utilizar una fuente primero debe de crear un:  ";
                        oracionDos=".";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        edtRespuesta.setMaxWidth(120);



        txvOracionParteUno.setText(oracionUno);
        txvOracionParteDos.setText(oracionDos);
        btnComprobarEscrito.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        String respuesta = "Incorrecto";
       // String espacios="\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
        int idModul=dbAdapter.idPrimerModuloIns(FormLoginActivity.ID_USU_LOGEADO,"Modulo 1");
        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 2://Sintaxis
                        if (edtRespuesta.getText().toString().toLowerCase().equals("addcell")) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 3);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 5://Operadores
                        if (edtRespuesta.getText().toString().toLowerCase().equals("paquetes")) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 6);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Bloques
                        if (edtRespuesta.getText().toString().toLowerCase().equals("pdf")) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 2, 3);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Hashes
                        if (edtRespuesta.getText().toString().toLowerCase().equals("open()")) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 3, 3);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 2://Directorios
                        if (edtRespuesta.getText().toString().toLowerCase().equals("pdf")) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            //int idModul=dbAdapter.idPrimerModuloIns(FormLoginActivity.ID_USU_LOGEADO,"Modulo 1");
                            dbAdapter.activarTema(idModul, 4, 3);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 5://Expreciónes Regulares
                        if (edtRespuesta.getText().toString().toLowerCase().equals("tipo de letra")) {
                            respuesta ="Correcto; Examen del módulo desbloqueado";
                            dbAdapter.activarTema(idModul, 4, 6);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioEditTextActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
