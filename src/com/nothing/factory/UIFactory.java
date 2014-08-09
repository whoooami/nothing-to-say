package com.nothing.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;

import com.nothing.c.filetrans.ProgressView;
import com.nothing.c.tree.GroupTree;
import com.nothing.c.tree.RecentTree;
import com.nothing.c.tree.TreeBase;
import com.nothing.c.tree.UserListOpera;
import com.nothing.clients.GroupForm;

public class UIFactory {

	/** 用于存储服务端监视器的滚屏 */
	public static JTextPane monitor;
	
	/** 用于存放文件传输的view */
	public static List<ProgressView> proview = new ArrayList<ProgressView>();
	
	/** 用于存放Nothing列表对象 */
	public static UserListOpera nothingTree;
	
	/** 用于存放群列表对象 */
	public static GroupTree groupTree;
	
	/** 存放群用户列表对象-根据群号码存储 */
	public static Map<String, TreeBase> groupUserTreeMap = new HashMap<String, TreeBase>();
	
	/** 群聊天窗口的对象 */
	public static Map<String, GroupForm> groupFormMap = new HashMap<String, GroupForm>();
	
	/** 用于存放最近联系列表对象 */
	public static RecentTree recentTree;
}
