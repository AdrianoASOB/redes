import java.io.* ;
import java.net.* ;
import java.util.* ;
public final class WebServer
{
	public static void main(String arvg[]) throws Exception
	{
		int port = 6788;
		
	// Estabelecer o socket de escuta.
		ServerSocket socketEscuta = new ServerSocket(port);  
	// Processar a requisi��o de servi�o HTTP em um la�o infinito.
		while(true){
		// Escutar requisi��o de conex�o TCP.
            Socket socketConecta = socketEscuta.accept(); 
			//System.out.println("rodando");
            HttpRequest request = new HttpRequest(socketConecta);
            // Criar um novo thread para processar a requisi��o.
            Thread thread = new Thread(request);
            //Iniciar o thread.
            thread.start();
		}
	}
}

final class HttpRequest implements Runnable{
	
    //HttpRequest request = new HttpRequest(socketConecta);
	final static String CRLF = "\r\n";
	Socket socket;
	// Construtor
	public HttpRequest(Socket socket) throws Exception
	{
		this.socket = socket;
	}
	
	// Implemente o m�todo run() da interface Runnable.
	public void run(){
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
	private void processRequest() throws Exception{
		// Obter uma refer�ncia para os trechos de entrada e sa�da do socket.
		InputStream is = socket.getInputStream();
		DataOutputStream saiDilma = new DataOutputStream(socket.getOutputStream());
		// Ajustar os filtros do trecho de entrada.
		BufferedReader br = 
	              new BufferedReader(new
	              InputStreamReader(is)); 
		
		// Obter a linha de requisi��o da mensagem de requisi��o HTTP.
		String linha = br.readLine();
		//  Exibir a linha de requisi��o.
		System.out.println();
		System.out.println(linha);

		// Obter e exibir as linhas de cabe�alho.
		String headerLine = null;
		while ((headerLine = br.readLine()).length() != 0) {
			System.out.println(headerLine);
		}

		saiDilma.close();
		br.close();
		socket.close();

	
	}

	
}
