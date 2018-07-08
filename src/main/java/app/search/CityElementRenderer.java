package app.search;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import app.location.City;

public class CityElementRenderer extends JPanel implements ListCellRenderer<City> {

  private JLabel name = new JLabel(" ");
  private JLabel country = new JLabel(" ");

  CityElementRenderer() {
    setOpaque(true);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255), 1),
        new EmptyBorder(10, 25, 10, 25)));
    setBackground(new Color(240, 240, 240));

    name.setFont(new Font(name.getFont().getName(), Font.BOLD, 13));
    name.setForeground(Color.GRAY);
    add(name);
    country.setFont(new Font(country.getFont().getName(), Font.PLAIN, 13));
    country.setForeground(Color.GRAY);
    add(country);
  }

  @Override
  public Component getListCellRendererComponent(JList<? extends City> list, City value, int index,
      boolean isSelected, boolean cellHasFocus) {

    if (isSelected || cellHasFocus) {
      setBackground(new Color(255, 255, 255));
    } else {
      setBackground(new Color(240, 240, 240));
    }

    name.setText(value.getName());
    country.setText(value.getCountry());

    return this;
  }


}
