/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

/**
 *
 * @author Wasee-Ur-Rehman CH
 */
import javax.swing.ButtonGroup;

public class Glance extends javax.swing.JFrame {

    String cityName = "";
    String stateCode = "";
    String countryCode = "";
    String longitude = "";
    String latitude = "";
    
    /**
     * Creates new form Glance
     */
    public Glance() {
        initComponents();
        myinitcomponents();
    }
    private void myinitcomponents()
    {
     //JFrame.getContentPane().add(new JPanelWithBackground("sample.jpeg"));
 
     ButtonGroup buttonGroup = new ButtonGroup();

    // Add radio buttons to the ButtonGroup
    buttonGroup.add(ByCity);
    buttonGroup.add(ByCoordinates);
    
     LatitudeInput.setVisible(false);
    LongitudeInput.setVisible(false);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        Option = new javax.swing.JLabel();
        ByCity = new javax.swing.JRadioButton();
        ByCoordinates = new javax.swing.JRadioButton();
        CityLabel = new javax.swing.JLabel();
        StateCodeLabel = new javax.swing.JLabel();
        CountryCodeLabel = new javax.swing.JLabel();
        CityInput = new javax.swing.JTextField();
        CountryCodeInput = new javax.swing.JTextField();
        StateCodeInput = new javax.swing.JTextField();
        Searchbtn = new javax.swing.JButton();
        LongitudeInput = new javax.swing.JTextField();
        LatitudeInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Skycast Weather App");
        setPreferredSize(new java.awt.Dimension(700, 400));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("SkyCast");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 39, -1, -1));

        Option.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Option.setForeground(new java.awt.Color(255, 255, 255));
        Option.setText("Select Option");
        getContentPane().add(Option, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 86, -1, -1));

        ByCity.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        ByCity.setForeground(new java.awt.Color(255, 255, 255));
        ByCity.setText("Search by City Name, State Code and Country Code");
        ByCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ByCityActionPerformed(evt);
            }
        });
        getContentPane().add(ByCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 114, -1, -1));

        ByCoordinates.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        ByCoordinates.setForeground(new java.awt.Color(255, 255, 255));
        ByCoordinates.setText("Search by Lattitude and Longitude");
        ByCoordinates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ByCoordinatesActionPerformed(evt);
            }
        });
        getContentPane().add(ByCoordinates, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 141, -1, -1));

        CityLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        CityLabel.setForeground(new java.awt.Color(255, 255, 255));
        CityLabel.setText("Enter City Name:");
        CityLabel.setFocusCycleRoot(true);
        getContentPane().add(CityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 185, -1, -1));

        StateCodeLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        StateCodeLabel.setForeground(new java.awt.Color(255, 255, 255));
        StateCodeLabel.setText("Enter State Code:");
        StateCodeLabel.setFocusCycleRoot(true);
        getContentPane().add(StateCodeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 223, -1, -1));

        CountryCodeLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        CountryCodeLabel.setForeground(new java.awt.Color(255, 255, 255));
        CountryCodeLabel.setText("Enter Country Code:");
        CountryCodeLabel.setFocusCycleRoot(true);
        getContentPane().add(CountryCodeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 267, -1, -1));

        CityInput.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        CityInput.setForeground(new java.awt.Color(0, 0, 0));
        CityInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CityInput.setFocusCycleRoot(true);
        CityInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CityInputMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CityInputMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CityInputMouseReleased(evt);
            }
        });
        CityInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CityInputActionPerformed(evt);
            }
        });
        getContentPane().add(CityInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 310, -1));

        CountryCodeInput.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        CountryCodeInput.setForeground(new java.awt.Color(0, 0, 0));
        CountryCodeInput.setFocusCycleRoot(true);
        CountryCodeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountryCodeInputActionPerformed(evt);
            }
        });
        getContentPane().add(CountryCodeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 310, -1));

        StateCodeInput.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        StateCodeInput.setForeground(new java.awt.Color(0, 0, 0));
        StateCodeInput.setFocusCycleRoot(true);
        StateCodeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StateCodeInputActionPerformed(evt);
            }
        });
        getContentPane().add(StateCodeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 310, -1));

        Searchbtn.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Searchbtn.setText("Search");
        Searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchbtnActionPerformed(evt);
            }
        });
        getContentPane().add(Searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 100, 40));

        LongitudeInput.setForeground(new java.awt.Color(0, 0, 0));
        LongitudeInput.setFocusCycleRoot(true);
        LongitudeInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LongitudeInputMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LongitudeInputMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LongitudeInputMouseReleased(evt);
            }
        });
        LongitudeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LongitudeInputActionPerformed(evt);
            }
        });
        getContentPane().add(LongitudeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 310, -1));

        LatitudeInput.setForeground(new java.awt.Color(0, 0, 0));
        LatitudeInput.setFocusCycleRoot(true);
        LatitudeInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LatitudeInputMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LatitudeInputMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LatitudeInputMouseReleased(evt);
            }
        });
        LatitudeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LatitudeInputActionPerformed(evt);
            }
        });
        getContentPane().add(LatitudeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 310, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\it\\Documents\\NetBeansProjects\\WeatherApplication\\src\\main\\java\\as.jpg")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void ByCityActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    CityLabel.setText("Enter City Name:");
    CityLabel.setVisible(true);
    CityInput.setVisible(true);
    StateCodeLabel.setText("Enter State Code:");
    StateCodeLabel.setVisible(true);
    StateCodeInput.setVisible(true);
    CountryCodeLabel.setText("Enter Country Code:");
    CountryCodeLabel.setVisible(true);
    CountryCodeInput.setVisible(true);
    // Hide latitude and longitude inputs
    LatitudeInput.setVisible(false);
    LongitudeInput.setVisible(false);
    }                                      

    private void ByCoordinatesActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
     CityLabel.setText("Enter Latitude:");
    CityLabel.setVisible(true);
    LatitudeInput.setVisible(true);
    StateCodeLabel.setText("Enter Longitude:");
    StateCodeLabel.setVisible(true);
    LongitudeInput.setVisible(true);
    // Hide city, state code, and country code inputs
    CityInput.setVisible(false);
    StateCodeInput.setVisible(false);
    CountryCodeInput.setVisible(false);
    CountryCodeLabel.setVisible(false);
    }                                             

    private void CityInputActionPerformed(java.awt.event.ActionEvent evt) {                                          
        cityName= CityInput.getText();
        System.out.println(cityName);
    }                                         

    private void StateCodeInputActionPerformed(java.awt.event.ActionEvent evt) {                                               
     stateCode=StateCodeInput.getText();
    }                                              

    private void SearchbtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        
        WeatherInfoWindow weatherInfoWindow = new WeatherInfoWindow(cityName, stateCode, countryCode, latitude, longitude);
        weatherInfoWindow.setVisible(true);
    }                                         

    private void CountryCodeInputActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        countryCode=CountryCodeInput.getText();
    }                                                

    private void CityInputMouseExited(java.awt.event.MouseEvent evt) {                                      

    }                                     

    private void CityInputMouseReleased(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
       
    }                                       

    private void CityInputMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
 
    }                                      

    private void LongitudeInputMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void LongitudeInputMouseExited(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void LongitudeInputMouseReleased(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void LongitudeInputActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        longitude= LongitudeInput.getText();
    }                                              

    private void LatitudeInputMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void LatitudeInputMouseExited(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void LatitudeInputMouseReleased(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void LatitudeInputActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        latitude=LatitudeInput.getText();
    }                                             

     public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // Getter and setter methods for stateCode
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    // Getter and setter methods for countryCode
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    // Getter and setter methods for latitude
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    // Getter and setter methods for longitude
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
            java.util.logging.Logger.getLogger(Glance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Glance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Glance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Glance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Glance().setVisible(true);
            }
        });
    }
 
    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton ByCity;
    private javax.swing.JRadioButton ByCoordinates;
    private javax.swing.JTextField CityInput;
    private javax.swing.JLabel CityLabel;
    private javax.swing.JTextField CountryCodeInput;
    private javax.swing.JLabel CountryCodeLabel;
    private javax.swing.JTextField LatitudeInput;
    private javax.swing.JTextField LongitudeInput;
    private javax.swing.JLabel Option;
    private javax.swing.JButton Searchbtn;
    private javax.swing.JTextField StateCodeInput;
    private javax.swing.JLabel StateCodeLabel;
    private javax.swing.JLabel Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration                   
}
