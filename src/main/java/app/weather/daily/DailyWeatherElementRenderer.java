package app.weather.daily;

import app.weather.WeatherUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class DailyWeatherElementRenderer extends JPanel implements
    ListCellRenderer<DailyWeatherSummary> {

  private JLabel dateLabel = new JLabel();
  private JLabel imageLabel = new JLabel();
  private JLabel highTempLabel = new JLabel();
  private JLabel lowTempLabel = new JLabel();
  private JLabel weatherDescLabel = new JLabel();

  public DailyWeatherElementRenderer() {
    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(15, 15, 15, 15));
    setBackground(Color.WHITE);
    initElement();

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    add(dateLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    add(imageLabel, c);

    JPanel tempContainer = new JPanel();
    tempContainer.setLayout(new BoxLayout(tempContainer, BoxLayout.X_AXIS));
    tempContainer.add(highTempLabel);
    tempContainer.add(lowTempLabel);
    tempContainer.setBackground(Color.WHITE);

    c.gridx = 0;
    c.gridy = 2;
    add(tempContainer, c);

    c.gridx = 0;
    c.gridy = 3;
    add(weatherDescLabel, c);
  }

  private void initElement() {
    dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    dateLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
    dateLabel.setFont(new Font(dateLabel.getFont().getName(), Font.PLAIN, 16));
    imageLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
    highTempLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    highTempLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
    highTempLabel.setFont(new Font(highTempLabel.getFont().getName(), Font.PLAIN, 22));
    lowTempLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    lowTempLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
    lowTempLabel.setFont(new Font(lowTempLabel.getFont().getName(), Font.PLAIN, 16));
    weatherDescLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    weatherDescLabel.setFont(new Font(weatherDescLabel.getFont().getName(), Font.PLAIN, 16));
  }

  @Override
  public Component getListCellRendererComponent(JList<? extends DailyWeatherSummary> list,
      DailyWeatherSummary value, int index, boolean isSelected, boolean cellHasFocus) {

    Date date = new Date(Integer.toUnsignedLong(value.getDateTime()) * 1000);
    SimpleDateFormat sdf = new SimpleDateFormat("EE d", Locale.ENGLISH);
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    String formattedDate = sdf.format(date);

    dateLabel.setText(formattedDate);
    highTempLabel.setText(Long.toString(value.getTempHigh() - 273) + "°");
    lowTempLabel.setText(Long.toString(value.getTempLow() - 273) + "°");
    weatherDescLabel.setText(value.getDescription());

    ImageIcon imageIcon = new ImageIcon(
        WeatherUtils.getImageResources(value.getWeatherID()));
    Image image = imageIcon.getImage();
    Image newImg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(newImg));

    return this;
  }
}
