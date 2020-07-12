package custom;

import capteur.Capteur;
import capteur.CapteurExterieur;
import capteur.CapteurInterieur;
import config.Config;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;


public class EnergyTree {

  private JTree tree;
  private EnergyNode root;
  private HashMap<EnergyNode, HashMap<EnergyNode, List<EnergyNode>>>
      treeElements;

  private TreeSet<CapteurExterieur> sensorsExt = new TreeSet<>();
  private TreeSet<CapteurInterieur> sensorsInt = new TreeSet<>();

  public static final byte INTERIOR = 1;
  public static final byte EXTERIOR = 2;
  private byte sensorType;
  private List<Capteur> sensorList = new ArrayList<>();
  private JTable table;

  private TreeSet<String> followSet;

  public EnergyTree(JTable table, TreeSet<String> followSet,
                    TreeSet<CapteurExterieur> sensorsExt,
                    TreeSet<CapteurInterieur> sensorsInt) {
    this.table = table;
    this.followSet = followSet;
    this.sensorsExt.addAll(sensorsExt);
    this.sensorsInt.addAll(sensorsInt);
    root = buildTree();
    tree = new JTree(root);
    tree.setRootVisible(false);
    tree.setCellRenderer(new EnergyTreeRenderer());
    tree.addTreeSelectionListener(new EnergyTreeListener());
  }

  public JTree getTree() { return tree; }

  public EnergyNode buildTree() {
    EnergyNode newroot = new EnergyNode("Capteurs du réseaux");

    EnergyNode nodeExt = new EnergyNode("Capteurs extérieurs");
    EnergyNode nodeInt = new EnergyNode("Capteurs intérieurs");

    newroot.add(nodeExt);
    newroot.add(nodeInt);

    int batId = 0;
    treeElements = new HashMap<>();
    for (String batiment : Config.getBatimentsFromConfig("config.txt")) {
      EnergyNode nodeBatiment = new EnergyNode(batiment);
      int etageId = 0;
      HashMap<EnergyNode, List<EnergyNode>> mapEtages = new HashMap<>();
      for (String etage : Config.getEtagesFromConfig("config.txt", batId)) {
        EnergyNode nodeEtage = new EnergyNode(etage);
        List<EnergyNode> listeSalles = new ArrayList<>();
        for (String salle :
             Config.getSallesFromConfig("config.txt", batId, etageId)) {
          EnergyNode nodeSalle = new EnergyNode(salle);
          nodeEtage.add(nodeSalle);
          listeSalles.add(nodeSalle);
        }
        nodeBatiment.add(nodeEtage);
        mapEtages.put(nodeEtage, listeSalles);
        ++etageId;
      }
      nodeInt.add(nodeBatiment);
      treeElements.put(nodeBatiment, mapEtages);
      ++batId;
    }

    for (CapteurExterieur c : sensorsExt) {
      EnergyNode nodeSensor = new EnergyNode();
      nodeSensor.setUserObject(c);
      nodeSensor.setAllowsChildren(false);
      nodeExt.add(nodeSensor);
    }

    for (CapteurInterieur c : sensorsInt) {
      EnergyNode nodeBatiment = new EnergyNode(c.getBatiment());
      HashMap<EnergyNode, List<EnergyNode>> map =
          treeElements.get(nodeBatiment);

      EnergyNode nodeEtage = new EnergyNode(c.getEtage());
      List<EnergyNode> list = map.get(nodeEtage);

      EnergyNode nodeSalle = new EnergyNode(c.getSalle());
      int index = list.indexOf(nodeSalle);
      EnergyNode node = list.get(index);

      EnergyNode nodeSensor = new EnergyNode();
      nodeSensor.setUserObject(c);
      nodeSensor.setAllowsChildren(false);
      node.add(nodeSensor);
    }

    return newroot;
  }

  public void setSensorsExt(TreeSet<CapteurExterieur> sensorsExt) {
    this.sensorsExt.clear();
    this.sensorsExt.addAll(sensorsExt);
  }

  public void setSensorsInt(TreeSet<CapteurInterieur> sensorsInt) {
    this.sensorsInt.clear();
    this.sensorsInt.addAll(sensorsInt);
  }

  public void setRoot(EnergyNode root) {
    DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
    model.setRoot(root);
  }

  public byte getSensorType() { return sensorType; }

  public CapteurExterieur getCapteurExterieurFromId(String identifiant) {
    for (CapteurExterieur c : sensorsExt)
      if (identifiant.equals(c.getIdentifiant()))
        return c;
    return null;
  }

