package com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.transaction.Transactional;

import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Models.RegistroEntradasySalidas;

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

@Transactional
@Repository
public class CountingTime {


    /*
        Semana 1 = 1 - 7
        Semana 2 = 8 - 14
        Semana 3 = 15 - 20
        Semana 4 = 21 - 31
    */

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<String> idUsuariosglobal = new ArrayList<>();

    public static String theMonth(int month){
        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return monthNames[month];
    }

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public SimpleDateFormat normalformat = new SimpleDateFormat("yyyy-MM-dd");


    public void processingUserFromPayload(String fk_id_usuario){

        idUsuariosglobal.clear();
        idUsuariosglobal.add(new String(fk_id_usuario));
        if(idUsuariosglobal.size()>0){

            gettingUserRecords();
            idUsuariosglobal.clear();

        }

    }



    public void satartCounting()  {


        String query = "SELECT id from usuarios";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {


            idUsuariosglobal.add(new String(row.get("id").toString()));
                //gettingUserRecords(row.get("id").toString());
            
        }

        if(idUsuariosglobal.size()>0){
           
            /*
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat justDay = new SimpleDateFormat("dd");
            Date d1;
            Date day;
            int week_of_month=0;
            try {
                d1 = sdf.parse("2019-09-25");
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


            } catch (ParseException e) {
                
                e.printStackTrace();
            }*/
            

            gettingUserRecords();
            idUsuariosglobal.clear();
        }
       
    }

    public long getMiliseconds(String dateStop, String dateStart){
        
        Date d1 = null;
        Date d2 = null;
        long diff =0;
        try {
			d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
			//in milliseconds
            diff = d2.getTime() - d1.getTime();
        } catch (Exception e) {
			e.printStackTrace();
		}    
        return diff;
    }


    private ArrayList<String> sortDates(List<String> fechas) throws ParseException {
        //THIS PIECE OF CODE IS FOR SORTING the DATES FROM THE LITTLE ONES TO THE BIGEST ones
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Map<Date, String> dateFormatMap = new TreeMap<>();
        for (String date : fechas)
            dateFormatMap.put(f.parse(date), date);
        return new ArrayList<>(dateFormatMap.values());
    }


    public void gettingUserRecords()  {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(String st:idUsuariosglobal){

        //checkTiempoPersmisosLaborados(st);    
        int counter=0;
        String query = "SELECT * from registro_entradas_salidas where fecha_analizada='no' and fk_id_usuario="+st+" order by id";
        List<RegistroEntradasySalidas> recordInOut = new ArrayList<>();
        List<String> fechas = new ArrayList<>();

        recordInOut = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new RegistroEntradasySalidas(
                                rs.getString("id"),
                                rs.getString("fk_id_usuario"),
                                rs.getString("tipo_registro"),
                                rs.getString("fechayhora_registro"),
                                rs.getString("fecha_registro"),
                                rs.getString("fecha_analizada")
                                
                        )
        );

        if(recordInOut.size()>0){

            for(RegistroEntradasySalidas st2:recordInOut ){
                    fechas.add(st2.getFechaRegistro());
            }
    
            Set<String> set = new HashSet<>(fechas);
            fechas.clear();
            fechas.addAll(set);
            String idTemporal="";
            String fechaYhoraRegistroTemporal="";
            long diff =0;
            long totaldiffPerDate=0;

                try {
                    fechas = sortDates(fechas);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            
            for(String st4:fechas){

                
    
                for(RegistroEntradasySalidas st3:recordInOut ){
    
                     if(st4.equals(st3.fechaRegistro)){
                        
                        if(st3.getTipoRegistro().equals("in")){
                            idTemporal=st3.getId();
                            fechaYhoraRegistroTemporal = st3.getFechayHora();
                            checPermission(st3.getFkIdUsuario(), st3.getFechayHora(),st4);
                            
                            //System.out.println("dates..."+st4);
                        }
                        if(st3.getTipoRegistro().equals("out")){

                            if(!fechaYhoraRegistroTemporal.equals("")){

                                diff = getMiliseconds(st3.getFechayHora(),fechaYhoraRegistroTemporal);
                                totaldiffPerDate = totaldiffPerDate+diff;
                                long diffSeconds = diff / 1000 % 60;
                                long diffMinutes = diff / (60 * 1000) % 60;
                                long diffHours = diff / (60 * 60 * 1000) % 24;
                                long diffDays = diff / (24 * 60 * 60 * 1000);
                        
                                System.out.println("TOTAL DIAS..."+diffDays + " days, ");
                                System.out.println("HORAS..."+diffHours + " hours, ");
                                System.out.println("MINUTES..."+diffMinutes + " minutes, ");
                                System.out.println("SEGUNDOS..."+diffSeconds + " seconds.");
        
                                System.out.println(" ****---------------------**** "+diff);
                                addingTimeWorked(st3.fkIdUsuario,st4, st3.getId(),idTemporal, diff);
                                addingTimeWorkedperweek(st3.fkIdUsuario,st4, st3.getId(),idTemporal, diff);
                                addingTimeWorkedpermonth(st3.fkIdUsuario,st4, st3.getId(),idTemporal, diff);

                            }
                            
                            fechaYhoraRegistroTemporal="";
    
                        }
                     }  
                }
    
            }


        }

       
       
    }

}

public void addingTimeWorked(String fk_id_usuario, String fecha, String idDateStart, String idDateEnd, long diff){

    
        String query = "SELECT id,fecha from total_tiempo_laborado where fk_id_usuario="+fk_id_usuario+" and fecha='"+fecha+"'";
        List<TemporalPermisos> recordInOut = new ArrayList<>();
        String fechaYhoraRegistroTemporal="";
        String id="";
        String fechaHoy = format.format(new Date());
    
        recordInOut = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new TemporalPermisos(
                                rs.getString("id"),
                                rs.getString("fecha")
                                
                        )
        );

