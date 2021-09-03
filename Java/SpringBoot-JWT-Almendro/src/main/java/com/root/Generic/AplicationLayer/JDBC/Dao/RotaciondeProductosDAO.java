package com.root.Generic.AplicationLayer.JDBC.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.util.Map;

import javax.transaction.Transactional;

import com.root.Generic.AplicationLayer.JDBC.Models.Laboratorios;
import com.root.Generic.AplicationLayer.JDBC.Models.RotacionProductosFinal;
import com.root.Generic.AplicationLayer.JDBC.Models.Totalventasdiarias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

//URL FOR JDBC METHODS https://www.mkyong.com/spring/spring-jdbctemplate-querying-examples/

@Transactional
@Repository
public class RotaciondeProductosDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Rproductos findRotactionByproduct(long id, String startDate, String endDate, int mes) {

        String query = "SELECT sum(rd.cantidad_producto) as cantidadTotal, p.nombre_producto as nombreProducto from registro_diario rd INNER JOIN productos p ON rd.fk_id_producto = p.id WHERE rd.fk_id_producto=:idProducto and fecha between :start and :end ";

        Map parameters = new HashMap();
        parameters.put("idProducto", id);
        parameters.put("start", startDate);
        parameters.put("end", endDate);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, new RotacionProductosRowMapper(mes));
    }

    public String getTotalhoy(){

        Calendar aCalendar = Calendar.getInstance();
        Date secpmdDdate = aCalendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDate = sdf.format(secpmdDdate);
        String totalHOy="0";

        try {
            String query="SELECT sum(valor_venta) FROM registro_diario_transaccional WHERE fecha ='" +firstDate+"'  ";
            int totalCurrentmonth = jdbcTemplate.queryForObject(query, Integer.class);
            totalHOy = new   DecimalFormat("#,###.##").format(totalCurrentmonth);        
            }catch(NullPointerException e) {


            }   

        return "$"+totalHOy;

    }


    public String getTotalmonth(String month){

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.DATE, 1);
        Date firstDdate = aCalendar.getTime();
        Calendar bCalendar = Calendar.getInstance();
        bCalendar.set(Calendar.DAY_OF_MONTH, bCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date secpmdDdate = bCalendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDate = sdf.format(firstDdate);
        String secondDate = sdf.format(secpmdDdate);
        String query="";
        String totalMonth="";
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        int totalCurrentmonth = 0;

        if(month.equals("current")){

            try {
                query="SELECT sum(valor_venta) FROM registro_diario WHERE fecha between '" +firstDate+"' and '"+secondDate+"' ";
                totalCurrentmonth = jdbcTemplate.queryForObject(query, Integer.class);
                totalMonth = new   DecimalFormat("#,###.##").format(totalCurrentmonth);        
                }catch(NullPointerException e) {
    
                    totalMonth ="0";
    
                }    

        }

        if(month.equals("last")){

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DATE, 1);
            Date firstDdate1 = cal.getTime();
           
            Calendar bCalendar1 = Calendar.getInstance();
            bCalendar1.add(Calendar.MONTH, -1);
            bCalendar1.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date secpmdDdate1 = bCalendar1.getTime();

            firstDate = sdf.format(firstDdate1);
            secondDate = sdf.format(secpmdDdate1);
            try {
            query="SELECT sum(valor_venta) FROM registro_diario WHERE fecha between '" +firstDate+"' and '"+secondDate+"' ";
            totalCurrentmonth = jdbcTemplate.queryForObject(query, Integer.class);
            totalMonth = new   DecimalFormat("#,###.##").format(totalCurrentmonth);        
            }catch(NullPointerException e) {

                totalMonth ="0";

            }    
            
            //

        }

        
        return "$"+totalMonth;
    }



    public String getsoloTotalventasdiarias(String morningAfternoon){
        String query="";
        List<Totalventasdiarias> totalventasDiarias = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date); 
        String total="0";
        int totalDay=0;
       
        if(morningAfternoon.equals("0")){
            //morning

            System.out.println("it hast got 0");
            query = "SELECT sum(valor_venta) fecha_y_hora from registro_diario_transaccional  where fecha_y_hora < '"+strDate+" 13:59' ";
            System.out.println("it hast got 1 "+query);
            try {

                totalDay = jdbcTemplate.queryForObject(query, Integer.class);
                total = new   DecimalFormat("#,###.##").format(totalDay);     

            }catch(NullPointerException e) {

            }
            
        }
        if(morningAfternoon.equals("1")){
            //afternoon
            
            System.out.println("it hast got 1");
            query = "SELECT sum(valor_venta) fecha_y_hora from registro_diario_transaccional where fecha_y_hora > '"+strDate+" 13:59' ";
            System.out.println("it hast got 1 "+query);
            try {

                totalDay = jdbcTemplate.queryForObject(query, Integer.class);
                total = new   DecimalFormat("#,###.##").format(totalDay);

            }catch(NullPointerException e) {

            }
            

        }    


        return "$"+total;
    }


    public List<Totalventasdiarias> getTotalventasdiariasconPaginacion(String morningAfternoon, int page){
        String query="";
        List<Totalventasdiarias> totalventasDiarias = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date); 
        String queryTotal = "";
       
        if(morningAfternoon.equals("0")){
            //morning

            System.out.println("it hast got 0");
            queryTotal = "select count(id) from registro_diario_transaccional where fecha_y_hora < '"+strDate+" 13:59' ";
            int totalItems = jdbcTemplate.queryForObject(queryTotal, Integer.class);
            int totalPages = totalItems/7;
            
            query = "SELECT p.nombre_producto as nombreProducto, cantidad_producto,valor_venta, fecha_y_hora from registro_diario_transaccional rd INNER JOIN productos p ON rd.fk_id_producto = p.id where rd.fecha_y_hora < '"+strDate+" 13:59' LIMIT 7 OFFSET "+page*7;
            System.out.println("it hast got 1 "+query);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
            int totalVentM=0;
            List<HashMap<String, String>> list = new ArrayList<>();
            
            for (Map row : rows) {

                HashMap<String, String> map = new HashMap<String, String>();
                
                map.put("nombreProducto",row.get("nombreProducto").toString());
                map.put("cantidad_producto",row.get("cantidad_producto").toString());
                map.put("valor_venta","$"+new   DecimalFormat("#,###.##").format(Integer.parseInt(row.get("valor_venta").toString())));
                map.put("fecha_y_hora",row.get("fecha_y_hora").toString());
                totalVentM = totalVentM+Integer.parseInt(row.get("valor_venta").toString());
                list.add(new HashMap(map));
                System.out.println("data...->"+query+row.get("nombreProducto").toString());
                
            }
            
            totalventasDiarias.add(new Totalventasdiarias("$"+new   DecimalFormat("#,###.##").format(totalVentM),list,totalPages));

        }
        if(morningAfternoon.equals("1")){
            //afternoon
            
            queryTotal = "select count(id) from registro_diario_transaccional where fecha_y_hora > '"+strDate+" 13:59' ";
            int totalItems = jdbcTemplate.queryForObject(queryTotal, Integer.class);
            int totalPages = totalItems/7
            
            ;

            System.out.println("it hast got 1");
            query = "SELECT p.nombre_producto as nombreProducto, cantidad_producto,valor_venta, fecha_y_hora from registro_diario_transaccional rd INNER JOIN productos p ON rd.fk_id_producto = p.id where rd.fecha_y_hora > '"+strDate+" 13:59' LIMIT 7 OFFSET "+page*7;
            System.out.println("it hast got 1 "+query);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
            int totalVentM=0;
            List<HashMap<String, String>> list = new ArrayList<>();
            for (Map row : rows) {

                HashMap<String, String> map = new HashMap<String, String>();
                
                map.put("nombreProducto",row.get("nombreProducto").toString());
                map.put("cantidad_producto",row.get("cantidad_producto").toString());
                map.put("valor_venta","$"+new   DecimalFormat("#,###.##").format(Integer.parseInt(row.get("valor_venta").toString())));
                map.put("fecha_y_hora",row.get("fecha_y_hora").toString());
                totalVentM = totalVentM+Integer.parseInt(row.get("valor_venta").toString());
                list.add(new HashMap(map));
                System.out.println("data...->"+query+row.get("nombreProducto").toString());

            }
            
            totalventasDiarias.add(new Totalventasdiarias("$"+new DecimalFormat("#,###.##").format(totalVentM),list,totalPages));
            

        }    


        return totalventasDiarias;
    }


    public List<RotacionProductosFinal> getRotacionproductos(long id, int page, String year) {

        String queryTotal = "select count(id) from productos where fk_id_laboratorio=" + id;

        int totalItems = jdbcTemplate.queryForObject(queryTotal, Integer.class);
        String query = "";
        
        query ="select id, nombre_producto from productos where fk_id_laboratorio=" + id+" LIMIT 10 OFFSET "+page*10;
            
        
        //String query = "select id, nombre_producto from productos where fk_id_laboratorio=" + id;
        String query2 = "";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        int totalPages = totalItems/10;
       
        
        //String year = String.valueOf(Year.now().getValue());
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        String startdate = "";
        String enddate = "";
        int cantidadActual=0;
        List<RotacionProductosFinal> rotacionFinal = new ArrayList<>();

        for (Map row : rows) {

            
            HashMap<String, String> map = new HashMap<String, String>();
            List<HashMap<String, String>> list = new ArrayList<>();
            query = "select inventario_cantidad from productos where id="+row.get("id");
            cantidadActual = jdbcTemplate.queryForObject(query, Integer.class);
            for (int i = 1; i <= 12; i++) {

                if (i < 10) {
                    startdate = year + "-0" + i + "-01";
                    enddate = year + "-0" + i + "-31";

                } else {
                    startdate = year + "-" + i + "-01";
                    enddate = year + "-" + i + "-31";
                }
                query2 = "SELECT sum(rd.cantidad_producto) as cantidadTotal, p.nombre_producto as nombreProducto from registro_diario rd INNER JOIN productos p ON rd.fk_id_producto = p.id WHERE rd.fk_id_producto="
                        + row.get("id").toString() + " and fecha between '" + startdate + "' and '" + enddate + "' ";

                List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(query2);
                for (Map row2 : rows2) {

                    if (row2.get("cantidadTotal") == null) {

                        map.put("cantidad", String.valueOf(0));
                    } else {

                        map.put("cantidad", String.valueOf(row2.get("cantidadTotal")));
                        
                    }
                    map.put("mes", meses[i-1]);
                    list.add(new HashMap(map));
                    
                }

                // Rproductos rt =
                // findRotactionByproduct(Long.parseLong(row.get("id").toString()), startdate,
                // enddate, i);

            }
            rotacionFinal.add(
                    new RotacionProductosFinal(row.get("nombre_producto").toString(), row.get("id").toString(), list,totalItems, totalPages,page,cantidadActual));
        }

        return rotacionFinal;

        // return null;
    }

    public List<Laboratorios> getLabs(){

        System.out.println("data..");
        String query = "select id, nombre from laboratorios";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<Laboratorios> list = new ArrayList<>();
        for (Map row : rows) {

            list.add(new Laboratorios(Integer.parseInt(row.get("id").toString()),row.get("nombre").toString()));
        }
        
        return list;
    }

}

class RotacionProductosRowMapper implements RowMapper<Rproductos> {

    int mes;

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public RotacionProductosRowMapper(int mes) {

        this.mes = mes;
    }

    @Override
    public Rproductos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rproductos rt = new Rproductos();
        rt.setCantidad(rs.getInt("cantidadTotal"));
        rt.setMes(this.mes);
        rt.setNombreProducto(rs.getString("nombreProducto"));

        return rt;
    }

}

class Rproductos {

    public String nombreProducto;
    public int cantidad;
    public int mes;

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

}