  public CapteurInterieur getCapteurInterieurFromId(String identifiant) {
    for (CapteurInterieur c : sensorsInt)
      if (identifiant.equals(c.getIdentifiant()))
        return c;
    return null;
  }

  public TreeSet<String> getFollowSet() { return followSet; }

  public JTable getTable() { return table; }

  public Object[][] getRows() {
    Object[][] rows = null;
    if (sensorType == EXTERIOR) {
      DefaultTableModel model = new DefaultTableModel(
          new Object[][] {}, new String[] {"Identifiant", "Type de données",
                                           "Latitude", "Longitude", "Suivi"});
      model.setColumnCount(5);
      table.setModel(model);

      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

      rows = new Object[sensorList.size()][4];
      int i = 0;
      for (ListIterator<Capteur> it = sensorList.listIterator(); it.hasNext();
           ++i) {
        CapteurExterieur current = (CapteurExterieur)it.next();
        String follow =
            (followSet.contains(current.getIdentifiant())) ? "Oui" : "Non";
        rows[i] = new String[] {current.getIdentifiant(),
                                current.getType().toString(),
                                Float.toString(current.getLatitude()),
                                Float.toString(current.getLongitude()), follow};
      }
    } else if (sensorType == INTERIOR) {
      DefaultTableModel model = new DefaultTableModel(
          new Object[][] {},
          new String[] {"Identifiant", "Type de données", "Bâtiment", "Étage",
                        "Salle", "Position relative", "Suivi"});
      model.setColumnCount(7);
      table.setModel(model);

      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

      rows = new Object[sensorList.size()][6];
      int i = 0;
      for (ListIterator<Capteur> it = sensorList.listIterator(); it.hasNext();
           ++i) {
        CapteurInterieur current = (CapteurInterieur)it.next();
        String follow =
            (followSet.contains(current.getIdentifiant())) ? "Oui" : "Non";
        rows[i] = new String[] {current.getIdentifiant(),
                                current.getType().toString(),
                                current.getBatiment(),
                                Integer.toString(current.getEtage()),
                                current.getSalle(),
                                current.getPositionRelative(),
                                follow};
      }
    }
    return rows;
  }

  private void listAllSensors(EnergyNode node) {
    sensorList.clear();
    @SuppressWarnings("unchecked")
    Enumeration<Capteur> e = node.depthFirstEnumeration();
    while (e.hasMoreElements()) {
      Object elt = e.nextElement();
      EnergyNode subnode = (EnergyNode)elt;
      if (!subnode.getAllowsChildren())
        sensorList.add((Capteur)subnode.getUserObject());
    }
  }

  public void refreshTable() {
    Object[][] rows = getRows();
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    for (int i = model.getRowCount() - 1; i >= 0; --i)
      model.removeRow(i);
    for (Object[] newRow : rows) {
      model.addRow(newRow);
    }
    model.fireTableDataChanged();
  }

  private static class EnergyTreeRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 3673532107252732625L;

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded,
                                                  boolean leaf, int row,
                                                  boolean hasFocus) {
      super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                                         hasFocus);

      if (value instanceof EnergyNode) {
        Object obj = ((EnergyNode)value).getUserObject();
        if (!(obj instanceof CapteurInterieur || obj instanceof
                                                     CapteurExterieur))
          setIcon(UIManager.getIcon("FileView.directoryIcon"));
        else
          setIcon(null);
      }

      return this;
    }
  }

  private class EnergyTreeListener implements TreeSelectionListener {

    public void valueChanged(TreeSelectionEvent e) {

      JTree tree = (JTree)e.getSource();
      EnergyNode node = (EnergyNode)tree.getLastSelectedPathComponent();

      if (node == null)
        return;

      boolean interior = false;
      boolean exterior = false;

      if (!node.toString().equals("Capteurs extérieurs") &&
          !node.toString().equals("Capteurs intérieurs")) {
        EnergyNode parent = (EnergyNode)node.getParent();
        interior = parent.toString().equals("Capteurs intérieurs");
        exterior = parent.toString().equals("Capteurs extérieurs");
        while (!interior && !exterior) {
          parent = (EnergyNode)parent.getParent();
          interior = parent.toString().equals("Capteurs intérieurs");
          exterior = parent.toString().equals("Capteurs extérieurs");
        }
      } else if (node.toString().equals("Capteurs extérieurs"))
        exterior = true;
      else
        interior = true;

      if (interior) {
        assert !exterior;
        sensorType = INTERIOR;
      } else {
        assert exterior;
        sensorType = EXTERIOR;
      }

      listAllSensors(node);
      refreshTable();
    }
  }
}
