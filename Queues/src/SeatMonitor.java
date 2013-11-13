import java.util.Random;

public class SeatMonitor implements Runnable
{	
	CircularQueue<CustomerInfo> windowSeat;
	CircularQueue<CustomerInfo> boothSeat;
	CircularQueue<CustomerInfo> barSeat;

	public SeatMonitor(CircularQueue<CustomerInfo> windowSeat, CircularQueue<CustomerInfo> boothSeat, CircularQueue<CustomerInfo> barSeat)
	{
		this.windowSeat = windowSeat;
		this.boothSeat = boothSeat;
		this.barSeat = barSeat;
	}

	Random randomNumber = new Random();

	public boolean isBoothAvailable()
	{
		boolean availableSeats = false;

		Random randomNumber = new Random();
		int seatNumber = randomNumber.nextInt (10);
		if (seatNumber%2 == 0)
		{
			availableSeats = true;
			System.out.println(" Booth Seat is available.");
		}
		else
			System.out.println("Booth Seat is not available.");			

		return availableSeats;
	}

	public boolean isBarAvailable()
	{
		boolean availableSeats = false;

		Random randomNumber = new Random();
		int seatNumber = randomNumber.nextInt (10);
		if (seatNumber%2 == 0)
		{
			availableSeats = true;
			System.out.println(" Bar Seat is available.");
		}
		else
			System.out.println("Bar Seat is not available.");			

		return availableSeats;	
	}

	public boolean isWindowAvailable()
	{
		boolean availableSeats = false;

		Random randomNumber = new Random();
		int seatNumber = randomNumber.nextInt (10);
		if (seatNumber%2 == 0)
		{
			availableSeats = true;
			System.out.println(" Window Seat is available.");
		}
		else
			System.out.println("Window Seat is not available.");			

		return availableSeats;
	}

	public void run()
	{
		while(true)
		{
			if(isBoothAvailable())
			{
				try
				{
					boothSeat.dequeue();
				} catch (QueueUnderflowException e)
				{
				}
			}
			if(isBarAvailable())
			{
				try
				{
					barSeat.dequeue();
				} catch (QueueUnderflowException e)
				{
				}
			}
			if(isWindowAvailable())
			{
				try
				{
					windowSeat.dequeue();
				} catch (QueueUnderflowException e)
				{
				}
			}
			try
			{
				Thread.currentThread().sleep(10000);
			} catch (InterruptedException e)
			{
			}
		}
	}
}
