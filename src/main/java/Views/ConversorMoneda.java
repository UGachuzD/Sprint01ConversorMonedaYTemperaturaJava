package Views;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import static Views.Menu.cantidad;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author Ulises Gachuz Davila
 */
public class ConversorMoneda extends javax.swing.JFrame {

    public static String from = "";
    public static String to = "";
    private static final String API_KEY = "007fdda99fe19fe1158aae34";
    public static BigDecimal resultado = BigDecimal.ZERO;
    private static String tituloTipoConversion = "";
    Menu menu = new Menu();
    /**
     * Creates new form ConversorMoneda
     */
    public ConversorMoneda() {
        initComponents();
        setTitle("Monedas");
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCBxOpcionesMoneda = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(181, 203, 183));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 140));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Elija la moneda a la que deseas convertir tu dinero:");

        jCBxOpcionesMoneda.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCBxOpcionesMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "De Pesos a Dólar", "De Pesos a Euro", "De Pesos a Libras", "De Pesos a Yen", "De Pesos a Won Sur-Coreano", "De Dólar a Pesos", "De Euro a Pesos", "De Libras a Pesos", "De Yen a Pesos", "De Won Sur-Coreano a Pesos" }));
        jCBxOpcionesMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBxOpcionesMonedaActionPerformed(evt);
            }
        });

        jButton1.setText("Menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBxOpcionesMoneda, 0, 324, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jCBxOpcionesMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    private void jCBxOpcionesMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBxOpcionesMonedaActionPerformed
        // TODO add your handling code here:
        int opcion = jCBxOpcionesMoneda.getSelectedIndex();
        switch (opcion) {
            case 0 -> {
                // Pesos a dolar
                try {
                    from = "MXN";
                    to = "USD";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 1 -> {
                // Peso a euro
                try {
                    from = "MXN";
                    to = "EUR";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 2 -> {
                // Peso a libra
                try {
                    from = "MXN";
                    to = "GBP";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 3 -> {
                // Peso a yen
                try {
                    from = "MXN";
                    to = "JPY";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 4 -> {
                // Peso a won sur-coreano 
                try {
                    from = "MXN";
                    to = "KRW";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 5 -> {
                // Dolar a peso 
                try {
                    from = "USD";
                    to = "MXN";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 6 -> {
                // Euro a peso
                try {
                    from = "EUR";
                    to = "MXN";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

            case 7 -> {
                // Libra a peso
                try {
                    from = "GBP";
                    to = "MXN";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }
            
            case 8 -> {
                // Yen a peso
                try {
                    from = "JPY";
                    to = "MXN";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }
            
            case 9 -> {
                // Won sur-coreano a peso
                try {
                    from = "KRW";
                    to = "MXN";
                    URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to + "/" + cantidad);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode = con.getResponseCode();
                    if (responsecode != 200) {
                        System.out.println("Error" + responsecode);
                    } else {
                        StringBuilder infoString = new StringBuilder();
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                            infoString.append(sc.nextLine());
                        }
                        sc.close();
                        JSONObject dataObject = new JSONObject(String.valueOf(infoString));
                        resultado = dataObject.getBigDecimal("conversion_result");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tituloTipoConversion = jCBxOpcionesMoneda.getItemAt(opcion);
                JOptionPane.showMessageDialog(null, "$" + cantidad + " " + from + " son $" + resultado + " " + to + "."
                                            ,"Resultado: "+ tituloTipoConversion+".", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jCBxOpcionesMonedaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        menu.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ConversorMoneda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConversorMoneda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConversorMoneda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConversorMoneda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConversorMoneda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBxOpcionesMoneda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
