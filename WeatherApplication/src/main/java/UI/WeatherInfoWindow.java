/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;
import Business.WeatherService;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
/**
 *
 * @author Wasee-Ur-Rehman CH
 */

public class WeatherInfoWindow extends javax.swing.JFrame 
{
   // private DesktopUI desktopUI;
    WeatherService serviceObj= new WeatherService();
    DesktopUI deskUi= new DesktopUI(serviceObj);

    private  String cityName;
    private  String stateCode;
    private  String countryCode;
    private  String latitude;
    private  String longitude;

    /**
     * Creates new form WeatherInfoWindow
     */
     public WeatherInfoWindow(String cityName, String stateCode, String countryCode, String latitude, String longitude) 
    {
        initComponents();
       
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
        System.out.println("City Name: " + cityName);
System.out.println("State Code: " + stateCode);
System.out.println("Country Code: " + countryCode);
System.out.println("Latitude: " + latitude);
System.out.println("Longitude: " + longitude);

         myinitcomponents();
         CurrentWeather();
         BasicWeather();
         FeelLike();
         SunTiming();
         displayPollution();
         AirQuality();
          getTimeStamp();
        Timer timer = new Timer(5000, e -> AirQualityNotification());
        timer.setRepeats(false); // Only fire once
        timer.start();
       Timer timer2 = new Timer(10000, e -> BadWeatherNotification());
        timer2.setRepeats(false); // Only fire once
        timer2.start();
        
    }
        
     public void myinitcomponents()
     {
         //jLabel1.setText(latitude);
         //jLabel1.setVisible(true);
        
         
     }
     
     public void CurrentWeather()
     {
         String CurrentData;
     if (cityName.equals("")) {
    CurrentData= deskUi.displayWeatherByCoordinates(Double.valueOf(latitude), Double.valueOf(longitude));
}   else {
    CurrentData= deskUi.displayWeatherByCity(cityName, stateCode, countryCode);
}
    CurrentInfoTB.setText(CurrentData);
         
     }
     
     public void BasicWeather()
     {
         String BasicData;
         BasicData=deskUi.displayBasicInformation();
         BasicInfoTB.setText(BasicData);
     }
     
     public void FeelLike()
     {
         String FeelLikeData;
         FeelLikeData=deskUi.displayFeelsLike();
         FeelsLikeTB.setText(FeelLikeData);
     }
     
     public void SunTiming()
     {
         String SunTiming;
         SunTiming= deskUi.displaySunriseSunsetTime();
         SunTimingTB.setText(SunTiming);
     }
     
     public void displayPollution() 
     {
    String pollutionData;
    pollutionData = deskUi.displayPollutionDataTwo();
    PollutingDataTb.setText(pollutionData);
    }
     
    public void AirQuality()
    {
        String message;
        message=deskUi.displayAirQualityInformation();
        AirQualityTB.setText(message);
    }
    
    public void AirQualityNotification()
    {
        String notify;
        notify=deskUi.getAQiMessage();
        if ("1".equals(notify))
        {
            AirNotificationPopUp.showMessageDialog(null, "Air Quality In your Area Is Critical.", "Air Quality Notification", AirNotificationPopUp.WARNING_MESSAGE);
        }        
    }
    public void BadWeatherNotification()
    {
        String notify;
        notify=deskUi.displayBadWeather();
        if ("1".equals(notify))
        {
            BadWeatherPopUp.showMessageDialog(null, "Weather Is Critical.", "Bad Weather Notification", BadWeatherPopUp.WARNING_MESSAGE);
        }        
    }
    
  public void GetfivedayData(JTextArea forecastTextArea) 
  {
        String Data;
        Data = deskUi.getForecastData();
        forecastTextArea.setText(Data);
    }
    
