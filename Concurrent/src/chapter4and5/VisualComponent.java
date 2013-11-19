package chapter4and5;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent
{
   private final List<KeyListener> KeyListeners=new CopyOnWriteArrayList<KeyListener>();
   private final List<MouseListener> mouseListner=new CopyOnWriteArrayList<MouseListener>();
   public void addKeyListener(KeyListener listener)
   {
	   this.KeyListeners.add(listener);
   }
   public void addMouseListener(MouseListener listener)
   {
	   this.mouseListner.add(listener);
   }
   public void removeKeyListener(KeyListener listener)
   {
       this.KeyListeners.remove(listener);	   
   }
   public void removeMouseListener(MouseListener listener)
   {
	   this.mouseListner.remove(listener);
   }
}
