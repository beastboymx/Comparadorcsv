import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import panamahitek.Arduino.PanamaHitek_Arduino;

public class ComparadorArchivoCSV extends javax.swing.JFrame {
    private File archivo1, archivo2;
    private boolean comparacionRealizada = false;
    private String ultimaRutaArchivo1, ultimaRutaArchivo2; // Almacena las últimas rutas seleccionadas
    private String ultimaRutaCarpeta; // Almacena la última carpeta seleccionada para guardar archivos

    
     PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();
     Timer timer;
    public ComparadorArchivoCSV() {
        initComponents();
          try {
            Arduino.arduinoTX("COM3", 9600); // Reemplaza "COM3" con el puerto correcto
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bloquearCampos(false); // Cambia a false para que los campos estén desbloqueados al iniciar
        setIconImage(getIconImage());
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar valores a la ventana
        setLocationRelativeTo(null);
        setVisible(true);
        // Bloqueo de maximizar ventana
        this.setResizable(false);
        Selectiva();
        
        jcSelectiva.setSelectedItem(null);
        jScrollPane1.setViewportView(txtmensaje);
        txtmensaje.setFont(new Font("Arial", Font.PLAIN, 15));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Aquí puedes enviar la señal para cerrar el servo
                enviarComando('c'); // Cambia 'c' por el comando correcto para cerrar el servo
                try {
                    // Agrega una pausa para dar tiempo al servo para cerrarse antes de salir
                    Thread.sleep(1000); // Puedes ajustar el tiempo de espera según sea necesario
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // Cierra la ventana y la aplicación
                dispose();
            }
        });
        // Configura un temporizador para realizar la comparación cada 30 segundos
        int intervalo = 30 * 1000; // Intervalo en milisegundos (30 segundos)
        timer = new Timer(intervalo, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarComparacionAutomatica();
            }
        });
        timer.start();
    }
    
private void realizarComparacionAutomatica() {
    if (archivo1 != null && archivo2 != null && comparacionRealizada) {
        String mensaje = compararArchivosCSV(archivo1, archivo2);
        txtmensaje.setText(mensaje);
        if (mensaje.startsWith("Parametria Correcta")) {
            txtmensaje.setForeground(Color.GREEN);
            enviarComando('a');
        } else {
            txtmensaje.setForeground(Color.RED);
            enviarComando('c');
        }
    }
}
    
    private void guardarRutasSeleccionadas() {
        if (archivo1 != null) {
            ultimaRutaArchivo1 = archivo1.getAbsolutePath();
        }
        if (archivo2 != null) {
            ultimaRutaArchivo2 = archivo2.getAbsolutePath();
        }
    }
    
   @Override
public Image getIconImage() {
    // Carga la imagen del ícono desde un recurso del sistema.
    Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Img/com.png"));

    return retValue;
}


 //Carga opciones selectivas desde un archivo y las agrega a un JComboBox.
