import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class CustomerGUI extends JFrame implements ActionListener
{
	private JLabel waitingTime;
	private JLabel waitingList;
	private JTextField partyName;
	private JButton windowSeat;
	private JButton boothSeat;
	private JButton barSeat;
	private JLabel typeOfSeat;
	private JTextField partySize;
	private JTextField arrivalTime;
	private JButton add;
	private JButton remove;
	private JLabel nextWaitingTime;
	private JLabel diningPeriod;
	private JButton removedYes;
	private JButton removedNo;
	
	CircularQueue<CustomerInfo> windowSeats = new CircularQueue<CustomerInfo>();
	CircularQueue<CustomerInfo> boothSeats = new CircularQueue<CustomerInfo>();
	CircularQueue<CustomerInfo> barSeats = new CircularQueue<CustomerInfo>();

	public static void main(String[] args)
	{	
		//gui runs on one thread
		CustomerGUI myGUI = new CustomerGUI();
		myGUI.setVisible(true);
		
		//seat monitor runs on another thread
		Runnable r = new SeatMonitor(myGUI.windowSeats, myGUI.boothSeats, myGUI.barSeats);
		Thread t = new Thread(r);
		t.start();

	}
	
	public CustomerGUI()
	{
		init();
		pack();
		setSize(700, 800);
	}

	public void actionPerformed(ActionEvent e)
	{
		//action events for buttons
		Container contentPane = getContentPane();
		if (e. getActionCommand().equals("Window Seat"))
		{
			typeOfSeat.setText("Window Seat");
			int diningTime = getDiningPeriod();
			int temp = diningTime * windowSeats.size();
			diningPeriod.setText(new String(new Integer(diningTime).toString()+ " minutes"));
			waitingTime.setText(new String(new Integer(temp).toString()+ " minutes"));
		}
		else if (e. getActionCommand().equals("Booth Seat"))
		{
			typeOfSeat.setText("Booth Seat");
			int diningTime = getDiningPeriod();
			int temp = diningTime * boothSeats.size();
			diningPeriod.setText(new String(new Integer(diningTime).toString()+ " minutes"));
			waitingTime.setText(new String(new Integer(temp).toString()+ " minutes"));
		}
		else if (e. getActionCommand().equals("Bar Seat"))
		{
			typeOfSeat.setText("Bar Seat");
			int diningTime = getDiningPeriod();
			int temp = diningTime * barSeats.size();
			diningPeriod.setText(new String(new Integer(diningTime).toString()+ " minutes"));
			waitingTime.setText(new String(new Integer(temp).toString()+ " minutes"));
		}
		else if (e. getActionCommand().equals("Add"))
		{
			CustomerInfo info = new CustomerInfo(partyName.getText(), typeOfSeat.getText(), new Integer(partySize.getText()).intValue());
			if(typeOfSeat.getText().equals("Window Seat"))
			{
				windowSeats.enqueue(info);
			}
			else if (typeOfSeat.getText().equals("Booth Seat"))
			{
				boothSeats.enqueue(info);
			}
			else if (typeOfSeat.getText().equals("Bar Seat"))
			{
				barSeats.enqueue(info);
			}
		}
		else if (e. getActionCommand().equals("Remove"))
		{
			CustomerInfo info = new CustomerInfo(partyName.getText(), typeOfSeat.getText(), new Integer(partySize.getText()).intValue());
			windowSeats.remove(info);
			boothSeats.remove(info);
			barSeats.remove(info);
		}
	}

	public void init()
	{
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.BLUE);
		contentPane.setLayout(new FlowLayout());

		//adding labels and entry fields to its own panel
		JPanel entryPanel = new JPanel();
		JLabel label = new JLabel("Enter Party's Name: ");
		entryPanel.add(label);
		partyName = new JTextField(50);
		entryPanel.add(partyName);
		contentPane.add(entryPanel);

		entryPanel = new JPanel();
		label = new JLabel("What type of seat do you want: ");
		entryPanel.add(label);
		windowSeat = new JButton("Window Seat");
		windowSeat.addActionListener(this);
		entryPanel.add(windowSeat);
		contentPane.add(entryPanel);
		boothSeat = new JButton("Booth Seat");
		boothSeat.addActionListener(this);
		entryPanel.add(boothSeat);
		contentPane.add(entryPanel);
		barSeat = new JButton("Bar Seat");
		barSeat.addActionListener(this);
		entryPanel.add(barSeat);
		typeOfSeat = new JLabel("Window Seat");
		entryPanel.add(typeOfSeat);
		contentPane.add(entryPanel);
		

		entryPanel = new JPanel();
		label = new JLabel("Party Size: ");
		entryPanel.add(label);
		partySize = new JTextField(50);
		entryPanel.add(partySize);
		contentPane.add(entryPanel);

		entryPanel = new JPanel();
		label = new JLabel("Do you want to be added or removed from the waiting list? ");
		entryPanel.add(label);
		add = new JButton("Add");
		add.addActionListener(this);
		entryPanel.add(add);
		contentPane.add(entryPanel);
		remove = new JButton("Remove");
		remove.addActionListener(this);
		entryPanel.add(remove);
		contentPane.add(entryPanel);
		
		entryPanel = new JPanel();
		label = new JLabel("Waiting Time: ");
		entryPanel.add(label);
		waitingTime = new JLabel("  ");
		entryPanel.add(waitingTime);
		contentPane.add(entryPanel);

		label = new JLabel("Estimated Dining Period: ");
		entryPanel.add(label);
		diningPeriod = new JLabel(" ");
		entryPanel.add(diningPeriod);
		contentPane.add(entryPanel);
	}
	public int getDiningPeriod()
	{
		Random randomNumber = new Random();
		int diningPeriod = randomNumber.nextInt (30);
		return diningPeriod;
	}
}