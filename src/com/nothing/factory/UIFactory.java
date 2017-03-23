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

	public static JTextPane monitor;
	
	public static List<ProgressView> proview = new ArrayList<ProgressView>();
	
	public static UserListOpera nothingTree;
	
	public static GroupTree groupTree;
	
	public static Map<String, TreeBase> groupUserTreeMap = new HashMap<String, TreeBase>();
	
	public static Map<String, GroupForm> groupFormMap = new HashMap<String, GroupForm>();
	
	public static RecentTree recentTree;
}
