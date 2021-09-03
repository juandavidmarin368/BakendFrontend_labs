package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.ParseException;
import javax.transaction.Transactional;

import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Controllers.CountingTime;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Cargo;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.PerfilCargo;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Permisions;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.Usuarios;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

//URL FOR JDBC METHODS https://www.mkyong.com/spring/spring-jdbctemplate-querying-examples/

@Transactional
@Repository
public class CargoPerfilesDAO{


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired 
    CountingTime countingtime;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createPerfilCargo(String cargoPerfilName){

        String sqlInsertQuery = "INSERT INTO prefil_cargo (nombre_cargo) VALUES ('"+cargoPerfilName+"')";
        jdbcTemplate.update(sqlInsertQuery);

    }

    public void createCargo(String id, String nombreCargo){

        String sqlInsertQuery = "INSERT INTO cargo (fk_id_perfil_cargo,cargo_nombre) VALUES ("+id+",'"+nombreCargo+"')";
        jdbcTemplate.update(sqlInsertQuery);

    }


    public void createUsuario(String fk_id_cargo, String cedula, String nombre, String celular, String email){

        String sqlInsertQuery = "INSERT INTO usuarios (fk_id_cargo,cedula,nombre,celular,email,activo) VALUES ("+fk_id_cargo+",'"+cedula+"','"+nombre+"','"+celular+"','"+email+"','SI')";
        jdbcTemplate.update(sqlInsertQuery);

    }

    public static String theMonth(int month){
        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return monthNames[month];
    }

    public static String theDay(String day){
        String[] monthNames = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
        
        int dayId=0;
        int selected=0;
        for(String s: monthNames){

            
            if(s.equals(day)){
                System.out.println(s +dayId);
                selected = dayId;
                selected++;
                
            }
            dayId++;
        }

        return String.valueOf(selected);
    }

    public boolean createPermision(Permisions payload){

       
        boolean status=true;
        String query = "SELECT activo from total_tiempo_permisos where fk_id_usuario="+payload.getFk_id_usuario()+" and fecha_solicitud_permiso='"+payload.fecha_solicitud_permiso+"'";
        System.out.println(query);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        
        if(rows.size()>0){

            status=false;

        }else{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat justDay = new SimpleDateFormat("dd");
        Date d1;
        Date day;
        int week_of_month=0;
        try {
            d1 = sdf.parse(payload.getFecha_solicitud_permiso());
            Calendar cl = Calendar. getInstance();
            cl.setMinimalDaysInFirstWeek(3);
            cl.setTime(d1);
            
            System.out.println("teh DAY .."+ cl.get(Calendar.DAY_OF_MONTH));
            int b= cl.get(Calendar.DAY_OF_MONTH);

            
            if( b <= 7){
                week_of_month = 1;
            }else
            if(b == 8 || b<=14){
                week_of_month = 2;
            }else
            if(b == 15 || b<=20){
                week_of_month = 3;
            }else
            if(b == 21 || b<=31){
                week_of_month = 4;
            }
            System.out.println("week of the month** " + week_of_month);
            System.out.println("MES .."+cl.get(cl.MONTH));
            String month = theMonth(cl.get(cl.MONTH)); 
            System.out.println("month name " + month);
            
            String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);   

            System.out.println("DAY NAME  " + dayOfWeek);
            System.out.println("DAY ID  " + theDay(dayOfWeek));
            
            String sqlInsertQuery = "INSERT INTO total_tiempo_permisos (fk_id_usuario,fk_id_personal_que_autoriza_permisos,fk_id_permiso, fk_id_mes, fk_id_semana,fk_id_dia,fecha_hora_solicitud_permiso,fecha_solicitud_permiso,total_dias,total_horas,total_minutos,total_segundos,activo) VALUES ";
            sqlInsertQuery = sqlInsertQuery+"("+payload.getFk_id_usuario()+","+ payload.getFk_id_personal_que_autoriza_permisos()+","+payload.getFk_id_permiso()+","+(cl.get(cl.MONTH)+1)+","+week_of_month+","+theDay(dayOfWeek)+",'"+payload.getFecha_hora_solicitud_permiso()+"','"+payload.getFecha_solicitud_permiso()+"',0,0,0,0,'si')";
           
            System.out.println("QUERY/...."+sqlInsertQuery);

            jdbcTemplate.update(sqlInsertQuery);
        } catch (ParseException e) {
            
            e.printStackTrace();
        } 

        }

