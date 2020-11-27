package ferramentas;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class EventosDB extends SQLiteOpenHelper{

    private Context contexto;

    public EventosDB(Context cont){
        super(cont, "evento", null, 1 );
        contexto = cont;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String criaTabela = String.format("CREATE TABLE IF NOT EXISTS evento(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXTvalor REAL, imagem TEXT" +
                ", dataocorreu DATE, datacadastro DATE, datavaliDA DATE)");

        db.execSQL(criaTabela);

    }
    public void insereEvento(){
            try (SQLiteDatabase db = this.getWritableDatabase()){

                ContentValues valores= new ContentValues();
                valores.put("nome", "padaria");
                valores.put("valor", -70);

                db.insert("evento", null, valores);


            }catch(SQLiteException ex){
                ex.printStackTrace();
            }

    }

    public void atualizaEvento(){

    }
    public void buscaEventos(){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ficará parado até a atualização da Activity de update(funcionalidade)
    }
}
