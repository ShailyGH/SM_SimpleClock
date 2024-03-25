//package SimpleClock;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        // A button which switches between 12/24 hr format
        JButton hourFormatButton;

        // A button which switches between local time and GMT
        JButton timeZoneFormatButton;

        String time;
        String day;
        String date;

        int hourCounter = 0;
        int timezoneCounter = 0;

        Date currentDate;

        SimpleDateFormat format_24Hour;
        SimpleDateFormat format_12Hour;

        SimpleDateFormat format_LocalDay;
        SimpleDateFormat format_GMTDay;

        SimpleDateFormat format_LocalDate;
        SimpleDateFormat format_GMTDate;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");

            Container cp = this.getContentPane();
            FlowLayout flow = new FlowLayout();

            cp.setLayout(flow);

            this.setSize(350, 310);
            this.setResizable(false);
            //this.setLayout(null);

            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            int xPosTime = timeLabel.getHorizontalAlignment(); // 10
            int yPosTime = timeLabel.getVerticalAlignment(); // 10
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setVerticalAlignment(JLabel.CENTER);
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);

            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
            dayLabel.setHorizontalAlignment(JLabel.CENTER);
            dayLabel.setVerticalAlignment(JLabel.CENTER);
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            dateLabel.setVerticalAlignment(JLabel.CENTER);

            hourFormatButton = new JButton("12/24 hr format");
            hourFormatButton.setFont(new Font("ITALIC",Font.BOLD,15));
            hourFormatButton.setPreferredSize(new Dimension(170, 40));
            hourFormatButton.setBackground(Color.CYAN);
            hourFormatButton.setHorizontalAlignment(JLabel.CENTER);
            hourFormatButton.setVerticalAlignment(JLabel.CENTER);

            timeZoneFormatButton = new JButton("local/GMT");
            timeZoneFormatButton.setPreferredSize(new Dimension(170, 40));
            timeZoneFormatButton.setFont(new Font("ITALIC",Font.BOLD,15));
            timeZoneFormatButton.setBackground(Color.orange);
            timeZoneFormatButton.setHorizontalAlignment(JLabel.CENTER);
            timeZoneFormatButton.setVerticalAlignment(JLabel.CENTER);


            hourFormatButton.setLayout(null);
            timeZoneFormatButton.setLayout(null);

            cp.add(timeLabel);
            cp.add(dayLabel);
            this.add(dateLabel);
            this.add(hourFormatButton);
            this.add(timeZoneFormatButton);

            this.getContentPane().setBackground(Color.PINK);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setSize(350, 310);

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
                            format_12Hour = new SimpleDateFormat("hh:mm:ss a");
                        }

                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            timeZoneFormatButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    timezoneCounter ++;

                    if (timezoneCounter % 2 != 0) {

                        format_LocalDay = new SimpleDateFormat("EEEE");
                        format_LocalDay.setTimeZone(TimeZone.getTimeZone("EST"));

                        format_LocalDate = new SimpleDateFormat("dd MMMMM, yyyy");
                        format_LocalDate.setTimeZone(TimeZone.getTimeZone("EST"));
                    }
                    else
                    {
                        format_GMTDay = new SimpleDateFormat("EEEE");
                        format_GMTDay.setTimeZone(TimeZone.getTimeZone("GMT"));

                        format_GMTDate = new SimpleDateFormat("dd MMMMM, yyyy");
                        format_GMTDate.setTimeZone(TimeZone.getTimeZone("GST"));
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
//                day = dayFormat.format(Calendar.getInstance().getTime());
//                dayLabel.setText(day);
//
//                date = dateFormat.format(Calendar.getInstance().getTime());
//                dateLabel.setText(date);

                currentDate = new Date(System.currentTimeMillis());

                if (hourCounter == 0)
                {
                    time = timeFormat.format(Calendar.getInstance().getTime());
                }
                else if (hourCounter % 2 != 0)
                {
                    time = format_24Hour.format(currentDate);
                }
                else
                {
                    time = format_12Hour.format(currentDate);
                }
                timeLabel.setText(time);


                if (timezoneCounter == 0) {
                    day = dayFormat.format(Calendar.getInstance().getTime());
                    date = dateFormat.format(Calendar.getInstance().getTime());
                }
                else if (timezoneCounter % 2 != 0) {
                    day = format_LocalDay.format(Calendar.getInstance().getTime());
                    date = format_LocalDate.format(Calendar.getInstance().getTime());
                    System.out.println("Local Time: " + day + " " + date);
                }
                else
                {
                    day = format_GMTDay.format(Calendar.getInstance().getTime());
                    date = format_GMTDate.format(Calendar.getInstance().getTime());
                    System.out.println("GMT Time: " + day + " " + date);
                }

                dayLabel.setText(day);
                dateLabel.setText(date);

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
