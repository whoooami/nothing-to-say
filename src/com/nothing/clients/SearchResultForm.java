package com.nothing.clients;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nothing.c.tree.UserListOpera;
import com.nothing.factory.DBFactory;
import com.nothing.factory.MapFactory;
import com.nothing.factory.TreeFactory;
import com.nothing.form.base.MinCloseForm;
import com.nothing.global.Var;
import com.nothing.object.Groups;
import com.nothing.object.Users;
import com.nothing.util.Tools;

public class SearchResultForm extends MinCloseForm {

	private JPanel panel = null;
	private JTable table;
	private DefaultTableModel model = null;
	
	private List list = new ArrayList();
	/**
	 * 0 用户 1群
	 */
	private int uorg = 0;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResultForm frame = new SearchResultForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @author NOTHING
	 * @param users 用户列表
	 * @param uorg 0用户 1群
	 */
	public SearchResultForm(List l, int uorg) {
		setBounds(100, 100, 390, 270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.lbTitle.setText(uorg==0?"查找联系人":"查找群");
		
		this.list = l;
		this.uorg = uorg;
		
		panel = new JPanel();
		panel.setBackground(c);
		getContentPane().add(panel, BorderLayout.CENTER);
		super.btnExit.setLocation(this.getWidth()-27, 0);
		super.btnMini.setLocation(this.getWidth()-50, 0);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		this.setLocationRelativeTo(null);

		JPanel TBpanel = new JPanel();
		TBpanel.setBounds(23, 21, 335, 174);
//		contentPane.add(TBpanel);
		TBpanel.setLayout(new BorderLayout());
		
		table = new JTable();
		TBpanel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(TBpanel);
		/*TB = new JTable();
		TB.setBounds(23, 21, 335, 174);
		panel.add(TB);*/
		
		JButton backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBounds(23, 209, 62, 23);
		backBtn.setName("backBtn");
		backBtn.addActionListener(new SRFormEvt());
		panel.add(backBtn);
		
		JButton infoBtn = new JButton("\u8BE6\u7EC6\u8D44\u6599");
		infoBtn.setBounds(95, 209, 88, 23);
		infoBtn.setName("infoBtn");
		infoBtn.addActionListener(new SRFormEvt());
		panel.add(infoBtn);
		
		JButton addBtn = new JButton("\u52A0\u4E3A\u597D\u53CB");
		addBtn.setBounds(193, 209, 88, 23);
		if(uorg == 1){
			addBtn.setText("加入群");
			addBtn.setName("addQunBtn");
		}
		addBtn.setName("addBtn");
		addBtn.addActionListener(new SRFormEvt());
		panel.add(addBtn);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(291, 209, 62, 23);
		btnCancel.setName("btnCancel");
		btnCancel.addActionListener(new SRFormEvt());
		panel.add(btnCancel);
	}
	
	public void fillInTable(){
		int i = 0;
		if(uorg == 0){
			model = new DefaultTableModel(new Object[][] { },
				    new String[] {"序号", "用户ID","昵称","性别","来自"});
		}else{
			model = new DefaultTableModel(new Object[][] { },
				    new String[] {"序号", "群ID","群昵称","类型","说明"});
		}
		table.setModel(model);
		//nothing
		table.setSelectionMode(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		for(;i<list.size();i++){
			if(uorg == 0){
				Users user = (Users) list.get(i);
				model.insertRow(i, new Object[]{i, user.getuID(), user.getuNickName(), user.getuSex()==0?"女":"男", user.getuProvice()+" "+user.getuCity()});
			}else{
				Groups group = (Groups)list.get(0);
				model.insertRow(i, new Object[]{i, group.getgID(), group.getgName(), group.getgKind(), group.getgNote()});
			}
		}
//		table.setAutoResizeMode(0);
		table.repaint();
	}
	
	public class SRFormEvt implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String btnName = ((JButton)e.getSource()).getName();
			if(btnName.equals("backBtn")){
				dispose();
				SearchConditionForm scForm = new SearchConditionForm();
				scForm.setVisible(true);
			}else if(btnName.equals("infoBtn")){
				int index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				Users user = (Users) list.get(index);
				UserInfoForm uinfo = new UserInfoForm();
				uinfo.fillInField(user);
				uinfo.setVisible(true);
			}else if(btnName.equals("addBtn")){
				String addID = table.getValueAt(table.getSelectedRow(), 1).toString();
				int index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String friends = DBFactory.getFriendsByID(Nothing.uID);
				if(friends.indexOf(addID) != -1){
					System.out.println("SearchResultForm.friends="+friends+" "+addID);
					Tools.alert(SearchResultForm.this, addID+"已经是您的好友！");
				}else{
					String newfriends = friends+","+addID;
					String sql = "update relations set friends='"+newfriends+"' where uID='"+Nothing.uID+"'";
					int i = 0;
					i = DBFactory.operaDB(sql);
					if(i>0){
						TreeFactory fac = new TreeFactory(Nothing.uID, UserListOpera.tree);
						//判断好友是否在线，在线isonline置为1
						Users user = (Users) list.get(index);
						if(MapFactory.onlineUsers.contains(user.getuID())){
							user.setIsOnline(Var.ONLINE);
						}
						fac.addTreeNode(UserListOpera.friendNode, user);
						Tools.alert(SearchResultForm.this, addID+"已经成功添加为您的好友！");
					}else{
						Tools.alert(SearchResultForm.this, "添加失败，稍候请重试！");
					}
				}
			}else if(btnName.equals("btnCancel")){
				dispose();
			}else if(btnName.equals("addQunBtn")){
				//TODO 添加进入群，@nothing
			}
		}
	}

}
