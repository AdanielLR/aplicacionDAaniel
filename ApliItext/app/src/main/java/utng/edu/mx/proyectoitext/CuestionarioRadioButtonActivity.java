package utng.edu.mx.proyectoitext;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import utng.edu.mx.proyectoitext.util.DBAdapter;

public class CuestionarioRadioButtonActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioGroup rbgOpciones;
    private RadioButton rbtRespuestaUno,rbtRespuestaDos;
    private Button btnComprobarRes;
    private Bundle valoresResividos;
    private TextView txvPregunta;
    private String pregunta="",opcionUno="",opcionDos="";
    private DBAdapter dbAdapter;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_radio_button_layout);
        initComponents();
    }

    private void initComponents() {
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        rbgOpciones = (RadioGroup) findViewById(R.id.rbg_opciones);
        rbtRespuestaUno = (RadioButton) findViewById(R.id.rbt_repuesta_uno);
        rbtRespuestaDos = (RadioButton) findViewById(R.id.rbt_repuesta_dos);
        btnComprobarRes = (Button) findViewById(R.id.btn_comprobar_res);
        txvPregunta=(TextView)findViewById(R.id.txv_pregunta);



        valoresResividos=getIntent().getExtras();

        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 1://Medio ambiente de configuración
                        pregunta="Que es iText ";
                        opcionUno="es una biblioteca Open Source para crear y manipular archivos PDF, RTF, y HTML en Java";
                        opcionDos="es un lector para documentos de oficce";
                        break;
                    case 3://Palabras reservadas
                        pregunta="¿La licencia de iText es gratis? ";
                        opcionUno="no";
                        opcionDos="si";
                        break;
                    default:
                        break;
                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Método
                        pregunta="Cuando fue fundado Adobe Systems Incorporated ";
                        opcionUno="En 1982 por John Warnock y Chuck";
                        opcionDos="En 1979 por John Daniels";
                        break;
                    case 3://Modulo
                        pregunta="Como se crea una instancia PDFWriter? ";
                        opcionUno="getInstance";
                        opcionDos="PdfWriter.getInstance";
                        break;
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Arreglos
                        pregunta="para que sirve el objeto chunk ";
                        opcionUno="Para agregar texto";
                        opcionDos="Para quitar texto";
                        break;
                    case 3://Fecha y hora
                        pregunta="para que sirve el objeto phrase";
                        opcionUno="Para eliminar texto";
                        opcionDos="Para agregar texto";
                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Iterators
                        pregunta="iText puede añadir capítulos y secciones ";
                        opcionUno="Cierto";
                        opcionDos="Falso";
                        break;
                    case 3://Excepciones
                        pregunta="Como se establece la alineación de un párrafo ";
                        opcionUno="paragraph.setAlignment(Element.ALIGN_LEFT);\n" +
                                "paragraph.setAlignment(Element.ALIGN_CENTER);\n" +
                                "paragraph.setAlignment(Element.ALIGN_RIGHT);\n";
                        opcionDos="(Element.ALIGN_LEFT);\n" +
                                "(Element.ALIGN_CENTER);\n" +
                                "(Element.ALIGN_RIGHT);\n";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        txvPregunta.setText(pregunta);
        rbtRespuestaUno.setText(opcionUno);
        rbtRespuestaDos.setText(opcionDos);

        btnComprobarRes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String respuesta = "Incorrecto";
        //String espacios="\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
        int idModul=dbAdapter.idPrimerModuloIns(FormLoginActivity.ID_USU_LOGEADO,"Modulo 1");
        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 1://Medio ambiente de configuración

                        if (rbtRespuestaUno.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 2);

                        } else if (rbtRespuestaDos.isChecked()) {
                                vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 3://Palabras reservadas
                        if (rbtRespuestaUno.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaDos.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 4);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Método
                        if (rbtRespuestaDos.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaUno.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 2, 2);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 3://Modulo
                        if (rbtRespuestaUno.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaDos.isChecked()) {
                            respuesta = "Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 2, 4);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Arreglos
                        if (rbtRespuestaUno.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 3, 2);
                        } else if (rbtRespuestaDos.isChecked()) {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 3://Fecha y hora
                        if (rbtRespuestaUno.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaDos.isChecked()) {
                            respuesta ="Correcto; Examen del módulo desbloqueado";
                            dbAdapter.activarTema(idModul, 3, 4);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 1://Iterators
                        if (rbtRespuestaDos.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaUno.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 4, 2);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 3://Excepciones
                        if (rbtRespuestaDos.isChecked()) {
                            vibrator.vibrate(1000);
                        } else if (rbtRespuestaUno.isChecked()) {
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 4, 4);
                        }
                        Toast.makeText(CuestionarioRadioButtonActivity.this, respuesta, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
