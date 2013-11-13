public class CircularQueue<T> implements QueueInterface<T>
{
	protected LinkedListNode<T> rear;

	@Override
	public boolean hasNext()
	{
		return false;
	}

	@Override
	public T next()
	{
		return null;
	}

	@Override
	public synchronized void remove(T parm)
	{
		CircularQueue<T> tempQueue = new CircularQueue<T>();
		try
		{
			while(true)
			{
				T element = dequeue();
				if (!element.equals(parm))
				{
					tempQueue.enqueue(element);
				}
			}
		} catch (QueueUnderflowException e)
		{
			while(true)
			{
				T element;
				try
				{
					element = tempQueue.dequeue();
					enqueue(element);
				} catch (QueueUnderflowException e1)
				{
					break;
				}
			}
		}
	}

	@Override
	public synchronized void enqueue(T element)
	{
		LinkedListNode<T> newNode = new LinkedListNode<T>(element);
		if (rear != null)
		{
			newNode.setNext(rear.getNext());
			rear.setNext(newNode);
		}
		else
		{
			newNode.setNext(newNode);
		}
		rear = newNode;
		System.out.println("ENQUEUE -- Queue Size: " + size() + " " + element.toString());
	}

	@Override
	public synchronized T dequeue() throws QueueUnderflowException
	{
		T element = null;
		if (isEmpty())
		{
			throw new QueueUnderflowException("Dequeue attempted on empty queue.");
		}
		else
		{
			element = rear.getNext().getData();
			if (size() == 1)
			{
				rear = null;
			}
			else
			{
				rear.setNext(rear.getNext().getNext());
			}
		}
		System.out.println("DEQUEUE -- Queue Size: " + size() + " " + element.toString());
		return element;
	}

	@Override
	public synchronized boolean isEmpty()
	{
		if (rear == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public synchronized int size()
	{
		if (rear == null)
		{
			return 0;
		}
		else
		{
			int count = 1;
			LinkedListNode<T> temp = rear.getNext();
			while (temp != rear)
			{
				count++;
				temp = temp.getNext();
			}
			return count;
		}
	}
	@Override
	public synchronized boolean contains(Object element)
	{
		boolean found=false;
		if (rear == null)
		{
			return found;
		}
		else
		{
			LinkedListNode<T> temp = rear;
			do
			{
				temp = temp.getNext();
				if (temp.getData().equals(element))
				{
					found = true;
					break;
				}
			}while(temp != rear);
		}		
		return found;
	}

	@Override
	public void remove()
	{
		
	}
}
