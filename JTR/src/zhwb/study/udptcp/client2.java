package zhwb.study.udptcp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class client2 extends JFrame 
{
	// UI界面需要组件
	private JTextField enter; // 创建文本框
	private JTextArea display; // 创建显示文本域
	private JTextField enter2; // 创建文本框
	private JTextArea display2; // 创建显示文本域
	private JLabel tcpLabel = new JLabel("与服务器连接");
	private JLabel udpLabel = new JLabel("与另一客户端连接");
	
	// Tcp需要的变量
	PrintStream output; // 输出流
	BufferedReader input; // 输入流
	String message = "";
	
	// Udp需要的变量
	DatagramSocket ds;
	InetAddress address;
	byte[] buf=new byte[1024];
	DatagramPacket dp;
	String strInfo="";
	String[] mess;
	String str="";
	int port;
	
	public client2() 
	{
		super("client2");
		Container c = getContentPane();
		c.setLayout(null);
		JPanel c1=new JPanel();
		JPanel c2=new JPanel();
		c1.setLayout(new BorderLayout());
		c2.setLayout(new BorderLayout());
		enter = new JTextField(); // 创建文本框对象
		enter.addActionListener(new ActionListener() { // 对文本区域添加监听
					public void actionPerformed(ActionEvent e) {
						sendData(enter.getText());
			} // 提取文本框中的内容
				});
		c1.add(enter, BorderLayout.NORTH); // 将文本区域添加到容器的上方
		display = new JTextArea(); // 创建显示文本域
		c1.add(new JScrollPane(display), BorderLayout.CENTER);// 添加带有滚动条的文本域到容器的中央
		c1.add(tcpLabel, BorderLayout.SOUTH);
		c1.setBounds(0, 0, 250, 300);
		enter2 = new JTextField(); // 创建文本框对象
		enter2.addActionListener(new ActionListener() { // 对文本区域添加监听
					public void actionPerformed(ActionEvent e) {
						sendData2(enter2.getText());
					} 
				});
		c2.add(enter2, BorderLayout.NORTH); 
		display2 = new JTextArea();
		c2.add(new JScrollPane(display2), BorderLayout.CENTER);
		c2.add(udpLabel, BorderLayout.SOUTH);
		c2.setBounds(250, 0, 250, 300);
		add(c1);
		add(c2);
		setSize(505, 350);
		setVisible(true);
	}
	
	public void connect() { // 与服务器的连接
		Socket socket; // 创建客户端的Socket对象
		try {
			// 第1步:与服务器端创建连接，地址是127.0.0.1，端口为4321
			display.setText("准备连接...\n");
			System.out.println(InetAddress.getByName(""));
			socket = new Socket(InetAddress.getByName(""), 4321);
//			socket=new Socket("127.0.0.1",4321);
			display.append("连接到:" + socket.getInetAddress().getHostName());
			// 获得主机名
			display.append("\n主机IP为:" + socket.getInetAddress().toString());
			// 获得主机IP地址
			// 第2步:获得输出/输入流
			output = new PrintStream(new BufferedOutputStream(socket
					.getOutputStream()));
			output.flush();
			input = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// 第3步:实现连接,读入服务器的信息
			do {
				try {
					message = (String) input.readLine();
					// 读入输入流的内容，也就是服务器端PrintStream缓冲区中传递的信息
					display.append("\n" + message); // 将读入的内容添加到文本域中
					display.setCaretPosition(display.getText().length());// 使到滚动条自动下滚
					
					if (message.startsWith("另一客户端IP与端口"))
					{
						mess=message.split(":");
						try {
							ds = new DatagramSocket(socket.getLocalPort());
							address = InetAddress.getByName(mess[1]);
							port=Integer.parseInt(mess[2]);
							dp=new DatagramPacket(buf, 1024);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						} catch (SocketException e) {
							e.printStackTrace();
						}
						new connect2().start();
						sendData2("建立连接");
					}
					
				} catch (IOException e) {
					display.append("\n无法获得信息");
				}
			} while (!message.equals("Server:  end")); // 当服务器端输入"end"时，通信结束
			// 第4步:关闭连接
			display.append("\n关闭连接");
			output.close();
			input.close();
			socket.close();
		} catch (EOFException eof) {
			System.out.println("服务器中断连接");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void sendData(String s) 
 { // 此子程序的功能是向服务器端传递信息
		try {
			message = s;
			output.println("Client2:  " + s); // 将文本框中的内容发送PrintStream缓冲区中
			output.flush();
			enter.setText("");
		} catch (Exception e) {
			display.append("\n数据传输错误！");
		}
	}
	
	private void sendData2(String s)
	{
		str="";
		str="Client2:  "+s;
		
		try {
			ds.send(new DatagramPacket(str.getBytes(), str.getBytes().length,address, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enter2.setText("");
	} 
	
	public static void main(String[] args) 
	{
		client2 clie2=new client2();
		clie2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		clie2.connect();
	}
	
	
	class connect2 extends Thread
	{
		@Override
		public void run() 
		{
			connect2();
		}

		public void connect2() // 与另一客户端的连接
		{
			do {
				try {
					ds.receive(dp);
					strInfo = new String(dp.getData(), 0, dp.getLength());
					if(strInfo.equals("")||strInfo.equals("Client1:  end"))
					{
						continue;
					}
					else 
					{
						display2.append("\n" + strInfo);
						display2.setCaretPosition(display2.getText().length());// 使到滚动条自动下滚
						strInfo="";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (!strInfo.substring(strInfo.indexOf(":")+1, strInfo.length()).equals("  end"));
			ds.close();
		}
	}
}
