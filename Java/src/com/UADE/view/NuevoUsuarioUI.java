package com.UADE.view;

import com.UADE.base.Singleton;
import com.UADE.controller.SucursalController;
import com.UADE.controller.UsuarioController;
import com.UADE.dto.SucursalDTO;
import com.UADE.dto.UsuarioDTO;
import com.UADE.enums.RolSistema;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NuevoUsuarioUI {
    private JButton guardarButton;
    private JPanel panel1;
    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtClave;
    private JTextField txtDNI;
    private JTextField txtEmail;
    private JTextField txtDomicilio;
    private JComboBox<RolSistema> comboRol;
    private JTextField txtNacimiento;
    private JComboBox<Integer> comboSucursal;
    private JCheckBox responsableTecnicoCheckBox;

    private final UsuarioController usuc;
    private final SucursalController succ;

    public NuevoUsuarioUI() throws Exception {
        JFrame frame = new JFrame("Nuevo usuario");
        panel1.setBorder(new EmptyBorder(15, 15, 15, 15));
        frame.setContentPane(panel1);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        for (RolSistema rol : RolSistema.values()) {
            comboRol.addItem(rol);
        }

        usuc = Singleton.getInstance().usuarioController;
        succ = Singleton.getInstance().sucursalController;

        List<SucursalDTO> listasuc = succ.obtenerListaSucursales();

        for (SucursalDTO suc : listasuc) {
            comboSucursal.addItem(suc.getCodigo());
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date datenac = null;
                try {
                    datenac = new SimpleDateFormat("dd/MM/yyyy").parse(txtNacimiento.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "El formato de fecha de nacimiento no es correcto. Utilice dd/mm/aaaa", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Integer result = null;
                UsuarioDTO newuser = new UsuarioDTO(null, txtUsuario.getText(), txtClave.getText(), txtEmail.getText(), txtNombre.getText(), txtDomicilio.getText(), Integer.valueOf(txtDNI.getText()), datenac, (RolSistema) comboRol.getSelectedItem());

                try {
                    result = usuc.nuevoUsuario(newuser);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (result == null) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe.", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Se ha creado el usuario " + txtUsuario.getText(), "Nuevo usuario creado", JOptionPane.INFORMATION_MESSAGE);

                    try {
                        succ.agregarUsuarioASucursal((Integer) comboSucursal.getSelectedItem(), result, responsableTecnicoCheckBox.isSelected());
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
            }
        });
    }
}
