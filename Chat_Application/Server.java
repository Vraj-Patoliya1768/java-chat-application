package Chat_Application;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.event.KeyEvent;

import java.net.*;
import java.io.*;

public class Server implements ActionListener{
    RPanel p1 , a1;
    RTextField tfusername;
    RButton send;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dou;
    static JScrollPane scrollPane;

    static JFrame f = new JFrame();
    Server(){
        f.setSize(400 , 510);
        f.setLocation(300,100);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setUndecorated(true);
        
        f.setLayout(null);
        p1 = new RPanel(0, new Color(0, 100, 0));
        p1.setBounds(0,0,400,60);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons1/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,18,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                System.exit(0);
            }      
        });
       

        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icons1/video.png"));
        Image ii2 = ii1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel VC = new JLabel(ii3);
        VC.setBounds(250,18,25,25);
        p1.add(VC);

        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icons1/phone.png"));
        Image iii2 = iii1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel Call = new JLabel(iii3);
        Call.setBounds(300,18,25,25);
        p1.add(Call);

        ImageIcon iiii1 = new ImageIcon(ClassLoader.getSystemResource("icons1/3icon.png"));
        Image iiii2 = iiii1.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon iiii3 = new ImageIcon(iiii2);
        JLabel Option = new JLabel(iiii3);
        Option.setBounds(350,18,5,25);
        p1.add(Option);

        JLabel lblhead = new JLabel("Server Side...");
        JLabel lblhead1 = new JLabel("Active Now");
        lblhead.setBounds(50,3,500,30);
        lblhead.setFont(new Font("Roman",Font.BOLD , 18 ));
        lblhead.setForeground(Color.WHITE);
        lblhead1.setBounds(55,25,500,30);
        lblhead1.setFont(new Font("Roman",Font.BOLD , 10 ));
        lblhead1.setForeground(Color.WHITE);
        p1.add(lblhead1);
        p1.add(lblhead);

        a1 = new RPanel();
        a1.setBounds(5,65,389,400);
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS));
        a1.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5, 65, 389, 400);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

        f.add(scrollPane);


        tfusername = new RTextField(10);
        tfusername.setBounds(5,470,270,30);
        tfusername.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        tfusername.setFont(new Font("Roman",Font.PLAIN,15));
        f.add(tfusername);
        tfusername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send.doClick();
                }
            }
        });
        
        
        send = new RButton("Send", 10);
        send.setBounds(285,470,100,30);
        send.setBackground(new Color(0,100,0));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Roman",Font.BOLD , 14 ));
        send.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        send.addActionListener(this);
        f.add(send);


        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
    try{
        String out = tfusername.getText();
        dou.writeUTF(out); // <-- Add this

        JPanel p2 = formatLabel(out);
        a1.setLayout(new BorderLayout());

        RPanel right = new RPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        a1.add(vertical,BorderLayout.PAGE_START);

        tfusername.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
    }catch(Exception e){
        e.printStackTrace();
    }
}

    public static RPanel formatLabel(String out) {
        // Message text
        JLabel output = new JLabel("<html><p style='width: 150px;'>" + out + "</p></html>");
        output.setFont(new Font("Roman", Font.PLAIN, 14));
        output.setForeground(Color.WHITE);

        // Rounded background container
        RoundedPanel messageBubble = new RoundedPanel(15, new Color(0, 100, 0));
        messageBubble.setLayout(new BorderLayout());
        messageBubble.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageBubble.add(output, BorderLayout.CENTER);

        // Wrap inside main panel
        RPanel panel = new RPanel(new BorderLayout(), 15, Color.WHITE);
        panel.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));
        panel.add(messageBubble, BorderLayout.CENTER);

        // Timestamp
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sl = new SimpleDateFormat("HH:mm");
        JLabel timo = new JLabel(sl.format(cal.getTime()));
        timo.setFont(new Font("Arial", Font.PLAIN, 10));
        timo.setForeground(Color.GRAY);
        timo.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 10));
        timo.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(timo, BorderLayout.SOUTH);

        return panel;
    }

    

    public static void main(String[] argus)
    {
        new Server();

        try{
                ServerSocket skt = new ServerSocket(6001);
                while(true){
                    Socket s = skt.accept();
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    dou = new DataOutputStream(s.getOutputStream());

                    while(true){
                        String msg = din.readUTF();
                        RPanel panel = formatLabel(msg); 
    
                        RPanel left = new RPanel(new BorderLayout());
                        left.add(panel,BorderLayout.LINE_START);
    
                        vertical.add(left);
    
                        vertical .add(Box.createVerticalStrut(15));
                        f.validate();
                        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));

                    }
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
