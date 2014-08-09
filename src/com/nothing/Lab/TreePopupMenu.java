package com.nothing.Lab;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreePopupMenu extends JFrame implements MouseListener, ActionListener {

    private static final long serialVersionUID = 1L;
    JTree tree;
    JPopupMenu popMenu;
    JMenuItem addItem;
    JMenuItem delItem;
    JMenuItem editItem;

    public TreePopupMenu(JTree tree) {
    	this.tree = tree;
        /*tree = new JTree();
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addMouseListener(this);
        tree.setCellEditor(new DefaultTreeCellEditor(tree, new DefaultTreeCellRenderer()));
        setSize(200, 200);*/

        popMenu = new JPopupMenu();
        addItem = new JMenuItem("Ìí¼Ó");
        addItem.addActionListener(this);
        delItem = new JMenuItem("É¾³ý");
        delItem.addActionListener(this);
        editItem = new JMenuItem("ÐÞ¸Ä");
        editItem.addActionListener(this);

        popMenu.add(addItem);
        popMenu.add(delItem);
        popMenu.add(editItem);

        getContentPane().add(new JScrollPane(tree));
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());  
        if (path == null) {
            return;
        }
        tree.setSelectionPath(path);

        if (e.getButton() == 3) {
            popMenu.show(tree, e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (e.getSource() == addItem) {
            ((DefaultTreeModel) tree.getModel()).insertNodeInto(new DefaultMutableTreeNode("Test"),
                                                                node, node.getChildCount());
            tree.expandPath(tree.getSelectionPath());
        } else if (e.getSource() == delItem) {
            if (node.isRoot()) {
                return;
            }
            ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
        } else if (e.getSource() == editItem) {
            tree.startEditingAtPath(tree.getSelectionPath());
        }
    }

    /*public static void main(String[] args) {
        TreePopupMenu frame = new TreePopupMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/
}