package utng.edu.mx.proyectoitext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import utng.edu.mx.proyectoitext.util.DBAdapter;


public class CuestionarioCheckBoxActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txvPreguntaCheckBox;
    private CheckBox chkCajaUno,chkCajaDos,chkCajaTres;
    private Bundle valoresResividos;
    private Button btnComprobarCheckBox;
    private DBAdapter dbAdapter;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_check_box_layout);

        initComponents();
    }

    private void initComponents() {
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        txvPreguntaCheckBox=(TextView)findViewById(R.id.txv_pregunta_ckech_box);
        chkCajaUno=(CheckBox)findViewById(R.id.chk_caja_uno);
        chkCajaDos=(CheckBox)findViewById(R.id.chk_caja_dos);
        chkCajaTres=(CheckBox)findViewById(R.id.chk_caja_tres);
        btnComprobarCheckBox=(Button)findViewById(R.id.btn_comprobar_check_box);

        valoresResividos=getIntent().getExtras();

        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 0: //Sobre ruby
                        txvPreguntaCheckBox.setText("iText fue creado por:");
                        chkCajaUno.setText("Marco Maccarini");
                        chkCajaDos.setText("Daniel Licea");
                        chkCajaTres.setText("Eduard Maccarini");
                        break;
                    case 4://Variables
                        txvPreguntaCheckBox.setText("¿Las primeras versiones de iText fueron publicado bajo que “Licencia Pública”?");
                        chkCajaUno.setText(" GNU ");
                        chkCajaDos.setText(" (LGPL)");
                        chkCajaTres.setText("TRAS");
                        break;
                    case 6://Comentarios
                        txvPreguntaCheckBox.setText("¿Para que copile un proyecto se necesita?");
                        chkCajaUno.setText(" jdk ");
                        chkCajaDos.setText(" El paquete com.Iowalgie.text ");
                        chkCajaTres.setText(" librerias");
                        break;
                    default:
                        break;

                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Ciclos
                        txvPreguntaCheckBox.setText("Para poder generar pdf en un entorno de java que se necesita?");
                        chkCajaUno.setText(" itext.jar");
                        chkCajaDos.setText(" jek ");
                        chkCajaTres.setText(" metodo ");
                        break;
                    case 4://Mixins
                        txvPreguntaCheckBox.setText("Como crear la sangría");
                        chkCajaUno.setText(" paragraph.setIndentationLeft(50);  ");
                        chkCajaDos.setText(" paragraph.setIndentationRight(50); ");
                        chkCajaTres.setText(" paragraph ");
                        break;
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Strings
                        txvPreguntaCheckBox.setText("Cual fue el objetivo de crear PostScript :");
                        chkCajaUno.setText(" Su objetivo principal es describir el aspecto del texto, objetos gráficos, y las imágenes ");
                        chkCajaDos.setText(" Su objetivo principal es visualisar documentos");
                        chkCajaTres.setText("Su objetivo principal fue enseñar al usuario a programar ");
                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Rangos
                        txvPreguntaCheckBox.setText("Como se realiza un interlineado :");
                        chkCajaUno.setText(" Paragraph paragraph = new Paragraph(50); ");
                        chkCajaDos.setText(" paragraph = new Paragraph(50);");
                        chkCajaTres.setText(" Paragraph paragraph = Paragraph(50);");
                        break;
                    case 4://Orientado a objetos
                        txvPreguntaCheckBox.setText("Como se ajusta un espacio :");
                        chkCajaUno.setText(" paragraph.setSpacingAfter(50);");
                        chkCajaDos.setText(" paragraph.setSpacingBefore(50);");
                        chkCajaTres.setText(" paragraph.setSpacingBefore; ");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        btnComprobarCheckBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String respuesta = "Incorrecto";
        //String espacios="\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
        int idModul = dbAdapter.idPrimerModuloIns(FormLoginActivity.ID_USU_LOGEADO, "Modulo 1");
        switch (valoresResividos.getInt("moduloS")){
            case 0://Modulo 1
                switch (valoresResividos.getInt("posicionTemaS")){
                    case 0: //Sobre ruby

                        if (chkCajaUno.isChecked()&&chkCajaTres.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            //Inicia lo del guardado------------------------------------------------------

                            if (valoresResividos.getBoolean("logeo")) {
                                dbAdapter.activarTema(idModul, 1, 1);
                                // boolean act=dbAdapter.temaActivo(1,2,idModul);
                                // Log.e("Tema 2 mod 1:", "onClick: "+act );
                            }else {
                                startActivity(new Intent(CuestionarioCheckBoxActivity.this,TerminoTemaOffActivity.class));
                                finish();
                                //mandarme a un pantalla que diga que Felizitaciones terinaste con extito el tema 1, para poder sergir con el curso te recomenadamos logearte
                                //dos botones uno que diga resgistrarme y otro que diga despues
                            }
                            //Termina lo del guardado------------------------------------------------------
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();

                        break;
                    case 4://Variables

                        if (chkCajaUno.isChecked()&&chkCajaDos.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 5);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        break;
                    case 6://Comentarios

                        if (chkCajaDos.isChecked()){
                            respuesta = "Correcto; Examen del módulo desbloqueado";
                            dbAdapter.activarTema(idModul, 1, 7);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        break;
                    default:
                        break;

                }
                break;
            case 1://Modulo 2
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Ciclos
                        ///////////////////////////////////////
                        if (chkCajaUno.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 2, 1);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        ///////////////////////////////////////
                        break;
                    case 4://Mixins
                        if (chkCajaDos.isChecked()){
                            respuesta ="Correcto; Examen del módulo desbloqueado";
                            dbAdapter.activarTema(idModul, 2, 5);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        ///////////////////////////////////////
                        break;
                    default:
                        break;
                }
                break;
            case 2://Modulo 3
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Strings
                        if (chkCajaUno.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 3, 1);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();

                        break;
                    default:
                        break;
                }
                break;
            case 3://Modulo 4
                switch (valoresResividos.getInt("posicionTemaS")) {
                    case 0://Rangos
                        if (chkCajaUno.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 4, 1);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        break;
                    case 4://Orientado a objetos
                        if (chkCajaUno.isChecked()||chkCajaDos.isChecked()){
                            respuesta ="Correcto; Siguiente tema desbloqueado";
                            dbAdapter.activarTema(idModul, 4, 5);
                        }else {
                            vibrator.vibrate(1000);
                        }
                        Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                        limpiar();
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

    private void limpiar(){
        chkCajaUno.setChecked(false);
        chkCajaDos.setChecked(false);
        chkCajaTres.setChecked(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        limpiar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
