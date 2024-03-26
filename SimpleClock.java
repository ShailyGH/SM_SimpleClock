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
    
        JLabel timeLabel, dayLabel, dateLabel;

        // hourFormatButton - button which switches between 12/24 hr format
        // timeZoneFormatButton - button which switches between local time and GMT
        JButton hourFormatButton, timeZoneFormatButton;

        String time, day, date;

        int hourCounter = 0;
        int timezoneCounter = 0;

        Date currentDate;

        SimpleDateFormat format_12_24_time, format_LocalDay, format_GMTDay, format_LocalDate, format_GMTDate;

        TimeZone tz;

    SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Shaily's Digital Clock");

            Container cp = this.getContentPane();
            FlowLayout flow = new FlowLayout();

            cp.setLayout(flow);

            this.setSize(350, 310);
            this.setResizable(false);

            tz = Calendar.getInstance().getTimeZone();

            format_12_24_time = new SimpleDateFormat("hh:mm:ss a");
            format_GMTDay = new SimpleDateFormat("EEEE");
            format_LocalDay = new SimpleDateFormat("EEEE");
            format_LocalDate = new SimpleDateFormat("dd MMMMM, yyyy");
            format_GMTDate = new SimpleDateFormat("dd MMMMM, yyyy");

            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setVerticalAlignment(JLabel.CENTER);
            timeLabel.setBackground(Color.ORANGE);
            timeLabel.setForeground(Color.BLACK);
            timeLabel.setOpaque(true);

            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
            dayLabel.setHorizontalAlignment(JLabel.CENTER);
            dayLabel.setVerticalAlignment(JLabel.CENTER);
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            dateLabel.setVerticalAlignment(JLabel.CENTER);

            hourFormatButton = new JButton("Change to 24 hr format");
            hourFormatButton.setFont(new Font("ITALIC",Font.BOLD,15));
            hourFormatButton.setPreferredSize(new Dimension(250, 40));
            hourFormatButton.setBackground(Color.CYAN);
            hourFormatButton.setHorizontalAlignment(JLabel.CENTER);
            hourFormatButton.setVerticalAlignment(JLabel.CENTER);

            timeZoneFormatButton = new JButton("Change to GMT format");
            timeZoneFormatButton.setPreferredSize(new Dimension(250, 40));
            timeZoneFormatButton.setFont(new Font("ITALIC",Font.BOLD,15));
            timeZoneFormatButton.setBackground(Color.MAGENTA);
            timeZoneFormatButton.setHorizontalAlignment(JLabel.CENTER);
            timeZoneFormatButton.setVerticalAlignment(JLabel.CENTER);

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
// Time (12/24 hr) format button action event
        hourFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    hourCounter ++;
                    if (hourCounter % 2 != 0) // 12 hr clock format condition
                    {
                        hourFormatButton.setText("Change to 12 Hr format");
                        format_12_24_time = new SimpleDateFormat("HH:mm:ss");
                    }
                    else // 24 hr clock format condition
                    {
                        hourFormatButton.setText("Change to 24 Hr format");
                        format_12_24_time = new SimpleDateFormat("hh:mm:ss a");
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        // Timezone (Local/GMT format button action event
        timeZoneFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    timezoneCounter ++;

                    if (hourCounter % 2 != 0) // 24 hr clock format condition
                    {
                        format_12_24_time = new SimpleDateFormat("HH:mm:ss");
                    }
                    else // 12 hr clock format condition
                    {
                        format_12_24_time = new SimpleDateFormat("hh:mm:ss a");
                    }

                    if (timezoneCounter % 2 != 0) // local timezone format condition
                    {
                        timeZoneFormatButton.setText("Change to GMT timezone");
                        format_12_24_time.setTimeZone(TimeZone.getTimeZone("GMT"));
                        format_GMTDay.setTimeZone(TimeZone.getTimeZone("GMT"));
                        format_GMTDate.setTimeZone(TimeZone.getTimeZone("GMT"));
                    }
                    else // GMT timezone format condition
                    {
                        timeZoneFormatButton.setText("Change to Local timezone");
                        format_12_24_time.setTimeZone(TimeZone.getTimeZone(tz.getID()));
                        format_LocalDay.setTimeZone(TimeZone.getTimeZone(tz.getID()));
                        format_LocalDate.setTimeZone(TimeZone.getTimeZone(tz.getID()));
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
                currentDate = new Date(System.currentTimeMillis());
                time = format_12_24_time.format(currentDate);

                if (timezoneCounter % 2 != 0) // local timezone format condition
                {
                    day = format_LocalDay.format(Calendar.getInstance().getTime());
                    date = format_LocalDate.format(Calendar.getInstance().getTime());
                }
                else // GMT timezone format condition
                {
                    day = format_GMTDay.format(Calendar.getInstance().getTime());
                    date = format_GMTDate.format(Calendar.getInstance().getTime());
                }

                timeLabel.setText(time);
                dayLabel.setText(day);
                dateLabel.setText(date);

                try
                {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                    e.getStackTrace();
                }
            }
        }

        public static void main(String[] args)
        {
            new SimpleClock();
        }
    }
