package app.search;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class SearchContainer extends JPanel {

  SearchContainer(JTextField searchField) {
    add(searchField);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension(300, 60));
    setMaximumSize(new Dimension(300, 60));
    setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255), 2),
        new EmptyBorder(0, 25, 0, 25)));
    setBackground(new Color(240, 240, 240));
  }

}
