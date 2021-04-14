package com.sis.Controlador;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@Named(value = "cargarArchivosCtrl")
@SessionScoped
public class CargarArchivosControlador implements Serializable {

    public CargarArchivosControlador() {
    }
    private Part file;
    private Part file2;
    private Part file3;
    private String nombre;
    private String pathReal;
    private String pathReal2;
    private String pathReal3;

    public Part getFile3() {
        return file3;
    }

    public void setFile3(Part file3) {
        this.file3 = file3;
    }

    public String getPathReal3() {
        return pathReal3;
    }

    public void setPathReal3(String pathReal3) {
        this.pathReal3 = pathReal3;
    }
    
    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public String getPathReal2() {
        return pathReal2;
    }

    public void setPathReal2(String pathReal2) {
        this.pathReal2 = pathReal2;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public Part getFile() {
        return file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void subirFoto() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\resources\\perfiles\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo = new File(path);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();
            path = path.replace("\\", "\\\\");
            //aspirantes.setFoto(pathReal);
            //aspirantesFacade.cargarArchivos(pathReal, "Aspirantes");
            //archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return "cargaFile";
    }

    public void upload2() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file2.getSubmittedFileName();
            pathReal2 = "/Sis2/Archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file2.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo = new File(path);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();
            path = path.replace("\\", "\\\\");
            //aspirantes.setHojaDeVida(pathReal2);
            //aspirantesFacade.cargarArchivos2(pathReal2, "Aspirantes");
            //archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return "cargaFile";
    }
    public void subirContrato() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file3.getSubmittedFileName();
            pathReal3 = "/Sis2/Archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file3.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo = new File(path);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();
            path = path.replace("\\", "\\\\");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return "cargaFile";
    }
    
}
