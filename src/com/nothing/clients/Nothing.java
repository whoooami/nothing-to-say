package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import com.nothing.c.tree.GroupTree;
import com.nothing.c.tree.RecentTree;
import com.nothing.c.tree.UserListOpera;
import com.nothing.evt.BorderSetEvt;
import com.nothing.evt.ButtomBtn;
import com.nothing.evt.SysExit;
import com.nothing.evt.TreeMenu;
import com.nothing.factory.UIFactory;
import com.nothing.global.Constants;
import com.nothing.object.Users;

public class Nothing extends JDialog {
	
	public static Nothing dialog = null;
	public static String uID = null;
	public JTree treeFriend, treeGroup, treeRecent;
	private final JPanel contentPanel;
	private JTextField textField;
	public JButton btnMini = null;
	Color c = new Color(128, 128, 255);
	
	/** 计算移动时的座标 */
	private int xx = 0,yy = 0;
	/** 移动的标志 */
	private boolean isDrag = false;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			dialog = new Nothing();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Nothing(Users user) {
		
		uID = user.getuID();
		
		setBounds(100, 100, 257, 600);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(c);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setUndecorated(true);
		setContentPane(contentPanel);
		
		JPanel panFriend = new JPanel();
		contentPanel.add(panFriend, BorderLayout.CENTER);
		panFriend.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panFriend.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u6211\u7684\u597D\u53CB", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		treeFriend = new JTree();
		UserListOpera ulo = new UserListOpera(treeFriend, uID);
		//nothing 要改为uifactory放至 ulo
//		ManageUserlist.addUserListByID(user.getuID(), ulo);
		UIFactory.nothingTree = ulo;
		panel.add(new JScrollPane(treeFriend, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		treeFriend.addMouseListener(new TreeMenu(treeFriend));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u7FA4\u7EC4", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		treeGroup = new JTree();
		GroupTree group = new GroupTree(treeGroup, uID);
		UIFactory.groupTree = group;
		panel_1.add(new JScrollPane(treeGroup, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		treeGroup.addMouseListener(new TreeMenu(treeGroup));
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u6700\u8FD1\u8054\u7CFB", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		treeRecent = new JTree();
		RecentTree recent = new RecentTree(treeRecent, uID);
		UIFactory.recentTree = recent;
		panel_2.add(new JScrollPane(treeRecent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		
		JPanel panTop = new JPanel();
		panTop.setBackground(c);
		panTop.setPreferredSize(new Dimension(0,110));
		contentPanel.add(panTop, BorderLayout.NORTH);
		panTop.setLayout(new BorderLayout(0, 0));
		
		JPanel panTopClose = new JPanel();
		panTop.add(panTopClose, BorderLayout.NORTH);
		panTopClose.setBackground(c);
		panTopClose.setPreferredSize(new Dimension(0, 16));
		panTopClose.setLayout(null);
		
		btnMini = new JButton();
		btnMini.setBounds(210, 0, 16, 16);
		btnMini.setIcon(new ImageIcon(Nothing.class.getResource("/images/mini.png")));
		panTopClose.add(btnMini);
		
		JButton btnExit = new JButton();
		btnExit.addActionListener(new SysExit(user.getuID()));
		btnExit.setBounds(231, 0, 16, 16);
		btnExit.setIcon(new ImageIcon(Nothing.class.getResource("/images/exit.png")));
		panTopClose.add(btnExit);
		
		JLabel lbID = new JLabel("lbID");
		lbID.setBounds(30, 1, 90, 15);
		lbID.setText(user.getuID());
		panTopClose.add(lbID);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 1, 24, 15);
		panTopClose.add(lblId);
		
		textField = new JTextField();
		panTop.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
//		final UserListOpera ul = new UserListOpera(treeFriend, user.getuID());
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				System.out.println("findUser:"+findName);
				/*UserListOpera ul = new UserListOpera(tree, uID);
				DefaultMutableTreeNode node = ul.searchNode(findName);
				DefaultTreeModel model = new DefaultTreeModel(ul.getRoot());
				if(node != null){
					TreeNode[] nodes = model.getPathToRoot(node);
					TreePath path = new TreePath(nodes);
					tree.scrollPathToVisible(path);
					tree.setSelectionPath(path);
				}*/
				UserListOpera.findInTree(textField.getText());
			}
		});
//		textField.addActionListener(new FU(textField.getText()));
		
		JPanel pan_topin = new JPanel();
		pan_topin.setBackground(c);
		panTop.add(pan_topin, BorderLayout.CENTER);
		pan_topin.setLayout(null);
		
		JButton btnHeadImg = new JButton();
		//nothing
		//这里不用内存中读，因为读时会有尺寸重设的问题。这句注释你没必要懂，也不用弄懂。
		//凡时想弄懂却没弄懂的人在这里把下面的计数器加1 。以警后世！
		//flag = 0;
		btnHeadImg.setIcon(new ImageIcon(Nothing.class.getResource("/images/head/"+user.getuHeadImg())));
		btnHeadImg.setBounds(7, 10, 40, 40);
		pan_topin.add(btnHeadImg);
		
		JLabel label_1 = new JLabel("\u6635\u79F0\uFF1A");
		label_1.setBounds(57, 10, 40, 15);
		pan_topin.add(label_1);
		
		JLabel lbNickName = new JLabel(user.getuNickName());
		lbNickName.setBounds(94, 10, 83, 15);
		pan_topin.add(lbNickName);
		
		JLabel lbWords = new JLabel(user.getuWords());
		lbWords.setBounds(57, 35, 152, 15);
		pan_topin.add(lbWords);
		
		JButton[] topButtons = new JButton[8];
		for(int i=0;i<topButtons.length;i++){
			topButtons[i] = new JButton();
			topButtons[i].setBorder(null);
			topButtons[i].setName(Constants.NTOPBTN+i);
			topButtons[i].setIcon(new ImageIcon(Nothing.class.getResource("/images/main/topbar_"+(i+1)+".gif")));
			pan_topin.add(topButtons[i]);
			topButtons[i].addMouseListener(new BorderSetEvt(user.getuID(), topButtons));
		}
		
		topButtons[0].setBounds(10, 52, 20, 20);
		topButtons[1].setBounds(30, 52, 37, 20);
		topButtons[2].setBounds(67, 52, 20, 20);
		topButtons[3].setBounds(87, 52, 19, 20);
		topButtons[4].setBounds(107, 52, 20, 20);
		topButtons[5].setBounds(127, 52, 20, 20);
		topButtons[6].setBounds(190, 52, 37, 20);
		topButtons[7].setBounds(227, 52, 20, 20);
		
		JPanel panFoot = new JPanel();
		panFoot.setBackground(c);
		panFoot.setPreferredSize(new Dimension(0,30));
		contentPanel.add(panFoot, BorderLayout.SOUTH);
		panFoot.setLayout(null);
		
		JButton button = new JButton();
		button.setIcon(new ImageIcon(Nothing.class.getResource("/images/head/p.jpg")));
		button.setBounds(0, 0, 28, 29);
		button.setName("pandaBtn");
		button.addActionListener(new ButtomBtn());
		panFoot.add(button);
		
		JButton button_1 = new JButton();
		button_1.setBackground(c);
		button_1.setIcon(new ImageIcon(Nothing.class.getResource("/images/main/qqgame.gif")));
		button_1.setBounds(81, 9, 20, 20);
		panFoot.add(button_1);
		
		JButton button_2 = new JButton();
		button_2.setIcon(new ImageIcon(Nothing.class.getResource("/images/main/qqchatroom.gif")));
		button_2.setBackground(c);
		button_2.setBounds(111, 9, 20, 20);
		panFoot.add(button_2);
		
		JButton photoBtn = new JButton();
		photoBtn.setIcon(new ImageIcon(Nothing.class.getResource("/images/main/qqtv.gif")));
		photoBtn.setBackground(c);
		photoBtn.setBounds(141, 9, 20, 20);
		photoBtn.setName("photoBtn");
		photoBtn.addActionListener(new ButtomBtn());
		panFoot.add(photoBtn);
		
		JButton findBtn = new JButton();
		findBtn.setIcon(new ImageIcon(Nothing.class.getResource("/images/main/qqfind.png")));
		findBtn.setBackground(new Color(128, 128, 255));
		findBtn.setBounds(182, 9, 55, 20);
		findBtn.setName("findBtn");
		findBtn.addActionListener(new ButtomBtn());
		panFoot.add(findBtn);
		
		JButton loveBtn = new JButton();
		loveBtn.setIcon(new ImageIcon(Nothing.class.getResource("/images/main/heart.jpg")));
		loveBtn.setBackground(new Color(128, 128, 255));
		loveBtn.setBounds(51, 8, 20, 20);
		loveBtn.setName("loveBtn");
		loveBtn.addActionListener(new ButtomBtn());
		panFoot.add(loveBtn);
		
		JPanel panel_left = new JPanel();
		contentPanel.add(panel_left, BorderLayout.WEST);
		panel_left.setPreferredSize(new Dimension(30, 0));
		panel_left.setBackground(c);
		panel_left.setLayout(null);
		
		JButton[] sideButtons = new JButton[8];
		for(int i=0;i<sideButtons.length;i++){
			sideButtons[i] = new JButton();
			sideButtons[i].setBorder(null);
			sideButtons[i].setBounds(0, (30*i), 32, 30);
			sideButtons[i].setName(Constants.NSIDEBTN+i);
			sideButtons[i].setIcon(new ImageIcon(Nothing.class.getResource("/images/main/Sidebar_0"+(i+1)+".gif")));
			panel_left.add(sideButtons[i]);
			sideButtons[i].addMouseListener(new BorderSetEvt(user.getuID(), sideButtons));
		}
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				isDrag = true;
				xx = e.getX();
				yy = e.getY();
			}
			public void mouseReleased(MouseEvent e){
				isDrag = false;
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if(isDrag){
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + e.getX()-xx,top + e.getY()-yy);
				}
			}
		});
//		Client.setHasMsg(ManageChatForm.msgList);
	}
}
