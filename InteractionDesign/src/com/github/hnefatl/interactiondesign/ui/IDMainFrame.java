package com.github.hnefatl.interactiondesign.ui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class IDMainFrame
{
	JFrame jFrame;
	JLayeredPane lPane;
	
	IDFrame bgFrame;
	IDFrame idFrame;
	
	IDFrame modalBgFrame;
	IDFrame modalFrame;
	
	boolean modalShowing;
	
	public IDMainFrame(IDFrame bgFrame)
	{
		this(bgFrame, null);
	}
	
	public IDMainFrame(IDFrame bgFrame, IDFrame modalBgFrame)
	{
		jFrame = new JFrame("IDFrame test");
		jFrame.setLayout(null);
		
		lPane = new JLayeredPane() {
			static final long serialVersionUID = 0x81393723; // Silences compiler warning about serialisation
			
			@Override
			public void paint(Graphics g)
			{
				super.paint(g);
				
				if (bgFrame != null)
				{
					bgFrame.repaint();
				}
				
				if (idFrame != null)
				{
					idFrame.repaint();
				}
				
				if (modalBgFrame != null)
				{
					modalBgFrame.repaint();
				}
				
				if (modalFrame != null)
				{
					modalFrame.repaint();
				}
			}
		};
		
		lPane.setLayout(null);
		
		lPane.setSize(bgFrame.getFrame().getActualSize().toRaw());
		
		this.bgFrame = bgFrame;
		idFrame = null;
		
		this.modalBgFrame = modalBgFrame;
		modalFrame = null;
		
		modalShowing = false;
		
		lPane.add(this.bgFrame.getRaw(), new Integer(0));
		this.bgFrame.repaint();
		
		lPane.setLocation(0, 0);
		
		jFrame.add(lPane);
		
		lPane.repaint();
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setUndecorated(true);
		
		jFrame.setSize(bgFrame.getFrame().getActualSize().toRaw());
		jFrame.setLocationRelativeTo(null);
		
		jFrame.setResizable(false);
		
		jFrame.setVisible(true);
	}
	
	public void setFrame(IDFrame idFrame)
	{
		if (this.idFrame != null)
		{
			lPane.remove(this.idFrame.getRaw());
		}
		
		this.idFrame = idFrame;
		
		if (this.idFrame != null)
		{
			lPane.add(this.idFrame.getRaw(), new Integer(1));
			this.idFrame.repaint();
		}
	}
	
	public void setModal(IDFrame modalFrame)
	{
		if (modalShowing)
		{
			return;
		}
		
		if (modalBgFrame != null)
		{
			lPane.add(modalBgFrame.getRaw(), new Integer(200));
		}
		
		this.modalFrame = modalFrame;
		
		if (this.modalFrame != null)
		{
			lPane.add(this.modalFrame.getRaw(), new Integer(201));
		}
		
		modalShowing = true;
		
		if (modalBgFrame != null)
		{
			modalBgFrame.repaint();
		}
		
		if (this.modalFrame != null)
		{
			this.modalFrame.repaint();
		}
	}
	
	public void clearModal()
	{
		if (!modalShowing)
		{
			return;
		}
		
		if (modalBgFrame != null)
		{
			lPane.remove(this.modalBgFrame.getRaw());
		}
		
		if (modalFrame != null)
		{
			lPane.remove(this.modalFrame.getRaw());
		}
		
		modalShowing = false;
		
		lPane.repaint(); // For some reason removing a panel doesn't trigger a redraw, so do it manually
	}
}