        return status;

    }

    public void createRegistroEntradaSalida(String cedula, String tipoRegistro, String fecha, String fechaconhora){

        String fk_id_usuario = getIdUsuario(cedula);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat justDay = new SimpleDateFormat("dd");
            Date d1;
            Date day;
            int week_of_month=0;
            try {
                d1 = sdf.parse(fecha);
                Calendar cl = Calendar. getInstance();
                cl.setMinimalDaysInFirstWeek(3);
                cl.setTime(d1);
                
                //System.out.println("teh DAY .."+ cl.get(Calendar.DAY_OF_MONTH));
                int b= cl.get(Calendar.DAY_OF_MONTH);

                
                if( b <= 7){
                    week_of_month = 1;
                }else
                if(b == 8 || b<=14){
                    week_of_month = 2;
                }else
                if(b == 15 || b<=20){
                    week_of_month = 3;
                }else
                if(b == 21 || b<=31){
                    week_of_month = 4;
                }
                //System.out.println("week of the month** " + week_of_month);
                //System.out.println("MES .."+cl.get(cl.MONTH));
                String month = theMonth(cl.get(cl.MONTH)); 
                //System.out.println("month name " + month);
                
                String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);   

                //System.out.println("DAY NAME  " + dayOfWeek);
                //System.out.println("DAY ID  " + theDay(dayOfWeek));

                String sqlInsertQuery = "INSERT INTO registro_entradas_salidas (fk_id_usuario,fk_id_mes,fk_id_semana, fk_id_dia, tipo_registro,fechayhora_registro,fecha_registro,fecha_analizada) VALUES ("+fk_id_usuario+","+ (cl.get(cl.MONTH)+1)+","+week_of_month+","+theDay(dayOfWeek)+",'"+tipoRegistro+"','"+fechaconhora+"','"+fecha+"','no')";
                jdbcTemplate.update(sqlInsertQuery);

                countingtime.processingUserFromPayload(fk_id_usuario);

            } catch (ParseException e) {
                
                e.printStackTrace();
            }

            
        /*
        System.out.println("cedula..."+cedula);
        System.out.println("tipoRegistro..."+tipoRegistro);
        System.out.println("fecha..."+fecha);
        System.out.println("fechaconhora..."+fechaconhora);*/

        //
        //

    }

    public boolean checkingUsuer(String cedula){

        String result="";
        boolean status=false;

        try {
        String sql = "SELECT cedula FROM `usuarios` WHERE cedula='"+cedula+"'";
        result = jdbcTemplate.queryForObject(sql,String.class);

        return false;

        }catch (EmptyResultDataAccessException e) {
            
            return true;
         }
    
    }

    public String getIdUsuario(String cedula){

        String sql = "SELECT id FROM `usuarios` WHERE cedula='"+cedula+"'";
        return (jdbcTemplate.queryForObject(sql,String.class));

    }

    public List<PerfilCargo> getPerfilCargo(){

        List<PerfilCargo> perfilCargo = new ArrayList<>();
        String query = "SELECT * from perfil_cargo";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {

            perfilCargo.add(new PerfilCargo(row.get("id").toString(), row.get("nombre_cargo").toString()));
            
        
        }

        return perfilCargo;
    }


    public List<Cargo> getCargos(String fk_id_cargos){

        List<Cargo> perfilCargo = new ArrayList<>();
        String query = "SELECT id,cargo_nombre from cargo where fk_id_perfil_cargo='"+fk_id_cargos+"'";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {

            perfilCargo.add(new Cargo(row.get("id").toString(), row.get("cargo_nombre").toString()));
            
        
        }

        return perfilCargo;
    }

    public void createUser(String fkidPerfil, String fkidCargo, String cedula, String nombre,String huella){

        System.out.println("BINARY ..."+huella);


        ///String queryinsertar = "INSERT INTO usuarios(fk_id_perfil,fk_id_cargo,cedula,nombre,celular,email,activo,huella)  VALUES ('" +fkidPerfil+ "','" + fkidCargo + "','" + cedula + "','" + nombre + "','" + "." + "','" + "." + "','" + "SI" + "','" + Base64.decodeBase64(huella) + "')";
       // jdbcTemplate.update(queryinsertar);
        String INSERT_SQL = "INSERT INTO usuarios(fk_id_perfil,fk_id_cargo,cedula,nombre,celular,email,activo,huella) values(?,?,?,?,?,?,?,?)";

       
       KeyHolder holder = new GeneratedKeyHolder();
       jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, Integer.parseInt(fkidPerfil));
				ps.setInt(2, Integer.parseInt(fkidCargo));
                ps.setString(3, cedula);
                ps.setString(4, nombre);
                ps.setString(5, ".");
                ps.setString(6, ".");
                ps.setString(7, ".");
                ps.setBytes(8, Base64.decodeBase64(huella));
				return ps;
			}
		}, holder);

    }

    LobHandler lobHandler = new DefaultLobHandler();
    public List<Usuarios> getUsuarios(){

        List<Usuarios> usuarios = new ArrayList<>();
        String query = "SELECT * from usuarios";
        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new Usuarios(
                                rs.getString("cedula"),
                                rs.getString("nombre"),
                                rs.getBytes("huella"),
                                Base64.encodeBase64String(rs.getBytes("huella"))
                                
                        )
        );


    }


    //************************ TESTING FOR DELETING*/
    public void createRecord(String fecha, String tipo, String cargo, String fk_cedula){
/*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechabase = "2019-09-12";

        Date d1;
try {
                d1 = sdf.parse(fechabase);
                Calendar cl = Calendar. getInstance();
                cl.setMinimalDaysInFirstWeek(3);
                cl.setTime(d1);
	
 String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);

System.out.println("the day.."+dayOfWeek );
if(dayOfWeek.equals("domingo")){

    cl.add(Calendar.DATE, 1);
    fechabase = sdf.format(cl.getTime()); 
}    

System.out.println("NUEVA FECHA "+fechabase);

} catch (ParseException e) {
                
                e.printStackTrace();
}
*/

        
        System.out.println("f "+fecha);
        System.out.println("t "+tipo);
        System.out.println("checked "+cargo);
        System.out.println("cedula "+fk_cedula);
        String temporal = "";
        if(tipo.equals("in")){

            String query = "SELECT id, checked from test where tipo='in' and fecha='"+fecha+"' and fk_id_cedula="+fk_cedula+" ORDER BY ID DESC LIMIT 1";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
            
            if(rows.size()==0){

                System.out.println("inserting...");
                query = "insert into test(fk_id_cedula,fecha,tipo,checked) values("+fk_cedula+",'"+fecha+"','"+tipo+"',1)";
                jdbcTemplate.update(query);

            }else{

                for (Map row : rows) {

                    System.out.println("heres..."+row.get("checked").toString());
                    if(row.get("checked").toString().equals("0")){

                        query = "insert into test(fk_id_cedula,fecha,tipo,checked) values("+fk_cedula+",'"+fecha+"','"+tipo+"',1)";
                        jdbcTemplate.update(query);

                    }

                }
               
            }

        }else{


            String query = "SELECT id from test where tipo='in' and fecha='"+fecha+"' and fk_id_cedula="+fk_cedula+" and checked=1 ORDER BY ID DESC LIMIT 1";
            System.out.println(query);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

            if(rows.size()>0){

                System.out.println("inserting...");
                query = "insert into test(fk_id_cedula,fecha,tipo,checked) values("+fk_cedula+",'"+fecha+"','"+tipo+"',1)";
                jdbcTemplate.update(query);

                for (Map row : rows) {

                    query = "update test set checked=0 where id="+row.get("id").toString();
                    jdbcTemplate.update(query);
                }
                
            }


        }


    }
//************************ TESTING FOR DELETING*/

}