    private void openForecastPanel() 
    {
         JFrame forecastFrame = new JFrame("Five Day Forecast");
        forecastFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forecastFrame.setSize(850, 650);

        JPanel forecastPanel = new JPanel();
        forecastPanel.setLayout(new BoxLayout(forecastPanel, BoxLayout.Y_AXIS)); // Setting layout to vertical

        JLabel forecastLabel = new JLabel("Weather Forecast for 5 Days:");
        JTextArea forecastTextArea = new JTextArea(10, 50);
        forecastTextArea.setEditable(false);
        JScrollPane forecastScrollPane = new JScrollPane(forecastTextArea);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> forecastFrame.dispose());

        // Add components to the panel
        forecastPanel.add(forecastLabel);
        forecastPanel.add(forecastScrollPane);
        forecastPanel.add(Box.createVerticalStrut(10)); // Add some vertical spacing between text area and button
        forecastPanel.add(closeButton);

        forecastFrame.getContentPane().add(forecastPanel);
        forecastFrame.setVisible(true);

        // Populate forecastTextArea with data
        GetfivedayData(forecastTextArea);
    }

    void getTimeStamp()
    {
        Timestamplabel.setText(deskUi.fetchAndDisplayTimestamp());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        MainCard = new javax.swing.JPanel();
        FeelLikeLabel = new javax.swing.JLabel();
        CurrentnfoLabel = new javax.swing.JLabel();
        BasicInfoLabel = new javax.swing.JLabel();
        SunTimingLabel = new javax.swing.JLabel();
        CurrentInfoTB = new javax.swing.JTextField();
        FeelsLikeTB = new javax.swing.JTextField();
        SunTimingTB = new javax.swing.JTextField();
        BasicInfoTB = new javax.swing.JTextField();
        PollutingGasesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PollutingDataTb = new javax.swing.JTextArea();
        AirQualityLabel = new javax.swing.JLabel();
        AirQualityTB = new javax.swing.JTextField();
        focaastPanelBtn = new javax.swing.JButton();
        Timestamplabel = new javax.swing.JLabel();
        apptitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Weather Details");
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        MainCard.setBackground(java.awt.Color.lightGray);
        MainCard.setPreferredSize(new java.awt.Dimension(950, 650));
        MainCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FeelLikeLabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        FeelLikeLabel.setForeground(new java.awt.Color(0, 0, 0));
        FeelLikeLabel.setText("Feels Like:");
        MainCard.add(FeelLikeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 253, -1, -1));

        CurrentnfoLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        CurrentnfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        CurrentnfoLabel.setText("Current Weather Information:");
        MainCard.add(CurrentnfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 129, -1, -1));

        BasicInfoLabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        BasicInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        BasicInfoLabel.setText("Basic Information:");
        MainCard.add(BasicInfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 188, -1, -1));

        SunTimingLabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        SunTimingLabel.setForeground(new java.awt.Color(0, 0, 0));
        SunTimingLabel.setText("Sun Rise and Sun Set Time:");
        MainCard.add(SunTimingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 312, -1, -1));

        CurrentInfoTB.setEditable(false);
        CurrentInfoTB.setBackground(new java.awt.Color(255, 255, 255));
        CurrentInfoTB.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        CurrentInfoTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        CurrentInfoTB.setFocusable(false);
        MainCard.add(CurrentInfoTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 490, 41));

        FeelsLikeTB.setEditable(false);
        FeelsLikeTB.setBackground(new java.awt.Color(255, 255, 255));
        FeelsLikeTB.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        FeelsLikeTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        FeelsLikeTB.setFocusable(false);
        FeelsLikeTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeelsLikeTBActionPerformed(evt);
            }
        });
        MainCard.add(FeelsLikeTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 490, 41));

        SunTimingTB.setEditable(false);
        SunTimingTB.setBackground(new java.awt.Color(255, 255, 255));
        SunTimingTB.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        SunTimingTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        SunTimingTB.setFocusable(false);
        SunTimingTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SunTimingTBActionPerformed(evt);
            }
        });
        MainCard.add(SunTimingTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 490, 41));

        BasicInfoTB.setEditable(false);
        BasicInfoTB.setBackground(new java.awt.Color(255, 255, 255));
        BasicInfoTB.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        BasicInfoTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        BasicInfoTB.setFocusable(false);
        BasicInfoTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BasicInfoTBActionPerformed(evt);
            }
        });
        MainCard.add(BasicInfoTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 490, 41));

        PollutingGasesLabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        PollutingGasesLabel.setForeground(new java.awt.Color(0, 0, 0));
        PollutingGasesLabel.setText("Air  Polluting  Gases Data:");
        MainCard.add(PollutingGasesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 371, -1, -1));

        PollutingDataTb.setBackground(new java.awt.Color(255, 255, 255));
        PollutingDataTb.setColumns(20);
        PollutingDataTb.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        PollutingDataTb.setRows(5);
        PollutingDataTb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        PollutingDataTb.setFocusable(false);
        jScrollPane1.setViewportView(PollutingDataTb);

        MainCard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 490, 131));

        AirQualityLabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        AirQualityLabel.setForeground(new java.awt.Color(0, 0, 0));
        AirQualityLabel.setText("Air Quality Status:");
        MainCard.add(AirQualityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 531, -1, -1));

        AirQualityTB.setEditable(false);
        AirQualityTB.setBackground(new java.awt.Color(255, 255, 255));
        AirQualityTB.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        AirQualityTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 255, 255), 3, true));
        AirQualityTB.setFocusable(false);
        MainCard.add(AirQualityTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 490, 41));

        focaastPanelBtn.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        focaastPanelBtn.setText("View Five Day Focast for this Location");
        focaastPanelBtn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        focaastPanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                focaastPanelBtnActionPerformed(evt);
            }
        });
        MainCard.add(focaastPanelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, 360, 40));

        Timestamplabel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        Timestamplabel.setForeground(new java.awt.Color(0, 0, 0));
        Timestamplabel.setText("Timestamp");
        MainCard.add(Timestamplabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 600, -1, -1));

        apptitle.setBackground(new java.awt.Color(51, 0, 51));
        apptitle.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        apptitle.setForeground(new java.awt.Color(51, 0, 51));
        apptitle.setText("SkyCast");
        MainCard.add(apptitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 332, 47));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\it\\Documents\\NetBeansProjects\\WeatherApplication\\src\\main\\java\\anime-style-clouds.jpg")); // NOI18N
        jLabel3.setText("jLabel3");
        MainCard.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 650));

        getContentPane().add(MainCard, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void focaastPanelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    openForecastPanel();
    }                                               

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void FeelsLikeTBActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void BasicInfoTBActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void SunTimingTBActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(WeatherInfoWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeatherInfoWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeatherInfoWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeatherInfoWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
                
        {
            public void run() {
             //new WeatherInfoWindow(cityName, stateCode, countryCode, latitude, longitude).setVisible(true);
               
           
              
              
              
            }
        });
    }
    private javax.swing.JOptionPane AirNotificationPopUp;
    private javax.swing.JOptionPane BadWeatherPopUp;
    // Variables declaration - do not modify                     
    private javax.swing.JLabel AirQualityLabel;
    private javax.swing.JTextField AirQualityTB;
    private javax.swing.JLabel BasicInfoLabel;
    private javax.swing.JTextField BasicInfoTB;
    private javax.swing.JTextField CurrentInfoTB;
    private javax.swing.JLabel CurrentnfoLabel;
    private javax.swing.JLabel FeelLikeLabel;
    private javax.swing.JTextField FeelsLikeTB;
    private javax.swing.JPanel MainCard;
    private javax.swing.JTextArea PollutingDataTb;
    private javax.swing.JLabel PollutingGasesLabel;
    private javax.swing.JLabel SunTimingLabel;
    private javax.swing.JTextField SunTimingTB;
    private javax.swing.JLabel Timestamplabel;
    private javax.swing.JLabel apptitle;
    private javax.swing.JButton focaastPanelBtn;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
