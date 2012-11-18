package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tp.pr5.Constants;
import tp.pr5.items.Item;
import tp.pr5.observers.PlayerObserver;

public class PlayerPanel extends JPanel implements PlayerObserver {

	private static final long serialVersionUID = 1L;

	private JLabel etqScore;
	private JLabel etqHealth;
	private JTable table;
	private PlayerTableModel tableModel;
	private MapPanel mapPanel;

	/**
	 * @return the tableModel
	 */

	private Vector<Item> itemData = new Vector<Item>();
	static Vector<Item> items = new Vector<Item>();

	public PlayerPanel(MapPanel mapPanel) {
		super();
		this.mapPanel = mapPanel;
		initialize();
	}
	
	public PlayerPanel() {
		super();
		initialize();

	}

	private void initialize() {
		this.setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel();
		panelNorte.add(new JLabel("Score: ", JLabel.LEFT));
		etqScore = new JLabel();
		panelNorte.add(etqScore);
		panelNorte.add(new JLabel("Health: ", JLabel.RIGHT));
		etqHealth = new JLabel();
		panelNorte.add(etqHealth);
		this.add(panelNorte, BorderLayout.NORTH);
		tableModel = new PlayerTableModel();
		setTable(new JTable(tableModel));
		JScrollPane sp = new JScrollPane(getTable());
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp.setPreferredSize(new Dimension(1000, 300));
		this.add(sp, BorderLayout.CENTER);
	}

	public void setHealth(int health) {
		etqHealth.setText(Integer.toString(health));
	}

	public void setScore(int score) {
		etqScore.setText(Integer.toString(score));
	}

	public void setInventario(List<Item> inventory) {
		this.getItemData().clear();
		this.getItemData().addAll(inventory);
		tableModel.fireTableDataChanged();
	}

	private class PlayerTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		String[] columnNames;

		PlayerTableModel() {
			super();
			columnNames = new String[] { "id", "description" };
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			return getItemData().size();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public Object getValueAt(int row, int column) {

			Item item = getItemData().get(row);
			if (column == 0)
				return item.getId();
			else if (column == 1)
				return item.getDescription();

			return "vacio";
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {

			return false;
		}

	}

	public void addItem(Item item) {
		items.add(item);
		this.setInventario(items);
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the itemData
	 */
	public Vector<Item> getItemData() {
		return itemData;
	}
	
	public String getSelectedItem() {
		return this.table.getValueAt(this.table.getSelectedRow(), 0).toString();
	}

	/**
	 * @param itemData
	 *            the itemData to set
	 */
	public void setItemData(Vector<Item> itemData) {
		this.itemData = itemData;
	}

	@Override
	public void playerLookedInventory(List<Item> inventory) {
	}

	@Override
	public void inventoryUpdate(List<Item> inventory) {
		setInventario(inventory);
	}

	@Override
	public void playerUpdate(int newHealth, int newScore) {
		etqHealth.setText(String.valueOf(newHealth));
		etqScore.setText(String.valueOf(newScore));
	}

	@Override
	public void playerDead() {
	}

	@Override
	public void itemLooked(String description) {
	}

	@Override
	public void itemUsed(String itemName) {
		JOptionPane.showMessageDialog(null, Constants.MESSAGE_CHANGES);

	}

	@Override
	public void itemEmpty(String itemName) {
	}

}
