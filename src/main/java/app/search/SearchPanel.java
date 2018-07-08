package app.search;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import app.location.City;

public class SearchPanel extends JPanel {

  private JList<City> cityJList = new JList<>();
  private JTextField searchField = new JTextField();

  public SearchPanel(LocationChangeListener locationChangeListener) {
    super(new FlowLayout());
    setLayout(new GridBagLayout());
    setMaximumSize(new Dimension(300, 1800));
    setPreferredSize(new Dimension(300, 1800));
    setBackground(new Color(255, 255, 255));
    setBorder(new LineBorder(new Color(255, 255, 255), 2));

    JScrollPane cityJScrollPane = new JScrollPane(cityJList);
    cityJScrollPane.setViewportBorder(new LineBorder(new Color(255, 255, 255), 1));
    cityJScrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 1));
    DefaultListModel<City> cityListModel = new DefaultListModel<>();
    cityJList.setModel(cityListModel);
    cityJList.setBackground(new Color(240, 240, 240));
    cityJList.setCellRenderer(new CityElementRenderer());

    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 0;

    add(new SearchContainer(searchField), c);

    c.weightx = 0.5;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(cityJScrollPane, c);

    searchField.setBackground(new Color(240, 240, 240));
    searchField.setBorder(new EmptyBorder(0, 0, 0, 0));
    searchField.setFont(new Font(searchField.getFont().getName(), Font.BOLD, 13));
    searchField.setForeground(Color.GRAY);
    searchField.setPreferredSize(new Dimension(250, 50));
    searchField.setMinimumSize(new Dimension(250, 50));
    searchField.setMaximumSize(new Dimension(250, 50));
    searchField.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        searchCity(searchField.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        String searchQuery = searchField.getText();
        System.out.println("Current query: " + searchQuery);
        searchCity(searchQuery);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {

      }
    });

    searchField.setText("Search location");
    searchField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (searchField.getText().equals("Search location")) {
          searchField.setText("");
          searchField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
          searchField.setForeground(Color.GRAY);
          searchField.setText("Search location");
        }
      }
    });

    cityJList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        City selectedCity = cityJList.getSelectedValue();
        locationChangeListener.locationChanged(selectedCity);
      }
    });
  }

  private void searchCity(String query) {
    // TODO Query a set of city
    try {
      FileInputStream cityListStream = new FileInputStream(
          "./src/main/resources/data/city_list.json");
      Thread cityStream = new Thread(() -> {
        ArrayList<City> searchResult = new ArrayList<>();
        try {
          JsonReader reader = new JsonReader(new InputStreamReader(cityListStream, "UTF-8"));
          Gson gsonBuilder = new GsonBuilder().create();

          reader.beginArray();
          while (reader.hasNext() && searchResult.size() <= 20) {
            City city = gsonBuilder.fromJson(reader, City.class);
            if (city.getName().contains(query)) {
              searchResult.add(city);
            }
          }
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> updateCityList(searchResult));
      });
      cityStream.start();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void updateCityList(ArrayList<City> searchResult) {
    DefaultListModel cityListModel = (DefaultListModel) cityJList.getModel();
    cityListModel.removeAllElements();
    for (City city : searchResult) {
      cityListModel.addElement(city);
    }
    cityJList.updateUI();
  }

}