public void Selectiva() {
    // Ruta del archivo de opciones selectivas
    String filepath = "C:\\Users\\Oscar\\Documents\\selectivas.txt";
    File file = new File(filepath);

    try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Object[] lines = br.lines().toArray();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].toString();
            jcSelectiva.addItem(line); // Agrega la opción selectiva al JComboBox.
        }
    } catch (FileNotFoundException ex) {
        // Manejo de excepción en caso de que el archivo no se encuentre.
        ex.printStackTrace();
    }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnArchivo1 = new javax.swing.JButton();
        btnArchivo2 = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmensaje = new javax.swing.JTextArea();
        btnCambio = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jtNombre = new javax.swing.JTextField();
        jtNum = new javax.swing.JTextField();
        jtModelo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtWo = new javax.swing.JTextField();
        jcSelectiva = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtComentario = new javax.swing.JTextField();
        btnBorrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comparador");
        setAlwaysOnTop(true);

        btnArchivo1.setText("Archivo 1");
        btnArchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivo1ActionPerformed(evt);
            }
        });

        btnArchivo2.setText("Archivo 2");
        btnArchivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivo2ActionPerformed(evt);
            }
        });

        btnComparar.setText("Comparar");
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompararActionPerformed(evt);
            }
        });

        txtmensaje.setColumns(20);
        txtmensaje.setRows(5);
        jScrollPane1.setViewportView(txtmensaje);

        btnCambio.setText("Cambio de modelo");
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/p.png"))); // NOI18N

        jLabel3.setText("Archivo de Selectiva");

        jLabel4.setText("Archivo validado");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/inf.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel5.setText("Num. Empleado");

        jLabel6.setText("Modelo");

        jLabel7.setText("WO");

        jcSelectiva.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel8.setText("Selectiva");

        jLabel9.setText("Comentario");

        jtComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtComentarioActionPerformed(evt);
            }
        });

        btnBorrar1.setText("Abrir");
        btnBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel4)
                                .addGap(87, 87, 87)))
                        .addGap(114, 114, 114)
                        .addComponent(btnComparar)
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(81, 81, 81))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1))
                            .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtNum))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jtWo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel7)))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcSelectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(538, 538, 538)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(btnBorrar1)
                .addGap(49, 49, 49)
                .addComponent(btnCambio)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtWo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcSelectiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArchivo1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArchivo2))
                    .addComponent(btnComparar))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBorrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarDatosEnArchivo(String nombre, String numEmpleado, String modelo, String wo, String selectiva, String comentario, String Fe, String Ho) {
    try {
        FileWriter fw = new FileWriter("datos.txt", true); // Nombre del archivo donde se guardarán los datos, "true" indica que se agregará al final del archivo si existe.
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        // Escribir los datos en una sola línea separados por comas
        pw.println(nombre + "," + numEmpleado + "," + modelo + "," + wo + "," + selectiva + "," + comentario + "," + Fe + "," + Ho);

        // Cerrar el archivo
        pw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    
    
    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
          Calendar Cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-6"));

    // Verificar que los campos estén completos antes de comparar
    if (archivo1 != null && archivo2 != null && !jtNombre.getText().isEmpty()
            && !jtNum.getText().isEmpty() && !jtModelo.getText().isEmpty()
            && !jtWo.getText().isEmpty() && jcSelectiva.getSelectedItem() != null) {
        String mensaje = compararArchivosCSV(archivo1, archivo2);
        txtmensaje.setText(mensaje);
        if (mensaje.startsWith("Parametria Correcta")) {
            txtmensaje.setForeground(Color.GREEN);
            enviarComando('a');

            // Guardar los datos en el archivo
            String nombre = jtNombre.getText();
            String numEmpleado = jtNum.getText();
            String modelo = jtModelo.getText();
            String wo = jtWo.getText();
            String selectiva = jcSelectiva.getSelectedItem().toString();
            String comentario = jtComentario.getText();
            String Fe = Cal.get(Calendar.DATE) + "/" + (Cal.get(Calendar.MONTH) + 1) + "/" + Cal.get(Calendar.YEAR);
            String Ho = Cal.get(Calendar.HOUR_OF_DAY) + ":" + Cal.get(Calendar.MINUTE) + " ";

            guardarDatosEnArchivo(nombre, numEmpleado, modelo, wo, selectiva, comentario, Fe, Ho);
            jtNombre.setText("");
            jtNum.setText("");
            jtModelo.setText("");
            jtWo.setText("");
            jcSelectiva.setSelectedItem(null);
            jtComentario.setText("");
            
            // Después de comparar y guardar, bloquear los campos
            bloquearCampos(true); // Cambia a true para bloquear los campos
            
            // Marcar que la comparación se ha realizado con éxito
            comparacionRealizada = true;
            
            // Si es la primera comparación exitosa, inicie el temporizador
            if (!timer.isRunning()) {
                timer.start();
            }
            
            JOptionPane.showMessageDialog(this, "Para desbloquear los campos, presiona el botón: Cambio de Modelo");
        } else {
            txtmensaje.setForeground(Color.RED);
            enviarComando('c');
        }
    } else {
        JOptionPane.showMessageDialog(this, "Complete todos los campos antes de comparar.");
    }
    }//GEN-LAST:event_btnCompararActionPerformed

    private void bloquearCampos(boolean bloquear) {
        // Método para habilitar o deshabilitar los componentes
        btnArchivo1.setEnabled(!bloquear);
        btnArchivo2.setEnabled(!bloquear);
        btnComparar.setEnabled(!bloquear);
        jtNombre.setEnabled(!bloquear);
        jtNum.setEnabled(!bloquear);
        jtModelo.setEnabled(!bloquear);
        jtWo.setEnabled(!bloquear);
        jcSelectiva.setEnabled(!bloquear);
        jtComentario.setEnabled(!bloquear);
    }

    
    private void btnArchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivo1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (ultimaRutaArchivo1 != null) {
            fileChooser.setCurrentDirectory(new File(ultimaRutaArchivo1));
        }
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivo1 = fileChooser.getSelectedFile();
            btnArchivo1.setText("Archivo 1: " + archivo1.getName()); // Muestra el nombre del archivo cargado.
            guardarRutasSeleccionadas();
        }
    }//GEN-LAST:event_btnArchivo1ActionPerformed

    private void btnArchivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivo2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (ultimaRutaArchivo2 != null) {
            fileChooser.setCurrentDirectory(new File(ultimaRutaArchivo2));
        }
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivo2 = fileChooser.getSelectedFile();
            btnArchivo2.setText("Archivo 2: " + archivo2.getName()); // Muestra el nombre del archivo cargado.
            guardarRutasSeleccionadas();
        }
    }//GEN-LAST:event_btnArchivo2ActionPerformed

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
enviarComando('c');
    archivo1 = null;
    archivo2 = null;
    btnArchivo1.setText("Archivo 1");
    btnArchivo2.setText("Archivo 2");
    txtmensaje.setText("");
    jtNombre.setText("");
    jtNum.setText("");
    jtModelo.setText("");
    jtWo.setText("");
    jcSelectiva.setSelectedItem(null);
    jtComentario.setText("");
    
    // Al hacer clic en "Cambio de modelo", desbloquear los campos y botones
    bloquearCampos(false); // Cambia a false para desbloquear los campos
    }//GEN-LAST:event_btnCambioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "     Program Created by " + "\n" + "          Oscar Lopez" + "\n" + "\n" +"               Contact" + "\n" + "Oscar.Lopez@plexus.com");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtComentarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtComentarioActionPerformed

    private void btnBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrar1ActionPerformed
        enviarComando('a');
    }//GEN-LAST:event_btnBorrar1ActionPerformed

    private void enviarComando(char comando) {
        try {
            Arduino.sendData(String.valueOf(comando));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String compararArchivosCSV(File archivo1, File archivo2) {
    try {
        BufferedReader reader1 = new BufferedReader(new FileReader(archivo1));
        BufferedReader reader2 = new BufferedReader(new FileReader(archivo2));

        String linea1, linea2;
        int numeroLinea = 1;

        StringBuilder mensajeDiferencias = new StringBuilder();

        // Variable para rastrear en qué línea estamos
        int lineaActual = 1;

        // Leer y descartar las primeras tres líneas
        while (lineaActual <= 3) {
            reader1.readLine();
            reader2.readLine();
            lineaActual++;
        }

        while ((linea1 = reader1.readLine()) != null && (linea2 = reader2.readLine()) != null) {
            if (!linea1.equals(linea2)) {
                mensajeDiferencias.append("Parametria Incorrecta en la línea ")
                        .append(numeroLinea).append(": ")
                        .append("Archivo 1: ").append(linea1)
                        .append(", Archivo 2: ").append(linea2).append("\n");
            }

            numeroLinea++;
        }

        if (mensajeDiferencias.length() > 0) {
            return mensajeDiferencias.toString();
        } else {
            return "Parametria Correcta: los archivos son idénticos.";
        }
    } catch (IOException e) {
        e.printStackTrace();
        return "Error al comparar los archivos.";
    }
}

    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComparadorArchivoCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComparadorArchivoCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComparadorArchivoCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComparadorArchivoCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComparadorArchivoCSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArchivo1;
    private javax.swing.JButton btnArchivo2;
    private javax.swing.JButton btnBorrar1;
    private javax.swing.JButton btnCambio;
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcSelectiva;
    private javax.swing.JTextField jtComentario;
    private javax.swing.JTextField jtModelo;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JTextField jtNum;
    private javax.swing.JTextField jtWo;
    private javax.swing.JTextArea txtmensaje;
    // End of variables declaration//GEN-END:variables
}
