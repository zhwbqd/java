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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class server extends JFrame  {
	private JTextField enter; // 创建文本框
	private JTextArea display; // 创建显示文本域
	PrintStream[] output = new PrintStream[10]; // 输出流
	BufferedReader[] input=new BufferedReader[10];
	String message;// 输入流
	Socket[] socket = new Socket[10]; // 客户端的Socket接口对象
	int counter = 1; // 连接数

	public server() {
		super("Server"); // 引用父类的超类
		Container c = getContentPane();
		enter = new JTextField(); // 创建文本框对象
		enter.setEnabled(false); // 文本框不可编辑
		enter.addActionListener(new ActionListener() { // 为文本框加入事件监听
					public void actionPerformed(ActionEvent e) {
						sendData(enter.getText());
			} // 将输入到文本框中的字符发送到客户端
				});
		c.add(enter, BorderLayout.NORTH); // 定义文本框在容器中的位置
		display = new JTextArea(); // 创建文本域对象
		c.add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
	}

	private void sendData(String s) 
 { // 将文本框中的字符传递给客户端
		try {
			for(int i=1;i<=counter;i++)
			{
				output[i].println("Server:  " + s); // 将文本框中的内容发送到PrintStream缓冲区中
				output[i].flush(); // 将缓冲区中的数据发送到客户端
				enter.setText(""); // 设置文本框的内容为“空”
			}
		} catch (Exception e) {
		}
			
	}

	public void connect() {
		ServerSocket server; // 服务器端的Socket接口对象

		try {
			// 第1步:创建一个监听，端口是4321，最大连接数是10
			server = new ServerSocket(4321, 10);
			while (true) 
			{
				// 第1步：等待一个请求
				display.append("等待客户端的请求\n");
				socket[counter] = server.accept(); // 等待客户端的请求
				display.append("连接" + counter + "来自:"
						+ socket[counter].getInetAddress().getHostName()
						+ "\n对方端口为" + socket[counter].getPort() + "\n");
				// 第3步：获得输入和输出流
				output[counter] = new PrintStream(new BufferedOutputStream(
						socket[counter].getOutputStream()));
				output[counter].flush();
				input[counter]= new BufferedReader(new InputStreamReader(
						socket[counter].getInputStream()));
				// 第4步：传递信息
				message = "Server Connection successful!";
				output[counter].println(message); // 将“message”字符串内容发送到PrintStream缓冲区中
				output[counter].flush(); // 将缓冲区中的数据发送到客户端
				enter.setEnabled(true);
				ser ser=new ser(socket[counter],input[counter],output[counter]);
				ser.start();
				if(counter==2)
				{
					output[1].println("另一客户端IP与端口:"
							+ socket[2].getInetAddress().getHostAddress()
							+":"+socket[2].getPort());
					output[1].flush();
					output[2].println("另一客户端IP与端口:"
							+ socket[1].getInetAddress().getHostAddress()
							+":"+socket[1].getPort());
					output[2].flush();
				}
				++counter;
			}
		} catch (EOFException eof) {
			System.out.println("Client terminated connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		server app = new server();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		app.connect();
	}

	
	// 多线程内部类，处理多个客户端
	class ser extends Thread
	{	
		Socket socket;
		BufferedReader input;
		PrintStream output;

		public ser(Socket socket, BufferedReader input,PrintStream output) 
		{
			this.socket=socket;
			this.input=input;
			this.output=output;
		}

		@Override
		public void run() 
		{
			try {
				do{
					message = (String) input.readLine();
					display.append("\n" + message); // 在文本域显示客户端传递的信息
					display.setCaretPosition(display.getText().length());// 使到滚动条自动下滚
					
				} while (!message.substring(message.indexOf(":")+1, message.length()).equals("  end"));
				
				// 第5步：拆除连接
				display.append("\n关闭连接");
				output.close();
				input.close();
				socket.close(); // 关闭当前客户端请求，继续监听其他客户端
				
			} catch (IOException e) {
				display.append("Data Error");
			}
			
		}
	}
}