        if(recordInOut.size()>0){

            query = "SELECT id, total_milisegundos from total_tiempo_laborado where fecha='"+fecha+"'";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
            long totalworkedtime=0;
            for (Map row : rows) {
                id = row.get("id").toString();
                totalworkedtime = Long.parseLong(row.get("total_milisegundos").toString());
               
            }   

            totalworkedtime = totalworkedtime+diff;

            long diffSeconds = totalworkedtime / 1000 % 60;
            long diffMinutes = totalworkedtime / (60 * 1000) % 60;
            long diffHours = totalworkedtime / (60 * 60 * 1000) % 24;
    
            System.out.println("NUEVO HORAS..."+diffHours + " hours, ");
            System.out.println("NUEVO MINUTES..."+diffMinutes + " minutes, ");
            System.out.println("NUEVO SEGUNDOS..."+diffSeconds + " seconds.");
            

            query = "update total_tiempo_laborado set total_horas="+diffHours+", total_minutos="+diffMinutes+", total_segundos="+diffSeconds+", total_milisegundos="+totalworkedtime+" where id="+id;
            jdbcTemplate.update(query);
            System.out.println("miliseconds..*"+totalworkedtime);

            query = "update registro_entradas_salidas set fecha_analizada='si' where id="+idDateStart;
            jdbcTemplate.update(query);

            query = "update registro_entradas_salidas set fecha_analizada='si' where id="+idDateEnd;
            jdbcTemplate.update(query);

        }else{


            creatingTotalTiempoLaborado(fk_id_usuario,fecha, diff);


            query = "update registro_entradas_salidas set fecha_analizada='si' where id="+idDateStart;
            jdbcTemplate.update(query);

            query = "update registro_entradas_salidas set fecha_analizada='si' where id="+idDateEnd;
            jdbcTemplate.update(query);
              

        }
    
}


