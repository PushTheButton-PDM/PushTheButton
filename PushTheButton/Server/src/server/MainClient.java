package server;

public class MainClient 
{
	public static void main(String[] args)
	{
		ClientSocket socket = new ClientSocket("localhost");
		DatiScambiati[] a=new DatiScambiati[1];
		a[0]=new DatiScambiati(1);
		socket.sendMessage(a);
		socket.recvMessage();
	}
}
