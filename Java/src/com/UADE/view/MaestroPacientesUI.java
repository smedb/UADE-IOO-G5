package com.UADE.view;

import com.UADE.controller.PacienteController;
import com.UADE.controller.UsuarioController;
import com.UADE.dao.PacienteDAO;
import com.UADE.dto.UsuarioDTO;
import com.UADE.model.Paciente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MaestroPacientesUI {

    private JList<String> listPacientes;
    private JPanel panel1;
    private JButton listaPacientesButton;
    private JButton nuevoPacienteButton;
    private JButton modificarPacienteButton;
    private JButton borrarPacienteButton;
    private PacienteController pacic;

    public MaestroPacientesUI() throws Exception {
        JFrame frame = new JFrame("Maestro de Usuarios");
        panel1.setBorder(new EmptyBorder(15, 15, 15, 15));
        frame.setContentPane(panel1);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listPacientes.setModel(listModel);

        pacic = new PacienteController();

        List<Paciente> lista = pacic.obtenerListaPacientes();

        for (Paciente i : lista)
            listModel.addElement("a");

        listaPacientesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                try {
                    new ListaPacientesUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        /*nuevoPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                try {
                    new NuevoPacienteUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        /*borrarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pacic.borrarUsuario(listUsuarios.getSelectedValue());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                frame.dispose();

                try {
                    new MaestroUsuariosUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
    }

}
