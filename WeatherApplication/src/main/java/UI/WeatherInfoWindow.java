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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Weather Details");
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        MainCard.setBackground(java.awt.Color.lightGray);
        MainCard.setPreferredSize(new java.awt.Dimension(950, 650));

        FeelLikeLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        FeelLikeLabel.setForeground(new java.awt.Color(0, 0, 0));
        FeelLikeLabel.setText("Feels Like:");

        CurrentnfoLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        CurrentnfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        CurrentnfoLabel.setText("Current Weather Information:");

        BasicInfoLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        BasicInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        BasicInfoLabel.setText("Basic Information:");

        SunTimingLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        SunTimingLabel.setForeground(new java.awt.Color(0, 0, 0));
        SunTimingLabel.setText("Sun Rise and Sun Set Time:");

        CurrentInfoTB.setEditable(false);
        CurrentInfoTB.setBackground(new java.awt.Color(255, 255, 255));
        CurrentInfoTB.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        CurrentInfoTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        CurrentInfoTB.setFocusable(false);

        FeelsLikeTB.setEditable(false);
        FeelsLikeTB.setBackground(new java.awt.Color(255, 255, 255));
        FeelsLikeTB.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        FeelsLikeTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        FeelsLikeTB.setFocusable(false);

        SunTimingTB.setEditable(false);
        SunTimingTB.setBackground(new java.awt.Color(255, 255, 255));
        SunTimingTB.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        SunTimingTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        SunTimingTB.setFocusable(false);

        BasicInfoTB.setEditable(false);
        BasicInfoTB.setBackground(new java.awt.Color(255, 255, 255));
        BasicInfoTB.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        BasicInfoTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        BasicInfoTB.setFocusable(false);

        PollutingGasesLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        PollutingGasesLabel.setForeground(new java.awt.Color(0, 0, 0));
        PollutingGasesLabel.setText("Air  Polluting  Gases Data:");

        PollutingDataTb.setBackground(new java.awt.Color(255, 255, 255));
        PollutingDataTb.setColumns(20);
        PollutingDataTb.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        PollutingDataTb.setRows(5);
        PollutingDataTb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        PollutingDataTb.setFocusable(false);
        jScrollPane1.setViewportView(PollutingDataTb);

        AirQualityLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        AirQualityLabel.setForeground(new java.awt.Color(0, 0, 0));
        AirQualityLabel.setText("Air Quality Status:");

        AirQualityTB.setEditable(false);
        AirQualityTB.setBackground(new java.awt.Color(255, 255, 255));
        AirQualityTB.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        AirQualityTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        AirQualityTB.setFocusable(false);

        focaastPanelBtn.setBackground(new java.awt.Color(255, 102, 102));
        focaastPanelBtn.setText("View Five Day Focast for this Location");
        focaastPanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                focaastPanelBtnActionPerformed(evt);
            }
        });

        Timestamplabel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Timestamplabel.setForeground(new java.awt.Color(0, 0, 0));
        Timestamplabel.setText("Timestamp");

        apptitle.setBackground(new java.awt.Color(51, 0, 51));
        apptitle.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 20)); // NOI18N
        apptitle.setForeground(new java.awt.Color(51, 0, 51));
        apptitle.setText("At a Glance Weather Application");

        javax.swing.GroupLayout MainCardLayout = new javax.swing.GroupLayout(MainCard);
        MainCard.setLayout(MainCardLayout);
        MainCardLayout.setHorizontalGroup(
            MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainCardLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BasicInfoLabel)
                    .addComponent(CurrentnfoLabel)
                    .addComponent(FeelLikeLabel)
                    .addComponent(SunTimingLabel)
                    .addGroup(MainCardLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AirQualityLabel)
                            .addComponent(PollutingGasesLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MainCardLayout.createSequentialGroup()
                        .addComponent(focaastPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Timestamplabel))
                    .addComponent(FeelsLikeTB)
                    .addComponent(CurrentInfoTB)
                    .addComponent(BasicInfoTB)
                    .addComponent(SunTimingTB)
                    .addComponent(jScrollPane1)
                    .addComponent(AirQualityTB)
                    .addComponent(apptitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        MainCardLayout.setVerticalGroup(
            MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainCardLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(apptitle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CurrentInfoTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentnfoLabel))
                .addGap(18, 18, 18)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BasicInfoLabel)
                    .addComponent(BasicInfoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FeelLikeLabel)
                    .addComponent(FeelsLikeTB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SunTimingLabel)
                    .addComponent(SunTimingTB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PollutingGasesLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AirQualityTB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AirQualityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(MainCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(focaastPanelBtn)
                    .addComponent(Timestamplabel))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
