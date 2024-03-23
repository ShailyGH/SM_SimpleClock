//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton hourFormatButton;
        JButton timeZoneFormatButton;
        String time;
        String day;
        String date;

        int hourCounter = 0;
        int timezoneCounter = 0;

        Date date1;

        SimpleDateFormat format_24Hour;
        SimpleDateFormat format_12Hour;

        SimpleDateFormat format_LocalDay;
        SimpleDateFormat format_GMTDay;

        SimpleDateFormat format_LocalDate;
        SimpleDateFormat format_GMTDate;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            //this.setSize(350, 220);
            this.setSize(500, 300);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            hourFormatButton = new JButton("12/24 hr format");
            hourFormatButton.setPreferredSize(new Dimension(170, 40));
            hourFormatButton.setBackground(Color.CYAN);

            timeZoneFormatButton = new JButton("local/GMT");
            timeZoneFormatButton.setPreferredSize(new Dimension(150, 40));
            timeZoneFormatButton.setBackground(Color.magenta);

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(hourFormatButton);
            this.add(timeZoneFormatButton);
            this.setVisible(true);

            hourFormatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        hourCounter ++;
                        if (hourCounter % 2 != 0) {
                            format_24Hour = new SimpleDateFormat("HH:mm:ss");

                        }
                        else {
                            format_12Hour = new SimpleDateFormat("HH:mm:ss a");
                        }

                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


            setTimer();
        }
    
        public void setTimer() {
            while (Thread.currentThread().isAlive()) {
//                time = timeFormat.format(Calendar.getInstance().getTime());
//                timeLabel.setText(time);
//
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);


                date1 = new Date(System.currentTimeMillis());

                if (hourCounter == 0) {
                    time = timeFormat.format(Calendar.getInstance().getTime());
                }
                else if (hourCounter % 2 != 0) {
                    time = format_24Hour.format(date1);
                }
                else
                {
                    time = format_12Hour.format(date1);
                }
                timeLabel.setText(time);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
