import java.util.Iterator;

public interface QueueInterface<T> extends Iterator<T> 
{
	public void enqueue(T element);
	
	public T dequeue() throws QueueUnderflowException;
	
	public boolean isEmpty();
	
	public int size();

	public boolean contains(Object element);

	public void remove(T element);
	
}
