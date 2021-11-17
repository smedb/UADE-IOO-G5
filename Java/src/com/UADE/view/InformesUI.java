package com.UADE.view;

import com.UADE.controller.PeticionController;
import com.UADE.controller.SucursalController;
import com.UADE.dao.PeticionDAO;
import com.UADE.dto.DatosPeticionDTO;
import com.UADE.dto.DatosSucursalDTO;
import com.UADE.model.Peticion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class InformesUI {
    private JPanel panel1;
    private JList<String> listaPeticiones;
    private PeticionController peticionController;


    public InformesUI(){
        JFrame frame = new JFrame("Informes");
        panel1.setBorder(new EmptyBorder(15, 15, 15, 15));
        frame.setContentPane(panel1);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listaPeticiones.setModel(listModel);


        //peticionController = new PeticionController();

        //List<DatosPeticionDTO> lista = peticionController.obtenerDatosPeticion(1);


        /*for (DatosPeticionDTO i : lista)
            listModel.addElement();*/


    }
}