public void addingTimeWorkedperweek(String fk_id_usuario, String fecha, String idDateStart, String idDateEnd, long diff){

           

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1;
            Date day;
            int week_of_month=0;
            String query ="";
            try {
                d1 = sdf.parse(fecha);
                Calendar cl = Calendar. getInstance();
                cl.setMinimalDaysInFirstWeek(3);
                cl.setTime(d1);
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
                String year = String.valueOf(cl.get(cl.YEAR));
                String month = theMonth(cl.get(cl.MONTH)); 
                String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);

                query = "SELECT id,total_milisegundos  from total_tiempo_laborado_por_semanas where fk_id_usuario="+fk_id_usuario+" and year="+year+" and fk_id_mes="+(cl.get(cl.MONTH)+1)+" and semana="+week_of_month;
                
                List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
                long totalworkedtime=0;
                if(rows.size()>0){

                    for (Map row : rows) {

                        long totaldiff = Long.parseLong(row.get("total_milisegundos").toString())+diff;
                        long diffSeconds = totaldiff / 1000 % 60;
                        long diffMinutes = totaldiff / (60 * 1000) % 60;
                        long diffHours = totaldiff / (60 * 60 * 1000) % 24;
                        long diffDays = totaldiff / (24 * 60 * 60 * 1000);
                        //System.out.println("wehave got data and the ID is .."+row.get("id").toString());
                        query = "update total_tiempo_laborado_por_semanas set total_dias="+diffDays+", total_horas="+diffHours+", total_minutos="+diffMinutes+", total_segundos="+diffSeconds+", total_milisegundos="+totaldiff+" where id="+row.get("id").toString();
                        jdbcTemplate.update(query);
                    }
                        

                }else{



                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);
                    query = "insert into total_tiempo_laborado_por_semanas(fk_id_usuario,year,fk_id_mes,semana,total_dias,total_horas,total_minutos,total_segundos,total_milisegundos) values("+fk_id_usuario+","+year+","+(cl.get(cl.MONTH)+1)+","+week_of_month+","+diffDays+","+diffHours+","+diffMinutes+","+diffSeconds+","+diff+")";
                    jdbcTemplate.update(query);
                }



            }catch(ParseException e) {
                
                e.printStackTrace();
            }
}

    
public void addingTimeWorkedpermonth(String fk_id_usuario, String fecha, String idDateStart, String idDateEnd, long diff){


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d1;
    Date day;
    int week_of_month=0;
    String query ="";
    try {
        d1 = sdf.parse(fecha);
        Calendar cl = Calendar. getInstance();
        cl.setMinimalDaysInFirstWeek(3);
        cl.setTime(d1);
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
        String year = String.valueOf(cl.get(cl.YEAR));
        String month = theMonth(cl.get(cl.MONTH)); 
        String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);

        query = "SELECT id,total_milisegundos  from total_tiempo_laborado_por_meses where fk_id_usuario="+fk_id_usuario+" and year="+year+" and fk_id_mes="+(cl.get(cl.MONTH)+1);
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        long totalworkedtime=0;
        if(rows.size()>0){

            for (Map row : rows) {

                long totaldiff = Long.parseLong(row.get("total_milisegundos").toString())+diff;
                long diffSeconds = totaldiff / 1000 % 60;
                long diffMinutes = totaldiff / (60 * 1000) % 60;
                long diffHours = totaldiff / (60 * 60 * 1000) % 24;
                long diffDays = totaldiff / (24 * 60 * 60 * 1000);
                //System.out.println("wehave got data and the ID is .."+row.get("id").toString());
                query = "update total_tiempo_laborado_por_meses set total_dias="+diffDays+", total_horas="+diffHours+", total_minutos="+diffMinutes+", total_segundos="+diffSeconds+", total_milisegundos="+totaldiff+" where id="+row.get("id").toString();
                jdbcTemplate.update(query);
            }
                

        }else{



            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            query = "insert into total_tiempo_laborado_por_meses(fk_id_usuario,year,fk_id_mes,total_dias,total_horas,total_minutos,total_segundos,total_milisegundos) values("+fk_id_usuario+","+year+","+(cl.get(cl.MONTH)+1)+","+
            
            diffDays+","+diffHours+","+diffMinutes+","+diffSeconds+","+diff+")";
            jdbcTemplate.update(query);
        }



    }catch(ParseException e) {
        
        e.printStackTrace();
    }
}


    public void creatingTotalTiempoLaborado(String fk_id_usuario, String fecha, long diff){


        System.out.println("THE DATE...."+fecha);    
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
                String month = theMonth(cl.get(cl.MONTH)); 
                String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es","ES")).format(d1);   

                String sqlInsertQuery = "INSERT INTO total_tiempo_laborado (fk_id_usuario,fecha,fk_id_mes, fk_id_semana, fk_id_dia,total_horas,	total_minutos,total_segundos,total_milisegundos) VALUES ("+fk_id_usuario+",'"+fecha+"',"+ (cl.get(cl.MONTH)+1)+","+week_of_month+","+theDay(dayOfWeek)+","+(diff / (60 * 60 * 1000) % 24)+","+(diff / (60 * 1000) % 60)+","+(diff / 1000 % 60)+","+diff+" )";

                System.out.println(sqlInsertQuery);

                jdbcTemplate.update(sqlInsertQuery);
            } catch (ParseException e) {
                
                e.printStackTrace();
            }



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

    public void checkTiempoPersmisosLaborados(String fk_id_usuario){

        String query = "SELECT id,fecha_hora_solicitud_permiso,fecha_solicitud_permiso from total_tiempo_permisos where fk_id_usuario="+fk_id_usuario+" and activo='si'";
        List<TemporalPermisos> recordInOut = new ArrayList<>();
        String fechaYhoraRegistroTemporal="";
        String id="";
        long diff =0;
        String fechaHoy = format.format(new Date());
        System.out.println("date now --> "+fechaHoy);
        recordInOut = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new TemporalPermisos(
                                rs.getString("id"),
                                rs.getString("fecha_hora_solicitud_permiso")
                                
                        )
        );

        if(recordInOut.size()>0){

            for (TemporalPermisos row : recordInOut) {
                
                fechaYhoraRegistroTemporal = row.getFechaYhoraRegistroTemporal();
                id = row.getId();
            }


            diff = getMiliseconds(fechaHoy,fechaYhoraRegistroTemporal);
    
    
            long diffSeconds = diff / 1000 % 60;
                            long diffMinutes = diff / (60 * 1000) % 60;
                            long diffHours = diff / (60 * 60 * 1000) % 24;
                            long diffDays = diff / (24 * 60 * 60 * 1000);
                    
                            System.out.println("TOTAL DIAS..."+diffDays + " days, ");
                            System.out.println("HORAS..."+diffHours + " hours, ");
                            System.out.println("MINUTES..."+diffMinutes + " minutes, ");
                            System.out.println("SEGUNDOS..."+diffSeconds + " seconds.");

            query = "UPDATE total_tiempo_permisos set total_dias="+diffDays+",total_horas="+diffHours+", total_minutos="+diffMinutes+", total_segundos="+diffSeconds+" where fk_id_usuario="+fk_id_usuario+" and activo='si' and id="+id;
            jdbcTemplate.update(query);               

        }

    }


    public void checPermission(String fk_id_usuario, String fechaHora, String rawDate){


        String query = "SELECT id,fecha_hora_solicitud_permiso,fecha_solicitud_permiso from total_tiempo_permisos where fk_id_usuario="+fk_id_usuario+" and activo='si' and fecha_solicitud_permiso <='"+rawDate+" '";

        System.out.println("QUERY .."+query);

        List<TemporalPermisos> recordInOut = new ArrayList<>();
        recordInOut = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new TemporalPermisos(
                                rs.getString("id"),
                                rs.getString("fecha_hora_solicitud_permiso")
                                
                        )
        );

        String fechaYhoraRegistroTemporal="";
        String fechaSolicitud = "";
        String id="";
        long diff =0;
        if(recordInOut.size()>0){

            System.out.println("||||||||||||||||||||||PERMISO||||||||||||||||");

            for (TemporalPermisos row : recordInOut) {
                fechaYhoraRegistroTemporal = row.getFechaYhoraRegistroTemporal();
                id = row.getId();
            }

            try {
                Date date1 = normalformat.parse("2009-12-31");
                Date date2 = normalformat.parse("2010-01-31");

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           

            diff = getMiliseconds(fechaHora,fechaYhoraRegistroTemporal);
            long diffSeconds = diff / 1000 % 60;
                            long diffMinutes = diff / (60 * 1000) % 60;
                            long diffHours = diff / (60 * 60 * 1000) % 24;
                            long diffDays = diff / (24 * 60 * 60 * 1000);
                    
                            System.out.println("TOTAL DIAS..."+diffDays + " days, ");
                            System.out.println("HORAS..."+diffHours + " hours, ");
                            System.out.println("MINUTES..."+diffMinutes + " minutes, ");
                            System.out.println("SEGUNDOS..."+diffSeconds + " seconds.");

            query = "UPDATE total_tiempo_permisos set activo='no', total_dias="+diffDays+",total_horas="+diffHours+", total_minutos="+diffMinutes+", total_segundos="+diffSeconds+" where fk_id_usuario="+fk_id_usuario+" and activo='si' and id="+id;
            jdbcTemplate.update(query);
    
            System.out.println("||||||||||||||||||||||PERMISO||||||||||||||||");
        }
        


    }

}


class TemporalPermisos{

    public String id;
    public String fechaYhoraRegistroTemporal;



    public TemporalPermisos(String id, String fechaYhoraRegistroTemporal) {
        this.id = id;
        this.fechaYhoraRegistroTemporal = fechaYhoraRegistroTemporal;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaYhoraRegistroTemporal() {
        return this.fechaYhoraRegistroTemporal;
    }

    public void setFechaYhoraRegistroTemporal(String fechaYhoraRegistroTemporal) {
        this.fechaYhoraRegistroTemporal = fechaYhoraRegistroTemporal;
    }



}