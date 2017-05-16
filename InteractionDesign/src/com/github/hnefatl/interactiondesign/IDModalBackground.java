package com.github.hnefatl.interactiondesign;

import com.github.hnefatl.interactiondesign.ui.IDAction;
import com.github.hnefatl.interactiondesign.ui.IDButton;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDEvent;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDSize;

public class IDModalBackground
{
	private static final IDColour mbColour = new IDColour(0.0, 0.0, 0.0, 0.5);
	
	private static final IDPosition dfOffset = new IDPosition(0, 20);
	private static final IDSize dfSize = new IDSize(1242, 2148);
	
	private static final IDLocation dfLocation = new IDLocation(dfOffset, dfSize);
	private static final double dfScale = 3.0;
	
	private IDFrame modalBackgroundFrame;
	
	private IDAction clearAction;
	
	public IDModalBackground(IDAction clearAction)
	{
		this.clearAction = clearAction;
		
		modalBackgroundFrame = createFrame();
	}
	
	private IDFrame createFrame()
	{
		IDFrame frame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		frame.addComponent(new IDRectangle(mbColour), new IDLocation(IDPosition.ZERO, dfSize));
		frame.addComponent(new IDButton(new IDAction() {
			public void onAction(IDEvent e)
			{
				if (clearAction != null)
				{
					clearAction.onAction(null);
				}
			}
		}, false), new IDLocation(IDPosition.ZERO, dfSize));
		
		return frame;
	}
	
	public IDFrame getRaw()
	{
		return modalBackgroundFrame;
	}
}
