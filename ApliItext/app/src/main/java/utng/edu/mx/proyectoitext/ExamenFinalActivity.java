package utng.edu.mx.proyectoitext;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import utng.edu.mx.proyectoitext.util.DBAdapter;


public class ExamenFinalActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txvPreguntaUno,txvPreguntaDos,txvPreguntaTres, txvPreguntaCuatro,txvPreguntaCinco;
    private Button btnComprobar;
    private RadioGroup rgbGrupoUno, rgbGrupoDos;
    private CheckBox chkUno,chkDos,chkTres,chkUnoDos,chkDosDos,chkTresDos;
    private TextView txvParteUno,txvParteTres;
    private EditText edtParteDos;
    private RadioButton rbtOpcionUno,rbtOpcionDos,rbtOpcionUnoDos,rbtOpcionDosDos;
    //Timepo
    private TextView txvTiempo,txvTituloExam;

    int calificacion=0;
    private Bundle valorResividos;
    private Bundle bundle;
    //SQlite
    private DBAdapter dbAdapter;
    CounterClass timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_final_layout);
        initComponents();
    }//nombreModulo

    private void initComponents() {
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        valorResividos =getIntent().getExtras();
        bundle=new Bundle();
        //TXV de las preguntas
        txvPreguntaUno=(TextView)findViewById(R.id.txv_pregunta_uno);
        txvPreguntaDos=(TextView)findViewById(R.id.txv_pregunta_dos);
        txvPreguntaTres=(TextView)findViewById(R.id.txv_pregunta_tres);
        txvPreguntaCuatro =(TextView)findViewById(R.id.txv_pregunta_cuatro);
        txvPreguntaCinco=(TextView)findViewById(R.id.txv_pregunta_cinco);
        //TXV extras
        txvTituloExam=(TextView)findViewById(R.id.titulo_exam);
        //BTN para comprobar
        btnComprobar=(Button)findViewById(R.id.btn_comprobar);
        //RGB de los radio
        rgbGrupoUno =(RadioGroup)findViewById(R.id.rbg_opciones_uno);
        rgbGrupoDos =(RadioGroup)findViewById(R.id.rbg_opciones_dos);
        //RBT radio
        rbtOpcionUno =(RadioButton)findViewById(R.id.rbt_repuesta_uno);
        rbtOpcionDos =(RadioButton)findViewById(R.id.rbt_repuesta_dos);
        rbtOpcionUnoDos =(RadioButton)findViewById(R.id.rbt_repuesta_uno_dos);
        rbtOpcionDosDos =(RadioButton)findViewById(R.id.rbt_repuesta_dos_dos);
        //CHK
        chkUno=(CheckBox)findViewById(R.id.chk_caja_uno);
        chkDos=(CheckBox)findViewById(R.id.chk_caja_dos);
        chkTres=(CheckBox)findViewById(R.id.chk_caja_tres);
        chkUnoDos=(CheckBox)findViewById(R.id.chk_caja_uno_dos);
        chkDosDos=(CheckBox)findViewById(R.id.chk_caja_dos_dos);
        chkTresDos=(CheckBox)findViewById(R.id.chk_caja_tres_dos);
        //Equipo
        txvParteUno=(TextView)findViewById(R.id.txv_parte_uno);
        txvParteTres=(TextView)findViewById(R.id.txv_parte_tres);
        edtParteDos=(EditText)findViewById(R.id.edt_parte_dos);

        //Tiempo
        txvTiempo=(TextView)findViewById(R.id.tiempo);
        txvTiempo.setText("00:01:00");

        timer = new CounterClass(90000, 1000);
        timer.start();

        txvTituloExam.setText(valorResividos.getString("nombreModulo"));
        switch (valorResividos.getInt("numeroExamne")){
            case 1:
                txvPreguntaUno.setText("Que es itext");
                rbtOpcionUno.setText("es una biblioteca open source para crear y manipular archivos PDF en java");
                rbtOpcionDos.setText("es un lector de documentos");

                txvPreguntaDos.setText("¿El lenguaje de programación fue creado por?");
                chkUno.setText("brake");
                chkDos.setText("Marco Maccarini");
                chkTres.setText("Eduard Maccarini");

                txvPreguntaTres.setText("Que metodo se utiliza para añadir celdas a una tabla" );
                txvParteUno.setText("");
                //edtParteDos
                txvParteTres.setText("");

                txvPreguntaCuatro.setText("¿La licencia de itext es gratis?");
                rbtOpcionUnoDos.setText("Falso");
                rbtOpcionDosDos.setText("Cierto");

                txvPreguntaCinco.setText("¿Las primeras versiones de iText fueron publicado bajo que “Licencia Pública”?\n(Elige dos)");
                chkUnoDos.setText("GNU ");
                chkDosDos.setText("traal");
                chkTresDos.setText("(LGPL)");
                break;
            case 2: ///////-----------------------------------------------aqui me quede
                txvPreguntaUno.setText("Para poder generar pdf en un entorno de java que se necesita");
                rbtOpcionUno.setText("itext.jar");
                rbtOpcionDos.setText("jdk");

                txvPreguntaDos.setText("Cuando fue fundado Adobe Systems Incorporated");
                chkUno.setText("En 1979 por John daniels");
                chkDos.setText("En 1982 por John Warnock y Chuck");
                chkTres.setText("En 1982 por Denis burge");

                txvPreguntaTres.setText("En la actualidad a que evoluciono PostScript (IPS)  ");
                txvParteUno.setText("");
                //edtParteDos
                txvParteTres.setText("");

                txvPreguntaCuatro.setText("Como se crea una instancia PDFWriter");
                rbtOpcionUnoDos.setText("PdfWriter.getInstance");
                rbtOpcionDosDos.setText("getInstance");

                txvPreguntaCinco.setText("Como crear una sangría");
                chkUnoDos.setText("paragraph.setIndentationLeft(50);\n" +
                        "paragraph.setIndentationRight(50);\n");
                chkDosDos.setText("paragraph");
                chkTresDos.setText("setIndentationLeft");
                break;
            case 3:
                txvPreguntaUno.setText("para que sirve el objrto phrase");
                rbtOpcionUno.setText("Para agregar texto");
                rbtOpcionDos.setText("Para eliminar texto");

                txvPreguntaDos.setText("para que sirve el objrto chunk");
                chkUno.setText("Para agregar texto");
                chkDos.setText("Para eliminar texto");
                chkTres.setText("Para insertar texto");

                txvPreguntaTres.setText("completa la sintaxis para abrir un doc. document.");
                txvParteUno.setText("");
                //edtParteDos
                txvParteTres.setText("");

                txvPreguntaCuatro.setText("para que sirve el objrto phrase");
                rbtOpcionUnoDos.setText("para agregar texto");
                rbtOpcionDosDos.setText("para eliminar texto");

                txvPreguntaCinco.setText("Cual fue el objetivo de crear PostScript");
                chkUnoDos.setText("Su objetivo principal es describir el aspecto del texto");
                chkDosDos.setText("Su objetivo principal es describir  objetos gráficos, y las imágenes");
                chkTresDos.setText("Ninguno");
                break;
            case 4:
                txvPreguntaUno.setText("Itext puede añadir capítulos y secciones");
                rbtOpcionUno.setText("Cierto");
                rbtOpcionDos.setText("Falso");

                txvPreguntaDos.setText("como se ajusta un espacio");
                chkUno.setText("paragraph.setSpacingAfter(50);");
                chkDos.setText("paragraph.setSpacingBefore(50);");
                chkTres.setText("paragraph.setSpacingBefore");

                txvPreguntaTres.setText("En la actualidad a que evoluciono PostScript (IPS)");
                txvParteUno.setText("");
                //edtParteDos
                txvParteTres.setText("");

                txvPreguntaCuatro.setText("Como se realiza un interlineado");
                rbtOpcionUnoDos.setText("Paragraph paragraph = new Paragraph(50);");
                rbtOpcionDosDos.setText(" paragraph = new Paragraph");

                txvPreguntaCinco.setText("Que métodos hay para agregar texto");
                chkUnoDos.setText("Phrase");
                chkDosDos.setText("Chunk");
                chkTresDos.setText("fuente");
                break;
            case 5:
                ///////////////////////////////////////////////////////////////////////////////////
                txvTituloExam.setText("Examen final");
                txvPreguntaUno.setText("¿La licencia de itext es gratis?");
                rbtOpcionUno.setText("si");
                rbtOpcionDos.setText("no");

                txvPreguntaDos.setText("desde que año esta la herramienta itext");
                chkUno.setText("1965");
                chkDos.setText("2000");
                chkTres.setText("2008");

                txvPreguntaTres.setText("En la actualidad a que evoluciono PostScript (IPS)");
                txvParteUno.setText("");
                //edtParteDos
                txvParteTres.setText("");

                txvPreguntaCuatro.setText("Que método se utiliza para añadir celdas a una tabla");
                rbtOpcionUnoDos.setText("addCell");
                rbtOpcionDosDos.setText("AddTab");

                txvPreguntaCinco.setText("Las primeras versiones de iText fueron publicado bajo que “Licencia Pública”?");
                chkUnoDos.setText("GNU ");
                chkDosDos.setText("(LGPL)");
                chkTresDos.setText("GNISK");
                break;
            default:
                Log.e("NO entro en el switch", "initComponents: ");
                break;
        }

        btnComprobar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int idModul=dbAdapter.idPrimerModuloIns(FormLoginActivity.ID_USU_LOGEADO, "Modulo 1");
        int califTraida;

        switch (valorResividos.getInt("numeroExamne")){
            case 1:
                if (rbtOpcionUno.isChecked()){
                    calificacion++;
                }
                if (chkDos.isChecked()||chkTres.isChecked()){
                    calificacion++;
                }
                if (edtParteDos.getText().toString().equals("addCell()")){
                    calificacion++;
                }
                if (rbtOpcionDosDos.isChecked()){
                    calificacion++;
                }
                if (chkUnoDos.isChecked()||chkTresDos.isChecked()){
                    calificacion++;
                }
                dbAdapter.activarTema(idModul, 1, 8);
                califTraida=dbAdapter.traerCalificacion(1, FormLoginActivity.ID_USU_LOGEADO);
                //Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                califTraida=califTraida/2;
                Log.e("Calificacion traida", "onClick: " + califTraida + " " + calificacion);
                //El que inserta
                if (calificacion>califTraida) {
                    dbAdapter.setCalifModulo(FormLoginActivity.ID_USU_LOGEADO, 1, calificacion);
                }
                //el que trae la calif en toast
                califTraida=dbAdapter.traerCalificacion(1,FormLoginActivity.ID_USU_LOGEADO);
                Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                //--------------------------------------------
                Log.e("Calificacion incertada", "onClick: "+califTraida );
                timer.cancel();
                timer.onFinish();
                break;
            case 2:
                /////////////////////////////////////////
                if (rbtOpcionUno.isChecked()){
                    calificacion++;
                }
                if (chkDos.isChecked()){
                    calificacion++;
                }
                if (edtParteDos.getText().toString().equals("PDF")){
                    calificacion++;
                }
                if (rbtOpcionUnoDos.isChecked()){
                    calificacion++;
                }
                if (chkUnoDos.isChecked()){
                    calificacion++;
                }
                dbAdapter.activarTema(idModul, 2, 6);
                califTraida=dbAdapter.traerCalificacion(2, FormLoginActivity.ID_USU_LOGEADO);
                //Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                califTraida=califTraida/2;
                Log.e("Calificacion traida", "onClick: " + califTraida + " " + calificacion);
                //El que inserta
                if (calificacion>califTraida) {
                    dbAdapter.setCalifModulo(FormLoginActivity.ID_USU_LOGEADO, 2, calificacion);
                }
                //el que trae la calif en toast
                califTraida=dbAdapter.traerCalificacion(2,FormLoginActivity.ID_USU_LOGEADO);
                Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                //--------------------------------------------
                Log.e("Calificacion incertada", "onClick: " + califTraida);
                timer.cancel();
                timer.onFinish();
                /////////////////////////////////////////
                break;
            case 3:
                if (rbtOpcionUno.isChecked()){
                    calificacion++;
                }
                if (chkUno.isChecked()||chkTres.isChecked()){
                    calificacion++;
                }
                if (edtParteDos.getText().toString().equals("open()")){
                    calificacion++;
                }
                if (rbtOpcionUnoDos.isChecked()){
                    calificacion++;
                }
                if (chkUnoDos.isChecked()||chkDosDos.isChecked()){
                    calificacion++;
                }
                dbAdapter.activarTema(idModul, 3, 5);
                califTraida=dbAdapter.traerCalificacion(3, FormLoginActivity.ID_USU_LOGEADO);
                //Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                califTraida=califTraida/2;
                Log.e("Calificacion traida", "onClick: " + califTraida + " " + calificacion);
                //El que inserta
                if (calificacion>califTraida) {
                    dbAdapter.setCalifModulo(FormLoginActivity.ID_USU_LOGEADO, 3, calificacion);
                }
                //el que trae la calif en toast
                califTraida=dbAdapter.traerCalificacion(3,FormLoginActivity.ID_USU_LOGEADO);
                Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                //--------------------------------------------
                Log.e("Calificacion incertada", "onClick: " + califTraida);
                timer.cancel();
                timer.onFinish();
                /////////////////////////////////////////
                break;
            case 4:
                if (rbtOpcionUno.isChecked()){
                    calificacion++;
                }
                if (chkUno.isChecked()||chkDos.isChecked()){
                    calificacion++;
                }
                if (edtParteDos.getText().toString().equals("PDF")){
                    calificacion++;
                }
                if (rbtOpcionUnoDos.isChecked()){
                    calificacion++;
                }
                if (chkUnoDos.isChecked()||chkDosDos.isChecked()){
                    calificacion++;
                }
                dbAdapter.activarTema(idModul, 4, 7);
                califTraida=dbAdapter.traerCalificacion(4, FormLoginActivity.ID_USU_LOGEADO);
                //Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                califTraida=califTraida/2;
                Log.e("Calificacion traida", "onClick: " + califTraida + " " + calificacion);
                //El que inserta
                if (calificacion>califTraida) {
                    dbAdapter.setCalifModulo(FormLoginActivity.ID_USU_LOGEADO, 4, calificacion);
                }
                //el que trae la calif en toast
                califTraida=dbAdapter.traerCalificacion(4,FormLoginActivity.ID_USU_LOGEADO);
                Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                //--------------------------------------------
                Log.e("Calificacion incertada", "onClick: " + califTraida);
                timer.cancel();
                timer.onFinish();
                /////////////////////////////////////////
                break;
            case 5:
                if (rbtOpcionUno.isChecked()){
                    calificacion++;
                }
                if (chkDos.isChecked()){
                    calificacion++;
                }
                if (edtParteDos.getText().toString().equals("elementos de diferentes tipos")){
                    calificacion++;
                }
                if (rbtOpcionUnoDos.isChecked()){
                    calificacion++;
                }
                if (chkUnoDos.isChecked()||chkDosDos.isChecked()){
                    calificacion++;
                }
                califTraida=dbAdapter.traerCalificacion(5, FormLoginActivity.ID_USU_LOGEADO);
                //Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                califTraida=califTraida/2;
                Log.e("Calificacion traida", "onClick: " + califTraida + " " + calificacion);
                //El que inserta
                if (calificacion>califTraida) {
                    dbAdapter.setCalifModulo(FormLoginActivity.ID_USU_LOGEADO, 5, calificacion);
                }
                //el que trae la calif en toast
                califTraida=dbAdapter.traerCalificacion(5,FormLoginActivity.ID_USU_LOGEADO);
                Toast.makeText(ExamenFinalActivity.this, "Su calif: "+califTraida, Toast.LENGTH_SHORT).show();
                //--------------------------------------------
                Log.e("Calificacion incertada", "onClick: " + califTraida);
                //startActivity(new Intent());
                timer.cancel();
                timer.onFinish();
                startActivity(new Intent(ExamenFinalActivity.this,EjemploEmailActivity.class));
                break;
            default:
                Log.e("NO entro", "onClick:  ");
                break;
        }
        finish();
    }

    public void tiempoTerminado(int tipo){
        bundle=new Bundle();
        bundle.putString("elegido", valorResividos.getString("nombreModulo"));
        bundle.putInt("posicion", valorResividos.getInt("modulo"));
        bundle.putBoolean("logeo", valorResividos.getBoolean("logeo"));
        switch (tipo){
            case 1:
                //pantalla con la calificacion(Splash)
                //dos diferentes tipos una que aprobo y otra que no
                break;
            case 2:
                Toast.makeText(ExamenFinalActivity.this, "Tiempo terminado", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(ExamenFinalActivity.this, SeleccionTemaActivity.class).putExtras(bundle));
                finish();
                break;
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onFinish() {
            if (calificacion>0){
                tiempoTerminado(1);
            }else {
                txvTiempo.setText("00:00:00");
                tiempoTerminado(2);
            }
            //--------------------------------------------------------------------------------------------------------------------
        }
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            txvTiempo.setText(hms);

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        calificacion=0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
