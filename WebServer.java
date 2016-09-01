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
	// Processar a requisição de serviço HTTP em um laço infinito.
		while(true){
		// Escutar requisição de conexão TCP.
            Socket socketConecta = socketEscuta.accept(); 
			//System.out.println("rodando");
            HttpRequest request = new HttpRequest(socketConecta);
            // Criar um novo thread para processar a requisição.
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
	
	// Implemente o método run() da interface Runnable.
	public void run(){
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
	private void processRequest() throws Exception{
		// Obter uma referência para os trechos de entrada e saída do socket.
		InputStream is = ;
		DataOutputStream os = ;
		// Ajustar os filtros do trecho de entrada.
		?
		BufferedReader br = ?;
	}

	
}
